import tkinter as tk

root =  tk.Tk()
root.title("Ejercicio 7")
root.geometry("330x220")


def mostrar_entrada():
    contenido = entrada.get()
    etiqueta_entrada.config(text=contenido)

def borrar_entrada():
    entrada.delete(0,tk.END)


frame_1 = tk.Frame(root, bg="lightsalmon", bd=2, width=400, height=200)
frame_1.grid(row=0, column=0, padx=10, pady=10, sticky='ew')

frame_2 = tk.Frame(root, bg="pink", bd=2, width=400, height=200)
frame_2.grid(row=1, column=0, padx=10, pady=10)



etiqueta_1 = tk.Label(frame_1, bg="lightsalmon", text="Esto abajo es un \"Entry\"")
etiqueta_1.grid(row=0, column=0, padx=10, pady=10)

etiqueta_2 = tk.Label(frame_1, bg="lightsalmon", text="Deberias escribir algo!")
etiqueta_2.grid(row=0, column=1, padx=10, pady=10)

entrada = tk.Entry(frame_1, width=30)
entrada.grid(row=1, column=0, columnspan=2, padx=10, pady=10)



boton_mostrar = tk.Button(frame_2, bg="#f9c5e9", text="Mostrar Contenido", command=mostrar_entrada)
boton_mostrar.grid(row=0, column=0, padx=10, pady=10)

boton_borrar = tk.Button(frame_2, bg="#f9c5e9", text="Borrar Contenido", command=borrar_entrada)
boton_borrar.grid(row=0, column=1, padx=10, pady=10)

etiqueta_entrada = tk.Label(frame_2, text="", bg="pink", width=40)
etiqueta_entrada.grid(row=1, column=0, columnspan=2, padx=10, pady=10)



root.mainloop()