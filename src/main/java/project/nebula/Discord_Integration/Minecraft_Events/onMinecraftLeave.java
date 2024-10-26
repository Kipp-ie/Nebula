package project.nebula.Discord_Integration.Minecraft_Events;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.awt.*;
import java.time.LocalDateTime;
import java.util.Objects;

public class onMinecraftLeave implements Listener {
    private final JDA jda;
    private final FileConfiguration config;
    public onMinecraftLeave(JDA jda, FileConfiguration config) {
        this.jda = jda;
        this.config = config;
    }
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        EmbedBuilder embed = new EmbedBuilder();
        embed.setDescription(event.getQuitMessage());
        embed.setAuthor(event.getPlayer().getName(), null ,"https://mc-heads.net/avatar/" + event.getPlayer().getUniqueId() + "/avatar.png");
        embed.setColor(Color.RED);
        embed.setFooter("Left " + Bukkit.getServer().getName() );
        embed.setTimestamp(LocalDateTime.now());

        if (jda.getTextChannelById(config.getString("Discord_ChatID")) == null) {
            Bukkit.getLogger().warning("--- Nebula | Error ---");
            Bukkit.getLogger().warning("Nebula - Nebula encountered an error while searching for: *Discord_ChatID*, make sure that the ID in the config is valid.");
            Bukkit.getLogger().warning("--- Nebula | Error ---");
        } else {
            jda.getTextChannelById(config.getString("Discord_ChatID")).sendMessageEmbeds(embed.build()).queue();
        }

        if (Bukkit.getOnlinePlayers().size() == 1) {
            jda.getPresence().setActivity(Activity.playing(Bukkit.getServer().getName()));
            jda.getPresence().setStatus(OnlineStatus.IDLE);

        } else {
            jda.getPresence().setActivity(Activity.watching(Bukkit.getOnlinePlayers().size() + " player(s) online!"));
            jda.getPresence().setStatus(OnlineStatus.DO_NOT_DISTURB);
        }

    }
}
