def is_prime(n: int) -> bool:
    """Primality test using 6k+-1 optimization."""
    if n <= 3:
        return n > 1
    if n % 2 == 0 or n % 3 == 0:
        return False
    i = 5
    while i ** 2 <= n:
        if n % i == 0 or n % (i + 2) == 0:
            return False
        i += 6
    return True


def find_nth_prime(n: int):
    counter = 0
    i = 2
    while counter < n:
        if is_prime(i):
            counter += 1
        i += 1

    return i - 1


def find_primes_up_to(n: int):
    primes = [i for i in range(n) if is_prime(i)]
    return sum(primes)


# print(find_nth_prime(10001))
print(find_primes_up_to(2_000_000))
