import math
from typing import Optional
from time import time


def pythagorean_triplet_for_sum(sum: int) -> Optional[tuple[int, int, int]]:
    for c in range(sum):
        for b in range(c):
            for a in range(b):
                if pow(a, 2) + pow(b, 2) == pow(c, 2) and (a + b + c) == sum:
                    return a, b, c


start = time()
triplet = pythagorean_triplet_for_sum(1000)

if triplet:
    print(math.prod(triplet))
else:
    print("No triplet found")
end = time()

print(end - start)
