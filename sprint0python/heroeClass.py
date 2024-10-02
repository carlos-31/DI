import random

class Heroe:
    def __init__(self,nombre):
        self.nombre = nombre
        self.ataque = random.randint(5,30)
        self.defensa = random.randint(5,20)
        self.salud = 100
        self.salud_maxima = 100


    def atacar(self,enemigo):
        print(f"El heroe ataca a {enemigo.nombre}")
        dmg = self.ataque - enemigo.defensa
        if dmg <= 0:
            print("El enemigo ha bloqueado el ataque.")
        else:
            print(f"El enemigo {enemigo.nombre} ha recibido {dmg} puntos de daño.")

    def curarse(self):
        self.salud += 10
        if self.salud > self.salud_maxima:
            self.salud = self.salud_maxima
        print(f"Héroe se ha curado. Salud actual: {self.salud}")

    def defenderse(self):
        self.defensa += 5
        print(f"Héroe se defiende. Defensa aumentada temporalmente a {self.defensa}")

    def reset_defensa(self):
        self.defensa -= 5
        print(f"La defensa de {self.nombre} vuelve a la normalidad")

    def esta_vivo(self):
        if self.salud > 0:
            return True

