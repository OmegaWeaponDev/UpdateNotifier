package me.omegaweapondev.updatenotifier;

import me.omegaweapondev.updatenotifier.utilities.MessageHandler;
import me.omegaweapondev.updatenotifier.utilities.StorageManager;
import me.ou.library.Utilities;
import org.bukkit.plugin.java.JavaPlugin;

public class UpdateNotifier extends JavaPlugin {
  private UpdateNotifier pluginInstance;
  private StorageManager storageManager;
  private MessageHandler messageHandler;

  @Override
  public void onEnable() {
    pluginInstance = this;
    Utilities.setInstance(pluginInstance);

    storageManager = new StorageManager(pluginInstance);
    storageManager.setupConfigs();
    storageManager.configUpdater();

    messageHandler = new MessageHandler(pluginInstance, storageManager.getMessagesFile().getConfig());

    registerCommands();
    registerEvents();

  }

  @Override
  public void onDisable() {
    super.onDisable();
  }

  public void onReload() {

  }

  private void registerCommands() {

  }

  private void registerEvents() {

  }

  public StorageManager getSettingsHandler() {
    return storageManager;
  }

  public MessageHandler getMessageHandler() {
    return messageHandler;
  }
}
