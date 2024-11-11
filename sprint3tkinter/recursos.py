import requests
from PIL import Image, ImageTk
from io import BytesIO


def descargar_imagen(url, size):
    try:

        response = requests.get(url)
        response.raise_for_status()
        image = Image.open(BytesIO(response.content))
        image = image.resize(size, Image.LANCZOS)

        return ImageTk.PhotoImage(image)


    except requests.exceptions.RequestException as e:
        print(f"Error al descargar la imagen: {e}")
        return None

