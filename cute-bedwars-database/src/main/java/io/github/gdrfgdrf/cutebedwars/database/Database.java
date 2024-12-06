package io.github.gdrfgdrf.cutebedwars.database;

import com.github.yitter.idgen.YitIdHelper;
import io.github.gdrfgdrf.cutebedwars.abstracts.commons.IIConfig;
import io.github.gdrfgdrf.cutebedwars.abstracts.commons.IConstants;
import io.github.gdrfgdrf.cutebedwars.abstracts.database.IDatabase;
import io.github.gdrfgdrf.cutebedwars.abstracts.utils.CommonsKt;
import io.github.gdrfgdrf.cutebedwars.abstracts.utils.IJarClassLoader;
import io.github.gdrfgdrf.cutebedwars.abstracts.utils.IJsons;
import io.github.gdrfgdrf.cutebedwars.beans.AbstractPlayerData;
import io.github.gdrfgdrf.cutebedwars.database.common.DatabaseImplDescription;
import io.github.gdrfgdrf.cutebedwars.database.exception.CloseDatabaseException;
import io.github.gdrfgdrf.cutebedwars.database.exception.InitDatabaseClassException;
import io.github.gdrfgdrf.cutebedwars.database.exception.LoadDatabaseException;
import io.github.gdrfgdrf.cutebedwars.database.service.IGamePlayerService;
import io.github.gdrfgdrf.cutebedwars.database.service.IPlayerService;
import io.github.gdrfgdrf.multimodulemediator.annotation.ServiceImpl;
import lombok.Cleanup;
import org.apache.commons.lang.StringUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;
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

    public static io.github.gdrfgdrf.cutebedwars.database.base.IDatabase instance() {
        return INSTANCE.database;
    }

    @Override
    public void initialize() {
        CommonsKt.logInfo("Initializing the database");

        Class<? extends io.github.gdrfgdrf.cutebedwars.database.base.IDatabase> databaseClass;

        try {
            String databaseImpl = IIConfig.Companion.get("DatabaseImpl");
            if (StringUtils.isBlank(databaseImpl)) {
                throw new IllegalArgumentException("No database implementation is specified in the configuration file");
            }
            CommonsKt.logInfo("Database: " + databaseImpl);

            if ("default-sqlite".equals(databaseImpl)) {
                databaseClass = initDefaultSqlite();
            } else {
                if ("default-mysql".equals(databaseImpl)) {
                    databaseClass = initDefaultMysql();
                } else {
                    File customDatabaseImplFolder = new File(IConstants.Companion.get("CUSTOM_DATABASE_IMPL_FOLDER_NAME"));
                    if (!customDatabaseImplFolder.exists()) {
                        customDatabaseImplFolder.mkdirs();
                    }

                    File customDatabaseImplFile = new File(
                            IConstants.Companion.get("CUSTOM_DATABASE_IMPL_FOLDER_NAME") + databaseImpl
                    );
                    CommonsKt.logInfo("Custom database: " + customDatabaseImplFile);

                    databaseClass = initCustom(customDatabaseImplFile);
                }
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
    private Class<? extends io.github.gdrfgdrf.cutebedwars.database.base.IDatabase> initDefaultSqlite() throws ClassNotFoundException {
        CommonsKt.logInfo("Initializing the default sqlite database");

        Class<?> defaultSqliteDatabaseClass =
                Class.forName("io.github.gdrfgdrf.cutebedwars.database.impl.sqlite.DefaultSQLiteDatabase");
        return (Class<? extends io.github.gdrfgdrf.cutebedwars.database.base.IDatabase>) defaultSqliteDatabaseClass;
    }

    @SuppressWarnings("unchecked")
    private Class<? extends io.github.gdrfgdrf.cutebedwars.database.base.IDatabase> initDefaultMysql() throws ClassNotFoundException {
        CommonsKt.logInfo("Initializing the default mysql database");

        Class<?> defaultMysqlDatabaseClass =
                Class.forName("io.github.gdrfgdrf.cutebedwars.database.impl.mysql.DefaultMySQLDatabase");
        return (Class<? extends io.github.gdrfgdrf.cutebedwars.database.base.IDatabase>) defaultMysqlDatabaseClass;
    }

    @SuppressWarnings("all")
    private Class<? extends io.github.gdrfgdrf.cutebedwars.database.base.IDatabase> initCustom(File implFile) throws IOException, ClassNotFoundException {
        CommonsKt.logInfo("Initializing the custom database");

        @Cleanup
        JarFile jarFile = new JarFile(implFile);
        JarEntry descriptionFile = jarFile.getJarEntry(IConstants.Companion.get("DATABASE_IMPL_DESCRIPTION_FILE_NAME"));

        InputStream inputStream = jarFile.getInputStream(descriptionFile);
        DatabaseImplDescription description = IJsons.Companion.instance().read(
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

        IJarClassLoader jarClassLoader = IJarClassLoader.Companion.create(implFile);
        Class<?> databaseClass = jarClassLoader.loadClass(databaseImplClass);
        return (Class<? extends io.github.gdrfgdrf.cutebedwars.database.base.IDatabase>) databaseClass;
    }

    private void load(Class<? extends io.github.gdrfgdrf.cutebedwars.database.base.IDatabase> databaseImplClass) throws Exception {
        CommonsKt.logInfo("Loading the database class " + databaseImplClass.getName());

        database = databaseImplClass.getConstructor().newInstance();;
        database.load();
    }

    @Override
    public void close() {
        CommonsKt.logInfo("Closing the database " + database.getDisplayName());

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
