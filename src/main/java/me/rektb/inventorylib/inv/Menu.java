package me.rektb.inventorylib.inv;

import lombok.Getter;
import lombok.Setter;
import me.rektb.inventorylib.managers.InventoryLibManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

@Getter
@Setter
public class Menu {
    private String id;
    private String title;
    private int rows = 3;
    private int columns = 9;
    private ContentProvider provider;
    private InventoryContents contents;
    private Inventory inventory;


    public Menu(int rows, int columns, String title, String id, ContentProvider provider) {
        this.rows = rows;
        this.columns = columns;
        this.title = title;
        this.id = id;
        this.contents = new InventoryContents(this);
        this.provider = provider;
        InventoryLibManager.getInstance().addMenu(this);
    }

    public void open(Player p) {
        inventory = Bukkit.createInventory(null, rows*9, title);
        provider.init(p, contents);
        updateInventoryContents();
        p.openInventory(inventory);
    }

    public void updateInventoryContents() {
        if(inventory==null) return;
        for(int row=0;row<rows;row++) {
            for(int column=0;column<columns;column++) {
                MenuItem menuItem = contents.get(row, column);
                if(menuItem==null) {
                    continue;
                }
                ItemStack item = menuItem.getItemStack();
                if(item==null) {
                    continue;
                }
                inventory.setItem(row*9+column, item);
            }
        }
    }

    public void tick() {
        if(inventory==null) return;
        if(inventory.getViewers().size()<1) return;
        provider.update((Player) inventory.getViewers().get(0), contents);
        updateInventoryContents();
    }

    public void close(Player p) {
    }

    public void click(InventoryClickEvent e) {
        int row = e.getSlot() / 9;
        int slot = e.getSlot() % 9;
        MenuItem menuItem = contents.get(row, slot);
        if(menuItem==null) return;
        menuItem.clickTrigger(e);
    }



    public static MenuBuilder builder() {
        return new MenuBuilder();
    }

}
