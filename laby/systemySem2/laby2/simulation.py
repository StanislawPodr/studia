import matplotlib.pyplot as plt

def pltShow(x, y): 
    plt.plot(x, y, marker='o', linestyle='-') 
    plt.xlabel("Cylindry")
    plt.ylabel("Czas")
    plt.title("Wykres zależności")
    plt.gca().invert_yaxis()
    plt.grid()
    plt.show()
    
def FCFS(input : list, startingPoint, max=200):
    Y = []
    sum = 0
    X = input[:]
    X.insert(0, startingPoint)
    current = startingPoint
    for x in X:
        sum += abs(current - x)
        Y.append(sum)
        current = x
    pltShow(X, Y)
    return sum

def SSTF(input : list, startingPoint, max=200):
    y = []
    sum = 0
    current = startingPoint 
    remainig = input[:]
    x = []
    for searchIndex in range(len(input)):
        closest = min(enumerate(remainig), key=lambda x: abs(x[1] - current))
        x.append(closest[1])
        sum += abs(current - closest[1])
        current = closest[1]
        y.append(sum)
        remainig.pop(closest[0])
    input.insert(0, startingPoint)
    y.insert(0, 0)
    x.insert(0, startingPoint)
    pltShow(x, y)
    return sum

def SCAN(input : list, startingPoint, max=200):
    y = []
    sum = 0
    current = startingPoint 
    remaining = input
    x = []
    go = 1
    greaterThanStartingPoint = sorted([x for x in remaining if x > startingPoint], reverse=True)
    smallerThanStartingPoint = sorted([x for x in remaining if x <= startingPoint])
    startIndex = len(smallerThanStartingPoint) + 1
    if(len(greaterThanStartingPoint) > len(smallerThanStartingPoint)):
        go = -1
        smallerThanStartingPoint.insert(0, 0)
    else:
        greaterThanStartingPoint.append(startingPoint)
        smallerThanStartingPoint.reverse()
        greaterThanStartingPoint.reverse()
    smallerThanStartingPoint.append(startingPoint)
    remaining = smallerThanStartingPoint + greaterThanStartingPoint
    for index in range(len(remaining)):
        next = remaining[(startIndex + index*go) % len(remaining)]
        x.append(next)
        sum += abs(current - next)
        current = next
        y.append(sum)
    pltShow(x, y)
    return sum
    
    
#print(FCFS([98,183,37,122,14,124,65,67], 53))
#print(SSTF([98,183,37,122,14,124,65,67], 53))
print(SCAN([98,183,37,122,14,124,65,67], 53))