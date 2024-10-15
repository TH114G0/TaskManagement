package br.com.gestao;

import br.com.gestao.service.TaskService;
import br.com.gestao.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class Application implements CommandLineRunner {
    @Autowired
    private TaskService taskService;
    @Autowired
    private UserService userService;
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>MENÚ PRINCIPAL<<<<<<<<<<<<<<<<<<<<<<<<");
            System.out.println(">>>>>>>>>>>>>>>>>>>>>OPCIONES DISPONIBLES<<<<<<<<<<<<<<<<<<<<<");
            System.out.println("0. Salir");
            System.out.println("1. Usuario");
            System.out.println("2. Tareas");
            int opcion = 0;
            try {
                opcion = scanner.nextInt();
                scanner.nextLine();
            }catch (ArithmeticException e) {
                System.err.println("¡Opción equivocada! Solo numeros.");
                scanner.nextLine();
                continue;
            }

            switch (opcion) {
                case 0:
                    return;
                case 1:
                    userService.menu(scanner);
                    break;
                case 2:
                    taskService.menu(scanner);
                    break;
                default:
                    System.out.println("¡La opción no es válida!");
            }
        }
    }
}