package me.rektb.inventorylib.test;

import me.rektb.inventorylib.inv.*;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class ExampleInventory implements ContentProvider {
    @Override
    public void init(Player p, InventoryContents contents) {
        contents.set(2, 3, MenuItem.of(new ItemBuilder().setMaterial(Material.BED).setName("BedGuars").build(), (e)->{
            p.sendMessage("hola bro");
        }));
    }

    @Override
    public void update(Player p, InventoryContents contents) {
    }

    public static Menu getMenu() {
        return new MenuBuilder()
                .setId("hola")
                .setName("ejemplo")
                .setProvider(new ExampleInventory())
                .setSize(6, 9)
                .build();
    }
}
