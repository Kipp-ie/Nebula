package project.nebula.Discord_Integration.Discord_Events;

import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;

public class onDiscordJoin extends ListenerAdapter {
    @Override
    public void onGuildMemberJoin(@NotNull GuildMemberJoinEvent event) { //When someone joins guild/server then:
        var mm = MiniMessage.miniMessage();
        Component parsed = mm.deserialize("<b><blue>Discord</blue></b> - " + event.getMember().getEffectiveName() + " | Joined the Discord server!");
        Bukkit.broadcast(parsed);

    }
}
