package helpers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

/**
 * Config reader
 */
public class ConfigReader {

    private static Properties props = null;
    private static ConfigReader instance = null;

    private ConfigReader() {
        props = new Properties();
        String env = "prod";
        if (System.getProperty("env") != null) {
            env = System.getProperty("env");
        }
        try {
            if (Files.isReadable(Paths.get(String.format("src/main/resources/%s.properties", env)))) {
                props.load(ConfigReader.class.getClassLoader().getResourceAsStream(String.format("%s.properties", env)));
            } else {
                throw new IOException("File does not exist");
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public static synchronized ConfigReader getInstance() {
        if (instance == null) instance = new ConfigReader();
        return instance;
    }

    public String getValue(String key) {
        if (System.getProperty(key) != null) {
            return System.getProperty(key);
        } else if (props.containsKey(key)) {
            return props.getProperty(key);
        }
        return null;
    }

}
