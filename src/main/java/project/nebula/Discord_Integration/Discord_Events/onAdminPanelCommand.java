package project.nebula.Discord_Integration.Discord_Events;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

public class onAdminPanelCommand extends ListenerAdapter {
    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        if (event.getName().equals("create-adminpanel")) {
            if (!event.getMember().hasPermission(Permission.ADMINISTRATOR)) {
                event.reply("You do not have the **ADMINISTRATOR** permission!").queue();
            }
            EmbedBuilder embed = new EmbedBuilder();
            embed.setTitle("Admin Panel");
            embed.setDescription("Please remember, these buttons won't work if the server is turned off!");
            embed.setColor(Color.GREEN);

            event.getChannel().sendMessageEmbeds(embed.build())
                    .addActionRow(
                            Button.danger("stop", "Stop")
                    ).queue();

            event.reply("The admin panel has been made!").setEphemeral(true).queue();
        }
    }
}
