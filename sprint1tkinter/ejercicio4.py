import tkinter as tk

root =  tk.Tk()
root.title("Ejercicio 4")
root.geometry("300x400")

#Se utilizan 3 variables diferentes, para las 3 opciones disponibles.
var_check_leer = tk.IntVar()
var_check_musica = tk.IntVar()
var_check_ciclismo = tk.IntVar()


def actualizar_etiqueta():
    aficiones_seleccionadas = []

    #Con el get() se verifica si la opción está seleccionada o no. Si lo está, se agrega a la lista aficiones_seleccionadas.
    if var_check_leer.get():
        aficiones_seleccionadas.append("Lectura")
    if var_check_musica.get():
        aficiones_seleccionadas.append("Música")
    if var_check_ciclismo.get():
        aficiones_seleccionadas.append("Ciclismo")

    if not aficiones_seleccionadas:
        etiqueta.config(text="No está seleccionada ninguna aficción.")
    else:
        #Con el .join() se pueden concatenar los elementos de la lista, separados por ", " en este caso.
        etiqueta.config(text="Aficiones seleccionadas: " + ", ".join(aficiones_seleccionadas))


check_leer = tk.Checkbutton(root, text="Lectura", variable=var_check_leer, command=actualizar_etiqueta)
check_leer.grid(row=0,column=0, padx=5)
check_musica = tk.Checkbutton(root, text="Música", variable=var_check_musica, command=actualizar_etiqueta)
check_musica.grid(row=0,column=1, padx=5)
check_ciclismo = tk.Checkbutton(root, text="Ciclismo", variable=var_check_ciclismo, command=actualizar_etiqueta)
check_ciclismo.grid(row=0,column=2, padx=5)
    #Cada vez que se pulsa una opción, se llama al metodo para actualizar la etiqueta con la nueva información.


etiqueta = tk.Label(root, text="No está seleccionada ninguna aficción.")
etiqueta.grid(row=1, column=0, columnspan=3, padx=5, pady=5)



root.mainloop()
