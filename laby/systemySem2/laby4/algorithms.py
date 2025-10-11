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
            reference.page_fault(time)
        else:
            frames.remove(reference.page_number)
            frames.append(reference.page_number)
            reference.frame_accessed(time)




