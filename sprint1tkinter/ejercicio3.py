import tkinter as tk

root =  tk.Tk()
root.title("Ejercicio 3")
root.geometry("300x400")

def saludo():
    nombre = entrada.get()  #Con .get() se puede obtener lo que tiene el Entry escrito.
    etiqueta_saludo.config(text=f"Buenos días {nombre}!")

etiqueta_titulo = tk.Label(root, text="Escriba su nombre:")
etiqueta_titulo.pack(pady=5)

entrada = tk.Entry(root, width=40)
entrada.pack()

boton = tk.Button(root, command=saludo, text="Buenos días!", bg="orange", fg="black")
boton.pack(pady=30)

etiqueta_saludo = tk.Label(root, text="")
#La etiqueta se coloca primero vacía, y luego al llamar a saludo() se modifica.
etiqueta_saludo.pack(pady=15)


root.mainloop()