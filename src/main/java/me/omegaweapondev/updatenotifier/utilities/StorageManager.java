package me.omegaweapondev.updatenotifier.utilities;

import me.omegaweapondev.updatenotifier.UpdateNotifier;
import me.ou.library.Utilities;
import me.ou.library.configs.ConfigCreator;
import me.ou.library.configs.ConfigUpdater;

import java.io.IOException;
import java.util.Arrays;

public class StorageManager {
  private final UpdateNotifier plugin;
  private final ConfigCreator configFile;
  private final ConfigCreator messagesFile;

  public StorageManager(final UpdateNotifier plugin) {
    this.plugin = plugin;
    configFile = new ConfigCreator("config.yml");
    messagesFile = new ConfigCreator("messages.yml");
  }

  public void setupConfigs() {
    getConfigFile().createConfig();
    getMessagesFile().createConfig();
  }

  public void configUpdater() {
    Utilities.logInfo(true, "Attempting to update the config files....");

    try {
      if(getConfigFile().getConfig().getDouble("Config_Version") != 1.3) {
        getConfigFile().getConfig().set("Config_Version", 1.3);
        getConfigFile().saveConfig();
        ConfigUpdater.update(plugin, "config.yml", getConfigFile().getFile(), Arrays.asList("none"));
        Utilities.logInfo(true, "The config.yml has successfully been updated!");
      }

      if(getMessagesFile().getConfig().getDouble("Config_Version") != 1.3) {
        getMessagesFile().getConfig().set("Config_Version", 1.3);
        getMessagesFile().saveConfig();
        ConfigUpdater.update(plugin, "messages.yml", getMessagesFile().getFile(), Arrays.asList("none"));
        Utilities.logInfo(true, "The messages.yml has successfully been updated!");
      }
      plugin.onReload();
    } catch(IOException ex) {
      ex.printStackTrace();
    }
  }

  public void reloadFiles() {
    getMessagesFile().reloadConfig();
    getConfigFile().reloadConfig();
  }

  public ConfigCreator getConfigFile() {
    return configFile;
  }

  public ConfigCreator getMessagesFile() {
    return messagesFile;
  }

}
