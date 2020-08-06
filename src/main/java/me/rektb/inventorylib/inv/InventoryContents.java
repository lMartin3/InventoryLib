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

    public void fill(MenuItem item) {
        for(int i = 0; i < menu.getRows(); i ++) {
            for(int j = 0; j < menu.getColumns(); j++) {
                contents[i][j] = item;
            }
        }
    }

    public void fillRow(int row, MenuItem item) {
        for(int i = 0; i < menu.getColumns(); i++) {
            contents[row][i] = item;
        }
    }

    public void fillColumn(int column, MenuItem item) {
        for(int i = 0; i < menu.getRows(); i ++) {
            contents[i][column] = item;
        }
    }


}
