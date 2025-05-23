from typing import Any


class Process:
    def __init__(self, virtual_memory_size : int):
        self.virtual_memory_size = virtual_memory_size
        self.max_number_of_frames = 1
        self.frames = []
        self.page_faults = []
        self.frames_accessed = []
        self.e = 0
        self.raport_e = []
        self.halted = False
        self.all_page_faults = []

    def adjust(self, time, w) -> None:
        if self.page_faults and self.page_faults[0][0] < time - w:
            self.page_faults.pop(0)
        elif self.frames_accessed and self.frames_accessed[0][0] < time - w:
            self.frames_accessed.pop(0)
        self.e = len(self.page_faults) / (len(self.frames_accessed) + len(self.page_faults)) if (len(self.frames_accessed) + len(self.page_faults)) > 0 else 0
        self.raport_e.append(self.e)

    def reset_process(self) -> None:
        self.max_number_of_frames = 1
        self.frames = []
        self.page_faults = []
        self.frames_accessed = []
        self.e = 0
        self.raport_e = []
        self.halted = False
        self.all_page_faults = []
            
class Reference:
    def __init__(self, page_number : int, process: Process):
        self.page_number = page_number
        self.process = process
    
    def page_fault(self, time):
        self.process.page_faults.append((time, self.page_number))
        self.process.all_page_faults.append((time, self.page_number))
        self.process.frames.append(self.page_number)
        
    def frame_accessed(self, time):
        self.process.frames_accessed.append((time, self.page_number))
