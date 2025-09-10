# Trabajo Práctico Especial :computer:

- [x] Proyecto Java orientado a la persistencia de datos usando **JDBC** y **DAO pattern**.
- [x] Gestión de entidades (Productos, Compras, etc.) conectadas a una base de datos **MySQL**.

---

## Tabla de Contenidos
- [Descripción](#descripción)
- [Arquitectura](#arquitectura)
- [Requisitos](#requisitos)
- [Instalación](#instalación)
- [Uso](#uso)
- [Estructura del Proyecto](#estructura-del-proyecto)
- [Autores](#autores)

---

## Descripción
Este proyecto corresponde al **Trabajo Práctico Especial** de la materia.  
Se centra en la implementación de un **sistema CRUD** para la gestión de productos y compras usando **Java, JDBC y MySQL**.  

El objetivo es aprender a:
- Conectarse a una base de datos.  
- Manipular entidades con **DAO (Data Access Object)**.  
- Ejecutar operaciones **CRUD (Create, Read, Update, Delete)**.  

---

## Arquitectura
- **Lenguaje:** Java 22  
- **Persistencia:** JDBC con MySQL | Derby | Postgres  
- **Gestión de dependencias:** Maven  
- **Patrón usado:** DAO  

---

## Requisitos
Antes de instalar el proyecto, asegurate de tener:  
- [Java JDK 22+]
- [Apache Maven](https://maven.apache.org/)  
- [MySQL 8]
- Docker (opcional, si querés levantar la DB en contenedor)  

---

## Instalación

1. Clonar el repositorio:
```bash
git clone https://github.com/MatiasBavacc/TrabajoPracticoEspecial.git
cd TrabajoPracticoEspecial
```

2. Crear la base de datos (ejemplo):
```sql
CREATE DATABASE integrador1;
```

## Uso

## Estructura del Proyecto
```
src/
 └── main/java/dao         # Clases DAO (ProductoDao, CompraDao, etc.)
 └── main/java/entities    # Entidades del sistema (Producto, Compra)
 └── main/java/factory     # Conexion con la db
 └── main/java/main        # Pruebas de funcionamiento
 └── main/utils            # Utilidades para llenar la db
 └── main/resources        # Archivos de configuración (scv)
```

---

## Autores

| Nombre    | Apellido | DNI         | E-mail                        | Sede   |
|-----------|----------|-------------|-------------------------------|--------|
| Matías    | Bava     | 38.961.362  | matiasbavacc@gmail.com        | Tandil |
| Alejandro | Garay    | 18.038.228  | alejandrogaray1966@gmail.com  | Tandil |
| Agustin   | Rypstra  | 42117730    | agusrypstra@gmail.com         | Tandil |
| Diego     | Navarro  | ********    | *********************         | Tandil |
