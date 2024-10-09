from heroeClass import Heroe
from mazmorraClass import Mazmorra

def main():
    #Se pide el nombre que se le va a dar al héroe, luego instanciando luego dandole este nombre
    nombre_heroe = input("Introduce el nombre de tu héroe: ")
    heroe = Heroe(nombre_heroe)

    #Se crea la mazmorra , la cual es la que controla el juego por completo. Llamando a jugar() se comienza el juego
    mazmorra = Mazmorra(heroe)
    mazmorra.jugar()

if __name__ == "__main__":
    main()
