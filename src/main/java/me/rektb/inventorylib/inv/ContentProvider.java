package me.rektb.inventorylib.inv;

import org.bukkit.entity.Player;

public interface ContentProvider {

    void init(Player p, InventoryContents contents);

    void update(Player p, InventoryContents contents);

}
