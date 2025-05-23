from process import Process
from abc import ABC, abstractmethod

class Frames(ABC):
    def __init__(self, frames_in_system) -> None:
        super().__init__()
        self.frames_in_system = frames_in_system

    @abstractmethod
    def set_max_number_of_frames(self, process: Process) -> None:
        pass
    
    @abstractmethod
    def init_max_number_of_frames(self, process: Process) -> None:
        pass


class EqualAllocation(Frames):
    def __init__(self, frames_in_system, number_of_processes) -> None:
        super().__init__(frames_in_system)
        self.number_of_processes = number_of_processes
        self.max_number_of_frames = self.frames_in_system // self.number_of_processes
    
    def init_max_number_of_frames(self, process: Process) -> None:
        process.max_number_of_frames = self.max_number_of_frames

    def set_max_number_of_frames(self, process: Process) -> None:
        pass
        

class ProportionalAllocation(Frames):
    def __init__(self, frames_in_system, virtual_memory_capacity) -> None:
        super().__init__(frames_in_system)
        self.virtual_memory_capacity = virtual_memory_capacity

    def init_max_number_of_frames(self, process: Process) -> None:
        process.max_number_of_frames = int(process.virtual_memory_size / self.virtual_memory_capacity * self.frames_in_system)

    def set_max_number_of_frames(self, process: Process) -> None:
        pass
        
class PageFaultsAllocation(Frames):
    def __init__(self, frames_in_system : int, processes: list, l : float, u : float) -> None:
        super().__init__(frames_in_system)
        self.processes = processes
        self.l = l
        self.u = u
        self.virtual_memory_capacity = sum(p.virtual_memory_size for p in processes)
        self.proportional = ProportionalAllocation(self.frames_in_system, self.virtual_memory_capacity)
    
    def init_max_number_of_frames(self, process: Process) -> None:
        self.proportional.set_max_number_of_frames(process)
        
    def set_max_number_of_frames(self, process: Process) -> None:
        if process.halted:
            return
        if process.e < self.l:
            process_with_highest_e = process
            for p in self.processes:
                if p.e > process_with_highest_e.e and not p.halted:
                    process_with_highest_e = p
            process_with_highest_e.max_number_of_frames += 1
            process.max_number_of_frames -= 1
            process.halted = process.max_number_of_frames == 0
        elif process.e > self.u:
            process_with_lowest_e = process
            for p in self.processes:
                if p.e < process_with_lowest_e.e and not p.halted:
                    process_with_lowest_e = p
            process_with_lowest_e.max_number_of_frames -= 1
            process.max_number_of_frames += 1
            process_with_lowest_e.halted = process_with_lowest_e.max_number_of_frames == 0






class ZoneModelHaltingStrategy(ABC):
    @abstractmethod
    def halt(self, wss: list, processes: list) -> Process:
        pass
    

class ZoneModelAllocation(Frames):
    def __init__(self, frames_in_system : int, virtual_memory_capacity: int, processes: list, halting_strategy: ZoneModelHaltingStrategy) -> None:
        super().__init__(frames_in_system)
        self.processes = processes
        self.halting_strategy = halting_strategy
        self.virtual_memory_capacity = virtual_memory_capacity
        
    def init_max_number_of_frames(self, process: Process) -> None:
        pass


    def re_allocate_frames(self, processes: list, frames_to_allocate: int) -> None:
        processes_not_halted = []
        virtual_memory_capacity = self.virtual_memory_capacity
        for process in processes:
            if not process.halted:
                processes_not_halted.append(process)
            else: 
                virtual_memory_capacity -= process.virtual_memory_size
        for process in processes_not_halted:
           process.max_number_of_frames = int(process.virtual_memory_size / virtual_memory_capacity * frames_to_allocate)
               
        
    def set_max_number_of_frames(self, process: Process) -> None:
        if process.halted:
            return
        available_frames = self.frames_in_system - sum(p.max_number_of_frames for p in self.processes)
        wss = self.calculate_WSS(process)
        if wss - len(process.frames) <= available_frames:
            process.max_number_of_frames = wss
        else:
            wss = [self.calculate_WSS(p) for p in self.processes]
            process_to_halt = self.halting_strategy.halt(wss, self.processes)
            process_to_halt.halted = True
            available_frames += process_to_halt.max_number_of_frames
            process_to_halt.max_number_of_frames = 0
            self.re_allocate_frames(self.processes, available_frames)
            
    
    def calculate_WSS(self, process: Process) -> int:
        used_frames = set()
        for frame in process.frames_accessed:
            used_frames.add(frame[1])
        for frame in process.page_faults:
            used_frames.add(frame[1])
        return len(used_frames)


class LowestWss(ZoneModelHaltingStrategy):
    def halt(self, wss: list, processes : list) -> Process:
        min_index = 0
        for i, val in enumerate(wss):
            if val < wss[min_index]:
                min_index = i
        return processes[min_index]
    
    
class HighestWss(ZoneModelHaltingStrategy):
    def halt(self, wss: list, processes : list) -> Process:
        max_index = 0
        for i, val in enumerate(wss):
            if val > wss[max_index]:
                max_index = i
        return processes[max_index]



