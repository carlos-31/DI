import tkinter as tk
from controlador import GameController
from modelo import GameModel


if __name__ == "__main__":
    root = tk.Tk()
    controlador = GameController(root)

    root.mainloop()
