import tkinter as tk
from tkinter import simpledialog


class MainMenu:
    def __init__(self,root, start_game_callback, show_stats_callback, quit_callback):
        self.root = root
        self.start_game_callback = start_game_callback
        self.show_stats_callback = show_stats_callback
        self.quit_callback = quit_callback

        self.root.title("Menú Principal")

        self.play_button = tk.Button(root, text="Jugar", command=self.start_game_callback)
        self.play_button.pack(pady=10)

        self.stats_button = tk.Button(root, text="Estadísticas", command=self.show_stats_callback)
        self.stats_button.pack(pady=10)

        self.quit_button = tk.Button(root, text="Salir", command=self.quit_callback)
        self.quit_button.pack(pady=10)

    def ask_player_name(self):
        player_name = simpledialog.askstring("Nombre del Jugador: ", "Introduce tu nombre...")
        return player_name

    def show_stats(self):
        stats_window = tk.Toplevel(self.root)
        stats_window.title("Estadísticas")

        stats_window.grid_columnconfigure(0, weight=1)
        stats_window.grid_columnconfigure(1, weight=1)
        stats_window.grid_columnconfigure(2, weight=1)

        header = tk.Label(stats_window, text="Estadísticas según dificultad:")
        header.grid(row=0, column=0, columnspan=3, pady=20, sticky="ew")


        frame_easy = tk.Frame(stats_window)
        frame_easy.grid(row = 1, column = 0, padx=10)

        label_easy = tk.Label(frame_easy, text="Fácil:")
        label_easy.grid(row=0,column=0, pady=5)


            #todo add top 10 of each dif lvl, name and num of movements


        frame_normal = tk.Frame(stats_window)
        frame_normal.grid(row=1, column=1, padx=10)

        label_normal = tk.Label(frame_normal, text="Normal:")
        label_normal.grid(row=0, column=0, pady=5)





        frame_hard = tk.Frame(stats_window)
        frame_hard.grid(row=1, column=2, padx=10)

        label_hard = tk.Label(frame_hard, text="Difícil:")
        label_hard.grid(row=0, column=0, pady=5)




class GameView:
    pass