package project.nebula.Discord_Integration.Minecraft_Events;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
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

        if (Bukkit.getOnlinePlayers().isEmpty()) {
            jda.getPresence().setActivity(Activity.playing(Bukkit.getServer().getName()));
            jda.getPresence().setStatus(OnlineStatus.IDLE);

            if (!(jda.getTextChannelById(config.get("Discord_StatusEmbedID").toString()).getTopic() == "")) {
                EmbedBuilder embed2 = new EmbedBuilder();

                embed2.setTitle(config.get("Server_Name").toString() + " | Server Status");
                embed2.setDescription("Server online");
                embed2.setColor(Color.GREEN);


                embed2.addField("Version", Bukkit.getMinecraftVersion(), false);
                embed2.addField("IP", Bukkit.getIp(), false);
                embed2.addField("Players", "There are currently no players online.", false);

                jda.getTextChannelById(config.get("Discord_StatusEmbedID").toString()).retrieveMessageById(jda.getTextChannelById(config.get("Discord_StatusEmbedID").toString()).getTopic()).queue(message ->
                        message.editMessageEmbeds(embed2.build()).queue());


            }

        } else {
            jda.getPresence().setActivity(Activity.watching(Bukkit.getOnlinePlayers().size() + " player(s) online!"));
            jda.getPresence().setStatus(OnlineStatus.DO_NOT_DISTURB);

            if (!(jda.getTextChannelById(config.get("Discord_StatusEmbedID").toString()).getTopic() == "")) {

                EmbedBuilder embed2 = new EmbedBuilder();

                embed2.setTitle(config.get("Server_Name").toString() + " | Server Status");
                embed2.setDescription("Server online");
                embed2.setColor(Color.GREEN);

                StringBuilder onlinePlayers = new StringBuilder(new String());

                for (int i = 0; i < Bukkit.getOnlinePlayers().toArray().length; i++) {

                    onlinePlayers.append(Bukkit.getOnlinePlayers().toArray()[i] + "\n");

                }
                embed2.addField("Version", Bukkit.getMinecraftVersion(), false);
                embed2.addField("IP", Bukkit.getIp(), false);
                embed2.addField("Players", onlinePlayers.toString(), false);

                jda.getTextChannelById(config.get("Discord_StatusEmbedID").toString()).retrieveMessageById(jda.getTextChannelById(config.get("Discord_StatusEmbedID").toString()).getTopic()).queue(message ->
                        message.editMessageEmbeds(embed2.build()).queue());



            }
        }
}}
