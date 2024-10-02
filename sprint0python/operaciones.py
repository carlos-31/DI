
def suma(a, b):
    return a + b

def resta(a, b):
    return a - b

def multiplicacion(a, b):
    return a * b

def division(a, b):
    if b != 0:
        return a / b
    else:
        return "No es posible la divison de estos números"



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


