# src/main.py
# Importar el decorador flow de Prefect
from prefect import flow

# Importar las tareas
from task.task_extract import task_extract_products
from task.task_load import task_load_products_baseline, task_load_products_update

TYPE_TASK = "update"  # Puede ser "baseline" o "update"

# Definir el flujo de trabajo
@flow(name="ETL Productos")
def main_flow(search_terms):
    for query in search_terms:
        # Ejecutar la tarea de extracción
        products = task_extract_products(query)  # Obtener el resultado de la tarea directamente
        
        # Cargar los productos en la base de datos según el tipo de tarea
        if TYPE_TASK == "baseline":
            task_load_products_baseline(products)
        elif TYPE_TASK == "update":
            task_load_products_update(products)

# Solo ejecutamos el main_flow si el archivo se llama como archivo principal
if __name__ == "__main__":
    # Solicitar al usuario que ingrese los términos de búsqueda, separados por comas
    user_input = input("Ingresa los términos de búsqueda separados por comas: ")
    
    # Dividir el input en una lista, eliminando espacios adicionales
    search_terms = [term.strip() for term in user_input.split(",") if term.strip()]
    
    # Ejecutar el flujo principal con los términos de búsqueda proporcionados
    main_flow(search_terms)
