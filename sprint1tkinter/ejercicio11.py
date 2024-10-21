import tkinter as tk

root =  tk.Tk()
root.title("Ejercicio 11")
root.geometry("400x500")

def actualizar_etiqueta(val):
    etiqueta.config(text=f"Valor: {val}")

scale = tk.Scale(root, from_=0, to=100, orient='horizontal', command=actualizar_etiqueta)
scale.pack(pady=10)

etiqueta = tk.Label(root, text="Valor: 0")
etiqueta.pack(pady=10)

root.mainloop()


