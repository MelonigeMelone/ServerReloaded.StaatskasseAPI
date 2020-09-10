package de.melonigemelone.staatskasse.utils;

import de.melonigemelone.staatskasse.StaatskasseAPI;
import de.melonigemelone.staatskasse.repositories.mysql.MySQL;
import de.melonigemelone.staatskasse.repositories.mysql.SQL;
import org.bukkit.scheduler.BukkitRunnable;

public class MySQLManager {

    SQL sql = new SQL();

    public void createTables() {
        sql.createTable(
                "staatskasse",

                "  `id` int(11) unsigned NOT NULL AUTO_INCREMENT," +
                        "  `name` varchar(64) NOT NULL DEFAULT ''," +
                        "  `money` double NOT NULL DEFAULT '0'," +
                        "   PRIMARY KEY (`id`)"
        );

    }

    public  void registerStaatskasseIfNotAlready() {
        if(!isRegistered()) {
            MySQL.connect();
            runAsync(() -> {
                sql.insertData(
                        "name,money",
                        "'Staatskasse'," + 0.0,
                        "staatskasse");
            });
        }
    }

    public  void update(double money) {
        MySQL.connect();
        runAsync(() -> {
            sql.update("money = " + money,
                    "name","=", "'Staatskasse'",
                    "staatskasse");
        });
    }

    public  double get() {

        MySQL.connect();
        return sql.getStaatskasse("staatskasse");

    }

    public  boolean isRegistered() {
        if(get() == -1) {
            return false;
        }

        return true;
    }

    private  void runAsync(final Runnable runnable){
        BukkitRunnable r = new BukkitRunnable() {
            public void run() {
                runnable.run();
            }
        };
        r.runTaskAsynchronously(StaatskasseAPI.getInstance());
    }
}
