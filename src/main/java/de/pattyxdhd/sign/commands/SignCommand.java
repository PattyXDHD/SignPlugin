package de.pattyxdhd.sign.commands;

import de.pattyxdhd.sign.data.Data;
import de.pattyxdhd.sign.manager.SignManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SignCommand implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(!(sender instanceof Player)){
            sender.sendMessage(Data.getPrefix() + "Du musst ein Spieler sein.");
            return false;
        }

        final Player player = ((Player) sender);

        if(player.hasPermission("sign.use")){

            if(args.length >= 1){

                if(player.getItemInHand().getType() != Material.AIR){
                    if(player.getItemInHand().getAmount() == 1){

                        SignManager signManager = new SignManager(player.getItemInHand());

                        if(args[0].equalsIgnoreCase("del")){

                            if(signManager.isSigned()){
                                player.setItemInHand(signManager.unSign());
                                player.sendMessage(Data.getPrefix() + "§aDu hast erfolgreich die signatur entfernt.");
                            }else{
                                player.sendMessage(Data.getPrefix() + "§cDas Item wurde noch nicht signiert.");
                            }

                        }else{
                            StringBuilder stringBuilder = new StringBuilder();

                            for (String arg : args){
                                stringBuilder.append(arg).append(" ");
                            }

                            if(!signManager.isSigned()){
                                player.setItemInHand(signManager.sign(player.getName(), stringBuilder.toString()));
                                player.sendMessage(Data.getPrefix() + "§aItem erfolgreich signiert.");
                            }else{
                                player.sendMessage(Data.getPrefix() + "§cDas Item wurde breits signiert.");
                            }
                        }

                    }else{
                        player.sendMessage(Data.getPrefix() + "§cDu darfst nur ein Item gleichzeitig signieren.");
                    }
                }else{
                    player.sendMessage(Data.getPrefix() + "§cDu musst ein Item in der Hand halten.");
                }
            }else{
                player.sendMessage(Data.getPrefix() + "Usage: /sign <Message>");
                player.sendMessage(Data.getPrefix() + "Usage: /sign del");
            }
        }else{
            player.sendMessage(Data.getNoPerm());
        }
        return false;
    }


}
