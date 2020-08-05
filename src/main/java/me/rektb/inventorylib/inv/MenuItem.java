package me.rektb.inventorylib.inv;

import lombok.Getter;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.function.Consumer;

@Getter
public class MenuItem {
    private ItemStack itemStack;
    private Consumer<InventoryClickEvent> consumer;

    public MenuItem(ItemStack item, Consumer<InventoryClickEvent> consumer) {
        this.itemStack = item;
        this.consumer = consumer;
    }

    public void clickTrigger(InventoryClickEvent e) {
        if(consumer ==null) return;
        consumer.accept(e);
    }


    public static MenuItem of(ItemStack itemStack, Consumer<InventoryClickEvent> consumer) {
        return new MenuItem(itemStack, consumer);
    }

    public static MenuItem empty(ItemStack itemStack) {
        return new MenuItem(itemStack, null);
    }

}
