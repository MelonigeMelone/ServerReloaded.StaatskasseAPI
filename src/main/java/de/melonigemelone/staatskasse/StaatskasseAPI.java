package de.melonigemelone.staatskasse;

import de.melonigemelone.staatskasse.commands.StaatskasseCommand;
import de.melonigemelone.staatskasse.repositories.mysql.config.MySQLConfigHandler;
import de.melonigemelone.staatskasse.utils.MySQLManager;
import de.melonigemelone.staatskasse.utils.config.MessagesConfigHandler;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public class StaatskasseAPI extends JavaPlugin {

    public static StaatskasseAPI instance;
    public static Economy economy;
    MySQLManager mySQLManager = new MySQLManager();

    @Override
    public void onEnable() {
        instance = this;

        setupEconomy();

        MessagesConfigHandler.createConfigFile();

        MySQLConfigHandler.createConfigFile();
        mySQLManager.createTables();
        mySQLManager.registerStaatskasseIfNotAlready();

        getCommand("staatskasse").setExecutor(new StaatskasseCommand());
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }

    private boolean setupEconomy() {
        if (Bukkit.getPluginManager().getPlugin("Vault") == null) {
            return false;
        }

        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        economy = rsp.getProvider();
        return economy != null;
    }

    public static StaatskasseAPI getInstance() {
        return instance;
    }

    public Economy getEconomy() {
        return economy;
    }
}
