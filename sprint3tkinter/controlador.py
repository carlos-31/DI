import tkinter as tk
from tkinter import messagebox, simpledialog, Toplevel
import time
from modelo import GameModel
from vista import MainMenu, GameView



class GameController:
    def __init__(self, root):
        self.model = GameModel("fácil","carlos")
        self.selected = []
        self.timer_started = False

        self.main_menu = MainMenu(
            root,
            start_game_callback=self.show_difficulty_selection,
            show_stats_callback=self.show_stats,
            quit_callback=self.exit_game
        )



    def show_difficulty_selection(self):
        print("dif")

    def show_stats(self):
        print("stats")

    def exit_game(self):
        print("salir")