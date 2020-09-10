package de.melonigemelone.staatskasse.utils;

public class StaatskasseManager {

    public static MySQLManager mySQLManager = new MySQLManager();

    public static boolean has(double money) {
        if(mySQLManager.get() >= money) {
            return true;
        }
        return false;
    }

    public static double get() {
        return  mySQLManager.get();
    }

    public static void add(double money) {
        double newValue = get() + money;
        mySQLManager.update(newValue);
    }

    public static void take(double money) {
        double newValue = get() - money;
        mySQLManager.update(newValue);
    }

    public static void set(double money) {
        mySQLManager.update(money);
    }
}
