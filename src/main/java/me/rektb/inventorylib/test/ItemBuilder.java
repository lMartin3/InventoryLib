package me.rektb.inventorylib.test;


import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ItemBuilder {
    /*
        ItemBuilder.
    */
    Material material = Material.STONE;
    ItemMeta itemMeta;
    String name = " ";
    List<String> lore = new ArrayList<>();
    boolean unbreakable = false;

    public ItemBuilder() {
    }

    public ItemBuilder setUnbreakable(boolean unbreakable) {
        this.unbreakable = unbreakable;
        return this;
    }

    public ItemBuilder setMaterial(Material m) {
        this.material = m;
        return this;
    }

    public ItemBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public ItemBuilder addLoreLine(String line) {
        this.lore.add(line);
        return this;
    }

    public ItemBuilder setLore(List<String> lore) {
        this.lore = lore;
        return this;
    }

    public ItemBuilder setMeta(ItemMeta im) {
        this.itemMeta = im;
        return this;
    }


    public ItemStack build() {
        ItemStack result = new ItemStack(material);
        if (this.itemMeta != null) {
            result.setItemMeta(itemMeta);
        }
        ItemMeta resultMeta = result.getItemMeta();
        resultMeta.setDisplayName(name);
        resultMeta.setLore(lore);
        result.setItemMeta(resultMeta);
        resultMeta.spigot().setUnbreakable(unbreakable);
        return result;
    }


    public ItemBuilder from(ItemStack is) {
        this.material = is.getType();
        this.itemMeta = is.getItemMeta();
        if(itemMeta.hasDisplayName() && itemMeta.getDisplayName()!=null) {
            this.name = itemMeta.getDisplayName();
        }
        if(itemMeta.hasLore() && itemMeta.getLore()!=null) {
            this.lore = itemMeta.getLore();
        }
        return this;
    }

    private static List<String> getPlayerList(List<String> input, String displayIfNone) {
        List<String> result = new ArrayList<>();
        int added = 0;
        for (String player : input) {
            result.add("§8- §7" + player);
            added++;
        }
        if (added == 0) {
            result.add(displayIfNone);
        }
        return result;
    }
}
