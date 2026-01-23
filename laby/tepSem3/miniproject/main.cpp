#include <iostream>
#include "FileReader.hpp"
#include "Evaluator.hpp"

int main(int argc, char** argv)
{
    std::string filePath = "lcvrp/Vrp-Set-D/Loggi-n601-k42.lcvrp";
    Data experimentData;
    double resultFitness;
    try
    {
        FileReader::getData(filePath, experimentData);
        Evaluator evaluator(experimentData);
        Individual result = evaluator.calculate(resultFitness);
        std::cout << resultFitness << "\n";
        std::cout << "cross prob: " << experimentData.crossProb << "\n";
        std::cout << "mutation prob: " << experimentData.mutationProb << "\n";
        std::cout << "population: " << experimentData.popSize << "\n";
        std::cout << "iterations: " << experimentData.iterCount << "\n";
        std::cout << "no groups: " << experimentData.numberOfGroups << "\n";
        std::cout << "max distance: " << experimentData.maxDistance << "\n";
        for (int perIdx : experimentData.permutation)
        {
            std::cout << perIdx << " ";
        }
        std::cout << "\n";
        std::cout << result.toString();
        std::cout << "\n";
    }
    catch (std::exception &e)
    {
        std::cerr << e.what() << std::endl;
        return 1;
    }
    
    return 0;
}