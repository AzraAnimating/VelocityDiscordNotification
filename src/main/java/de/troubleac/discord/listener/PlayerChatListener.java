package de.troubleac.discord.listener;

import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.player.PlayerChatEvent;
import de.troubleac.discord.DiscordNotification;
import de.troubleac.discord.discord.DiscordWebhook;
import org.slf4j.Logger;

import java.awt.*;
import java.io.IOException;
import java.net.http.WebSocketHandshakeException;

/*
» Author:  1combit
» Date: 29.06.2021 | 18:34
» Twitter: @1combit
» Github: 1combit
» Project: » TroubleAC - VelocityDiscordNotification
 */public class PlayerChatListener {

    private final DiscordNotification plugin;

    public PlayerChatListener(DiscordNotification plugin) {
        this.plugin = plugin;
    }

    @Subscribe
    public void onPlayerChat(PlayerChatEvent event) throws IOException {


        System.out.println(plugin.getConfig().node("WebhookURL").getString());
        DiscordWebhook webhook = new DiscordWebhook(plugin.getConfig().node("WebhookURL").getString());
        webhook.addEmbed(new DiscordWebhook.EmbedObject()
                .setTitle("Chat message from " + event.getPlayer().getUsername())
                .setDescription(event.getMessage())
                .setImage("https://cravatar.eu/helmavatar/" + event.getPlayer().getUsername() + "/190.png")
                .setColor(Color.cyan));
        if (plugin.getConfig().node("UseChat").getBoolean()) {
            webhook.execute();
        }


    }


}
