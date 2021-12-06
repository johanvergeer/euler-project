from itertools import permutations

for i, c in enumerate(permutations("0123456789", 10)):
    if i == 999999:
        print("".join(c))
        break

# print(list(combinations("0123456789".split(), r=3)))
