package project.nebula.messages;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.event.HoverEvent;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Utility class for building and sending custom interactive messages in Minecraft 1.21.1
 * Uses the Adventure API for creating rich text Components with hover and click actions
 */
public class MessageBuilder {
    private final List<Component> components;

    /**
     * Create a new MessageBuilder instance
     */
    public MessageBuilder() {
        this.components = new ArrayList<>();
    }

    /**
     * Add text to the message
     *
     * @param text Text to add
     * @return This builder instance
     */
    public MessageBuilder text(String text) {
        components.add(Component.text(text));
        return this;
    }

    /**
     * Add colored text to the message
     *
     * @param text  Text to add
     * @param color NamedTextColor or custom TextColor
     * @return This builder instance
     */
    public MessageBuilder text(String text, TextColor color) {
        components.add(Component.text(text).color(color));
        return this;
    }

    /**
     * Add a new line to the message
     *
     * @return This builder instance
     */
    public MessageBuilder newLine() {
        components.add(Component.newline());
        return this;
    }

    /**
     * Add hover text to the last added component
     *
     * @param hoverText Text to show on hover
     * @return This builder instance
     */
    public MessageBuilder withHoverText(String hoverText) {
        if (!components.isEmpty()) {
            int lastIndex = components.size() - 1;
            Component lastComponent = components.get(lastIndex);
            components.set(lastIndex, lastComponent.hoverEvent(HoverEvent.showText(Component.text(hoverText))));
        }
        return this;
    }

    /**
     * Add hover text to the last added component as a Component
     *
     * @param hoverComponent Component to show on hover
     * @return This builder instance
     */
    public MessageBuilder withHoverComponent(Component hoverComponent) {
        if (!components.isEmpty()) {
            int lastIndex = components.size() - 1;
            Component lastComponent = components.get(lastIndex);
            components.set(lastIndex, lastComponent.hoverEvent(HoverEvent.showText(hoverComponent)));
        }
        return this;
    }

    /**
     * Add a command execution click action to the last added component
     *
     * @param command Command to execute (without the leading slash)
     * @return This builder instance
     */
    public MessageBuilder withCommandExecution(String command) {
        if (!components.isEmpty()) {
            int lastIndex = components.size() - 1;
            Component lastComponent = components.get(lastIndex);
            components.set(lastIndex, lastComponent.clickEvent(ClickEvent.runCommand("/" + command)));
        }
        return this;
    }

    /**
     * Add a suggest command click action to the last added component
     *
     * @param command Command to suggest
     * @return This builder instance
     */
    public MessageBuilder withCommandSuggestion(String command) {
        if (!components.isEmpty()) {
            int lastIndex = components.size() - 1;
            Component lastComponent = components.get(lastIndex);
            components.set(lastIndex, lastComponent.clickEvent(ClickEvent.suggestCommand(command)));
        }
        return this;
    }

    /**
     * Add a URL click action to the last added component
     *
     * @param url URL to open
     * @return This builder instance
     */
    public MessageBuilder withUrl(String url) {
        if (!components.isEmpty()) {
            int lastIndex = components.size() - 1;
            Component lastComponent = components.get(lastIndex);
            components.set(lastIndex, lastComponent.clickEvent(ClickEvent.openUrl(url)));
        }
        return this;
    }

    /**
     * Add a copy to clipboard click action to the last added component
     *
     * @param textToCopy Text to copy to clipboard
     * @return This builder instance
     */
    public MessageBuilder withCopyToClipboard(String textToCopy) {
        if (!components.isEmpty()) {
            int lastIndex = components.size() - 1;
            Component lastComponent = components.get(lastIndex);
            components.set(lastIndex, lastComponent.clickEvent(ClickEvent.copyToClipboard(textToCopy)));
        }
        return this;
    }

    /**
     * Make the last added component bold
     *
     * @return This builder instance
     */
    public MessageBuilder bold() {
        if (!components.isEmpty()) {
            int lastIndex = components.size() - 1;
            Component lastComponent = components.get(lastIndex);
            components.set(lastIndex, lastComponent.decoration(TextDecoration.BOLD, true));
        }
        return this;
    }

    /**
     * Make the last added component italic
     *
     * @return This builder instance
     */
    public MessageBuilder italic() {
        if (!components.isEmpty()) {
            int lastIndex = components.size() - 1;
            Component lastComponent = components.get(lastIndex);
            components.set(lastIndex, lastComponent.decoration(TextDecoration.ITALIC, true));
        }
        return this;
    }

    /**
     * Make the last added component underlined
     *
     * @return This builder instance
     */
    public MessageBuilder underlined() {
        if (!components.isEmpty()) {
            int lastIndex = components.size() - 1;
            Component lastComponent = components.get(lastIndex);
            components.set(lastIndex, lastComponent.decoration(TextDecoration.UNDERLINED, true));
        }
        return this;
    }

    /**
     * Make the last added component strikethrough
     *
     * @return This builder instance
     */
    public MessageBuilder strikethrough() {
        if (!components.isEmpty()) {
            int lastIndex = components.size() - 1;
            Component lastComponent = components.get(lastIndex);
            components.set(lastIndex, lastComponent.decoration(TextDecoration.STRIKETHROUGH, true));
        }
        return this;
    }

    /**
     * Make the last added component obfuscated (magic)
     *
     * @return This builder instance
     */
    public MessageBuilder obfuscated() {
        if (!components.isEmpty()) {
            int lastIndex = components.size() - 1;
            Component lastComponent = components.get(lastIndex);
            components.set(lastIndex, lastComponent.decoration(TextDecoration.OBFUSCATED, true));
        }
        return this;
    }

    /**
     * Build the message into a single Component
     *
     * @return The built Component
     */
    public Component build() {
        if (components.isEmpty()) {
            return Component.empty();
        }

        Component result = components.get(0);
        for (int i = 1; i < components.size(); i++) {
            result = result.append(components.get(i));
        }
        return result;
    }

    /**
     * Send the message to a player
     *
     * @param player Player to send the message to
     */
    public void send(Player player) {
        player.sendMessage(build());
    }

    /**
     * Send the message to multiple players
     *
     * @param players Players to send the message to
     */
    public void send(Iterable<? extends Player> players) {
        Component message = build();
        for (Player player : players) {
            player.sendMessage(message);
        }
    }

    /**
     * Send the message to all online players
     */
    public void broadcast() {
        Bukkit.getServer().sendMessage(build());
    }

    /**
     * Send the message to a command sender
     *
     * @param sender CommandSender to send the message to
     */
    public void send(CommandSender sender) {
        sender.sendMessage(build());
    }

    /**
     * Create a simple formatted message with hover and click (convenience method)
     *
     * @param text      The text content
     * @param color     Color for the text
     * @param hoverText Text to show on hover (null for none)
     * @param command   Command to execute on click (null for none)
     * @return The built Component
     */
    public static Component quickMessage(String text, TextColor color, String hoverText, String command) {
        Component component = Component.text(text).color(color);

        if (hoverText != null) {
            component = component.hoverEvent(HoverEvent.showText(Component.text(hoverText)));
        }

        if (command != null) {
            component = component.clickEvent(ClickEvent.runCommand("/" + command));
        }

        return component;
    }

    /**
     * Parse a legacy formatted string (using & color codes) into a Component
     *
     * @param text Text with legacy formatting codes
     * @return A Component with appropriate formatting
     */
    public static Component fromLegacyText(String text) {
        // Replace & with § for proper color code parsing
        String formattedText = text.replace('&', '§');

        // Use Adventure's built-in legacy text deserialization
        return net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer.legacySection()
                .deserialize(formattedText);
    }
}