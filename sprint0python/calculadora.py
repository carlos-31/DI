from operaciones import suma, resta, multiplicacion, division
#Se importan los metodos de las operaciones

#Usando este loop como un do while, repitiendolo hasta que el usuario escriba n para salir del programa
continuar = 'S'
while continuar == 'S':
    #Pide ambos numeros con los que se van a operar
    num1 = int(input("Número 1: "))
    num2 = int(input("Número 2: "))

    #Se pregunta que operación quiere hacer, y luego se hace.
    print("\nOperaciónes: \n Suma \n Resta \n Multiplicación \n División")
    operacion = input("Qué quiere hacer? (puede escribir la palabra o la primera letra): ").upper()[0]
    if operacion == 'S':
        print(suma(num1,num2))
    elif operacion == 'R':
        print(resta(num1,num2))
    elif operacion == 'M':
        print(multiplicacion(num1, num2))
    elif operacion == 'D':
        print(division(num1,num2))
    else:
        print("Opción no válida.")

    continuar = input("\nEscriba 'S' para hacer otro cálculo 'N' para salir: ").upper()[0]
    if continuar == 'N':
        break
