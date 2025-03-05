package project.nebula.Discord_Integration.Discord_Events;

import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;

public class onAdminPanelButtonInteraction extends ListenerAdapter {
    @Override
    public void onButtonInteraction(@NotNull ButtonInteractionEvent event) {
        if (event.getComponentId().equals("stop")) {
            Bukkit.getServer().shutdown();
            event.reply("Server is shutting down.").setEphemeral(true).queue();
        }
    }
}
