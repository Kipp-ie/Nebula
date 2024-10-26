package project.nebula.JoinLeave_Messages;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.jetbrains.annotations.NotNull;

public class onMinecraftLeave implements Listener {
    private final FileConfiguration config;
    public onMinecraftLeave(@NotNull FileConfiguration config) {
        this.config = config;
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent event) {
        String leave_message = config.getString("Leave_Message_Format").replace("[player]", event.getPlayer().getName());
        var mm = MiniMessage.miniMessage();
        Component parsed = mm.deserialize(leave_message);
        event.setQuitMessage(parsed.toString());

    }
}
