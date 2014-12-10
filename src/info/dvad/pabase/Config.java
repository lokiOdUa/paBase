package info.dvad.pabase;

import javafx.application.Application;
import javafx.stage.Stage;
import org.ini4j.Ini;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Hashtable;
import java.util.Map;

public class Config extends Application {
    protected boolean isDebug = true;
    protected String configFileName = "paBaseOp.ini";
    Map<String, String> paBaseOpConfig = new Hashtable<String, String>(100, (float)0.5);
    protected String keyName, userName, userPassword;
    protected String blockName = "paBaseOp";

    @Override
    public void start(Stage stage) {
        System.out.println(" :: Executong Config::start()... ");
    }
    public void ReadConfig() {
        System.out.println(" :: Executing Config::ReadConfig()... ");
        System.out.println(" :: Got Filename: " + configFileName);
        try {
            readConfigFile(configFileName);
        } catch (FileNotFoundException e) {
            System.out.println("Ini file not found: `" + configFileName + "`");
            System.exit(1);
        } catch (Exception e) {
            System.out.println("Exception thrown: `" + e + "`");
        }
        if (isDebug) {
            for (String key : paBaseOpConfig.keySet()) {
                System.out.println(" :: key: " + key + " value: " + paBaseOpConfig.get(key));
            }
        }
    }
    private void readConfigFile(String fileName) throws Exception {
        try {
//            Ini ini = new Ini(new FileReader(fileName)); // check difference between File and FileReader
            Ini ini = new Ini(new File(fileName));         // looks like no difference for now
            for (String key : ini.get("paBaseOp").keySet())
            {
                if (isDebug) System.out.println(blockName + "/" + key + " = " + ini.get("paBaseOp").fetch(key));
                paBaseOpConfig.put(key, ini.get(blockName).fetch(key));
            }
        } catch (Exception e) {
            throw e;
        }
    }
    @Deprecated // since we need no to saving for now
    private void writeConfigFile(String fileName) {
        /*
        if (isDebug) System.out.println(" :: Trying to save config into: " + fileName);
        try {
            Wini ini = new Wini(new File(fileName));
            ini.put("sleepy", "age", 55);
            ini.put("sleepy", "weight", 45.6);
            for (String key : paBaseOpConfig.keySet()) {
                ini.put(blockName, key, paBaseOpConfig.get(key));
                if (isDebug) System.out.println(" :: key: " + key + " value: " + paBaseOpConfig.get(key));
            }
            ini.store();
        } catch (Exception e) {
            System.out.println("Exception thrown: `" + e + "`");
        }
    */
    }
}
