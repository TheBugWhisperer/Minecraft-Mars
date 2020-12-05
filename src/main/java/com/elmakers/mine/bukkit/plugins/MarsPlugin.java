package com.elmakers.mine.bukkit.plugins;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.java.JavaPlugin;

public class MarsPlugin extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        // Put here anything you want to happen when the server starts
    	
    	// Register our join event
    	getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {
        // Put here anything you want to happen when the server stops
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        // Put here anything you want to happen when a player joins
    	event.setJoinMessage("Good Luck!");
    }

    @Override
    public ChunkGenerator getDefaultWorldGenerator(String worldName, String id) {
        getLogger().info("install mars generator in world: " + worldName);
        return new MarsWorldGenerator(this);
    }
}
