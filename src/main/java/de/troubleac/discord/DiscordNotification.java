package de.troubleac.discord;

/*
» Author:  1combit
» Date: 29.06.2021 | 17:58
» Twitter: @1combit
» Github: 1combit
» Project: » TroubleAC - VelocityDiscordNotification
 */

import com.google.inject.Inject;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.plugin.annotation.DataDirectory;
import com.velocitypowered.api.proxy.ProxyServer;
import de.troubleac.discord.util.ConfigExtractor;
import org.spongepowered.configurate.ConfigurateException;
import org.spongepowered.configurate.ConfigurationNode;
import org.spongepowered.configurate.yaml.YamlConfigurationLoader;

import java.io.File;
import java.nio.file.Path;
import org.slf4j.Logger;

@Plugin(id = "discordnotification", name = "DiscordNotification", version = "1.0.0-SNAPSHOT",
        authors = {"1combit"})
public class DiscordNotification {
    private final ProxyServer server;
    private ConfigurationNode conf = null;
    private final Path path;
    private final Logger logger;
    private File config;


    @Inject
    public DiscordNotification(ProxyServer server, @DataDirectory Path dataDirectory, Logger logger) {
        this.server = server;
        this.path = dataDirectory;
        this.logger = logger;

        logger.info("The Plugin DiscordNotification was loaded!");
    }

    @Subscribe
    public void onProxyInitialization(ProxyInitializeEvent event) {

        this.config = new ConfigExtractor(this.getClass(),path.toFile(),"config.yml")
                .extract();

        this.reloadConfig();




       // server.getEventManager().register(this, this);
    }

    public void reloadConfig() {
        final YamlConfigurationLoader loader = YamlConfigurationLoader.builder().path(config.toPath()).build();
        try {
            this.conf = loader.load();
        } catch (ConfigurateException e) {
            logger.error("Unable to load config: ", e);
        }
    }
    public ConfigurationNode getConfig() {
        return conf;
    }

}
