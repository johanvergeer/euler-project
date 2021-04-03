"""Solution of the Even Fibonacci Numbers puzzle

https://projecteuler.net/problem=2
"""

from typing import Iterator


def fibonacci_sequence(up_to: int) -> Iterator[int]:
    a = 1
    yield a
    b = 2
    yield b
    c = a + b
    while c <= up_to:
        yield c
        a = b
        b = c
        c = a + b


even_fibonacci_numbers = [i for i in fibonacci_sequence(4_000_000) if i % 2 == 0]

print(sum(even_fibonacci_numbers))
