package de.pattyxdhd.sign;

import de.pattyxdhd.sign.commands.SignCommand;
import de.pattyxdhd.sign.data.Data;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class SignPlugin extends JavaPlugin {

    @Getter
    private static SignPlugin instance;

    @Override
    public void onEnable() {
        loadCommands();
        loadListener(Bukkit.getPluginManager());

        log("§aPlugin geladen.");
    }

    @Override
    public void onDisable() {

    }

    private void loadCommands(){
        getCommand("sign").setExecutor(new SignCommand());
    }

    private void loadListener(final PluginManager pluginManager){

    }

    public static void log(final String message){
        Bukkit.getConsoleSender().sendMessage(Data.getPrefix() + message);
    }

}
