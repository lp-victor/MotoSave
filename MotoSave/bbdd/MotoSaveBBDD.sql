DROP DATABASE IF EXISTS MotoSaveBBDD;
CREATE DATABASE MotoSaveBBDD;
USE MotoSaveBBDD;


CREATE TABLE garajes (
    idGaraje INT AUTO_INCREMENT PRIMARY KEY,
    sucursal VARCHAR(25),
    plazas INT DEFAULT 10 CHECK (plazas >= 0 AND plazas <= 10)
);

CREATE TABLE motos (
    idGaraje INT,
    matricula VARCHAR(7),
    marca VARCHAR(15),
    modelo VARCHAR(15),
    color VARCHAR(15),
    cc INT,
    precio INT,
    PRIMARY KEY (idGaraje, matricula),
    FOREIGN KEY (idGaraje) REFERENCES garajes(idGaraje)
);

CREATE TABLE usuarios (
    admin BOOLEAN,
    idUsuario INT AUTO_INCREMENT PRIMARY KEY,
    password VARCHAR(20),
    nombre VARCHAR(20),
    apellido VARCHAR(15),
    idGaraje INT,
    FOREIGN KEY (idGaraje) REFERENCES garajes(idGaraje)
);

-- Index para las PK's (no se pueden crear las tablas de abajo si no, no entiendo por qué)

CREATE INDEX idx_idUsuario ON usuarios (idUsuario);
CREATE INDEX idx_idGaraje ON garajes (idGaraje);
CREATE INDEX idx_matricula ON motos (matricula);

CREATE TABLE ventas (
    idUsuario INT,
    idGaraje INT,
    matricula VARCHAR(7),
    precio INT,
    fecha DATE,
    PRIMARY KEY (idUsuario, idGaraje, matricula),
    FOREIGN KEY (idUsuario) REFERENCES usuarios(idUsuario),
    FOREIGN KEY (idGaraje) REFERENCES garajes(idGaraje),
    FOREIGN KEY (matricula) REFERENCES motos(matricula)
);

CREATE TABLE motos_vendidas (
    idGaraje INT,
    matricula VARCHAR(7),
    marca VARCHAR(15),
    modelo VARCHAR(15),
    color VARCHAR(15),
    cc INT,
    precio INT,
    PRIMARY KEY (idGaraje, matricula),
    FOREIGN KEY (idGaraje) REFERENCES garajes(idGaraje)
);


-- Trigger para actualizar las plazas -1

DELIMITER $$
CREATE TRIGGER actualizarPlazas
AFTER INSERT ON motos
FOR EACH ROW
BEGIN
    IF NEW.idGaraje IS NOT NULL THEN
        UPDATE garajes
        SET plazas = plazas - 1
        WHERE idGaraje = NEW.idGaraje;
    END IF;
END$$
DELIMITER ;

-- Trigger para actualizar las plazas +1

DELIMITER $$
CREATE TRIGGER restaurarPlazas
AFTER DELETE ON motos
FOR EACH ROW
BEGIN
    IF OLD.idGaraje IS NOT NULL THEN
        UPDATE garajes
        SET plazas = plazas + 1
        WHERE idGaraje = OLD.idGaraje;
    END IF;
END$$
DELIMITER ;

-- Trigger para añadir las motos vendidas a la tabla motos_vendidas

DELIMITER $$

CREATE TRIGGER after_delete_moto
AFTER DELETE
ON motos FOR EACH ROW
BEGIN
    -- Desactivar la comprobación de claves foráneas
    SET foreign_key_checks = 0;

    INSERT INTO motos_vendidas (idGaraje, matricula, marca, modelo, color, cc, precio)
    VALUES (OLD.idGaraje, OLD.matricula, OLD.marca, OLD.modelo, OLD.color, OLD.cc, OLD.precio);

    -- Volver a activar la comprobación de claves foráneas
    SET foreign_key_checks = 1;
END;

$$

DELIMITER ;

-- Trigger para añadir las motos vendidas a la tabla motos_vendidas

DELIMITER $$
CREATE TRIGGER after_insert_venta
AFTER INSERT
ON ventas FOR EACH ROW
BEGIN
    -- Variables para almacenar los valores de la moto vendida
DELIMITER $$
CREATE TRIGGER after_insert_venta
AFTER INSERT
ON ventas FOR EACH ROW
BEGIN

    -- Desactivar la comprobación de claves foráneas
    SET foreign_key_checks = 0;

    DELETE FROM motos
    WHERE idGaraje = NEW.idGaraje AND matricula = NEW.matricula;    

    -- Volver a activar la comprobación de claves foráneas
    SET foreign_key_checks = 1;
END;
$$
DELIMITER ;

-- Insters a garajes

INSERT INTO garajes(sucursal) VALUES ('Madrid');
INSERT INTO garajes(sucursal) VALUES ('Sevilla');
INSERT INTO garajes(sucursal) VALUES ('Barcelona');

-- Inserts motos

INSERT INTO motos VALUES (1, '1234AAA', 'Honda', 'CBR', 'Negro', 1250, 2500);
INSERT INTO motos VALUES (2, '1234BBB', 'Kawasaki', 'Ninja', 'Blanco', 1000, 2000);
INSERT INTO motos VALUES (3, '1234CCC', 'Yamaha', ' YZF-R1', 'Azul', 750, 1500);
INSERT INTO motos VALUES (2, '1234DDD', 'Ducati', 'Panigale', 'Rojo', 1300, 2600);
INSERT INTO motos VALUES (3, '1234EEE', 'Ducati', 'Panigale', 'Amarillo', 1300, 2600);
INSERT INTO motos VALUES (1, '1234FFF', 'Kawasaki', 'Ninja', 'Verde', 1000, 2000);
INSERT INTO motos VALUES (1, '1234GGG', 'Honda', 'CBR', 'Rojo', 1250, 2500);
INSERT INTO motos VALUES (3, '1234HHH', 'Yamaha', ' YZF-R1', 'Azul', 750, 1500);
INSERT INTO motos VALUES (2, '1234III', 'Honda', 'CBR', 'Gris', 1250, 2500);

-- Inserts usuarios

INSERT INTO usuarios VALUES (true, 0, 'admin', 'Juan', 'Bautista', null);
INSERT INTO usuarios(admin, password, nombre, apellido, idGaraje) VALUES (false, '1234', 'Luis', 'García', 1);
INSERT INTO usuarios(admin, password, nombre, apellido, idGaraje) VALUES (false, '1234', 'Alberto', 'Jimenez', 2);
INSERT INTO usuarios(admin, password, nombre, apellido, idGaraje) VALUES (false, '1234', 'Manuel', 'Perez', 3);
