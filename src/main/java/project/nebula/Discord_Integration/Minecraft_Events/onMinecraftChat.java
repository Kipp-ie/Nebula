package project.nebula.Discord_Integration.Minecraft_Events;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.awt.*;
import java.time.LocalDateTime;
import java.util.Objects;

public class onMinecraftChat implements Listener {
    private final JDA jda;
    private final FileConfiguration config;
    public onMinecraftChat(JDA jda, FileConfiguration config) {
        this.jda = jda;
        this.config = config;
    }

    @EventHandler
    public void onMessage(AsyncPlayerChatEvent event) {
        EmbedBuilder embed = new EmbedBuilder();
        embed.setDescription(event.getMessage());
        embed.setAuthor(event.getPlayer().getDisplayName()).setImage("https://mc-heads.net/avatar/" + event.getPlayer().getUniqueId() + "/avatar.png");
        embed.setColor(Color.BLUE);
        embed.setFooter("Chat placed in " + Bukkit.getName() );
        embed.setTimestamp(LocalDateTime.now());

        jda.getTextChannelById(config.getInt("Discord_ChatID")).sendMessageEmbeds(embed.build()).queue();

    }
}
