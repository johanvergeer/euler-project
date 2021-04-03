"""Solution of the Smallest Multiple puzzle

https://projecteuler.net/problem=5
"""


def smallest_multiple(max: int):
    i = 1
    while True:
        if all([i % divider == 0 for divider in range(1, max + 1)]):
            return i
        i += 1


print(smallest_multiple(20))
