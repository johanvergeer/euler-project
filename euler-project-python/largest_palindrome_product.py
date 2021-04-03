"""Solution of the Largest Palindrome Product puzzle

https://projecteuler.net/problem=2
"""


def _is_palindrome(value: str):
    return value == value[::-1]


def find_largest_palindrome_product(highest_number: int):
    palindromes = set()

    for i in range(highest_number, 0, -1):
        for j in range(highest_number, 0, -1):
            product = i * j
            if _is_palindrome(str(product)):
                palindromes.add(product)

    return max(palindromes)


print(find_largest_palindrome_product(999))
