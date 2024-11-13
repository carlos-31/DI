import tkinter as tk
from tkinter import simpledialog


class MainMenu:

    def __init__(self,root, start_game_callback, show_stats_callback, quit_callback):
        self.root = root
        self.root.title("Menú Principal")

        self.play_button = tk.Button(root, text="Jugar", command=start_game_callback)
        self.play_button.pack(pady=10)

        self.stats_button = tk.Button(root, text="Estadísticas", command=show_stats_callback)
        self.stats_button.pack(pady=10)

        self.quit_button = tk.Button(root, text="Salir", command=quit_callback)
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
    def __init__(self,root, on_card_click_callback, update_move_count_callback, update_time_callback):
        self.window = None
        self.root = root
        self.labels = {}
        self.board_frame = None
        self.labels_frame = None
        self.label_time = None
        self.label_movements_num = None
        self.on_card_click_callback = on_card_click_callback
        self.update_move_count_callback = update_move_count_callback
        self.update_time_callback = update_time_callback


    def create_board(self, model):
        self.window = tk.Toplevel(self.root)
        self.window.title("Juego")

        self.board_frame = tk.Frame(self.window)
        self.board_frame.pack(pady = 15, padx = 15)

        # for i in range(model.difficulty):
        #     for j in range(model.difficulty):
        #         label = tk.Label(self.board_frame, image=model.images[model.board[i][j]])
        #         label.grid(row=i, column = j)

        for i in range(model.difficulty):
            for j in range(model.difficulty):
                label = tk.Label(self.board_frame, image=model.hidden_image)
                label.grid(row=i, column = j)

                label.bind("<Button-1>", lambda event, r=i, c=j: self.on_card_click_callback((r, c)))


                self.labels_frame = tk.Frame(self.window)
        self.labels_frame.pack(pady=10, padx=15)

        label_movements = tk.Label(self.labels_frame, text="Movimientos: ")
        label_movements.grid(row=0, column=0)

        self.label_movements_num = tk.Label(self.labels_frame, text="0")
        self.label_movements_num.grid(row=0, column=1)

        label_timer = tk.Label(self.labels_frame, text="Tiempo: ")
        label_timer.grid(row=0, column=2)

        self.label_time = tk.Label(self.labels_frame, text="0 segundos")
        self.label_time.grid(row=0, column=3)

        label_carta = tk.Label(self.labels_frame, text="")
        label_carta.grid(row=1,column=0)


    def update_time(self, time):
        self.label_time.config(text=str(time)+" segundos")

    def update_moves(self, moves):
        self.label_movements_num.config(text=moves)


    def update_board(self, pos, image_id, model):
        i, j = pos

        for widget in self.board_frame.grid_slaves():
            #gets a list of all the labels in the board with their coords
            grid_info = widget.grid_info()
            row, col = int(grid_info['row']), int(grid_info['column'])
                #saving the coords to then compare them to the one you're looking for


            if row == i and col == j:
                widget.config(image=model.images[image_id])
                break #breaks after finding the correct label


    def reset_cards(self, pos1, pos2, model):
        i, j = pos1
        x, y = pos2

        for widget in self.board_frame.grid_slaves():
            grid_info = widget.grid_info()
            row, col = int(grid_info['row']), int(grid_info['column'])

            #changes the img only to the ones that are turned
            if row == i and col == j or row == x and col == y:
                widget.config(image=model.hidden_image)












































