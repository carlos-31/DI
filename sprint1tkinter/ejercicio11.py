import tkinter as tk

root =  tk.Tk()
root.title("Ejercicio 11")
root.geometry("400x500")

def actualizar_etiqueta(val):
    #Cambia el texto de la etiqueta usando el valor actual del scale
    etiqueta.config(text=f"Valor: {val}")


#Crear el scale y ponerlo en pantalla con pack, cada vez que se ineractue con el se llama al metodo de actualizar_etiqueta
scale = tk.Scale(root, from_=0, to=100, orient='horizontal', command=actualizar_etiqueta)
scale.pack(pady=10)

etiqueta = tk.Label(root, text="Valor: 0")
etiqueta.pack(pady=10)

root.mainloop()


