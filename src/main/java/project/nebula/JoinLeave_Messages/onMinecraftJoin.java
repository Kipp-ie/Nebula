package project.nebula.JoinLeave_Messages;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.awt.event.ActionListener;

public class onMinecraftJoin implements Listener {
    @EventHandler
    public void onMinecraftJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer(); //Get the player
        event.setJoinMessage(player.getName() + " has joined the server!");

    }

}
