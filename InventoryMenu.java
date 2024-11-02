package inventory;

import java.util.Scanner;

public class InventoryMenu {

    private final InventoryManager inventoryManager;

    public InventoryMenu(InventoryManager inventoryManager) {
        this.inventoryManager = inventoryManager;
    }

    public void open() {
        var scanner = new Scanner(System.in);
        int option;

        do {
            System.out.print(
                    """
╔══════════════════════════════════════════════════════════════════╗
║                      BIENVENIDO A KAYKRYS                        ║
╚══════════════════════════════════════════════════════════════════╝
╔══════════════════════════════════════════════════════════════════╗
║                         MENÚ INVENTARIO                          ║
╠══════════════════════════════════════════════════════════════════╣
║ (1) » Mostrar todos los productos                                ║
║ (2) » Buscar producto por SKU                                    ║
║ (3) » Agregar existencias                                        ║
║ (4) » Restar existencias                                         ║
║ (5) » Atrás                                                      ║
╚══════════════════════════════════════════════════════════════════╝                            
                    """
            );
            System.out.print("Escribe una opción: ");
            option = scanner.nextInt();

            switch (option) {
                case 1:
                    System.out.println("""
                    ╔══════════════════════════════════════════════════════════════════╗
                    ║                   LISTA DE PRODUCTOS EXISTENTES                  ║
                    ╚══════════════════════════════════════════════════════════════════╝
                            """);
                    for (InventoryItem item : inventoryManager.getItems()) {
                        System.out.println(item);
                    }
                    break;
                case 2:
                    searchProduct(scanner);
                    break;
                case 3:
                    addStock(scanner);
                    break;
                case 4:
                    subtractStock(scanner);
                    break;
            }
        } while (option != 5);
    }

    private void subtractStock(Scanner scanner) {
        System.out.println(
                """
╔══════════════════════════════════════════════════════════════════╗
║                        RESTAR EXISTENCIAS                        ║
╚══════════════════════════════════════════════════════════════════╝                
                """);
        System.out.print("Ingrese el codigo de producto: ");
        String sku3 = scanner.next();
        var item3 = inventoryManager.getItem(sku3);

        if (item3 != null) {
            System.out.println(item3);
            System.out.print("Ingrese la cantidad de productos que desea quitar: ");
            var qty = scanner.nextInt();
            try {
                item3.subtractStock(qty);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("No existe el producto.");
        }
    }

    private void addStock(Scanner scanner) {
        System.out.println(
                """
╔══════════════════════════════════════════════════════════════════╗
║                       AGREGAR EXISTENCIAS                        ║
╚══════════════════════════════════════════════════════════════════╝                
                """);
        System.out.print("Ingrese el codigo de producto: ");
        String sku2 = scanner.next();
        var item2 = inventoryManager.getItem(sku2);

        if (item2 != null) {
            System.out.println(item2);
            System.out.print("Ingrese la cantidad de productos que desea agregar: ");
            var qty = scanner.nextInt();
            item2.addStock(qty);
        } else {
            System.out.println("No existe el producto.");
        }
    }

    private void searchProduct(Scanner scanner) {
        System.out.println(
                """
╔══════════════════════════════════════════════════════════════════╗
║                     BUSCAR PRODUCTO POR SKU                      ║
╚══════════════════════════════════════════════════════════════════╝                
                """);
        System.out.print("Ingrese el codigo de producto: ");
        String sku = scanner.next();
        var item = inventoryManager.getItem(sku);

        if (item != null) {
            System.out.println(item.getProduct());
            System.out.println();
            System.out.println(item);
        } else {
            System.out.println("No existe el producto.");
        }
    }
}
