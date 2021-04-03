import pytest


def primes(value):
    primes_ = []
    divider = 2
    while value > 1:
        while value % divider == 0:
            primes_.append(divider)
            value /= divider
        divider += 1
        # if value > 1:
        #     primes_.append(int(value))
    return primes_


@pytest.mark.parametrize(
    "value,expected",
    [
        (1, []),
        (2, [2]),
        (3, [3]),
        (4, [2, 2]),
        (5, [5]),
        (6, [2, 3]),
        (7, [7]),
        (8, [2, 2, 2]),
        (9, [3, 3]),
        (2 * 2 * 3 * 5 * 5 * 7 * 11, [2, 2, 3, 5, 5, 7, 11]),
        (600851475143, [71, 839, 1471, 6857]),
    ],
)
def test_prime_factors(value, expected):
    assert primes(value) == expected
