from processor import Process, Processor
from processor import RedirectionStrategy
from processor import RandomRedirectionStrategy
from processor import RandomRedirectionUpToZStrategy
from processor import RedirectionAndTrafficRedirectStrategy


def generate_processes(num_processes: int, traffic_range: tuple) -> list:
    from random import randint
    return [Process(randint(*traffic_range)) for _ in range(num_processes)]

def show_statistics(traffic: list, deviation: list, number_of_requests: int, number_of_redirects: int, loaded_processors: float):
    from statistics import mean
    avg_traffic = mean(traffic) if traffic else 0
    print(f"Average traffic for {loaded_processors * 100:.0f}% most loaded processors: {avg_traffic:.2f}")
    print(f"Traffic deviation: {mean(deviation):.2f}")
    print(f"Total number of requests: {number_of_requests}")
    print(f"Total number of redirects: {number_of_redirects}")

def simulate(processors: list, processes: list[Process], p : int, redirection_strategy: RedirectionStrategy, loaded_processors: float = 0.1):
    from statistics import mean
    from random import Random
    rng = Random()
    traffic = []
    deviation = []
    for process in processes:
        rng.choice(processors).add_process(process, redirection_strategy, processors, p)
        avg_traffic = [proc.get_traffic() / p for proc in processors]
        number_of_loaded = int(len(avg_traffic) * loaded_processors)
        avg_traffic.sort(reverse=True)
        traffic.append(mean(avg_traffic[:(number_of_loaded + 1)]))
        deviation.append(mean(abs(x - traffic[-1]) for x in avg_traffic))
    number_of_requests = redirection_strategy.number_of_requests
    number_of_redirects = redirection_strategy.number_of_redirects
    show_statistics(traffic,deviation, number_of_requests, number_of_redirects, loaded_processors)
    
def clear_after_simulation(processors: list, strategy: RedirectionStrategy):
    strategy.clear()
    for processor in processors:
        processor.clear()


def main():
    num_processors = 80
    num_processes = 2000
    traffic_range = (1, 10)
    p = 100
    most_loaded_processors = 0.1
    
    processors = [Processor() for _ in range(num_processors)]
    
    processes = generate_processes(num_processes, traffic_range)


    redirection_strategy = RandomRedirectionUpToZStrategy(60)  
    simulate(processors, processes, p, redirection_strategy, most_loaded_processors)
    clear_after_simulation(processors, redirection_strategy)
    
    redirection_strategy = RandomRedirectionStrategy()
    simulate(processors, processes, p, redirection_strategy, most_loaded_processors)
    clear_after_simulation(processors, redirection_strategy)

    redirection_strategy = RedirectionAndTrafficRedirectStrategy(50)
    simulate(processors, processes, p, redirection_strategy, most_loaded_processors)


if __name__ == "__main__":
    main()