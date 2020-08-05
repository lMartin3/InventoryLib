package me.rektb.inventorylib.inv;

public class InventoryContents {
    private Menu menu;
    private MenuItem[][] contents;

    public InventoryContents(Menu menu) {
        this.menu = menu;
        contents = new MenuItem[menu.getRows()][menu.getColumns()];
    }
    public void set(int row, int column, MenuItem item) {
        contents[row][column] = item;
    }

    public MenuItem get(int row, int column) {
        return contents[row][column];
    }


}
