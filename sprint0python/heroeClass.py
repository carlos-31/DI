import random

class Heroe:
    """
    El constructor recibe el nombre que se le quiere dar al héroe
    Ataque y defensa se asignan aleatoriamente entro de los rangos
    La salud y la salud máxima serían 100 al momento de instanciar al héroe
    """
    def __init__(self,nombre):
        self.nombre = nombre
        self.ataque = random.randint(12,30)
        self.defensa = random.randint(5,10)
        self.salud = 100
        self.salud_maxima = 100


    def atacar(self,enemigo):
        #Ataca al enemigo, revisando que el daño se ajuste según la defensa del enemigo
        print(f"El heroe ataca a {enemigo.nombre}")
        dmg = self.ataque - enemigo.defensa
        if dmg <= 0:
            print("El enemigo ha bloqueado el ataque.")
        else:
            print(f"El enemigo {enemigo.nombre} ha recibido {dmg} puntos de daño.")
            enemigo.salud -= dmg

    def cura(self,hp):
        #Restaura salud del héroe, en la cantidad especificada en hp
        self.salud += hp
        #Si al incrementarle la salud, esta supera la salud máxima, se le ajusta a la máxima para no sobrepasarla
        if self.salud > self.salud_maxima:
            self.salud = self.salud_maxima
        print(f"La salud de {self.nombre} ha sido restaurada a {self.salud}.")

    def defenderse(self):
        #Le incrementa la defensa al héroe en 5
        self.defensa += 5
        print(f"Héroe se defiende. Defensa aumentada temporalmente a {self.defensa}")

    def reset_defensa(self):
        #Le regresa la defensa a su valor incial
        self.defensa -= 5
        print(f"La defensa de {self.nombre} vuelve a la normalidad")

    def esta_vivo(self):
        #Si el heroe está vivo, regresa True
        if self.salud > 0:
            return True

    def incrementar_ataque(self,num):
        #Incrementa el ataque del héroe según el valor de num
        self.ataque += num



