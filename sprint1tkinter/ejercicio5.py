import tkinter as tk

root =  tk.Tk()
root.title("Ejercicio 5")
root.geometry("300x250")

def cambiar_color():
    root.config(bg=var_radio.get())


var_radio = tk.StringVar()
var_radio.set("verde")


radio1 = tk.Radiobutton(root, text="Verde", variable=var_radio, value="green",command=cambiar_color)
radio1.pack(pady=20,padx=25)
radio2 = tk.Radiobutton(root, text="Rojo", variable=var_radio, value="red",command=cambiar_color)
radio2.pack(pady=20,padx=25)
radio3 = tk.Radiobutton(root, text="Azul", variable=var_radio, value="blue",command=cambiar_color)
radio3.pack(pady=20,padx=25)

root.mainloop()


