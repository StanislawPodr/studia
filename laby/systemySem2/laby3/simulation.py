def generateReferences(size_of_block : int, num_per_block : int, virtual_memory_capacity : int, number_of_blocks : int) -> list:
    """
    Generates a list of references based on the specified parameters.

    Args:
        size_of_block (int): The number of references to include in each block.
        num_per_block (int): The number of unique references to sample from for each block.
        virtual_memory_capacity (int): The maximum value (exclusive) for the range of references.
        number_of_blocks (int): The number of blocks to generate.

    Returns:
        list: A list of references generated based on the input parameters.
    """
    import random
    references = []
    for _ in range(number_of_blocks):
        k = random.sample(range(1, virtual_memory_capacity + 1), num_per_block)
        references += random.choices(k, k=size_of_block)    
    return references

def FIFO(references : list, physical_memory_size : int) -> int:
    pages = []
    page_faults = 0
    for reference in references:
        if reference not in pages:
            if len(pages) < physical_memory_size:
                pages.append(reference)
            else:
                pages.pop(0)
                pages.append(reference)
            page_faults += 1
    return page_faults

def OPT(references : list, physical_memory_size : int) -> int:
    pages = []
    page_faults = 0
    for i, reference in enumerate(references):
        if reference not in pages:
            if len(pages) < physical_memory_size:
                pages.append(reference)
            else:
                farthest = -1
                page_to_replace = -1
                sublist = references[i+1:]
                for page in pages:
                    if page in sublist:
                        index = sublist.index(page)
                    else:
                        index = float('inf')
                    if index > farthest:
                        farthest = index
                        page_to_replace = page
                pages.remove(page_to_replace)
                pages.append(reference)
            page_faults += 1
    return page_faults

def LRU(references : list, physical_memory_size : int) -> int:
    pages = []
    page_faults = 0
    for i, reference in enumerate(references):
        if reference not in pages:
            if len(pages) < physical_memory_size:
                pages.append(reference)
            else:
                not_used = [True] * len(pages)
                number_of_not_used = len(pages)
                lru_page = pages[0]
                for j in references[i-1::-1]:
                    if j in pages:
                        index = pages.index(j)
                        if not_used[index]:
                            not_used[index] = False
                            number_of_not_used -= 1
                            lru_page = pages[index]
                    if number_of_not_used == 0:
                        break    
                pages.remove(lru_page)
                pages.append(reference)
            page_faults += 1
    return page_faults

def approxLRU(references : list, physical_memory_size : int) -> int:
    import collections
    pages = collections.deque(maxlen=physical_memory_size)
    page_faults = 0
    for reference in references:
        page= [pages[i][0] for i in range(len(pages))]
        if reference not in [pages[i][0] for i in range(len(pages))]:
            if len(pages) < physical_memory_size:
                pages.append([reference, 1])
            else:
                current = pages.popleft()
                while current[1] == 1:
                    current[1] = 0
                    pages.append(current)
                    current = pages.popleft()
                pages.append([reference, 1])
            page_faults += 1
        else:
            pages[page.index(reference)][1] = 1
    return page_faults

def RAND(references : list, physical_memory_size : int) -> int:
    import random
    pages = []
    page_faults = 0
    for reference in references:
        if reference not in pages:
            if len(pages) < physical_memory_size:
                pages.append(reference)
            else:
                pages[random.randint(0, physical_memory_size-1)] = reference
            page_faults += 1
    return page_faults

def showFaultsChart(references : list, max_physical_memory_size : int) -> None:
    import matplotlib.pyplot as plt
    import numpy as np

    list_of_numbers = range(1, max_physical_memory_size + 1)
    FIFO_faults = [FIFO(references, size) for size in list_of_numbers]
    OPT_faults = [OPT(references, size) for size in list_of_numbers]
    LRU_faults = [LRU(references, size) for size in list_of_numbers]
    approxLRU_faults = [approxLRU(references, size) for size in list_of_numbers]
    RAND_faults = [RAND(references, size) for size in list_of_numbers]

    x = np.arange(len(list_of_numbers))
    width = 0.15 

    fig, ax = plt.subplots()
    rects1 = ax.bar(x - width*2, FIFO_faults, width, label='FIFO')
    rects2 = ax.bar(x - width, OPT_faults, width, label='OPT')
    rects3 = ax.bar(x, LRU_faults, width, label='LRU')
    rects4 = ax.bar(x + width, approxLRU_faults, width, label='Approx LRU')
    rects5 = ax.bar(x + width*2, RAND_faults, width, label='RAND')

    ax.set_ylabel('Liczba błędów strony')
    ax.set_xlabel('Rozmiar pamięci fizycznej')
    ax.set_title('Porównanie algorytmów zarządzania pamięcią')
    ax.set_xticks(x)
    ax.set_xticklabels(list_of_numbers)
    ax.legend()

    plt.show()

references = []
virtual_memory_capacity = 1000
print(FIFO([1, 2, 3, 4, 1, 2, 5, 1, 2, 3, 4, 5], 4))
print(OPT([1, 2, 3, 4, 1, 2, 5, 1, 2, 3, 4, 5], 4))
print(LRU([1, 2, 3, 4, 1, 2, 5, 1, 2, 3, 4, 5], 4))
print(approxLRU([1, 2, 3, 4, 1, 2, 5, 1, 2, 3, 4, 5], 4))
print(approxLRU([1, 2, 3, 4, 1, 2, 5, 3, 2, 1, 4, 5], 4))
#showFaultsChart([1, 2, 3, 4, 1, 2, 5, 1, 2, 3, 4, 5], 5)
showFaultsChart(generateReferences(15, 5, 100, 1000), 100)
