import tkinter as tk


class VistaNotas:
    def __init__(self, root):
        self.root = root
        self.root.title("Aplicación de gestión de notas")
        self.root.geometry("450x850")

        self.etiqueta_coords = tk.Label(root, text="Coordenadas click: ")
        self.etiqueta_coords.pack(pady=5)

        self.etiqueta = tk.Label(root, text="Notas:")
        self.etiqueta.pack(pady=15)

        self.lista = tk.Listbox(root)
        self.lista.pack(pady=10)

        self.entrada = tk.Entry(root)
        self.entrada.pack(pady=10)

        self.boton_agregar = tk.Button(root, text="Agregar nota")
        self.boton_agregar.pack(pady=5)

        self.boton_eliminar = tk.Button(root, text="Eliminar nota")
        self.boton_eliminar.pack(pady=5)

        self.boton_guardar = tk.Button(root, text="Guardar en archivo")
        self.boton_guardar.pack(pady=5)

        self.boton_cargar = tk.Button(root, text="Cargar desde archivo")
        self.boton_cargar.pack(pady=5)

        self.boton_descargar = tk.Button(root, text="Descargar imagen")
        self.boton_descargar.pack(pady=15)

        self.etiqueta_img = tk.Label(root)
        self.etiqueta_img.pack(pady=15)
