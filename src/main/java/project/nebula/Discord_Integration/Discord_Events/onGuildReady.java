package project.nebula.Discord_Integration.Discord_Events;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.time.LocalDateTime;

public class onGuildReady extends ListenerAdapter {
    private final FileConfiguration config;
    public onGuildReady(@NotNull FileConfiguration config) {
        this.config = config;
    }

    @Override
    public void onGuildReady(@NotNull GuildReadyEvent event) {
        EmbedBuilder embed = new EmbedBuilder();
        embed.setTitle(Emoji.fromUnicode("U+1F7E2").getFormatted() + " | " + config.getString("Server_Name"));
        embed.setDescription("Server is now online!");
        embed.setColor(Color.GREEN);
        embed.setTimestamp(LocalDateTime.now());
        if (event.getJDA().getTextChannelById(config.getString("Discord_ChatID")) == null) {
            Bukkit.getLogger().warning("--- Nebula | Error ---");
            Bukkit.getLogger().warning("Nebula - Nebula encountered an error while searching for: *Discord_ChatID*, make sure that the ID in the config is valid.");
            Bukkit.getLogger().warning("--- Nebula | Error ---");
        } else {
            event.getJDA().getTextChannelById(config.getString("Discord_ChatID")).sendMessageEmbeds(embed.build()).queue();
        }

        if (config.get("Discord_StatusEmbedID").equals("")) {
            return;
        }

        EmbedBuilder embed2 = new EmbedBuilder();

        embed2.setTitle(config.get("Server_Name").toString() + " | Server Status");
        embed2.setDescription("Server online");
        embed2.setColor(Color.GREEN);

        embed2.addField("Version", Bukkit.getMinecraftVersion(), false);
        embed2.addField("IP", Bukkit.getIp(), false);

        event.getJDA().getTextChannelById(config.get("Discord_StatusEmbedID").toString()).retrieveMessageById(event.getJDA().getTextChannelById(config.get("Discord_StatusEmbedID").toString()).getTopic()).queue(message ->
                message.editMessageEmbeds(embed2.build()).queue());

    }
}
