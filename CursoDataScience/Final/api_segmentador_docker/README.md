# API de Segmentación de Clientes - Dockerizado

## 🚀 Cómo ejecutar

### 1. Construir la imagen Docker

```bash
docker build -t api-segmentador .
```

### 2. Ejecutar el contenedor

```bash
docker run -d -p 8000:8000 api-segmentador
```

### 3. Acceder a la documentación interactiva

```
http://localhost:8000/docs
```

## 📦 Requisitos

- Tener Docker instalado
- Colocar `Modelo_Segmentos.zip` en este mismo directorio antes de construir la imagen
