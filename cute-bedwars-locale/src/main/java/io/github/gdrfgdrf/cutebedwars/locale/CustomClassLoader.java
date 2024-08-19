package io.github.gdrfgdrf.cutebedwars.locale;

import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.stream.Stream;

/**
 * @author gdrfgdrf
 */
public class CustomClassLoader extends ClassLoader {
    private static CustomClassLoader INSTANCE;

    private final ClassLoader pluginClassLoader;
    private final HashMap<String, Class<?>> map = new HashMap<>();
    {
        putClass("io.github.gdrfgdrf.cutebedwars.locale.collect.CommandDescriptionLanguage");
        putClass("io.github.gdrfgdrf.cutebedwars.locale.language.chinese.simplified.CommandDescriptionLanguage");

        putClass("io.github.gdrfgdrf.cutebedwars.locale.collect.CommandLanguage");
        putClass("io.github.gdrfgdrf.cutebedwars.locale.language.chinese.simplified.CommandLanguage");

        putClass("io.github.gdrfgdrf.cutebedwars.locale.collect.CommandSyntaxLanguage");
        putClass("io.github.gdrfgdrf.cutebedwars.locale.language.chinese.simplified.CommandSyntaxLanguage");
    }

    private CustomClassLoader(ClassLoader pluginClassLoader) {
        this.pluginClassLoader = pluginClassLoader;
    }

    public static CustomClassLoader getInstance(ClassLoader pluginClassLoader) {
        if (INSTANCE == null) {
            INSTANCE = new CustomClassLoader(pluginClassLoader);
        }
        return INSTANCE;
    }

    @Override
    protected Class<?> findClass(String moduleName, String name) {
        return map.get(name);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        return map.get(name);
    }

    @Override
    protected URL findResource(String moduleName, String name) throws IOException {
        return pluginClassLoader.getResource(name);
    }

    @Nullable
    @Override
    public URL getResource(String name) {
        return pluginClassLoader.getResource(name);
    }

    @Override
    protected Enumeration<URL> findResources(String name) throws IOException {
        return pluginClassLoader.getResources(name);
    }

    @Nullable
    @Override
    public InputStream getResourceAsStream(String name) {
        return pluginClassLoader.getResourceAsStream(name);
    }

    @Override
    public Stream<URL> resources(String name) {
        return pluginClassLoader.resources(name);
    }

    @Override
    public Enumeration<URL> getResources(String name) throws IOException {
        return pluginClassLoader.getResources(name);
    }

    @Override
    protected URL findResource(String name) {
        return pluginClassLoader.getResource(name);
    }

    private void putClass(String packageString) {
        try {
            Class<?> clazz = Class.forName(packageString);
            map.put(packageString, clazz);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
