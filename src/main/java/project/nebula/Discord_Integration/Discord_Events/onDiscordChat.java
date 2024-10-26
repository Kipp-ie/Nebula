package project.nebula.Discord_Integration.Discord_Events;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class onDiscordChat extends ListenerAdapter {
    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        if (event.getAuthor().isBot() ^ event.getAuthor().isSystem()) { //If a message is posted from a bot or system, ignore.
            return; //Stop the code.
        } else { //If author is not bot or system then:


        }
    }
}
