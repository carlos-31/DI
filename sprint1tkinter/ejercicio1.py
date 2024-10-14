import tkinter as tk

root =  tk.Tk()
root.title("Ejercicio 1")
root.geometry("300x400")

etiqueta_1 = tk.Label(root, text="Bienvenido!")
etiqueta_1.pack()
etiqueta_2 = tk.Label(root, text="Soy carlos.")
etiqueta_2.pack()
etiqueta_3 = tk.Label(root, text="Texto antes de hacer click en el botón.")
etiqueta_3.pack()

def cambiar_mensaje_3():
    etiqueta_3.config(text="Texto luego de pisar el botón")

boton = tk.Button(root, text="Haz click!" , command=cambiar_mensaje_3, bg="lightgreen", fg="black")
boton.pack(pady=25)


root.mainloop()