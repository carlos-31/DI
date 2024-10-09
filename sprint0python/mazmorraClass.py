import random

import monstruoClass
import tesoroClass
import heroeClass

class Mazmorra:
    def __init__(self,heroe):
        self.heroe = heroe
        self.monstruos = []
        for i in range(random.randint(3,7)):
            self.monstruos.append(monstruoClass.Monstruo())
        self.tesoro = tesoroClass.Tesoro()


    def enfrentar_enemigo(self,enemigo):
        while self.heroe.esta_vivo() and enemigo.esta_vivo():
            print("\nPosibles acciones: \n 1. Atacar \n 2. Defender \n 3. Curarse")
            operacion = 0
            while operacion != 1 and operacion != 2 and operacion != 3:
                try:
                    operacion = int(input("Qué quiere hacer?: "))
                except ValueError:
                    operacion = 0
                if operacion == 1:
                    self.heroe.atacar(enemigo)
                elif operacion == 2:
                    self.heroe.defenderse()
                elif operacion == 3:
                    self.heroe.cura(10)
                else:
                    print("Opción no válida.")

            print()
            enemigo.atacar(self.heroe)

            if operacion == 2:
                self.heroe.reset_defensa()


    def buscar_tesoro(self):
        print("\nBuscando tesoro...")
        self.tesoro.encontrar_tesoro(self.heroe)


    def jugar(self):
        print("El héroe ha estrado en la mazmorra.")
        while self.monstruos and self.heroe.esta_vivo():
            print(f"\nTe has encontrado con {self.monstruos[0].nombre}!")
            self.enfrentar_enemigo(self.monstruos[0])

            if not self.heroe.esta_vivo():
                print(f"{self.heroe.nombre} ha sido derrotado en la mazmorra.")
                break
            elif not self.monstruos[0].esta_vivo():
                print(f"\nEl héroe ha derrotado a {self.monstruos[0].nombre}!\n")
                self.monstruos.pop(0)
                self.buscar_tesoro()


        if not self.monstruos:
            print(f"¡{self.heroe.nombre} ha derrotado a todos los monstruos y ha conquistado la mazmorra!")





"""
lista = [1,2,3,4,5,6,7]
for i in lista:
    print(i)
lista.pop(0)
print("----------------")
for i in lista:
    print(i)
if lista:
    print("true")
lista.clear()
if lista:
    print("true")
else:
    print("false")

if not lista:
    print("is not lista")

print("----------------")
monster = []
for i in range(random.randint(3, 7)):
    monster.append(monstruoClass.Monstruo())

for i in monster:
    print(i.nombre)

print("----------------")
cosa = False
if not cosa:
    print("yay")
"""




