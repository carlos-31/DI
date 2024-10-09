"""
Cada metodo recibe 2 números, y luego se opera entre ellos según el nombre del metodo
"""

def suma(a, b):
    return a + b

def resta(a, b):
    return a - b

def multiplicacion(a, b):
    return a * b

def division(a, b):
    #Se controla que el divisor no sea 0
    if b != 0:
        return a / b
    else:
        return "No es posible la divison de estos números"



#pruebas anteriores para verificar funcionamiento
def prueba():
    print(suma(2, 3))
    print(resta(5,20))
    print(multiplicacion(4,5))
    print(division(2,0))
    print(division(50,5))

    print("Suma:")
    a = int(input("Numero 1: "))
    b = int(input("Numero 2: "))
    print(suma(a,b))


