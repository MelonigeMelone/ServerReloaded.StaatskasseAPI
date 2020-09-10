package de.melonigemelone.staatskasse.commands;

import de.melonigemelone.staatskasse.StaatskasseAPI;
import de.melonigemelone.staatskasse.repositories.Methods;
import de.melonigemelone.staatskasse.utils.StaatskasseManager;
import de.melonigemelone.staatskasse.utils.config.Messages;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StaatskasseCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player p = (Player) sender;

            if(args.length == 1) {
                if(args[0].equalsIgnoreCase("view")) {
                    p.sendMessage(Messages.STAATSKASSE.getMessage().replaceAll("%money%", String.valueOf(StaatskasseManager.get())));
                } else {
                    p.sendMessage(Messages.NUTZE.getMessage().replaceAll("%command%", "/staatskasse (view|pay) [Betrag]"));
                }
            } else  if(args.length == 2) {
                if(args[0].equalsIgnoreCase("pay")) {
                    if(Methods.isDouble(args[1])) {
                        double money = Double.parseDouble(args[1]);
                        if(money >= 0) {
                            if(StaatskasseAPI.economy.has(p, money)) {

                                StaatskasseAPI.economy.withdrawPlayer(p, money);
                                StaatskasseManager.add(money);
                                p.sendMessage(Messages.STAATSKASSE_EINGEZAHLT.getMessage().replaceAll("%money%", String.valueOf(StaatskasseManager.get())));

                            } else {
                                p.sendMessage(Messages.NICHT_GENUG_GELD.getMessage());
                            }
                        } else {
                            p.sendMessage(Messages.FALSCHER_WERT.getMessage());
                        }
                    } else {
                        p.sendMessage(Messages.FALSCHER_WERT.getMessage());
                    }
                } else {
                    p.sendMessage(Messages.NUTZE.getMessage().replaceAll("%command%", "/staatskasse (view|pay) [Betrag]"));
                }
            } else {
                p.sendMessage(Messages.NUTZE.getMessage().replaceAll("%command%", "/staatskasse (view|pay) [Betrag]"));
            }

        } else {
            sender.sendMessage(Messages.NUR_SPIELER.getMessage());
        }
        return false;
    }
}
