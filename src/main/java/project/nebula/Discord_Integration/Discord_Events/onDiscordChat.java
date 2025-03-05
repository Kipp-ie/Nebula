package project.nebula.Discord_Integration.Discord_Events;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.jetbrains.annotations.NotNull;
import project.nebula.messages.MessageBuilder;

public class onDiscordChat extends ListenerAdapter {
    private final FileConfiguration config;
    public onDiscordChat(FileConfiguration config) {
        this.config = config;
    }
    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        if (event.getAuthor().isBot()) {//If a message is posted from a bot or system, ignore.
            if (event.getAuthor().isBot() || event.getAuthor().isSystem()) {//If a message is posted from a bot or system, ignore.
                return; //Stop the code.
            } else {//If author is not bot or system then:
                if (!event.getAuthor().isSystem()) {
                    if (event.getChannel().equals(event.getJDA().getTextChannelById(config.getString("Discord_ChatID")))) {
                        if(!event.getMessage().getAttachments().isEmpty()) {
                            handleAttachment(event);
                        }else {
                            handleMessage(event);
                        }
                    }
                }
            }
        }
    }

    private void handleMessage(MessageReceivedEvent event) {
        Component message = new MessageBuilder().text("&9&lDiscord &7- &f" + event.getAuthor().getName() + " &7| &f" + event.getMessage().getContentDisplay()).build();
        Bukkit.broadcast(message);
    }

    private void handleAttachment(MessageReceivedEvent event) {
        MessageBuilder message = new MessageBuilder().text("&9&lDiscord &7- &f" + event.getAuthor().getName() + " &7| &f" + event.getMessage().getContentDisplay());
        event.getMessage().getAttachments().forEach(attachment -> {
            message.text("&f&n&l[ATTACHMENT] ").withCopyToClipboard(attachment.getUrl());
        });
    }

}

