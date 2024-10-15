package br.com.gestao.service;

import br.com.gestao.model.TaskEnum;
import br.com.gestao.model.TaskModel;
import br.com.gestao.model.UserModel;
import br.com.gestao.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    public void menu(Scanner scanner) {
        while (true) {
            System.out.println();
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>MENÚ DE TAREA<<<<<<<<<<<<<<<<<<<<<<<<");
            System.out.println(">>>>>>>>>>>>>>>>>>>>>OPCIONES DISPONIBLES<<<<<<<<<<<<<<<<<<<<<");
            System.out.println("0. Salir");
            System.out.println("1. Vista de tarea");
            System.out.println("2. Editar tarea");
            System.out.println("3. Borrar tarea");
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
                    viewTask(scanner);
                    break;
                case 2:
                    editTask(scanner);
                    break;
                case 3:
                    deleteTask(scanner);
                    break;
                default:
                    System.out.println("¡La opción no es válida!");
            }
        }
    }

    public List<TaskModel> createTask(Scanner scanner, UserModel userModel) {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>CREA TASK<<<<<<<<<<<<<<<<<<<<<<");
        List<TaskModel> taskModelList = new ArrayList<>();

        while (true) {
            TaskModel taskModel = new TaskModel();
            System.out.print("Ingrese la descripción de la tarea: ");
            taskModel.setDescription(scanner.nextLine());

            while (true) {
                System.out.println("Status de la tarea");
                System.out.println("0. salir");
                System.out.println("1. pending");
                System.out.println("2. progress");
                System.out.println("3. completed");
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
                        System.out.println("Operación cancelada.");
                        return taskModelList;
                    case 1:
                        taskModel.setStatus(TaskEnum.pending);
                        break;
                    case 2:
                        taskModel.setStatus(TaskEnum.progress);
                        break;
                    case 3:
                        taskModel.setStatus(TaskEnum.completed);
                        break;
                    default:
                        System.out.println("¡La opción no es válida!");
                        continue;
                }
                break;
            }

            try {
                taskModel.setUser(userModel);
                taskModelList.add(taskModel);
                System.out.println("Tarea creada exitosamente - " + taskModel);
            } catch (Exception e) {
                System.err.println("Erro al guardar tearea - " + e.getMessage());
            }

            System.out.print("¿Quieres crear otra tarea? (s/n) ");
            String resp = scanner.nextLine().trim().toUpperCase();
            if(resp.equals("N")) {
                break;
            }else if (!resp.equals("S")){
                System.err.println("¡respuesta inválida! solo 'S' para continuar y 'N' para finalizar.");
            }
        }
        return taskModelList;
    }

    private void viewTask(Scanner scanner) {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>VER TASK<<<<<<<<<<<<<<<<<<<<<<");
        TaskModel taskModel = new TaskModel();

        while (true) {
            System.out.print("Ingrese el ID: ");
            taskModel.setId(scanner.nextLong());
            scanner.nextLine();

            Optional<TaskModel> taskModelOptional = taskRepository.findById(taskModel.getId());
            if (taskModelOptional.isPresent()) {
                taskModel = taskModelOptional.get();
                System.out.println(taskModel);
                break;
            } else {
                System.out.println("No se encontró el ID de la tarea");
            }
        }
    }

    private void editTask(Scanner scanner) {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>EDITAR TAREA<<<<<<<<<<<<<<<<<<<<<<");
        TaskModel taskModel = new TaskModel();

        while (true) {
            System.out.println();
            System.out.print("Ingrese el ID de la tarea o '0' para salir: ");
            long id = 0;
            try {
                id = scanner.nextLong();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.err.println("¡ID inválido! Solo números.");
                scanner.nextLine();
                continue;
            }

            if (id == 0) {
                System.out.println("Operación cancelada.");
                return;
            }

            Optional<TaskModel> taskModelOptional = taskRepository.findById(id);
            if (taskModelOptional.isPresent()) {
                taskModel = taskModelOptional.get();

                System.out.print("Ingrese la descripción de la tarea: ");
                taskModel.setDescription(scanner.nextLine());

                while (true) {
                    System.out.println("Status de la tarea");
                    System.out.println("0. salir");
                    System.out.println("1. pending");
                    System.out.println("2. progress");
                    System.out.println("3. completed");
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
                            System.out.println("Operación cancelada.");
                            return;
                        case 1:
                            taskModel.setStatus(TaskEnum.pending);
                            break;
                        case 2:
                            taskModel.setStatus(TaskEnum.progress);
                            break;
                        case 3:
                            taskModel.setStatus(TaskEnum.completed);
                            break;
                        default:
                            System.out.println("¡La opción no es válida!");
                            continue;
                    }
                    break;
                }
                taskRepository.save(taskModel);
                System.out.println("Tarea editada exitosamente");
                break;
            } else {
                System.out.println("No se encontró el ID de la tarea");
                break;
            }
        }
    }

    private void deleteTask(Scanner scanner) {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>BORRAR TAREA<<<<<<<<<<<<<<<<<<<<<<");

        while (true) {
            System.out.println();
            System.out.print("Ingrese el ID de la tarea o '0' para salir: ");
            long id = 0;

            try {
                id = scanner.nextLong();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.err.println("¡ID inválido! Solo números.");
                scanner.nextLine();
                continue;
            }

            if (id == 0) {
                System.out.println("Operación cancelada.");
                return;
            }

            Optional<TaskModel> taskModelOptional = taskRepository.findById(id);
            if (taskModelOptional.isPresent()) {
                taskRepository.deleteById(id);
                System.out.println("Tarea eliminada exitosamente");
                break;
            } else {
                System.out.println("No se encontró el ID de la tarea");
            }
        }
    }
}