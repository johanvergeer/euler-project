def sum_of_squares(n: int) -> int:
    return sum(pow(i, 2) for i in range(1, n + 1))


def square_of_sum(n: int) -> int:
    return pow(sum(range(n + 1)), 2)


number = 100
print(square_of_sum(number) - sum_of_squares(number))
