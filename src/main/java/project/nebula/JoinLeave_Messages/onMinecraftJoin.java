package project.nebula.JoinLeave_Messages;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.jetbrains.annotations.NotNull;

import java.awt.event.ActionListener;

public class onMinecraftJoin implements Listener {
    final private FileConfiguration config;
    public onMinecraftJoin(@NotNull FileConfiguration config) {
        this.config = config;
    }

    @EventHandler
    public void onMinecraftJoin(PlayerJoinEvent event) {
        String join_message = config.getString("Join_Message_Format").replace("[player]", event.getPlayer().getName());
        var mm = MiniMessage.miniMessage();
        Component parsed = mm.deserialize(join_message);
        event.setJoinMessage(parsed.toString());

    }

}
