package de.melonigemelone.staatskasse.utils.config;

public enum Messages {
    PREFIX("§8[§eWirtschaft§8] "),

    KEINE_RECHTE(PREFIX.getDefaultMessage() + "§cDazu hast du keine Rechte!"),
    NUTZE(PREFIX.getDefaultMessage() + "§cBitte nutze: §7§o%command%"),
    FALSCHER_WERT(PREFIX.getDefaultMessage() + "§cUngültiger Wert!"),
    NUR_SPIELER("§cDer Befehl ist nur für Spieler!"),
    NICHT_GENUG_GELD(PREFIX.getDefaultMessage() + "§cDu hast nicht genug Geld!"),
    STAATSKASSE(PREFIX.getDefaultMessage() + "§7Die Staatskasse hat einen Kontostand von: §e%money%€"),
    STAATSKASSE_EINGEZAHLT(PREFIX.getDefaultMessage() + "§7Du hast §e%money%€ §7in die Staatskasse eingezahlt!"),


    ;


    String message;

    Messages(String message) {
        this.message = message;
    }

    public String getDefaultMessage() {
        return message;
    }

    public String getMessage() { return MessagesConfigHandler.getMessage(this); }
}
