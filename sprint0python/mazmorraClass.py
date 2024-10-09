import random

import monstruoClass
import tesoroClass
import heroeClass

class Mazmorra:
    """
    El constructor solo recibe el objeto del héroe
    El atributo de monstruos es una lista vacia que luego ese llena con objetos de esa clase,
        se usa randon para variar el número de enemigos en la mazmorra cada vez que se juega
    El atributo de tesoro es simplemente un objeto de la clase tesoro
    """
    def __init__(self,heroe):
        self.heroe = heroe
        self.monstruos = []
        for i in range(random.randint(3,7)):
            self.monstruos.append(monstruoClass.Monstruo())
        self.tesoro = tesoroClass.Tesoro()


    def enfrentar_enemigo(self,enemigo):
        """
        Este metodo controla cada encuentro con un enemigo hasta que el héroe o el enemigo muere.
        Recibe el enemigo al que se encontró en la mazmorra.

        Se le pide al usuario que acción quiere tomar, controlando que su respuesta sea válida,
            y según su respuesta se hace lo correspondiente a su acción.

        """
        while self.heroe.esta_vivo() and enemigo.esta_vivo():
            print("\nPosibles acciones: \n 1. Atacar \n 2. Defender \n 3. Curarse")
            operacion = 0
            while operacion != 1 and operacion != 2 and operacion != 3:
                try:
                    operacion = int(input("Qué quiere hacer?: "))
                    print()
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

            if operacion == 2: #Si escoge defenderse se le regresa la defensa a su valor inicial luego del turno
                self.heroe.reset_defensa()


    def buscar_tesoro(self):
        print("\nBuscando tesoro...")
        self.tesoro.encontrar_tesoro(self.heroe)


    def jugar(self):
        print("El héroe ha estrado en la mazmorra.")
        #Controla el juego, y este continua hasta que el héroe muera, o derrote a todos los enemigos
        while self.monstruos and self.heroe.esta_vivo():
            print(f"\nTe has encontrado con {self.monstruos[0].nombre}!")
            self.enfrentar_enemigo(self.monstruos[0])

            if not self.heroe.esta_vivo():
                #En el caso que el héroe muera en el enfrentamiento con el enemigo
                print(f"\n\n{self.heroe.nombre} ha sido derrotado en la mazmorra.")
                break
            elif not self.monstruos[0].esta_vivo():
                print(f"\nEl héroe ha derrotado a {self.monstruos[0].nombre}!\n")
                    #Si derrota al enemigo, este se elimina de la lista de enemigos, y luego se le da el tesoro al héroe
                self.monstruos.pop(0)
                self.buscar_tesoro()


        if not self.monstruos:
            #Si la lista esta vacia es por que ya derrotó a todos los monstruos, ganando el juego
            print(f"\n\n¡{self.heroe.nombre} ha derrotado a todos los monstruos y ha conquistado la mazmorra!")









