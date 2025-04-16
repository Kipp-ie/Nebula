package project.nebula.Tab_List;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.jetbrains.annotations.NotNull;

public class onPlayerJoin implements Listener {
    private final FileConfiguration config;
    public onPlayerJoin(@NotNull FileConfiguration config) {
        this.config = config;
    }
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        var mm = MiniMessage.miniMessage();
        final Component header =  mm.deserialize(config.get("Tablist_Header").toString());
        final Component footer = mm.deserialize(config.get("Tablist_Footer").toString());
        event.getPlayer().sendPlayerListHeaderAndFooter(header, footer);

    }

}
