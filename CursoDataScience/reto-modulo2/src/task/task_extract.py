# src/task/task_extract.py
import requests
from bs4 import BeautifulSoup
from prefect import task, get_run_logger

@task(name="Extraer productos de TiendaMania")
def task_extract_products(query):
    # Obtener el logger de Prefect para el registro
    logger = get_run_logger()

    # URL de la página de búsqueda
    url = f"https://tiendamia.com/pe/search?amzs={query}"
    logger.info(f"Extrayendo productos de la URL: {url}")

    response = requests.get(url)
    if response.status_code != 200:
        logger.error(f"Error al acceder a la URL, código de estado: {response.status_code}")
        return []
    
    html_doc = response.text
    html = BeautifulSoup(html_doc, "html.parser")

    # Encontrar todos los divs de productos
    product_list = html.find_all("div", {"class": "item button-border"})
    logger.info(f"Se encontraron {len(product_list)} productos en la página.")

    products = []

    for index, product in enumerate(product_list, start=1):
        try:
            # Extraer la marca
            brand = product.find("div", {"class": "item-brand"})
            brand_text = brand.get_text(strip=True) if brand else "N/A"

            # Extraer el nombre del producto
            name = product.find("div", {"class": "item-name"})
            name_text = name.get_text(strip=True) if name else "N/A"

            # Extraer el precio en moneda local
            currency_price = product.find("span", {"class": "currency_price"})
            currency_price_text = currency_price.get_text(strip=True) if currency_price else "N/A"

            # Extraer el precio en dólares
            dollar_price = product.find("span", {"class": "dollar_price"})
            dollar_price_text = dollar_price.get_text(strip=True) if dollar_price else "N/A"

            # Agregar la información del producto a la lista de resultados
            products.append({
                "brand": brand_text,
                "name": name_text,
                "currency_price": currency_price_text,
                "dollar_price": dollar_price_text
            })

            # Registrar la información del producto extraído
            logger.info(f"Producto {index}: Marca={brand_text}, Nombre={name_text}, "
                        f"Precio en moneda local={currency_price_text}, Precio en dólares={dollar_price_text}")
        
        except Exception as e:
            # Registrar cualquier error que ocurra durante la extracción de un producto
            logger.error(f"Error al procesar el producto {index}: {e}")

    # Registrar la cantidad total de productos extraídos
    logger.info(f"Extracción completada. Total de productos extraídos: {len(products)}")

    return products
