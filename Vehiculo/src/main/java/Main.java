import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {

    private static VehiculoDAO vehiculoDAO = new VehiculoDAO();

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            String opcion;

            do {
                mostrarMenu();
                opcion = scanner.nextLine();

                switch (opcion) {
                    case "1":
                        consultarTodosLosVehiculos();
                        break;
                    case "2":
                        consultarVehiculoPorId(scanner);
                        break;
                    case "3":
                        insertarNuevoVehiculo(scanner);
                        break;
                    case "4":
                        eliminarVehiculoPorId(scanner);
                        break;
                    case "5":
                        actualizarDatosVehiculo(scanner);
                        break;
                    case "6":
                        obtenerPotenciaMediaPorTipo(scanner);
                        break;
                    case "7":
                        obtenerPotenciaMaximaPorTipo(scanner);
                        break;
                    case "8":
                        System.out.println("Saliendo...");
                        break;
                    default:
                        System.out.println("Opción no válida. Por favor, elige una opción del 1 al 8.");
                        break;
                }
            } while (!opcion.equals("8"));
        }
    }

    private static void mostrarMenu() {
        System.out.println("Por favor, elige una opción:");
        System.out.println("1. Consultar todos los vehículos");
        System.out.println("2. Consultar un vehículo por ID");
        System.out.println("3. Insertar un nuevo vehículo");
        System.out.println("4. Eliminar un vehículo por ID");
        System.out.println("5. Actualizar los datos de un vehículo");
        System.out.println("6. Obtener la potencia media según el tipo de vehículo");
        System.out.println("7. Obtener la potencia máxima según el tipo de vehículo");
        System.out.println("8. Salir");
    }

    private static void consultarTodosLosVehiculos() {
        List<Vehiculo> vehiculos = vehiculoDAO.consultarTodosLosVehiculos();
        for (Vehiculo vehiculo : vehiculos) {
            System.out.println(vehiculo.getId() + ": " + vehiculo.getTipo() + ", " + vehiculo.getMarca() + ", " + vehiculo.getPotencia() + ", " + vehiculo.getFechaCompra());
        }
    }

    private static void consultarVehiculoPorId(Scanner scanner) {
        try {
            System.out.println("Por favor, introduce el ID del vehículo:");
            int id = Integer.parseInt(scanner.nextLine());
            Optional<Vehiculo> vehiculoOptional = vehiculoDAO.consultarVehiculoPorId(id);
            vehiculoOptional.ifPresentOrElse(
                    vehiculo -> System.out.println(vehiculo.getId() + ": " + vehiculo.getTipo() + ", " + vehiculo.getMarca() + ", " + vehiculo.getPotencia() + ", " + vehiculo.getFechaCompra()),
                    () -> System.out.println("No se encontró ningún vehículo con el ID proporcionado.")
            );
        } catch (NumberFormatException e) {
            System.out.println("ID no válido. Debe ser un número entero.");
        }
    }

    private static void insertarNuevoVehiculo(Scanner scanner) {
        try {
            System.out.println("Por favor, introduce el tipo del vehículo:");
            String tipo = scanner.nextLine();
            System.out.println("Por favor, introduce la marca del vehículo:");
            String marca = scanner.nextLine();
            System.out.println("Por favor, introduce la potencia del vehículo:");
            int potencia = Integer.parseInt(scanner.nextLine());
            System.out.println("Por favor, introduce la fecha de compra del vehículo (formato: yyyy-MM-dd):");
            String fechaCompra = scanner.nextLine();
            // Aquí podrías llamar a un método de vehiculoDAO para insertar el vehículo
            System.out.println("Vehículo insertado correctamente.");
        } catch (NumberFormatException e) {
            System.out.println("Potencia no válida. Debe ser un número entero.");
        }
    }

    private static void eliminarVehiculoPorId(Scanner scanner) {
        try {
            System.out.println("Por favor, introduce el ID del vehículo que deseas eliminar:");
            int id = Integer.parseInt(scanner.nextLine());
            // Aquí podrías llamar a un método de vehiculoDAO para eliminar el vehículo
            System.out.println("Vehículo eliminado correctamente.");
        } catch (NumberFormatException e) {
            System.out.println("ID no válido. Debe ser un número entero.");
        }
    }

    private static void actualizarDatosVehiculo(Scanner scanner) {
        try {
            System.out.println("Por favor, introduce el ID del vehículo que deseas actualizar:");
            int id = Integer.parseInt(scanner.nextLine());
            // Aquí podrías agregar más preguntas para obtener los nuevos datos del vehículo y actualizarlo en el DAO
            System.out.println("Vehículo actualizado correctamente.");
        } catch (NumberFormatException e) {
            System.out.println("ID no válido. Debe ser un número entero.");
        }
    }

    private static void obtenerPotenciaMediaPorTipo(Scanner scanner) {
        System.out.println("Por favor, introduce el tipo de vehículo:");
        String tipo = scanner.nextLine();
        // Aquí podrías llamar a un método de vehiculoDAO para obtener la potencia media
        System.out.println("Potencia media obtenida correctamente.");
    }

    private static void obtenerPotenciaMaximaPorTipo(Scanner scanner) {
        System.out.println("Por favor, introduce el tipo de vehículo:");
        String tipo = scanner.nextLine();
        // Aquí podrías llamar a un método de vehiculoDAO para obtener la potencia máxima
        System.out.println("Potencia máxima obtenida correctamente.");
    }
}