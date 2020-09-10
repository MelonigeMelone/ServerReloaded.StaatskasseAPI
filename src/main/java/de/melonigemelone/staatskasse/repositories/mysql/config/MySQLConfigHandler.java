package de.melonigemelone.staatskasse.repositories.mysql.config;

import de.melonigemelone.staatskasse.StaatskasseAPI;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class MySQLConfigHandler {

    public static File config = new File(StaatskasseAPI.getInstance().getDataFolder(), "mysql.yml");
    public static FileConfiguration configFile = YamlConfiguration.loadConfiguration(config);

    public static void createConfigFile() {
        if (!config.exists()) {
            configFile.options().header("MySQL Data");


          for(MySQLValues value : MySQLValues.values()) {
              configFile.set(value.getPath(), value.getDefaultValue());
          }

            configFile.options().copyDefaults(true);
            configFile.options().copyHeader(true);
            save();
        }
    }

    public static String getValue(MySQLValues values) {
        return configFile.getString(values.getPath());
    }

    private static void save() {
        try {
            configFile.save(config);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
