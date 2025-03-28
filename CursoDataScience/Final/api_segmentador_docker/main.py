from fastapi import FastAPI, HTTPException
from pydantic import BaseModel
from Modelo_Segmentos.predictor import predecir_segmento

app = FastAPI(title="API de Segmentación de Clientes")

class Cliente(BaseModel):
    gender: str
    customer_type: str
    product_line: str
    unit_price: float
    quantity: int
    payment: str
    city: str
    branch: str
    hour: int
    weekday: int
    month: int
    avgprice: float

@app.post("/predict")
def obtener_segmento(cliente: Cliente):
    try:
        cliente_dict = cliente.dict()
        segmento = predecir_segmento(cliente_dict)
        if segmento is None:
            raise HTTPException(status_code=400, detail="No se pudo asignar un segmento al cliente")
        return {"segmento": segmento}
    except Exception as e:
        raise HTTPException(status_code=500, detail=str(e))
