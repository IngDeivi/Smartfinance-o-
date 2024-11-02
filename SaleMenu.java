package sales;

import inventory.InventoryManager;
import users.Role;
import users.User;

import java.util.Scanner;

public class SaleMenu {
    private final SaleManager saleManager;
    private final InventoryManager inventoryManager;

    public SaleMenu(SaleManager saleManager, InventoryManager inventoryManager) {
        this.saleManager = saleManager;
        this.inventoryManager = inventoryManager;
    }

    public void open(User user) {
        var scanner = new Scanner(System.in);
        int option;

        do {
            System.out.print(
                    """
╔══════════════════════════════════════════════════════════════════╗
║                      BIENVENIDO A KAYKRYS                        ║
╚══════════════════════════════════════════════════════════════════╝
╔══════════════════════════════════════════════════════════════════╗
║                         MENÚ DE VENTAS                           ║
╠══════════════════════════════════════════════════════════════════╣
║ (1) » Registrar una venta                                        ║
║ (2) » Listar todas las ventas de un producto                     ║
║ (3) » Buscar una venta                                           ║
║ (4) » Eliminar una venta                                         ║
║ (5) » Ganancias totales                                          ║
║ (6) » Ganancias por venta                                        ║
║ (7) » Estadisticas de ganancias                                  ║
║ (8) » Atrás                                                      ║
╚══════════════════════════════════════════════════════════════════╝                            
                            """
            );
            System.out.print("Escribe una opcion: ");
            option = scanner.nextInt();

            switch (option) {
                case 1:
                    registerSale(scanner);
                    break;
                case 2:
                    System.out.println(
                            """
            ╔══════════════════════════════════════════════════════════════════╗
            ║                  LISTA DE LAS VENTAS DE UN PRODUCTO              ║
            ╚══════════════════════════════════════════════════════════════════╝                
                            """);
                    for (Sale sale : saleManager.getSales()) {
                        System.out.println(sale);
                    }
                    break;
                case 3:
                    findSaleById(scanner);
                    break;
                case 4:
                    deleteSale(scanner);
                    break;
                case 5:
                    System.out.println(
                            """
            ╔══════════════════════════════════════════════════════════════════╗
            ║                        GANANCIAS TOTALES                         ║
            ╚══════════════════════════════════════════════════════════════════╝                
                            """);
                    if (user.getRole() == Role.ADMIN) {
                        System.out.println("Ganancias totales: " + saleManager.getProfits());
                    } else {
                        System.out.println("Usted no tiene permitida esta opción.");
                    }
                    break;
                case 6:
                    if (user.getRole() == Role.ADMIN) {
                        getProfitsBySaleId(scanner);
                    } else {
                        System.out.println("Usted no tiene permitida esta opción.");
                    }
                    break;
                case 7:
                    if (user.getRole() == Role.ADMIN) {
                        System.out.println(
                                """
                ╔══════════════════════════════════════════════════════════════════╗
                ║                    ESTADISTICAS DE GANANCIAS                     ║
                ╚══════════════════════════════════════════════════════════════════╝                
                                """);
                        System.out.println("Minimo de ganancia por venta: " + saleManager.minProfit());
                        System.out.println("Promedio de ganancia por venta: " + saleManager.averageProfit());
                        System.out.println("Maximo de ganancia por venta: " + saleManager.maxProfit());
                    } else {
                        System.out.println("Usted no tiene permitida esta opción.");
                    }
                    break;
            }
        } while (option != 8);
    }

    private void getProfitsBySaleId(Scanner scanner) {
        System.out.println(
                """
╔══════════════════════════════════════════════════════════════════╗
║                       GANANCIAS POR VENTA                        ║
╚══════════════════════════════════════════════════════════════════╝                
                """);
        System.out.print("Ingrese el código de la venta: ");
        int id = scanner.nextInt();
        var sale = saleManager.findSaleById(id);
        if (sale != null) {
            System.out.println("Las ganancias de esta venta son: " + saleManager.getProfits());
        } else {
            System.out.println("No existe ninguna venta registrada con el id: " + id);
        }
    }

    private void deleteSale(Scanner scanner) {
        System.out.println(
                """
╔══════════════════════════════════════════════════════════════════╗
║                       ELIMINAR UNA VENTA                         ║
╚══════════════════════════════════════════════════════════════════╝                
                """);
        System.out.print("Ingrese el ID de la venta: ");
        var id = scanner.nextInt();
        var removed = saleManager.removeSale(id);
        if (removed) {
            System.out.println("Se ha eliminado la venta.");
        } else {
            System.out.println("No se ha eliminado la venta, porque no existe.");
        }
    }

    private void findSaleById(Scanner scanner) {
        System.out.println(
                """
╔══════════════════════════════════════════════════════════════════╗
║                       BUSCAR UNA VENTA                           ║
╚══════════════════════════════════════════════════════════════════╝                
                """);
        System.out.print("Ingrese el id de la venta: ");
        int id = scanner.nextInt();
        var sale = saleManager.findSaleById(id);
        if (sale != null) {
            System.out.println(sale);
        } else {
            System.out.println("No existe una venta registrada.");
        }
    }

    private void registerSale(Scanner scanner) {
        System.out.println(
                """
╔══════════════════════════════════════════════════════════════════╗
║                       REGISTRAR UNA VENTA                        ║
╚══════════════════════════════════════════════════════════════════╝                
                """);
        System.out.print("Escribe el codigo de producto a vender: ");
        var sku = scanner.next();

        var item = inventoryManager.getItem(sku);
        if (item != null) {
            System.out.print("Escribe la cantidad a vender: ");
            var qty = scanner.nextInt();

            if (qty <= 0) {
                System.out.println("Ingrese una cantidad valida.");
            } else if (qty > item.getStock()) {
                System.out.println("No hay suficientes productos para vender.");
            } else {
                saleManager.addSale(item.getProduct(), qty);
            }
        } else {
            System.out.println("El codigo de producto no existe.");
        }
    }
}
