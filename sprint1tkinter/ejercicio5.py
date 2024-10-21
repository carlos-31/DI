import tkinter as tk

root =  tk.Tk()
root.title("Ejercicio 5")
root.geometry("300x250")

def cambiar_color():
    #Con .config(bg="") se cambia el color de fondo del root
    #.get() regresa el value de cada radiobutton, por lo que se puede usar para cambiar el color.
    root.config(bg=var_radio.get())


#Se necesita usar una variable de cadena
var_radio = tk.StringVar()
var_radio.set("green")

#Todas las opciones llaman al metodo para actualizar el color de la página
radio1 = tk.Radiobutton(root, text="Verde", variable=var_radio, value="green",command=cambiar_color)
radio1.pack(pady=20,padx=25)
radio2 = tk.Radiobutton(root, text="Rojo", variable=var_radio, value="red",command=cambiar_color)
radio2.pack(pady=20,padx=25)
radio3 = tk.Radiobutton(root, text="Azul", variable=var_radio, value="blue",command=cambiar_color)
radio3.pack(pady=20,padx=25)

root.mainloop()


