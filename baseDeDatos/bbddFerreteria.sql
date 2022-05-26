DROP DATABASE IF EXISTS ferreteria;
CREATE DATABASE ferreteria DEFAULT CHARACTER SET utf8 COLLATE utf8_spanish_ci;
CREATE USER IF NOT EXISTS 'ferreteria'@'localhost' IDENTIFIED BY 'ferreteria2022';
GRANT ALL ON ferreteria.* TO 'ferreteria'@'localhost';

use ferreteria;

CREATE TABLE producto (
    codigo INT NOT NULL AUTO_INCREMENT,
    nombre varchar(20) NOT NULL UNIQUE,
    precio_compra decimal(8,2) NOT NULL,
    precio_venta decimal(8,2) NOT NULL,
    stock int NOT NULL,
    PRIMARY KEY (codigo)
) ENGINE=InnoDB;

INSERT INTO producto VALUES
(1,'Tuerca',0.1,0.15,1200),
(2,'Tornillo',0.2,0.27,1400),
(3,'Destornillador',3.75,4.5,150),
(4,'Llave Inglesa',7.95,8.9,75),
(5,'Arandelas',0.1,0.25,125),
(6,'Llave Allen',5.00,6.00,20),
(7,'Corta Fríos',87.75,99.25,25),
(8,'Tuerca Oval',0.10,0.15,1200),
(9,'Rollo Alambre 10m.',10.00,15.00,120);

/*Procedimiento insertarProducto*/
DELIMITER $$
CREATE DEFINER=ferreteria@localhost PROCEDURE 
insertarProducto(codigo CHAR(5), nombre VARCHAR(20), precio_compra DECIMAL(8,2),
precio_venta DECIMAL(8,2), stock INT)
BEGIN
    INSERT INTO producto
    VALUES (codigo, nombre, precio_compra, precio_venta, stock);
END$$
DELIMITER ;

/*Procedimiento eliminarProducto->elimina el producto de código pasado parám.*/
DELIMITER $$
CREATE DEFINER=ferreteria@localhost PROCEDURE eliminarProducto(codigo CHAR(5))
BEGIN
    DELETE from producto WHERE producto.codigo=codigo;
END$$
DELIMITER ;

/*Procedimiento modificarProducto->modifica el producto del código pasado parám.*/
DELIMITER $$
CREATE DEFINER=ferreteria@localhost PROCEDURE 
modificarProducto(codigo CHAR(5), nombre VARCHAR(20), precio_compra DECIMAL(8,2),
precio_venta DECIMAL(8,2), stock INT)
BEGIN
    UPDATE producto SET 
        producto.codigo=codigo, producto.nombre=nombre,
        producto.precio_compra=precio_compra,
        producto.precio_venta=precio_venta,
        producto.stock=stock
    WHERE producto.codigo=codigo;
END$$
DELIMITER ;

/*Función numProductos-> devuelve número total productos*/
DELIMITER $$
CREATE DEFINER=ferreteria@localhost FUNCTION numProductos()
RETURNS INT DETERMINISTIC
BEGIN
    DECLARE numProds INT;    
    SELECT count(*) FROM producto INTO numProds;
    RETURN numProds;
END$$
DELIMITER ;

/*Procedimiento obtenerProducto-> devuelve el producto de código pasado por parám.*/
DELIMITER $$
CREATE DEFINER=ferreteria@localhost PROCEDURE obtenerProducto(codigo CHAR(5))
BEGIN
    SELECT * FROM producto WHERE producto.codigo=codigo;
END$$
DELIMITER ;

/*Procedimiento obtenerProductos-> devuelve todos los proudctos*/
DELIMITER $$
CREATE DEFINER=ferreteria@localhost PROCEDURE obtenerProductos()
BEGIN
    SELECT * FROM producto;
END$$
DELIMITER ;

/*Procedimiento siguienteProducto-> devuelve el producto posterior al id
pasado como parámetro*/
DELIMITER $$
CREATE DEFINER=ferreteria@localhost PROCEDURE siguienteProducto(cod CHAR(5))
BEGIN
    SELECT * FROM producto WHERE codigo>cod ORDER BY codigo LIMIT 1;
END$$
DELIMITER ;

/*Procedimiento prevProducto-> devuelve el producto anterior al id
pasado como parámetro*/
DELIMITER $$
CREATE DEFINER=ferreteria@localhost PROCEDURE prevProducto(cod CHAR(5))
BEGIN
    SELECT * FROM producto WHERE codigo<cod ORDER BY codigo DESC LIMIT 1;
END$$
DELIMITER ;