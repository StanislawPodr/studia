from abc import ABC, abstractmethod
from process import Reference

class MemoryManager(ABC):
    def __init__(self) -> None:
        super().__init__()
    @abstractmethod
    def next_page(self, reference: Reference, time : int) -> None:
       pass
    
                
class FIFO(MemoryManager):
    def __init__(self) -> None:
        super().__init__()
    def next_page(self, reference: Reference, time : int) -> None:
        if reference.process.halted:
            return
        frames = reference.process.frames
        while len(frames) > reference.process.max_number_of_frames:
               frames.pop(0)
        if reference.page_number not in frames:
            if len(frames) == reference.process.max_number_of_frames:
               frames.pop(0)
            reference.page_fault(time)
        else:
            reference.frame_accessed(time)


class LRU(MemoryManager):
    def __init__(self) -> None:
        super().__init__()
    def next_page(self, reference: Reference, time: int) -> None:
        if reference.process.halted:
            return
        frames = reference.process.frames
        while len(frames) > reference.process.max_number_of_frames:
            frames.pop(0)
        if reference.page_number not in frames:
            if len(frames) == reference.process.max_number_of_frames:
                frames.pop(0)
            else:
                not_used = [True] * len(pages)
                number_of_not_used = len(pages)
                lru_page = pages[0]
                for j in process.references[i-1::-1]:
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
                reference.page_fault(time)
        else:
            frames.remove(reference.page_number)
            frames.append(reference.page_number)
            reference.frame_accessed(time)

# class ApproxLRU(MemoryManager):
#     def next_page(self, process: Process) -> int:
#         import collections
#         pages = collections.deque(maxlen=process.number_of_frames)
#         page_faults = 0
#         for reference in process.references:
#             page= [pages[i][0] for i in range(len(pages))]
#             if reference not in [pages[i][0] for i in range(len(pages))]:
#                 if len(pages) < process.number_of_frames:
#                     pages.append([reference, 1])
#                 else:
#                     current = pages.popleft()
#                     while current[1] == 1:
#                         current[1] = 0
#                         pages.append(current)
#                         current = pages.popleft()
#                     pages.append([reference, 1])
#                 page_faults += 1
#             else:
#                 pages[page.index(reference)][1] = 1
#         return page_faults



