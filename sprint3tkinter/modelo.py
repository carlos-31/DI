import datetime
import random
import threading
import time

from recursos import descargar_imagen

class GameModel:
    def __init__(self, difficulty, player_name, cell_size=(75,100)):
        if difficulty == "fácil":
             self.difficulty = 4
        elif difficulty == "normal":
            self.difficulty = 6
        else:
            self.difficulty = 8

        self.name = player_name
        self.moves = 0
        self.start_time = None
        self.images_loaded = False
        self.cell_size = cell_size
        self.board = None
        self.cards = None
        self.hidden_image = None
        self.images = {}
        self.img_names = [
            "prayerCrown.jpg",
            "galaxyGroundWithin.jpg",
            "deathWestaway.jpg",
            "wayKings.jpg",
            "jLegacy.jpg",
            "hollowPlaces.jpg",
            "theBetween.jpg",
            "fifthSeason.jpg",
            "swordKaigen.jpg",
            "longWayAngryPlanet.jpg",
            "WoR.jpg",
            "goodHouse.jpg",
            "stoneSky.jpg",
            "bunny.jpg",
            "dowry.jpg",
            "psalmWild.jpg",
            "legendsLattes.jpg",
            "RoW.jpg",
            "obeliskGate.jpg",
            "jCity.jpg",
            "taughtFortunate.jpg",
            "oathbringer.jpg",
            "recordSpaceborn.jpg",
            "childrenTime.jpg",
            "jWar.jpg",
            "closedCommonOrbit.jpg",
            "turnKey.jpg",
            "petSem.jpg",
            "WaT.jpg",
            "neverKnown.jpg",
            "kindred.jpg",
            "drowningDeep.jpg"
        ]


        self._generate_board()
        self._load_images()




    def _generate_board(self):
        num_cards = int((self.difficulty * self.difficulty) // 2)
            #number of unique imgs you need according to the difficulty
        self.cards = self.img_names[:num_cards] * 2
            #[:x] takes the first x items (slices) (same as [0:x], take from 0 to x from list)
        random.shuffle(self.cards)

        print(self.cards)
        print("-" * 15)

        self.board = [[None for _ in range(self.difficulty)] for _ in range(self.difficulty)]
                            #_ in the loop is the same as i but when the i is not sth you're using

        index = 0
        for i in range(self.difficulty):
            for j in range(self.difficulty):
                self.board[i][j] = self.cards[index]
                print(self.cards[index])
                index += 1



    def _load_images(self):

        def load_images_thread():
            url_base = "https://raw.githubusercontent.com/carlos-31/memory_game_imgs/refs/heads/main/"

            self.hidden_image = descargar_imagen(url_base + "back_card.jpg", self.cell_size)

            unique_image_ids = []
            for i in range(self.difficulty):
                for j in range(self.difficulty):
                    if self.board[i][j] not in unique_image_ids:
                        unique_image_ids.append(self.board[i][j])

            for image_name in unique_image_ids:
                url = url_base + image_name
                photo_image = descargar_imagen(url, self.cell_size)

                self.images[image_name] = photo_image

            self.images_loaded = True


        threading.Thread(target=load_images_thread, daemon=True).start()


    def images_are_loaded(self):
        return self.images_loaded

    def get_time(self):
        return time.time()

    def start_timer(self):
        self.start_time = time.time()



