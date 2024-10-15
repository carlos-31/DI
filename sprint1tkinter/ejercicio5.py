import tkinter as tk

root =  tk.Tk()
root.title("Ejercicio 5")
root.geometry("300x250")

def cambiar_color_verde():
    root.config(bg="Green")

def cambiar_color_rojo():
    root.config(bg="red")

def cambiar_color_azul():
    root.config(bg="blue")


var_radio = tk.StringVar()
var_radio.set("verde")

radio1 = tk.Radiobutton(root, text="Verde", variable=var_radio, value="Verde",command=cambiar_color_verde)
radio1.pack(pady=20,padx=25)
radio2 = tk.Radiobutton(root, text="Rojo", variable=var_radio, value="Rojo",command=cambiar_color_rojo)
radio2.pack(pady=20,padx=25)
radio3 = tk.Radiobutton(root, text="Azul", variable=var_radio, value="Azul",command=cambiar_color_azul)
radio3.pack(pady=20,padx=25)

root.mainloop()


