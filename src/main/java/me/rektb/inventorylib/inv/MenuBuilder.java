package me.rektb.inventorylib.inv;

public class MenuBuilder {
    public String id = "";
    public int rows = 3;
    public int columns = 9;
    public String name = "InvLib Inventory";
    public ContentProvider provider;

    public MenuBuilder setSize(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        return this;
    }

    public MenuBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public MenuBuilder setId(String id) {
        this.id = id;
        return this;
    }
    public MenuBuilder setProvider(ContentProvider provider) {
        this.provider = provider;
        return this;
    }

    public Menu build() {
        return new Menu(rows, columns, name, id, provider);
    }
}
