import tkinter as tk

from ControladorNotas import ControladorNotas
from NotasModel import NotasModel
from VistaNotas import VistaNotas


def main():
    root = tk.Tk()
    modelo = NotasModel()
    vista = VistaNotas(root)
    controlador = ControladorNotas(modelo, vista)
    root.mainloop()


if __name__ == "__main__":
    main()
