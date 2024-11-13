import tkinter as tk
from time import sleep
from tkinter import messagebox, simpledialog, Toplevel
import time
from modelo import GameModel
from vista import MainMenu, GameView



class GameController:
    def __init__(self, root):
        self.root = root
        self.model = None
        self.selected = []
        self.timer_started = False
        self.name = None
        self.difficulty = None
        self.loading_window = None
        self.GameView = None

        self.main_menu = MainMenu(
            root,
            start_game_callback=self.show_difficulty_selection,
            show_stats_callback=self.show_stats,
            quit_callback=self.exit_game
        )



    def show_difficulty_selection(self):
        difficulty = ""
        valid = ["fácil", "normal", "difícil"]
        while difficulty not in valid:
            difficulty = simpledialog.askstring("Escriba la dificultad que quiera jugar: ",
                                                "fácil, normal o difícil")
            difficulty = difficulty.lower()

        self.difficulty = difficulty
        self.name = self.main_menu.ask_player_name()

        self.start_game()


    def show_stats(self):
        print("stats")

    def exit_game(self):
        self.root.quit()

    def show_loading_window(self, message):
        self.loading_window = tk.Toplevel(self.root)
        self.loading_window.title("Espere")
        self.loading_window.geometry("300x100")

        label = tk.Label(self.loading_window, text=message)
        label.pack(pady=20)


    def check_images_loaded(self):
        if self.model.images_are_loaded():
            self.loading_window.destroy()
            for row in self.model.board:
                print("\t".join(str(cell) for cell in row))

            self.GameView = GameView(self.root,
                                     on_card_click_callback = self.on_card_click,
                                     update_move_count_callback = self.update_move_count,
                                     update_time_callback = self.update_time)
            self.GameView.create_board(self.model)


        else:
            self.root.after(100, self.check_images_loaded)


    def start_game(self):
        self.model = GameModel(self.difficulty, self.name)
        self.show_loading_window("Descargando imágenes...")

        self.check_images_loaded()

    def on_card_click(self, pos):
        if not self.timer_started:
            self.timer_started = True
            self.model.start_timer()
            self.update_time()


        img_name = self.model.board[pos[0]][pos[1]]
        self.GameView.update_board(pos,img_name, self.model)
            #shows the image on the label clicked

        self.selected.append(pos)
            #saves the coords to the img clicked in selected

        if self.selected.__len__() == 2:
                #if there's two cards selected it calls the func to handle the comparison
            self.root.after(300, self.handle_card_selection)


    def handle_card_selection(self):
        print("hay dos!")

        pos_1 = self.selected[0]
        pos_2 = self.selected[1]

        if self.model.board[pos_1[0]][pos_1[1]] == self.model.board[pos_2[0]][pos_2[1]]:
            print("YAY")
        else:
            self.root.after(1000, self.GameView.reset_cards(pos_1,pos_2,self.model))
                #need to wait before calling it so the 2nd card actually shows up

        self.selected = []
            #reset selected

        self.model.moves += 1
        self.GameView.update_moves(self.model.moves)



    def update_move_count(self, moves):
        pass

    def update_time(self):
        current_time = self.model.get_time() - self.model.start_time

        self.GameView.update_time(int(current_time)) #used int so only the second shows

        self.root.after(1000, self.update_time)
            #keeps calling this every second so it keeps updating on the screen


