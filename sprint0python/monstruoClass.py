import random

class Monstruo:
    def __init__(self):
        lista_nombres = ["Lucía", "Leo", "Martina", "Mateo", "Sofía", "Hugo", "Emma", "Lucas", "Valeria", "Martín"]
        self.nombre = lista_nombres[random.randint(0,9)]
        self.ataque = random.randint(10,25)
        self.defensa = random.randint(2,8)
        self.salud = 100


    def atacar(self,heroe):
        print(f"El monstruo {self.nombre} ataca a {heroe.nombre}")
        dmg = self.ataque - heroe.defensa
        if dmg <= 0:
            print("El héroe ha bloqueado el ataque.")
        else:
            print(f"El héroe {heroe.nombre} ha recibido {dmg} puntos de daño.")
            heroe.salud -= dmg

    def esta_vivo(self):
        if self.salud > 0:
            return True
