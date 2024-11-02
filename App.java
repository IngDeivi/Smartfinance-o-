import inventory.InventoryManager;
import inventory.InventoryMenu;
import product.ProductManager;
import product.ProductMenu;
import sales.SaleManager;
import sales.SaleMenu;
import users.Role;
import users.User;
import users.UserManager;
import users.UserMenu;

import java.util.Scanner;

public class App {
    private static final InventoryManager inventoryManager = new InventoryManager();
    private static final ProductManager productManager = new ProductManager(inventoryManager);
    private static final SaleManager saleManager = new SaleManager(inventoryManager);
    private static final UserManager userManager = new UserManager(
            new User("kevinpacheco", "123456", Role.ADMIN),
            new User("kameronhernandez", "123456", Role.ADMIN),
            new User("deivialeman", "123456", Role.USER)
    );

    public static void main(String[] args) {
        var scanner = new Scanner(System.in);

        while (true) {
            System.out.print(
                    """
╔══════════════════════════════════════════════════════════════════╗
║                      BIENVENIDO A KAYKRYS                        ║
╚══════════════════════════════════════════════════════════════════╝
╔══════════════════════════════════════════════════════════════════╗
║                             LOGIN                                ║
╠══════════════════════════════════════════════════════════════════╣
║                        ¡HOLA DE NUEVO!                           ║
║        Por favor ingrese sus credenciales para continuar         ║
╚══════════════════════════════════════════════════════════════════╝                    
                    """
            );
            System.out.print("Escribe el nombre de usuario: ");
            var username = scanner.next();
            System.out.print("Escribe la contraseña: ");
            var password = scanner.next();

            var user = userManager.login(username, password);

            if (user != null) {
                mainMenu(scanner, user);
            } else {
                System.out.println("Usuario y/o contraseña es invalido.");

                System.out.print("Deseas salir del programa? (si/no) ");
                var answer = scanner.next();

                if (answer.equals("si")) {
                    System.exit(0);
                }
            }
        }
    }

    private static void mainMenu(Scanner scanner, User user) {
        int option;

        var productMenu = new ProductMenu(productManager);
        var inventoryMenu = new InventoryMenu(inventoryManager);
        var saleMenu = new SaleMenu(saleManager, inventoryManager);
        var userMenu = new UserMenu(userManager);

        do {
            System.out.print(
                    """
╔══════════════════════════════════════════════════════════════════╗
║                      BIENVENIDO A KAYKRYS                        ║
╚══════════════════════════════════════════════════════════════════╝
╔══════════════════════════════════════════════════════════════════╗
║                         MENÚ PRINCIPAL                           ║
╠══════════════════════════════════════════════════════════════════╣
║ (1) » Administrar productos                                      ║
║ (2) » Administrar inventario                                     ║
║ (3) » Administrar ventas                                         ║
║ (4) » Administrar usuarios                                       ║
║ (5) » Salir                                                      ║
╚══════════════════════════════════════════════════════════════════╝                            
                     """
            );
            System.out.print("Escribe una opción: ");
            option = scanner.nextInt();

            switch (option) {
                case 1:
                    if (user.getRole() == Role.ADMIN) {
                        productMenu.open();
                    } else {
                        System.out.println("Usted no tiene el rol administrador.");
                        System.out.println("Solo el administrador tiene acceso a esta opción.");
                    }
                    break;
                case 2:
                    if (user.getRole() == Role.ADMIN) {
                        inventoryMenu.open();
                    } else {
                        System.out.println("Usted no tiene el rol administrador.");
                        System.out.println("Solo el administrador tiene acceso a esta opción.");
                    }
                    break;
                case 3:
                    saleMenu.open(user);
                    break;
                case 4:
                    if (user.getRole() == Role.ADMIN) {
                        userMenu.open();
                    } else {
                        System.out.println("Usted no tiene el rol administrador.");
                        System.out.println("Solo el administrador tiene acceso a esta opción.");
                    }
                    break;
            }
        } while (option != 5);
    }
}
