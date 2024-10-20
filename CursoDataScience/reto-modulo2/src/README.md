# ETL Productos con Prefect

Este proyecto realiza un flujo de trabajo ETL (Extracción, Transformación y Carga) para extraer productos de la página TiendaMania, procesar la información y cargar los datos en una base de datos MySQL utilizando Prefect.

## Estructura del Proyecto

- `task/task_extract.py`: Contiene la tarea para extraer los productos desde la web.
- `task/task_load.py`: Contiene las tareas para cargar los productos en la base de datos.
- `main.py`: Archivo principal para ejecutar el flujo de trabajo.
- `.env`: Archivo de configuración con las credenciales de la base de datos.

## Requisitos

- Python 3.8 o superior
- Prefect
- BeautifulSoup
- Requests
- MySQL Connector
- MySQL Server

## Ejecución

**Inicia Prefect para visualizar el flujo de trabajo:**

   Antes de ejecutar el flujo, es importante iniciar Prefect Orion para monitorear el progreso del ETL. Ejecuta el siguiente comando en la terminal:

   prefect server start

**Ejecuta el script principal:**

  cd src
  python main.py

**El programa te pedirá que ingreses los términos de búsqueda, separados por comas. Por ejemplo:**

  Ingresa los términos de búsqueda separados por comas: tablet