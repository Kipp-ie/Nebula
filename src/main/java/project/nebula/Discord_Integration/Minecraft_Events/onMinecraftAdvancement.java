package project.nebula.Discord_Integration.Minecraft_Events;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerAdvancementDoneEvent;

import java.awt.*;
import java.time.LocalDateTime;
import java.util.Objects;

public class onMinecraftAdvancement implements Listener {
    private final JDA jda;
    private final FileConfiguration config;
    public onMinecraftAdvancement(JDA jda, FileConfiguration config) {
        this.jda = jda;
        this.config = config;
    }
    @EventHandler
    public void onPlayerAchievement(PlayerAdvancementDoneEvent event) {
        if(!event.getAdvancement().getKey().getKey().contains("recipes")) {
            EmbedBuilder embed = new EmbedBuilder();
            embed.setDescription(Objects.requireNonNull(event.getAdvancement().getDisplay()).displayName().toString());
            embed.setAuthor(event.getPlayer().getDisplayName()).setImage("https://mc-heads.net/avatar/" + event.getPlayer().getUniqueId() + "/avatar.png");
            embed.setColor(Color.MAGENTA);
            embed.setFooter("Event in " + Bukkit.getName() );
            embed.setTimestamp(LocalDateTime.now());
            embed.setDescription(Objects.requireNonNull(event.getAdvancement().getDisplay().displayName().toString()));

            jda.getTextChannelById(config.getInt("Discord_ChatID")).sendMessageEmbeds(embed.build()).queue();

        }

    }
}
