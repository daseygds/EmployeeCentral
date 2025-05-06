package epcentral.Utility;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

import static epcentral.Utility.Constants.EnvironmentConstants.*;

public class ReadProperties {
    private static Logger logger = LogManager.getLogger(ReadProperties.class);
    private static final String CONFIG_DEV = "src/main/resources/config/dev.properties";
    private static final String CONFIG_TEST = "src/main/resources/config/test.properties";
    private static final String CONFIG_PROD = "src/main/resources/config/prod.properties";

    public static String getConfigProperties(String env,String key){
        String value = null;
        switch(env){
            case DEV:
                value = configureProp(CONFIG_DEV,key);
                logger.info("DB Config URL :: "+value);
                break;
            case TEST:
                value = configureProp(CONFIG_TEST,key);
                logger.info("DB Config URL :: "+value);
                break;
            case PROD:
                value = configureProp(CONFIG_PROD,key);
                logger.info("DB Config URL :: "+value);
                break;
            default:
                logger.error("Invalid environment variable passed!!");
                logger.info("DB Config URL :: "+value);
                break;
        }
        return value;
    }

    private static String configureProp(String fileLocation,String key){
        try {
            InputStreamReader inputStreamReader = new InputStreamReader
                    (Files.newInputStream(Paths.get(fileLocation)));
            Properties properties = new Properties();
            properties.load(inputStreamReader);
            return properties.getProperty(key);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return null;
    }

    public static String getEnvironmentName(String args){
        String[] argList = args.split("=");
        logger.info("Environment :: "+argList[1]);
        return argList[1];
    }
}
