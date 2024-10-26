package project.nebula.Discord_Integration.Discord_Events;

import net.dv8tion.jda.api.events.guild.GuildLeaveEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberRemoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;

public class onDiscordLeave extends ListenerAdapter {
    @Override
    public void onGuildMemberRemove(@NotNull GuildMemberRemoveEvent event) {//If someone leaves the guild/server then:
        var mm = MiniMessage.miniMessage();
        Component parsed = mm.deserialize("<bold><blue>Discord</bold></blue>");
        Bukkit.broadcastMessage(parsed + " - " + event.getMember().getEffectiveName() + " | Left the Discord server.");

    }
}
