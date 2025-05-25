from random import Random
from abc import ABC, abstractmethod

class Process:
    def __init__(self, traffic: int):
        self.traffic = traffic

class Processor:
    def __init__(self):
        self.processes = []

    def get_traffic(self):
        return sum(process.traffic for process in self.processes)


    def add_process(self, process: Process, strategy: 'RedirectionStrategy', processors: list, p: int):
        strategy.redirect_task(process, processors, self, p)
        
    def clear(self):
        self.processes.clear()


class RedirectionStrategy(ABC):
    def __init__(self):
        super().__init__()
        self.rng = Random()
        self.number_of_requests = 0
        self.number_of_redirects = 0


    @abstractmethod
    def redirect_task(self, process: Process, processors: list, this_processor: Processor, p: int):
        pass
    
    def redirect_to_random_with_low_traffic(self, other_processors: list, process: Process, p: int):
        found = False
        while other_processors and not found:
            processor = self.rng.choice(other_processors)
            other_processors.remove(processor)
            if processor.get_traffic() < p:
                processor.processes.append(process)
                found = True
                self.number_of_redirects += 1
            self.number_of_requests += 1
        return found
    
    def clear(self):
        self.number_of_requests = 0
        self.number_of_redirects = 0
    
class RandomRedirectionUpToZStrategy(RedirectionStrategy):
    def __init__(self, z: int):
        super().__init__()
        self.z = z

    def redirect_task(self, process: Process, processors: list, this_processor: Processor, p: int):
        z = self.z
        found = False
        other_processors = [proc for proc in processors if proc != this_processor]
        while z > 0 and not found:
            processor = self.rng.choice(other_processors)
            other_processors.remove(processor)
            if processor.get_traffic() < p:
                processor.processes.append(process)
                found = True
                self.number_of_redirects += 1
            self.number_of_requests += 1
            z -= 1
        if not found:
            this_processor.processes.append(process)
    
    
class RandomRedirectionStrategy(RedirectionStrategy):
    def __init__(self):
        super().__init__()

    def redirect_task(self, process: Process, processors: list, this_processor: Processor, p: int):
        if this_processor.get_traffic() < p:
            this_processor.processes.append(process)
            return
        
        other_processors = [proc for proc in processors if proc != this_processor]
        found = self.redirect_to_random_with_low_traffic(other_processors, process, p)

        if not found:
            this_processor.processes.append(process)
            
            
class RedirectionAndTrafficRedirectStrategy(RedirectionStrategy):
    def __init__(self, r: float):
        super().__init__()
        self.r = r
        
    def share_traffic(self, this_processor: Processor, found_processor: Processor, p: int):
            to_be_added = p - this_processor.get_traffic()
            closest = found_processor.processes[0]
            for proc in found_processor.processes:
                if proc.traffic < to_be_added and proc.traffic > closest.traffic:
                    closest = proc
            found_processor.processes.remove(closest)
            this_processor.processes.append(closest)

    
    def redirect_task(self, process: Process, processors: list, this_processor: Processor, p: int):
        rng = Random()
        other_processors = [proc for proc in processors if proc != this_processor]
        rng.shuffle(other_processors)
        found = False
        if this_processor.get_traffic() < self.r:
            this_processor.processes.append(process)
            while other_processors and not found:
                processor = rng.choice(other_processors)
                other_processors.remove(processor)
                if processor.get_traffic() > p:
                    self.share_traffic(this_processor, processor, p)
                    self.number_of_redirects += 1
                    found = True
                self.number_of_requests += 1
            return
        elif this_processor.get_traffic() < p:
            this_processor.processes.append(process)
            return
        
        other_processors = [proc for proc in processors if proc != this_processor]
        found = self.redirect_to_random_with_low_traffic(other_processors, process, p)

        if not found:
            this_processor.processes.append(process)
        