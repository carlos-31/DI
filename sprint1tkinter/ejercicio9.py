import tkinter as tk
from tkinter import messagebox

root =  tk.Tk()
root.title("Ejercicio 9")
root.geometry("300x300")

menu = tk.Menu(root)
root.config(menu=menu)

menu_archivo = tk.Menu(menu, tearoff=0)
menu.add_cascade(label="Archivo", menu=menu_archivo)
menu_archivo.add_command(label="Abrir")
menu_archivo.add_command(label="Salir",command=root.quit)

def mensaje_ayuda():
    messagebox.showinfo("Ayuda","Esta es una ventana nueva!")

menu_ayuda = tk.Menu(menu, tearoff=0)
menu.add_cascade(label="Ayuda", menu=menu_ayuda)
menu_ayuda.add_command(label="Acerca de ...", command=mensaje_ayuda)


root.mainloop()
