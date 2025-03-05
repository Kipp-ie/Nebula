package project.nebula.Discord_Integration.Minecraft_Events;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.awt.*;
import java.time.LocalDateTime;
import java.util.Objects;

public class onMinecraftJoin implements Listener {
    private final JDA jda;
    private final FileConfiguration config;
    public onMinecraftJoin(JDA jda, FileConfiguration config) {
        this.jda = jda;
        this.config = config;
    }


    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        EmbedBuilder embed = new EmbedBuilder();
        embed.setDescription(event.getJoinMessage().replace("§e", ""));
        embed.setAuthor(event.getPlayer().getName().replace("§e", ""), null ,"https://mc-heads.net/avatar/" + event.getPlayer().getUniqueId() + "/avatar.png");
        embed.setColor(Color.GREEN);
        embed.setFooter("Joined " + Bukkit.getServer().getName() );
        embed.setTimestamp(LocalDateTime.now());
        if (jda.getTextChannelById(config.getString("Discord_ChatID")) == null) {
            Bukkit.getLogger().warning("--- Nebula | Error ---");
            Bukkit.getLogger().warning("Nebula - Nebula encountered an error while searching for: *Discord_ChatID*, make sure that the ID in the config is valid.");
            Bukkit.getLogger().warning("--- Nebula | Error ---");
        } else {
            jda.getTextChannelById(config.getString("Discord_ChatID")).sendMessageEmbeds(embed.build()).queue();
        }



        if (Bukkit.getOnlinePlayers().isEmpty()) {
            jda.getPresence().setActivity(Activity.playing(Bukkit.getServer().getName()));
            jda.getPresence().setStatus(OnlineStatus.IDLE);
        } else {
            jda.getPresence().setActivity(Activity.watching(Bukkit.getOnlinePlayers().size() + " player(s) online!"));
            jda.getPresence().setStatus(OnlineStatus.DO_NOT_DISTURB);
        }


    }
}
