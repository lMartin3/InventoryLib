# InventoryLib
Una librería mediocre para hacer inventarios improvisada en 40 minutos.
Para un ejemplo de como utilizarla ver `me.rektb.inventorylib.test.ExampleInventory`.

IMPORTANTE: Si se usa desde otro plugin como un jar shadeado, hacer esto:
```java
    @Override
    public void onEnable() {
        InventoryLibManager.getInstance().init(this);
    }

    @Override
    public void onDisable() {
        InventoryLibManager.getInstance().stop();
    }
```

Esta librería fue creada como un reemplazo para 
[SmartInvs](https://github.com/MinusKube/SmartInvs), ya que cuando se shadea
el peso del jar incrementa en 5 o 6 MB (por todas las librerías que requiere).
También es por esta misma razón que la estructura es muy similar.
