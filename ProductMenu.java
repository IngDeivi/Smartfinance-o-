package product;

import java.util.Scanner;

public class ProductMenu {

    private final ProductManager productManager;

    public ProductMenu(ProductManager productManager) {
        this.productManager = productManager;
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
║                        MENÚ DE PRODUCTOS                         ║
╠══════════════════════════════════════════════════════════════════╣
║ (1) » Agregar producto                                           ║
║ (2) » Listar productos                                           ║
║ (3) » Buscar producto                                            ║
║ (4) » Actualizar producto                                        ║
║ (5) » Eliminar producto                                          ║
║ (6) » Atrás                                                      ║
╚══════════════════════════════════════════════════════════════════╝
                            """
            );
            System.out.print("Escribe una opción: ");
            option = scanner.nextInt();

            switch (option) {
                case 1:
                    createProduct(scanner);
                    break;
                case 2:
                    System.out.println(
                            """
            ╔══════════════════════════════════════════════════════════════════╗
            ║                        LISTA DE PRODUCTOS                        ║
            ╚══════════════════════════════════════════════════════════════════╝                
                            """);
                    for (Product product : productManager.getProducts()) {
                        System.out.println(product);
                    }
                    break;
                case 3:
                    searchProduct(scanner);
                    break;
                case 4:
                    updateProduct(scanner);
                    break;
                case 5:
                    deleteProduct(scanner);
                    break;
            }
        } while (option != 6);
    }

    private void deleteProduct(Scanner scanner) {
        System.out.println(
                """
╔══════════════════════════════════════════════════════════════════╗
║                        ELIMINAR PRODUCTO                         ║
╚══════════════════════════════════════════════════════════════════╝                
                """);
        System.out.print("Ingrese el codigo de producto: ");
        String sku = scanner.next();
        var removed = productManager.removeProduct(sku);
        if (removed) {
            System.out.println("¡El producto fue eliminado con exito!");
        } else {
            System.out.println("El producto no existe.");
        }
    }

    private void searchProduct(Scanner scanner) {
        System.out.println(
                """
╔══════════════════════════════════════════════════════════════════╗
║                          BUSCAR PRODUCTO                         ║
╚══════════════════════════════════════════════════════════════════╝                
                """);
        System.out.print("Ingrese el codigo de producto: ");
        String sku = scanner.next();
        var product = productManager.findProduct(sku);
        if (product != null) {
            System.out.println(product);
        } else {
            System.out.println("No existe el producto.");
        }
    }

    private void updateProduct(Scanner scanner) {
        System.out.println(
                """
╔══════════════════════════════════════════════════════════════════╗
║                       ACTUALIZAR PRODUCTO                        ║
╚══════════════════════════════════════════════════════════════════╝                
                """);
        System.out.print("Ingrese el codigo de producto: ");
        String sku = scanner.next();
        var product = productManager.findProduct(sku);
        if (product != null) {
            System.out.print("Ingrese el nombre del producto: ");
            var name = scanner.next();
            System.out.print("Ingrese el costo del producto: ");
            var cost = scanner.nextInt();
            System.out.print("Ingrese el precio del producto: ");
            var price = scanner.nextInt();

            product.setName(name);
            product.setCost(cost);
            product.setPrice(price);
        } else {
            System.out.println("No existe el producto.");
        }
    }

    private void createProduct(Scanner scanner) {
        System.out.println(
                """
╔══════════════════════════════════════════════════════════════════╗
║                         AGREGAR PRODUCTO                         ║
╚══════════════════════════════════════════════════════════════════╝                
                """);
        try {
            System.out.print("Escribe el código del producto: ");
            var sku = scanner.next();
            System.out.print("Escribe el nombre del producto: ");
            var name = scanner.next();
            System.out.print("Escribe el costo del producto: ");
            var cost = scanner.nextInt();
            System.out.print("Escribe el precio del producto: ");
            var price = scanner.nextInt();
            productManager.addProduct(new Product(sku, name, cost, price));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
