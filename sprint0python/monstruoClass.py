import random

class Monstruo:
    """
    El nombre del monstruo se le asigna al azar desde una lista de nombres posibles.
    También los valores de ataque y defensa son aleatoreos
    """
    def __init__(self):
        lista_nombres = ["Lucía", "Leo", "Martina", "Mateo", "Sofía", "Hugo", "Emma", "Lucas", "Valeria", "Martín"]
        self.nombre = lista_nombres[random.randint(0,9)]
        self.ataque = random.randint(10,25)
        self.defensa = random.randint(2,8)
        self.salud = 100


    def atacar(self,heroe):
        print(f"El monstruo {self.nombre} ataca a {heroe.nombre}")
        #Controla el daño real que se aplica respecto al ataque del enemigo y a la defensa del héroe
        dmg = self.ataque - heroe.defensa
        if dmg <= 0:
            print("El héroe ha bloqueado el ataque.")
        else:
            print(f"El héroe {heroe.nombre} ha recibido {dmg} puntos de daño.")
            heroe.salud -= dmg

    def esta_vivo(self):
        #Si el enemigo está vivo regresa True
        if self.salud > 0:
            return True
