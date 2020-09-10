package de.melonigemelone.staatskasse.repositories.mysql.config;

public enum MySQLValues {
    HOST("HOST", "localhost"),
    PORT("PORT", "3306"),
    DATABASE("DATABASE", "server"),
    USER("USER", "root"),
    PASSWORD("PASSWORD", "")
    ;

    String path;
    String defaultValue;

    MySQLValues(String path, String defaultValue) {
        this.path = path;
        this.defaultValue = defaultValue;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public String getPath() {
        return path;
    }

    public String getValue() {
        return MySQLConfigHandler.getValue(this);
    }
}
