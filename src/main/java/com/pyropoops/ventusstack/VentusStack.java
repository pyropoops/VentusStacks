package com.pyropoops.ventusstack;

import org.bukkit.plugin.java.JavaPlugin;

public final class VentusStack extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getCommand("stack").setExecutor(new StackCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
