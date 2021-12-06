from time import time


def fibonacci_sequence():
    terms = (1, 1)

    while True:
        yield terms[0]
        terms = (terms[1], terms[0] + terms[1])


def fib(n):
    v1, v2, v3 = 1, 1, 0  # initialise a matrix [[1,1],[1,0]]
    for rec in bin(n)[3:]:
        calc = v2 * v2
        v1, v2, v3 = v1 * v1 + calc, (v1 + v3) * v2, calc + v3 * v3
        if rec == '1':
            v1, v2, v3 = v1 + v2, v1, v2
    return v2


def fib_to(n):
    fibs = [0, 1]
    for i in range(2, n+1):
        fibs.append(fibs[-1] + fibs[-2])
    return fibs


if __name__ == '__main__':
    fib(10)
    # start = time()
    # goal = pow(10, 10000) - 1
    # i = 0
    # for n in fib(pow(10, 1000)):
    #     i += 1
    #     if n > goal:
    #         print(i)
    #         break
    #
    # print(int((time() - start) * 1000))
