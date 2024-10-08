import random

class Tesoro:
    def __init__(self):
        self.lista_beneficios = ["Restaurar completa la salud del héroe", "Defensa del héroe +5", "Ataque del héroe +10",
                          "El héroe recupera 40hp", "Ataque del héroe +5","El héroe recupera 25hp"]

    def encontrar_tesoro(self,heroe):
        beneficio = self.lista_beneficios[random.randint(0, len(self.lista_beneficios))]
        print(f"Héroe ha encontrado un tesoro: {beneficio}.")
        if beneficio == "Restaurar completa la salud del héroe":
            heroe.cura(100)
            print(f"La salud de {heroe.nombre} ha sido restaurada a {heroe.salud_maxima}.")
        elif beneficio == "Defensa del héroe +5":
            heroe.defenderse()
            print(f"La defensa de {heroe.nombre} aumenta a {heroe.defensa}.")
        elif beneficio == "Ataque del héroe +10":
            heroe.incrementar_ataque(10)
            print(f"El ataque de {heroe.nombre} aumenta a {heroe.ataque}.")
        elif beneficio == "Ataque del héroe +5":
            heroe.incrementar_ataque(5)
            print(f"El ataque de {heroe.nombre} aumenta a {heroe.ataque}.")
        elif beneficio == "El héroe recupera 40hp":
            heroe.cura(40)
            print(f"La salud de {heroe.nombre} ha sido restaurada a {heroe.salud_maxima}.")
        elif beneficio == "El héroe recupera 25hp":
            heroe.cura(25)
            print(f"La salud de {heroe.nombre} ha sido restaurada a {heroe.salud_maxima}.")




