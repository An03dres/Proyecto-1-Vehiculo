import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class VehiculoDAO {

    public List<Vehiculo> consultarTodosLosVehiculos() {
        List<Vehiculo> vehiculos = new ArrayList<>();
        Connection connection = DatabaseConnection.obtenerConexion();

        if (connection != null) {
            try (Statement statement = connection.createStatement()) {
                ResultSet resultSet = statement.executeQuery("SELECT * FROM Vehiculo");

                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String tipo = resultSet.getString("tipo");
                    String marca = resultSet.getString("marca");
                    int potencia = resultSet.getInt("potencia");
                    Date fechaCompra = resultSet.getDate("fechaCompra");

                    vehiculos.add(new Vehiculo(id, tipo, marca, potencia, fechaCompra));
                }
            } catch (SQLException e) {
                System.out.println("Error al consultar todos los vehículos: " + e.getMessage());
            }
        }

        return vehiculos;
    }

    public Optional<Vehiculo> consultarVehiculoPorId(int id) {
        Optional<Vehiculo> vehiculoOptional = Optional.empty();
        Connection connection = DatabaseConnection.obtenerConexion();

        if (connection != null) {
            try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Vehiculo WHERE id = ?")) {
                preparedStatement.setInt(1, id);
                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    String tipo = resultSet.getString("tipo");
                    String marca = resultSet.getString("marca");
                    int potencia = resultSet.getInt("potencia");
                    Date fechaCompra = resultSet.getDate("fechaCompra");

                    vehiculoOptional = Optional.of(new Vehiculo(id, tipo, marca, potencia, fechaCompra));
                }
            } catch (SQLException e) {
                System.out.println("Error al consultar el vehículo por su ID: " + e.getMessage());
            }
        }

        return vehiculoOptional;
    }

    public void insertarNuevoVehiculo(Vehiculo vehiculo) {
        Connection connection = DatabaseConnection.obtenerConexion();

        if (connection != null) {
            try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Vehiculo (tipo, marca, potencia, fechaCompra) VALUES (?, ?, ?, ?)")) {
                preparedStatement.setString(1, vehiculo.getTipo());
                preparedStatement.setString(2, vehiculo.getMarca());
                preparedStatement.setInt(3, vehiculo.getPotencia());
                preparedStatement.setDate(4, new java.sql.Date(vehiculo.getFechaCompra().getTime()));

                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                System.out.println("Error al insertar un nuevo vehículo: " + e.getMessage());
            }
        }
    }

    public void eliminarVehiculoPorId(int id) {
        Connection connection = DatabaseConnection.obtenerConexion();

        if (connection != null) {
            try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM Vehiculo WHERE id = ?")) {
                preparedStatement.setInt(1, id);

                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                System.out.println("Error al eliminar el vehículo por su ID: " + e.getMessage());
            }
        }
    }

    public void actualizarDatosDeVehiculo(Vehiculo vehiculo) {
        Connection connection = DatabaseConnection.obtenerConexion();

        if (connection != null) {
            try (PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Vehiculo SET tipo = ?, marca = ?, potencia = ?, fechaCompra = ? WHERE id = ?")) {
                preparedStatement.setString(1, vehiculo.getTipo());
                preparedStatement.setString(2, vehiculo.getMarca());
                preparedStatement.setInt(3, vehiculo.getPotencia());
                preparedStatement.setDate(4, new java.sql.Date(vehiculo.getFechaCompra().getTime()));
                preparedStatement.setInt(5, vehiculo.getId());

                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                System.out.println("Error al actualizar los datos de un vehículo: " + e.getMessage());
            }
        }
    }

    public double obtenerPotenciaMediaSegunTipo(String tipo) {
        double potenciaMedia = 0;
        Connection connection = DatabaseConnection.obtenerConexion();

        if (connection != null) {
            try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT AVG(potencia) AS potencia_media FROM Vehiculo WHERE tipo = ?")) {
                preparedStatement.setString(1, tipo);
                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    potenciaMedia = resultSet.getDouble("potencia_media");
                }
            } catch (SQLException e) {
                System.out.println("Error al obtener la potencia media según el tipo de vehículo: " + e.getMessage());
            }
        }

        return potenciaMedia;
    }

    public int obtenerPotenciaMaximaSegunTipo(String tipo) {
        int potenciaMaxima = 0;
        Connection connection = DatabaseConnection.obtenerConexion();

        if (connection != null) {
            try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT MAX(potencia) AS potencia_maxima FROM Vehiculo WHERE tipo = ?")) {
                preparedStatement.setString(1, tipo);
                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    potenciaMaxima = resultSet.getInt("potencia_maxima");
                }
            } catch (SQLException e) {
                System.out.println("Error al obtener la potencia máxima según el tipo de vehículo: " + e.getMessage());
            }
        }

        return potenciaMaxima;
    }
}
