import tkinter as tk

root =  tk.Tk()
root.title("Ejercicio 6")
root.geometry("300x400")

def mostrar_fruta():
    #listbox.curselection() regresa el indice de lo que fue seleccionado en la lista.
    lista = listbox.curselection()
    etiqueta.config(text=f"Seleccionaste: {listbox.get(lista)}")
        #Con get se obtiene el elemento que esta en el índice seleccionado.


frutas = ["Manzana", "Pera", "Banana", "Naranja", "Mango", "Fresa", "Piña"]

listbox = tk.Listbox(root, selectmode=tk.SINGLE)
#Con este for, se agregan todas las frutas en la lista, a la Listbox
for fruta in frutas:
    listbox.insert(tk.END, fruta)  #tk.END indica que ese elemento se debe agregar al final de la lista actual
listbox.pack(pady = 10)

boton = tk.Button(root, bg="lightgrey", text="Mostrar fruta seleccionada", command=mostrar_fruta)
boton.pack(pady=5)

#Mensaje inicial de la etiqueta, donde no hay nada seleccionado en la lista
etiqueta = tk.Label(root, text="Seleccionaste: Ninguno")
etiqueta.pack(pady=5)


root.mainloop()
