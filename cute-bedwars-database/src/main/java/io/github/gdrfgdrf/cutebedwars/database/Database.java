package io.github.gdrfgdrf.cutebedwars.database;

import io.github.gdrfgdrf.cutebedwars.abstracts.commons.IConfig;
import io.github.gdrfgdrf.cutebedwars.abstracts.commons.IConstants;
import io.github.gdrfgdrf.cutebedwars.abstracts.database.IDatabase;
import io.github.gdrfgdrf.cutebedwars.database.common.DatabaseImplDescription;
import io.github.gdrfgdrf.cutebedwars.database.exception.CloseDatabaseException;
import io.github.gdrfgdrf.cutebedwars.database.exception.InitDatabaseClassException;
import io.github.gdrfgdrf.cutebedwars.database.exception.LoadDatabaseException;
import io.github.gdrfgdrf.cutebedwars.utils.extension.StringExtensionKt;
import io.github.gdrfgdrf.cuteframework.api.loader.JarClassLoader;
import io.github.gdrfgdrf.cuteframework.bean.BeanManager;
import io.github.gdrfgdrf.cuteframework.utils.StringUtils;
import io.github.gdrfgdrf.cuteframework.utils.jackson.JacksonUtils;
import io.github.gdrfgdrf.multimodulemediator.annotation.ServiceImpl;
import lombok.Cleanup;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * @author gdrfgdrf
 */
@ServiceImpl(value = "database", instanceGetter = "getInstance")
public class Database implements IDatabase {
    private static Database INSTANCE;

    private io.github.gdrfgdrf.cutebedwars.database.base.IDatabase database;

    private Database() {
    }

    public static Database getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Database();
        }
        return INSTANCE;
    }

    public static io.github.gdrfgdrf.cutebedwars.database.base.IDatabase get() {
        return INSTANCE.database;
    }

    @Override
    public void initialize() {
        StringExtensionKt.logInfo("Initializing the database");

        Class<? extends io.github.gdrfgdrf.cutebedwars.database.base.IDatabase> databaseClass;

        try {
            String databaseImpl = IConfig.Companion.databaseImpl();
            if (StringUtils.isBlank(databaseImpl)) {
                throw new IllegalArgumentException("No database implementation is specified in the configuration file");
            }
            StringExtensionKt.logInfo("Database: " + databaseImpl);

            if ("default-sqlite".equals(databaseImpl)) {
                databaseClass = initDefault();
            } else {
                File customDatabaseImplFolder = new File(IConstants.Companion.customDatabaseImplFolderName());
                if (!customDatabaseImplFolder.exists()) {
                    customDatabaseImplFolder.mkdirs();
                }

                File customDatabaseImplFile = new File(
                        IConstants.Companion.customDatabaseImplFolderName() + databaseImpl
                );
                StringExtensionKt.logInfo("Custom database: " + customDatabaseImplFile);

                databaseClass = initCustom(customDatabaseImplFile);
            }
        } catch (Exception e) {
            throw new InitDatabaseClassException("An error occurred while initializing the database class", e);
        }
        if (databaseClass == null) {
            throw new InitDatabaseClassException("The database class is null");
        }

        try {
            load(databaseClass);
        } catch (Exception e) {
            throw new LoadDatabaseException("An error occurred while trying to load the database class", e);
        }
    }

    @SuppressWarnings("unchecked")
    private Class<? extends io.github.gdrfgdrf.cutebedwars.database.base.IDatabase> initDefault() throws ClassNotFoundException {
        StringExtensionKt.logInfo("Initializing the default database");

        Class<?> defaultDatabaseClass =
                Class.forName("io.github.gdrfgdrf.cutebedwars.database.impl.DefaultDatabase");
        return (Class<? extends io.github.gdrfgdrf.cutebedwars.database.base.IDatabase>) defaultDatabaseClass;
    }

    @SuppressWarnings("all")
    private Class<? extends io.github.gdrfgdrf.cutebedwars.database.base.IDatabase> initCustom(File implFile) throws IOException, ClassNotFoundException {
        StringExtensionKt.logInfo("Initializing the custom database");

        @Cleanup
        JarFile jarFile = new JarFile(implFile);
        JarEntry descriptionFile = jarFile.getJarEntry(IConstants.Companion.databaseImplDescriptionFileName());

        InputStream inputStream = jarFile.getInputStream(descriptionFile);
        DatabaseImplDescription description = JacksonUtils.readInputStream(
                inputStream,
                DatabaseImplDescription.class
        );

        String databaseImplClass = description.getDatabaseImplClass();
        if (StringUtils.isBlank(databaseImplClass)) {
            throw new IllegalArgumentException(
                    "The description file of database implementation file " +
                            implFile +
                            " is missing necessary information"
            );
        }

        JarClassLoader jarClassLoader = new JarClassLoader(implFile);
        Class<?> databaseClass = jarClassLoader.loadClass(databaseImplClass);
        return (Class<? extends io.github.gdrfgdrf.cutebedwars.database.base.IDatabase>) databaseClass;
    }

    private void load(Class<? extends io.github.gdrfgdrf.cutebedwars.database.base.IDatabase> databaseImplClass) throws Exception {
        StringExtensionKt.logInfo("Loading the database class " + databaseImplClass.getName());

        io.github.gdrfgdrf.cutebedwars.database.base.IDatabase instance;
        if ("io.github.gdrfgdrf.cutebedwars.database.impl.DefaultDatabase".equals(databaseImplClass.getPackageName())) {
            instance = (io.github.gdrfgdrf.cutebedwars.database.base.IDatabase) BeanManager.getInstance().getBean("DefaultDatabase");
        } else {
            instance = databaseImplClass.getConstructor().newInstance();
        }
        database = instance;

        instance.load();
    }

    @Override
    public void close() {
        StringExtensionKt.logInfo("Closing the database " + database.getDisplayName());

        if (database == null) {
            throw new CloseDatabaseException("The database cannot be closed because it is not loaded");
        }
        try {
            database.close();
        } catch (Exception e) {
            throw new CloseDatabaseException("An error occurred while trying to close the database");
        }
        database = null;
    }

}
