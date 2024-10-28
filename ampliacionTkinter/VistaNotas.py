import tkinter as tk

from PIL import Image, ImageTk


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

        self.imagen = Image.open('C:\\Users\\carlo_xldsprr\\Desktop\\DAM2\\cosas\\python\\mvc\\lightweaver.jpg')
        self.imagen_redimensionada = self.imagen.resize((200, 200), Image.Resampling.LANCZOS)
        self.imagen_tk = ImageTk.PhotoImage(self.imagen_redimensionada)
        self.etiqueta_img = tk.Label(root, image=self.imagen_tk)
        self.etiqueta_img.pack(pady=15)






#cosa = tk.Tk()#
#vista = VistaNotas(cosa)
#cosa.mainloop()

'''self.listbox.delete(0, tk.END)
        for nota in notas:
            self.listbox.insert(tk.END, nota)'''
