package me.rektb.inventorylib;

import me.rektb.inventorylib.managers.InventoryLibManager;
import org.bukkit.plugin.java.JavaPlugin;

public class InventoryLib extends JavaPlugin {

    @Override
    public void onEnable() {
        InventoryLibManager.getInstance().init(this);
    }

    @Override
    public void onDisable() {
        InventoryLibManager.getInstance().stop();
    }
}
