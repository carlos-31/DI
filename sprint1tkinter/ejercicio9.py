import tkinter as tk
from tkinter import messagebox

root =  tk.Tk()
root.title("Ejercicio 9")
root.geometry("300x300")

#Menu principal
menu = tk.Menu(root)
root.config(menu=menu)

#Crear el menu de Archivo, y agregarlo dentro de menu
menu_archivo = tk.Menu(menu, tearoff=0)
menu.add_cascade(label="Archivo", menu=menu_archivo)
menu_archivo.add_command(label="Abrir")
menu_archivo.add_command(label="Salir",command=root.quit)

def mensaje_ayuda():
    #hace que salga una nueva ventana.
    #messagebox.showinfo("titulo de la página","contenido de la página")
    messagebox.showinfo("Ayuda","Esta es una ventana nueva!")


#Crear el menu de Ayuda y agregarlo dentro del menu principal
menu_ayuda = tk.Menu(menu, tearoff=0)
menu.add_cascade(label="Ayuda", menu=menu_ayuda)
menu_ayuda.add_command(label="Acerca de ...", command=mensaje_ayuda)


root.mainloop()
