# src/task/task_load.py
import os
from dotenv import load_dotenv
from mysql import connector
from prefect import task

load_dotenv()

config = {
    "host": "localhost",
    "user": "root",
    "password": os.getenv("DB_PASSWORD"),
    "database": "db_codigo"
}

@task(name="Limpieza y carga de productos en la BD")
def task_load_products_baseline(products):
    with connector.connect(**config) as db:
        with db.cursor() as cursor:
            try:
                cursor.execute("DROP TABLE IF EXISTS reto2_productos")
                db.commit()

                cursor.execute("""
                    CREATE TABLE IF NOT EXISTS reto2_productos(
                        id INT PRIMARY KEY AUTO_INCREMENT,
                        brand VARCHAR(200),
                        name VARCHAR(200) UNIQUE,
                        currency_price VARCHAR(50),
                        dollar_price VARCHAR(50)
                    )
                """)
                db.commit()
            except Exception as error:
                print("Error al crear la tabla:", error)

            query_insert = """
                INSERT INTO reto2_productos (brand, name, currency_price, dollar_price)
                VALUES (%s, %s, %s, %s)
            """
            try:
                # Asegúrate de que los productos se pasen como una lista de tuplas
                values = [(p["brand"], p["name"], p["currency_price"], p["dollar_price"]) for p in products]
                cursor.executemany(query_insert, values)
                db.commit()
            except Exception as error:
                print("Error al insertar los datos:", error)

@task(name="Cargar nuevos productos en la BD")
def task_load_products_update(products):
    with connector.connect(**config) as db:
        with db.cursor() as cursor:
            query_insert = """
                INSERT INTO reto2_productos (brand, name, currency_price, dollar_price)
                VALUES (%s, %s, %s, %s)
            """
            for product in products:
                try:
                    cursor.execute(query_insert, (product["brand"], product["name"], product["currency_price"], product["dollar_price"]))
                    db.commit()
                except Exception as error:
                    print("Error al insertar el producto:", error)
