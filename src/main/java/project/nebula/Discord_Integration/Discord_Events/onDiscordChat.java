package project.nebula.Discord_Integration.Discord_Events;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.jetbrains.annotations.NotNull;

public class onDiscordChat extends ListenerAdapter {
    private final FileConfiguration config;
    public onDiscordChat(FileConfiguration config) {
        this.config = config;
    }
    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        if (event.getAuthor().isBot()) {//If a message is posted from a bot or system, ignore.
            return; //Stop the code.
        } else {//If author is not bot or system then:
            if (!event.getAuthor().isSystem()) {
                if (event.getChannel().equals(event.getJDA().getTextChannelById(config.getString("Discord_ChatID")))) {
                    var mm = MiniMessage.miniMessage();
                    Component parsed = mm.deserialize("<bold><blue>Discord</bold></blue>");
                    Bukkit.broadcastMessage(parsed + " - " + event.getAuthor().getName() + " | " + event.getMessage().getContentDisplay());
            }

            }


        }
    }
}
