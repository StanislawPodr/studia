import matplotlib.pyplot as plt

def pltShow(x : list, y : list, additionalText : str = "", afterDeadline : list = [], realTimeInput : list = []): 
    fig, ax = plt.subplots()  # Tworzymy główny subplot
    
    x_afterDeadline = [xi for xi, yi in afterDeadline]
    y_afterDeadline = [yi for xi, yi in afterDeadline]
    
    x_realTime = [xi for xi, yi in realTimeInput]
    y_realTime = [yi for xi, yi in realTimeInput]

    # Rysujemy linię
    ax.plot(x, y, linestyle='-', marker="o", color='blue', zorder=1) 
    
    ax.scatter(x_afterDeadline, y_afterDeadline, c="red", marker='o', zorder=5)
    ax.scatter(x_realTime, y_realTime, c="green", marker='o', zorder=4)
    
    # Dodajemy tekst
    ax.text(1.00, 0.2, additionalText, fontsize=12, ha='center', va='center', transform=ax.transAxes)
    
    # Etykiety i tytuł
    ax.set_xlabel("Cylindry")
    ax.set_ylabel("Czas")
    ax.set_title("Wykres zależności")
    
    # Odwracamy oś Y
    ax.invert_yaxis()
    
    # Siatka
    ax.grid()
    
    # Pokazujemy wykres
    plt.show()
    
def FCFS(input : list, startingPoint, max=200, sum=0):
    Y = []
    X = input[:]
    X.insert(0, startingPoint[0])
    current = startingPoint[0]
    for x in X:
        sum += abs(current - x)
        Y.append(sum)
        current = x
    return X, Y, sum

def showFCFS(input : list, startingPoint, max=200):
    X, Y, sum = FCFS(input, startingPoint, max)
    pltShow(X, Y, f'FCFS, czas = {sum}')

def SSTF(input : list, startingPoint, max=200, sum=0):
    y = []
    current = startingPoint[0] 
    remainig = input[:]
    x = []
    for searchIndex in range(len(input)):
        closest = min(enumerate(remainig), key=lambda x: abs(x[1] - current))
        x.append(closest[1])
        sum += abs(current - closest[1])
        current = closest[1]
        y.append(sum)
        remainig.pop(closest[0])
    
    y.insert(0, startingPoint[1])
    x.insert(0, startingPoint[0])
    return x, y, sum


def showSSTF(input : list, startingPoint, max=200):
    x, y, sum = SSTF(input, startingPoint, max)
    pltShow(x, y, f'SSTF, czas = {sum}')

def SCAN(input : list, startingPoint, max=200, sum=0):
    y = []
    current = startingPoint[0] 
    remaining = input
    x = []
    go = 1
    greaterThanStartingPoint = sorted([x for x in remaining if x > startingPoint[0]], reverse=True)
    smallerThanStartingPoint = sorted([x for x in remaining if x <= startingPoint[0]])
    startIndex = len(smallerThanStartingPoint)
    if(len(greaterThanStartingPoint) > len(smallerThanStartingPoint)):
        go = -1
        smallerThanStartingPoint.insert(0, 0)
        startIndex+=1
    else:
        smallerThanStartingPoint.reverse()
        greaterThanStartingPoint.reverse()
        greaterThanStartingPoint.append(max)
    smallerThanStartingPoint.append(startingPoint[0])
    remaining = smallerThanStartingPoint + greaterThanStartingPoint
    for index in range(len(remaining)):
        next = remaining[(startIndex + index*go) % len(remaining)]
        x.append(next)
        sum += abs(current - next)
        current = next
        y.append(sum)
    return x, y, sum


def showSCAN(input : list, startingPoint, max=200):
    x, y, sum = SCAN(input, startingPoint, max)
    pltShow(x, y, f'SCAN, czas = {sum}')


def CSCAN(input : list, startingPoint, max=200, sum=0):
    y = []
    current = startingPoint[0] 
    remaining = input
    x = []
    greaterThanStartingPoint = sorted([x for x in remaining if x > startingPoint[0]])
    smallerThanStartingPoint = sorted([x for x in remaining if x <= startingPoint[0]])
    if(len(greaterThanStartingPoint) < len(smallerThanStartingPoint)):
        greaterThanStartingPoint.reverse()
        smallerThanStartingPoint.reverse()
        smallerThanStartingPoint.insert(0, startingPoint[0])
        smallerThanStartingPoint.append(0)
        smallerThanStartingPoint.append(max)
        remaining = smallerThanStartingPoint + greaterThanStartingPoint
    else:
        greaterThanStartingPoint.insert(0, startingPoint[0])
        greaterThanStartingPoint.append(max)
        greaterThanStartingPoint.append(0)
        remaining = greaterThanStartingPoint + smallerThanStartingPoint
    for e in remaining:
        next = e
        x.append(next)
        sum += abs(current - next)
        current = next
        y.append(sum)
    return x, y, sum

def showCSCAN(input : list, startingPoint, max=200):
    x, y, sum = CSCAN(input, startingPoint, max)
    pltShow(x, y, f'C-SCAN, czas = {sum}')

def generateInput(size : int, max : int = 200):
    import random
    input = []
    for i in range(size):
        input.append(random.randint(0, max))
    return input

def generateRealTimeInput(size : int, minTime : int = 100, maxTime : int = 400 , max : int = 200):
    import random
    input = []
    for i in range(size):
        input.append((random.randint(0, max), random.randint(minTime, maxTime)))
    return input

def EDF(realTimeInput : list, input: list, startingPoint, normalAlgorithm, max=200):
    y = []
    sum = 0
    current = startingPoint 
    remaining = input[:]
    x = []
    afterDeadline = []
    realTimeDone = []
    realTimeInput.sort(key=lambda x: x[1])
    for realTime in realTimeInput:
        if realTime[1] < sum:
            afterDeadline.append((realTime[0], realTime[1]))
            continue
        x.append(realTime[0])
        sum += abs(current - realTime[0])
        current = realTime[0]
        y.append(sum)
        if realTime[1] < sum:
            afterDeadline.append((realTime[0], sum))
        else:
            realTimeDone.append((realTime[0], sum))
    X, Y, sum = normalAlgorithm(remaining, (current, sum), max, sum)
    y.insert(0, 0)
    x.insert(0, startingPoint)
    x += X
    y += Y
    pltShow(x, y, f'EDF, czas = {sum}', afterDeadline, realTimeDone)
    return sum

def FDSCAN(realTimeInput : list, input: list, startingPoint, normalAlgorithm, max=200):
    y = []
    sum = 0
    current = startingPoint 
    remaining = input[:]
    x = []
    afterDeadline = []
    realTimeInput.sort(key=lambda x: x[1])
    realTimeDone = []
    for realTime in realTimeInput:
        if realTime[1] <= sum + abs(current - realTime[0]):
            afterDeadline.append((realTime[0], realTime[1]))
            continue
        go = (realTime[0] - current)/abs(current - realTime[0])
        onWay = [X for X in remaining if X * go > current * go and X * go <= realTime[0] * go]
        onWay.sort(reverse=True if go == -1 else False)
        for X in onWay:
            remaining.remove(X)
            x.append(X)
            sum += abs(current - X)
            y.append(sum)
            current = X
        x.append(realTime[0])
        sum += abs(current - realTime[0])
        current = realTime[0]
        y.append(sum)
        realTimeDone.append((realTime[0], sum))
    X, Y, sum = normalAlgorithm(remaining, (current, sum), max, sum)
    y.insert(0, 0)
    x.insert(0, startingPoint)
    x += X
    y += Y
    pltShow(x, y, f'FD-SCAN, czas = {sum}', afterDeadline, realTimeDone)
    return sum



   
# showFCFS([98,183,37,122,14,124,65,67], (53, 0))
# showSSTF([98,183,37,122,14,124,65,67], (53, 0))
# showSCAN([98,183,37,122,14,124,65,67], (53, 0))
# showCSCAN([98,183,37,122,14,124,65,67], (53, 0))
# realTimeInput = generateRealTimeInput(10)
realTimeInput = [(112, 210), (116, 158), (103, 185), (25, 391), (193, 211), (84, 192), (131, 131), (43, 322), (151, 178), (153, 193)]
# print(realTimeInput)
# EDF(realTimeInput, [98,183,37,122,14,124,65,67], 53, FCFS)
FDSCAN(realTimeInput, [98,183,37,122,14,124,65,67], 53, FCFS)