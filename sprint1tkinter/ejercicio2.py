import tkinter as tk

root =  tk.Tk()
root.title("Ejercicio 2")
root.geometry("300x300")

def mostrar_mensaje():
    etiqueta = tk.Label(root, text="Mensaje muy importante del botón.")
    etiqueta.grid(row=1,column=0, columnspan=2)

def cerrar_todo():
    root.quit()

boton_mensaje = tk.Button(root, text="Mostrar mensaje" , command=mostrar_mensaje, bg="lightgreen", fg="black")
boton_mensaje.grid(row=0, column=0, padx=20, pady=30, sticky="e")

boton_cerrar = tk.Button(root, text="Cerrar todo" , command=cerrar_todo, bg="lightgreen", fg="black")
boton_cerrar.grid(row=0, column=1, padx=20, pady=30, sticky="w")


root.mainloop()