package users;

import java.util.Scanner;

public class UserMenu {
    private final UserManager userManager;

    public UserMenu(UserManager userManager) {
        this.userManager = userManager;
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
║                        MENÚ DE USUARIOS                          ║
╠══════════════════════════════════════════════════════════════════╣
║ (1) » Agregar usuario                                            ║
║ (2) » Eliminar usuario                                           ║
║ (3) » Listar usuarios                                            ║
║ (4) » Buscar usuario por nombre                                  ║
║ (5) » Modificar un usuario                                       ║
║ (6) » Atrás                                                      ║
╚══════════════════════════════════════════════════════════════════╝               
                    """);
            System.out.print("Escribe una opción: ");
            option = scanner.nextInt();

            switch (option) {
                case 1:
                    registerNewUser(scanner);
                    break;
                case 2:
                    deleteUser(scanner);
                    break;
                case 3:
                    System.out.println(
                            """
            ╔══════════════════════════════════════════════════════════════════╗
            ║                         LISTA DE USUARIOS                        ║
            ╚══════════════════════════════════════════════════════════════════╝                
                            """);
                    for (User user : userManager.getUsers()) {
                        System.out.println(user);
                    }
                    break;
                case 4:
                    searchUser(scanner);
                    break;
                case 5:
                    updateUser(scanner);
                    break;
            }
        } while (option != 6);
    }

    private void updateUser(Scanner scanner) {
        System.out.println(
                """
╔══════════════════════════════════════════════════════════════════╗
║                        ACTUALIZAR USUARIO                        ║
╚══════════════════════════════════════════════════════════════════╝                
                """);
        try {
            System.out.print("Escribe el nombre del usuario a modificar: ");
            var username = scanner.next();
            var user = userManager.findUserByUsername(username);
            if (user != null) {
                System.out.print("Escribe la nueva contraseña: ");
                var password = scanner.next();
                user.setPassword(password);

                Role role;
                do {
                    System.out.print("Escribe el nuevo rol: ");
                    role = Role.parse(scanner.next());

                    if (role == null) {
                        System.out.println("El rol escrito no existe, escribe un rol valido.");
                        System.out.println("Roles validos: (admin y cajero).");
                    }
                } while(role == null);
                user.setRole(role);

                System.out.println("Has modificado el usuario con exito.");
            } else {
                System.out.println("El nombre del usuario " + username + " no existe.");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void searchUser(Scanner scanner) {
        System.out.println(
                """
╔══════════════════════════════════════════════════════════════════╗
║                     BUSCAR USUARIO POR NOMBRE                    ║
╚══════════════════════════════════════════════════════════════════╝                
                """);
        System.out.print("Escribe el nombre de usuario a buscar: ");
        var username = scanner.next();
        var user = userManager.findUserByUsername(username);
        if (user != null) {
            System.out.println(user);
        } else {
            System.out.println("El nombre no existe.");
        }
    }

    private void deleteUser(Scanner scanner) {
        System.out.println(
                """
╔══════════════════════════════════════════════════════════════════╗
║                         ELIMINAR USUARIO                         ║
╚══════════════════════════════════════════════════════════════════╝                
                """);
        System.out.print("Ingrese el nombre del usuario a eliminar: ");
        var username = scanner.next();
        var removed = userManager.removeUser(username);
        if (removed) {
            System.out.println("Usuario eliminado exitosamente.");
        } else {
            System.out.println("Usuario no eliminado.");
        }
    }

    private void registerNewUser(Scanner scanner) {
        System.out.println(
                """
╔══════════════════════════════════════════════════════════════════╗
║                         AGREGAR USUARIO                          ║
╚══════════════════════════════════════════════════════════════════╝
╔══════════════════════════════════════════════════════════════════╗
║ → Ingresa un nombre de usuario para continuar.                   ║
║ → La contraseña puede ser cualquier combinacion de letras,       ║
║   números y símbolos. Se recomienda minimo 8 caracteres.         ║
║ → Asigna un rol de usuario (admin o cajero).                     ║
╚══════════════════════════════════════════════════════════════════╝                
                """);
        try {
            System.out.print("Ingrese nombre: ");
            var username = scanner.next();

            System.out.print("Ingrese la contraseña: ");
            var password = scanner.next();

            Role role;
            do {
                System.out.print("Ingrese el rol del usuario: ");
                role = Role.parse(scanner.next());

                if (role == null) {
                    System.out.println("El rol escrito no existe, escribe un rol valido.");
                }
            } while(role == null);

            userManager.addUser(new User(username, password, role));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
