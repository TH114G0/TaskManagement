package br.com.gestao.service;


import br.com.gestao.model.UserModel;
import br.com.gestao.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.InputMismatchException;
import java.util.Optional;
import java.util.Scanner;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public void menu(Scanner scanner) {
        while (true) {
            System.out.println();
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>MENÚ DE USUARIO<<<<<<<<<<<<<<<<<<<<<<<<");
            System.out.println(">>>>>>>>>>>>>>>>>>>>>OPCIONES DISPONIBLES<<<<<<<<<<<<<<<<<<<<<");
            System.out.println("0. Salir");
            System.out.println("1. Crear usuario");
            System.out.println("2. Vista de usuario");
            System.out.println("3. Editar usuario");
            System.out.println("4. Borrar usuario");
            int opcion = 0;
            try {
                opcion = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.err.println("¡Opción equivocada! Solo numeros.");
                scanner.nextLine();
                continue;
            }
            switch (opcion) {
                case 0:
                    return;
                case 1:
                    createUsuario(scanner);
                    break;
                case 2:
                    viewUsuario(scanner);
                    break;
                case 3:
                    editTask(scanner);
                    break;
                case 4:
                    deleteTask(scanner);
                    break;
                default:
                    System.out.println("¡La opción no es válida!");
            }
        }
    }

    private void createUsuario(Scanner scanner) {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>CREA TASK<<<<<<<<<<<<<<<<<<<<<<");
        UserModel userModel = new UserModel();
        TaskService taskService = new TaskService();

        System.out.print("Introduce tu nombre: ");
        userModel.setName(scanner.nextLine());

        System.out.print("Introduce tu e-mail: ");
        userModel.setEmail(scanner.nextLine());

        userModel.setTaskModelList(taskService.createTask(scanner, userModel));

        try {
            userRepository.save(userModel);
            System.out.println("Usuario creado exitosamente - " + userModel);
        } catch (Exception e) {
            System.err.println("Erro al guardar usuario - " + e.getMessage());
        }
    }

    private void viewUsuario(Scanner scanner) {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>VER USUARIO<<<<<<<<<<<<<<<<<<<<<<");
        UserModel userModel = new UserModel();

        while (true) {
            System.out.print("Ingrese el ID: ");
            userModel.setId(scanner.nextLong());
            scanner.nextLine();

            Optional<UserModel> userModelOptional = userRepository.findById(userModel.getId());
            if (userModelOptional.isPresent()) {
                userModel = userModelOptional.get();
                System.out.println(userModel);
                break;
            } else {
                System.out.println("ID de usuario no encontrado");
            }
        }
    }

    private void editTask(Scanner scanner) {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>EDITAR USUARIO<<<<<<<<<<<<<<<<<<<<<<");
        UserModel userModel = new UserModel();

        while (true) {
            System.out.print("Ingrese el ID: ");
            userModel.setId(scanner.nextLong());
            scanner.nextLine();

            Optional<UserModel> userModelOptional = userRepository.findById(userModel.getId());
            if (userModelOptional.isPresent()) {
                userModel = userModelOptional.get();
                System.out.print("Introduce tu nombre: ");
                userModel.setName(scanner.nextLine());

                System.out.print("Introduce tu e-mail: ");
                userModel.setEmail(scanner.nextLine());
                try {
                    userRepository.save(userModel);
                    System.out.println("Usuario editado exitosamente - " + userModel);
                    break;
                } catch (Exception e) {
                    System.err.println("Erro al editar usuario - " + e.getMessage());
                }
            } else {
                System.out.println("ID de usuario no encontrado");
            }
        }
    }

    private void deleteTask(Scanner scanner) {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>EDITAR USUARIO<<<<<<<<<<<<<<<<<<<<<<");
        UserModel userModel = new UserModel();

        while (true) {
            System.out.print("Ingrese el ID: ");
            userModel.setId(scanner.nextLong());
            scanner.nextLine();

            Optional<UserModel> userModelOptional = userRepository.findById(userModel.getId());
            if (userModelOptional.isPresent()) {
                userModel = userModelOptional.get();
                userRepository.deleteById(userModel.getId());
                System.out.println("Usuario eliminada exitosamente");
                break;
            } else {
                System.out.println("ID de usuario no encontrado");
            }
        }
    }
}