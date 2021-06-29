package de.troubleac.discord.listener;

import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.player.PlayerChatEvent;
import com.velocitypowered.api.event.player.ServerConnectedEvent;
import de.troubleac.discord.DiscordNotification;
import de.troubleac.discord.discord.DiscordWebhook;

import java.awt.*;
import java.io.IOException;

/*
» Author:  1combit
» Date: 29.06.2021 | 18:56
» Twitter: @1combit
» Github: 1combit
» Project: » TroubleAC - VelocityDiscordNotification
 */public class ConnectionListener {

    private final DiscordNotification plugin;

    public ConnectionListener(DiscordNotification plugin) {
        this.plugin = plugin;
    }

    @Subscribe
    public void onConnectedEvent(ServerConnectedEvent event) throws IOException {
        DiscordWebhook webhook = new DiscordWebhook(plugin.getConfig().node("WebhookURL").getString());
        webhook.addEmbed(new DiscordWebhook.EmbedObject()
                .setTitle("The player " + event.getPlayer().getUsername() + "has connected to the " + event.getServer()  + " server!")
                .setImage("https://cravatar.eu/helmavatar/" + event.getPlayer().getUsername() + "/190.png")
                .setColor(Color.cyan));
        if (plugin.getConfig().node("UseSwitch").getBoolean()) {
            webhook.execute();
        }

    }

}
