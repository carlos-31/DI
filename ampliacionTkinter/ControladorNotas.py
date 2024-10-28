import threading
import tkinter as tk
from tkinter import messagebox
import requests
from io import BytesIO
from PIL import Image, ImageTk


class ControladorNotas:
    def __init__(self, modelo, vista):
        self.modelo = modelo
        self.vista = vista

        self.vista.boton_agregar.config(command=self.agregar_nota)
        self.vista.boton_eliminar.config(command=self.eliminar_nota)
        self.vista.boton_guardar.config(command=self.guardar_notas)
        self.vista.boton_cargar.config(command=self.cargar_notas)
        self.vista.boton_descargar.config(command=self.iniciar_descarga)

        self.vista.root.bind("<Button-1>", self.actualizar_coordenadas)

    def agregar_nota(self):
        nueva_nota = self.vista.entrada.get()
        self.vista.entrada.delete(0, tk.END)
        if nueva_nota:  # checks not empty
            self.modelo.agregar_nota(nueva_nota)
            self.actualizar_listbox(self.modelo.obtener_notas())

    def eliminar_nota(self):
        seleccion = self.vista.lista.curselection()
        if seleccion:
            self.modelo.eliminar_nota(seleccion[0])
            self.actualizar_listbox(self.modelo.obtener_notas())

    def guardar_notas(self):
        self.modelo.guardar_notas()
        messagebox.showinfo("Aviso", "Se han guardado las notas en el archivo.")

    def cargar_notas(self):
        self.modelo.cargar_notas()
        self.actualizar_listbox(self.modelo.obtener_notas())
        messagebox.showinfo("Aviso", "Se han cargado las notas desde el archivo.")

    def descargar_imagen(self, url, callback):
        try:
            respuesta = requests.get(url)
            respuesta.raise_for_status()
            imagen = Image.open(BytesIO(respuesta.content))
            imagen_tk = ImageTk.PhotoImage(imagen)
            self.vista.root.after(0, callback, imagen_tk)
        except requests.exceptions.RequestException as e:
            print(f"Error al descargar la imagen: {e}")
            self.vista.root.after(0, callback, None)

    def actualizar_etiqueta(self, imagen_tk):
        if imagen_tk:
            self.vista.etiqueta_img.config(image=imagen_tk)
            self.vista.etiqueta_img.image = imagen_tk
        else:
            self.vista.etiqueta_img.config(text="Error al descargar la imagen.")

    def iniciar_descarga(self):
        url = 'https://raw.githubusercontent.com/Marcos-Rama/DI/refs/heads/main/fototkinter.jpg'
        # uso la imagen de marcos ya que la mia da error por ser de un repositorio privado.
        hilo = threading.Thread(target=self.descargar_imagen, args=(url, self.actualizar_etiqueta))
        hilo.start()

    def actualizar_coordenadas(self, event):
        self.vista.etiqueta_coords.config(text=f"Coordenadas click: {event.x}, {event.y}")

    def actualizar_listbox(self, notas):
        self.vista.lista.delete(0, tk.END)
        for nota in notas:
            self.vista.lista.insert(tk.END, nota)
