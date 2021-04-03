"""Solution of the Multiples of 3 and 5 puzzle

https://projecteuler.net/problem=1
"""
total = 0

for i in range(0, 1000):
    if (i % 3) == 0 or (i % 5) == 0:
        total += i

print(total)
