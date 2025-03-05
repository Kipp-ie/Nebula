package project.nebula;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import project.nebula.Discord_Integration.Discord_Events.onAdminPanelButtonInteraction;
import project.nebula.Discord_Integration.Discord_Events.onDiscordChat;
import project.nebula.Discord_Integration.Discord_Events.onDiscordJoin;
import project.nebula.Discord_Integration.Discord_Events.onDiscordLeave;
import project.nebula.Discord_Integration.Minecraft_Events.onMinecraftChat;
import project.nebula.Discord_Integration.Minecraft_Events.onMinecraftDeath;
import project.nebula.Discord_Integration.Minecraft_Events.onMinecraftJoin;
import project.nebula.JoinLeave_Messages.onMinecraftLeave;

import java.time.Duration;

public final class Nebula extends JavaPlugin {
    private JDA jda;

    private JDA buildJDA() {
        if (!getConfig().getBoolean("Discord_Integration")) {

        } else {
            if (getConfig().getString("Discord_Bottoken").equals("")) {
                Bukkit.getLogger().warning("--- Nebula | Error ---");
                Bukkit.getLogger().warning("Nebula - Nebula encountered an error while searching for: *Discord_Bottoken*, make sure that the token in the config is valid.");
                Bukkit.getLogger().warning("--- Nebula | Error ---");

            } else {
                JDABuilder builder = JDABuilder.createDefault(getConfig().getString("Discord_Bottoken"));
                builder.setStatus(OnlineStatus.IDLE);
                builder.setActivity(Activity.playing(Bukkit.getServer().getName()));
                builder.enableIntents(GatewayIntent.GUILD_MEMBERS, GatewayIntent.GUILD_MESSAGES, GatewayIntent.DIRECT_MESSAGES, GatewayIntent.MESSAGE_CONTENT);
                builder.addEventListeners(
                        new onDiscordChat(getConfig()),
                        new onDiscordJoin(),
                        new onDiscordLeave(),
                        new onAdminPanelButtonInteraction(),
                        new onAdminPanelButtonInteraction()
                );
                return builder.build();

            }


        }
        return null;
    }


    @Override
    public void onEnable() {
        // Startup Nebula
        saveDefaultConfig();

        Bukkit.getLogger().info("--------------------");
        Bukkit.getLogger().info("Nebula - Hello! ( ⸝⸝•ᴗ•⸝⸝ )੭⁾⁾");
        if (!getConfig().getBoolean("eula")) {
            Bukkit.getLogger().warning("Nebula - EULA has not been accepted, please accept it in the plugin config!");
            Bukkit.getLogger().info("Nebula - Disabling Nebula...");
            getServer().getPluginManager().disablePlugin(this);
            Bukkit.getLogger().info("--------------------");
        } else {
            Bukkit.getLogger().info("Nebula - Starting");
            Bukkit.getLogger().info("Nebula - Running: " + getDescription().getVersion());
            if (getConfig().getBoolean("Discord_Integration")) {
                Bukkit.getLogger().info("Nebula - Enabling Discord Integration");
                jda = buildJDA();
                Bukkit.getPluginManager().registerEvents(new onMinecraftChat(jda, getConfig()), this);
                Bukkit.getPluginManager().registerEvents(new onMinecraftDeath(jda, getConfig()), this);
                Bukkit.getPluginManager().registerEvents(new onMinecraftJoin(jda, getConfig()), this);
                Bukkit.getPluginManager().registerEvents(new project.nebula.Discord_Integration.Minecraft_Events.onMinecraftLeave(jda, getConfig()), this);
            }
            if (getConfig().getBoolean("Joinleave_Messages")) {
                Bukkit.getLogger().info("Nebula - Enabling Join/Leave messages");
                Bukkit.getPluginManager().registerEvents(new onMinecraftLeave(getConfig()), this);
                Bukkit.getPluginManager().registerEvents(new project.nebula.JoinLeave_Messages.onMinecraftJoin(getConfig()), this);
            }
            Bukkit.getLogger().info("Nebula - Startup complete");
            Bukkit.getLogger().info("--------------------");

        }

    }

    @Override
    public void onDisable() {
        // Shutdown Nebula
        Bukkit.getLogger().info("--------------------");
        Bukkit.getLogger().info("Nebula - Shutting down");
        if (getConfig().getBoolean("Discord_Integration")) {
            if (!(jda == null)) {
                jda.shutdown(); //Shutting down Discord Bot
                try {
                    if (!jda.awaitShutdown(Duration.ofSeconds(10))) {
                        jda.shutdownNow(); // Cancel all remaining requests
                        jda.awaitShutdown(); // Wait until shutdown is complete (indefinitely)
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            Bukkit.getLogger().info("Nebula - Discord Integration has shut down.");
        }

        Bukkit.getLogger().info("Nebula - Goodbye ( *・∀・)ノ゛");
        Bukkit.getLogger().info("--------------------");
    }
}
