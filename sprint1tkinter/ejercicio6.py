import tkinter as tk

root =  tk.Tk()
root.title("Ejercicio 6")
root.geometry("300x400")

def mostrar_fruta():
    lista = listbox.curselection()
    frutas = [listbox.get(i) for i in lista]
    etiqueta.config(text=f"Seleccionaste: {', '.join(frutas)}")


frutas = ["Manzana", "Pera", "Banana", "Naranja", "Mango", "Fresa", "Piña"]

listbox = tk.Listbox(root, selectmode=tk.SINGLE)
for fruta in frutas:
    listbox.insert(tk.END, fruta)
    listbox.pack(pady = 10)

boton = tk.Button(root, bg="lightgrey", text="Mostrar fruta seleccionada", command=mostrar_fruta)
boton.pack(pady=5)

etiqueta = tk.Label(root, text="Seleccionaste: Ninguno")
etiqueta.pack(pady=5)


root.mainloop()
