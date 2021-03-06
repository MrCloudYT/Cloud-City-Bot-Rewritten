package de.mrcloud.command;

import com.google.api.client.util.Sets;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.guild.GenericGuildMessageEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

public abstract class Command {


    public static final String PREFIX = "!";

    public final String name;
    public final Category category;
    public final String description;
    public final String usage;
    public long commandChannelId;

    /**
     * Executable command
     *
     * @param name        The name of the command as it should be used in discord
     * @param description The description of the command as should be used in the help command
     * @param usage       How to use the command (example !help [adming/etc])
     * @param category    The category of the command described in {@link Category}
     */
    public Command(String name, String description, String usage, Category category) {
        this.name = name;
        this.category = category;
        this.description = description;
        this.usage = usage;
    }

    /**
     * Executable command
     *
     * @param name        The name of the command as it should be used in discord
     * @param description The description of the command as should be used in the help command
     * @param usage       How to use the command (example !help [adming/etc])
     * @param category    The category of the command described in {@link Category}
     */
    public Command(String name, String description, String usage, Category category, long commandChannelId) {
        this.name = name;
        this.category = category;
        this.description = description;
        this.usage = usage;
        this.commandChannelId = commandChannelId;
    }

    public abstract boolean execute(GuildMessageReceivedEvent e, String[] args);

    public abstract boolean execute(MessageReceivedEvent e, String[] args);

    public @NotNull Set<String> getAliases() {
        return Sets.newHashSet();
    }

    /**
     * @param event The event that should be check to match the channel
     * @return Returns true if the channel lock is disabled or the channel that its locked to is matching the event channel
     */
    public boolean checkChannelLock(GenericGuildMessageEvent event) {
        if (commandChannelId != 0) {
            return event.getChannel().getIdLong() == commandChannelId;
        }
        return true
                ;
    }

    public enum Category {
        STAFF, OTHER, STATISTICS, DEBUG, ECONOMY, GAME
    }
}
