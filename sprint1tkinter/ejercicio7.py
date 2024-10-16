import tkinter as tk

root =  tk.Tk()
root.title("Ejercicio 7")
root.geometry("500x550")


def dibujar():
    datos_circulo = entrada_circulo.get().split(',')
    datos_rectangulo = entrada_rectangulo.get().split(',')

    x_circulo = int(datos_circulo[0])
    y_circulo = int(datos_circulo[1])
    radio = int(datos_circulo[2])

    rectangulo_x = int(datos_rectangulo[0])
    rectangulo_y = int(datos_rectangulo[1])
    ancho = int(datos_rectangulo[2])
    alto = int(datos_rectangulo[3])

    canvas.create_rectangle(rectangulo_x, rectangulo_y, rectangulo_x + ancho, rectangulo_y + alto,
                                outline="orange", width=2)
    canvas.create_oval(x_circulo - radio, y_circulo - radio, x_circulo + radio, y_circulo + radio,
                                outline="lightgreen", width=2)





etiqueta_rectangulo = tk.Label(root, text="Datos Rectángulo (x,y,altura,ancho):")
etiqueta_rectangulo.grid(row=0, column=0, padx=5, pady=5)

entrada_rectangulo = tk.Entry(root, width=40)
entrada_rectangulo.grid(row=1, column=0, padx=5, pady=5)

etiqueta_circulo = tk.Label(root, text="Datos círculo (x,y,radio):")
etiqueta_circulo.grid(row=0, column=1, padx=5)

entrada_circulo = tk.Entry(root, width=40)
entrada_circulo.grid(row=1,column=1, padx=5)

boton_mensaje = tk.Button(root, text="Dibujar" , command=dibujar, bg="lightgreen", fg="black")
boton_mensaje.grid(row=2, column=0, columnspan=2, pady=5)


canvas = tk.Canvas(root, width=400,height=400, bg="white")
canvas.grid(row=3,column=0, columnspan=2, padx=5, pady=15)



root.mainloop()