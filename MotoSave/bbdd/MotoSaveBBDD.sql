DROP DATABASE IF EXISTS MotoSaveBBDD;
CREATE DATABASE MotoSaveBBDD;
USE MotoSaveBBDD;

-- Crear la tabla 'garaje'
CREATE TABLE garaje (
    idGaraje INT AUTO_INCREMENT PRIMARY KEY,
    nombreGaraje VARCHAR(25),
    plazas INT CHECK (plazas <= 30)
);

-- Crear la tabla 'moto' con la clave foránea 'idGaraje' referenciando la tabla 'garaje'
CREATE TABLE moto (
    idGaraje INT,
    Matricula VARCHAR(7),
    marca VARCHAR(15),
    modelo VARCHAR(15),
    color VARCHAR(15),
    cc INT CHECK (cc <= 4),
    PRIMARY KEY (idGaraje, Matricula),
    FOREIGN KEY (idGaraje) REFERENCES garaje(idGaraje)
);


-- Trigger para actualizar las plazas -1

DELIMITER $$
CREATE TRIGGER actualizarPlazas
AFTER INSERT ON moto
FOR EACH ROW
BEGIN
    IF NEW.idGaraje IS NOT NULL THEN
        UPDATE garaje
        SET plazas = plazas - 1
        WHERE idGaraje = OLD.idGaraje;
    END IF;
END$$
DELIMITER ;

-- Trigger para actualizar las plazas +1

DELIMITER $$
CREATE TRIGGER restaurarPlazas
AFTER DELETE ON moto
FOR EACH ROW
BEGIN
    IF OLD.idGaraje IS NOT NULL THEN
        UPDATE garaje
        SET plazas = plazas + 1
        WHERE idGaraje = OLD.idGaraje;
    END IF;
END$$
DELIMITER ;