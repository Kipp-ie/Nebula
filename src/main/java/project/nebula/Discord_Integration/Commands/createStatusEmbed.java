package project.nebula.Discord_Integration.Commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

public class createStatusEmbed extends ListenerAdapter {
    private final FileConfiguration config;
    public createStatusEmbed(FileConfiguration config) {
        this.config = config;
    }

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        if (event.getName().equals("create-statusembed")) {

            if (config.get("Discord_StatusEmbedID").toString() == "") {
                event.reply("There is no StatusEmbed set in the config.yaml!").setEphemeral(true).queue();
                return;
            }

            EmbedBuilder embed = new EmbedBuilder();

            embed.setTitle(config.get("Server_Name").toString() + " | Server Status");
            embed.setDescription("Server status");
            embed.setColor(Color.GREEN);

            embed.addField("Version", Bukkit.getMinecraftVersion(), false);
            embed.addField("IP", Bukkit.getIp(), false);

            event.getGuild().getTextChannelById(config.get("Discord_StatusEmbedID").toString()).sendMessageEmbeds(embed.build()).queue(message ->
                    event.getGuild().getTextChannelById(config.get("Discord_StatusEmbedID").toString()).getManager().setTopic(message.getId()).queue());
        }
    }
}
