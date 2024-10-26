package project.nebula.Discord_Integration.Minecraft_Events;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.awt.*;
import java.time.LocalDateTime;
import java.util.Objects;

public class onMinecraftDeath implements Listener {
    private final JDA jda;
    public onMinecraftDeath(JDA jda) {
        this.jda = jda;
    }
    @EventHandler
    public void onDeath(PlayerDeathEvent event){
        EmbedBuilder embed = new EmbedBuilder();
        embed.setDescription(event.getDeathMessage());
        embed.setAuthor(event.getEntity().getDisplayName()).setImage("https://mc-heads.net/avatar/" + event.getEntity().getUniqueId() + "/avatar.png");
        embed.setColor(Color.RED);
        embed.setFooter("Death happened in " + Bukkit.getName() );
        embed.setTimestamp(LocalDateTime.now());
    }
}
