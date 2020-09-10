package de.melonigemelone.staatskasse.utils.config;


import de.melonigemelone.staatskasse.StaatskasseAPI;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class MessagesConfigHandler {

    public static File config = new File(StaatskasseAPI.getInstance().getDataFolder(), "messages.yml");
    public static FileConfiguration configFile = YamlConfiguration.loadConfiguration(config);

    public static void createConfigFile() {
        if (!config.exists()) {
            configFile.options().header("Message File");

            for (Messages messages : Messages.values()) {
                configFile.addDefault("MESSAGE." + messages, messages.getDefaultMessage());
            }

            configFile.options().copyDefaults(true);
            configFile.options().copyHeader(true);
        } else {
            for (Messages messages : Messages.values()) {
                if(!configFile.contains("MESSAGE." + messages)) {
                    configFile.addDefault("MESSAGE." + messages, messages.getDefaultMessage());
                }
            }
        }

        save();
    }

    public static String getMessage(Messages messages) {
        String msg = configFile.getString("MESSAGE." + messages);
        msg = msg.replaceAll("&", "§");
        return msg;
    }

    private static void save() {
        try {
            configFile.save(config);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
