from math import factorial as fact
def task_1():
    p,n, k = map(float, input('Введите через пробел вероятность попасть, колво бросков, колво удачных\n').split(' '))
    return round(fact(n)/(fact(n-k)*fact(k)) * p**k * (1-p)**(n-k), 3)

def task_2():
    print()
    f1, f2, f3 = map(float, input('Введите через пробел долю занимаего рынка фирмой 1, 2, 3 (десят дроби)\n').split())
    m1, m2, m3 = map(float, input('Введите через пробел колво ошибок на 100 у фирмы 1, 2, 3 (десят дроби)\n').split())
    chocolate_prob = 1-(f1*m1 + f2*m2 + f3*m3)
    print(round(chocolate_prob), 3)
    f = int(input('Для какой фирмы нужно посчитать в пункте 2 (целое число)\n'))
    p_e = [f1, f2, f3][f-1]
    p_c_e = [1-m1, 1-m2, 1-m3][f-1]
    return round((p_e*p_c_e/chocolate_prob), 3)


if __name__ == '__main__':
    print(task_1())
    print(task_2())
