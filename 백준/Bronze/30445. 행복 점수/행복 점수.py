line = input()

PH, PG = 0, 0

for char in line:

    if char == 'A':
        PH += 1
        PG += 1
    elif char in 'HPY':
        PH += 1
    elif char in 'SD':
        PG += 1

H = 5000

if not (PH == PG == 0):
    temp = (PH * 100000) // (PH + PG)

    if 5 <= temp % 10:
        temp += 10
    
    H = temp // 10

print(f'{(H * 0.01):.2f}')