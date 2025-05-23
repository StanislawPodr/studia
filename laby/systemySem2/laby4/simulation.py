import algorithms

from process import Process
from process import Reference
import frames 

class Simulation:
    def __init__(self, memory_manager: algorithms.MemoryManager, frames_allocation: frames.Frames, physical_memory_size: int, processes: list):
        self.memory_manager = memory_manager
        self.frames_allocation = frames_allocation
        self.physical_memory_size = physical_memory_size
        self.processes = processes

    def simulate(self, references: list, w: int, skip: int = 0) -> None:
        """
        Simulates the memory management process using the specified memory manager.

        Args:
            references (list): A list of references to simulate.
        """
        for process in self.processes:
            self.frames_allocation.init_max_number_of_frames(process)

        for time, reference in enumerate(references):
            self.memory_manager.next_page(reference, time)
            for process in self.processes:
                if time > skip:
                    self.frames_allocation.set_max_number_of_frames(reference.process)
                process.adjust(time, w)



def generateReferences(size_of_block : int, num_per_block : int, processes : list, number_of_blocks : int) -> list:
    """
    Generates a list of references based on the specified parameters.

    Args:
        size_of_block (int): How many data in single block.
        num_per_block (int): The number of unique references to sample from for each block.
        processes (list): A list of processes to generate references for.
        number_of_blocks (int): The number of blocks to generate.

    Returns:
        list: A list of references generated based on the input parameters.
    """
    import random
    references = []
    for _ in range(number_of_blocks):
        temp = []
        for process in processes:
            k = random.sample(range(1, process.virtual_memory_size + 1), num_per_block)
            temp += [Reference(i, process) for i in random.choices(k, k=size_of_block)]
        random.shuffle(temp)
        references += temp
    return references

    
def show_chart(processes: list, raport_e: float) -> None:
    import matplotlib.pyplot as plt
    import matplotlib.cm as cm
    import numpy as np

    _, axs = plt.subplots(len(processes), 1, figsize=(10, 4 * len(processes)), sharex=True)
    axs = np.atleast_1d(axs)

    cmap = cm.get_cmap('tab10')
    colors = [cmap(i % 10) for i in range(len(processes))]

    for i, process in enumerate(processes):
        if isinstance(process.raport_e, list) and len(process.raport_e) > 0 and isinstance(process.raport_e[0], tuple):
            times, es = zip(*process.raport_e)
        else:
            times = list(range(len(process.raport_e)))
            es = process.raport_e

        times = np.array(times)
        es = np.array(es)

        below = es <= raport_e
        axs[i].plot(times[below], es[below], marker='o', linestyle='-', color=colors[i], label=f"Process {i} (E ≤ {raport_e})")

        above_indices = np.where(es > raport_e)[0]
        if above_indices.size > 0:
            runs = np.split(above_indices, np.where(np.diff(above_indices) != 1)[0] + 1)
            for run in runs:
                axs[i].plot(times[run], es[run], marker='o', linestyle='-', color='red', label=f"Process {i} (E > {raport_e})" if run is runs[0] else "")

        avg_e = np.mean(es)
        percent_above = 100.0 * np.sum(es > raport_e) / len(es) if len(es) > 0 else 0

        stats_text = f"Avg E: {avg_e:.2f}\n% E > {raport_e}: {percent_above:.1f}%"
        if hasattr(process, 'halted') and process.halted:
            stats_text += "\nStatus: Halted"

        axs[i].text(1.02, 0.5, stats_text, transform=axs[i].transAxes, va='center', ha='left', fontsize=11, bbox=dict(facecolor='white', alpha=0.7, edgecolor='gray'))

        title_text = f"Process {i}: E over Time"
        if hasattr(process, 'halted') and process.halted:
            title_text += " (Halted)"

        axs[i].set_ylabel("E")
        axs[i].set_title(title_text)
        axs[i].legend()
        axs[i].grid(True)

    axs[-1].set_xlabel("Time")
    plt.tight_layout()
    plt.show()


def main():
    physical_memory_size = 100
    processes = [Process(100), Process(200), Process(300)]
    references = generateReferences(100, 50, processes, 50)
    frames_allocation = frames.EqualAllocation(physical_memory_size, len(processes))
    w = 30
    raport_e = 0.5

    memory_manager = algorithms.LRU()

    simulation = Simulation(memory_manager, frames_allocation, physical_memory_size, processes)
    simulation.simulate(references, w)
    show_chart(processes, raport_e)

    for process in processes:
        process.reset_process()


    frames_allocation = frames.ProportionalAllocation(physical_memory_size, 600)
    #memory_manager = algorithms.LRU()
    simulation = Simulation(memory_manager, frames_allocation, physical_memory_size, processes)
    simulation.simulate(references, w)
    show_chart(processes, raport_e)

    for process in processes:
        process.reset_process()

    frames_allocation = frames.PageFaultsAllocation(physical_memory_size, processes, 0.2, 0.8)
    simulation = Simulation(memory_manager, frames_allocation, physical_memory_size, processes)

    simulation.simulate(references, w=w, skip = 300)
    show_chart(processes, raport_e)

    for process in processes:
        process.reset_process()

    halting_strategy = frames.LowestWss()
    frames_allocation = frames.ZoneModelAllocation(physical_memory_size, 600, processes, halting_strategy)
    simulation = Simulation(memory_manager, frames_allocation, physical_memory_size, processes)
    simulation.simulate(references, w=w)
    show_chart(processes, raport_e)


if __name__ == "__main__":
    main()