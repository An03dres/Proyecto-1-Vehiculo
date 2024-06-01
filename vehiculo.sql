CREATE DATABASE vehiculo;
USE vehiculo;

CREATE TABLE Vehiculo (
    id INT AUTO_INCREMENT PRIMARY KEY,
    tipo VARCHAR(255),
    marca VARCHAR(255),
    potencia INT,
    fechaCompra DATE
);

INSERT INTO Vehiculo (tipo, marca, potencia, fechaCompra) VALUES 
('coche', 'Toyota', 120, '2020-01-01'),
('moto', 'Honda', 140, '2019-05-15'),
('camion', 'Ford', 200, '2018-07-30'),
('coche', 'Chevrolet', 150, '2021-03-22'),
('moto', 'BMW', 250, '2020-08-17'),
('van', 'Volkswagen', 110, '2019-11-02'),
('avion', 'Mercedes', 180, '2018-12-25'),
('barco', 'Subaru', 130, '2019-06-14'),
('helicoptero', 'Nissan', 160, '2021-01-10'),
('jet', 'Hyundai', 140, '2020-05-18');

COMMIT;