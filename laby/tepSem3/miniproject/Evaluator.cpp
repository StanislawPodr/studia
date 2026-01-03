#include "Evaluator.hpp"
#include <algorithm>

Evaluator::Evaluator(const Data &data) : generator(std::random_device{}()), data(data)
{
}

Individual Evaluator::generateIndividual()
{
    return Individual(generator, data);
}

Individual Evaluator::calculate(double &bestFitness)
{
    std::uniform_int_distribution<int> randIndividual(0, data.popSize - 1);

    // generujemy populację startową
    std::vector<Individual> individuals(data.popSize);
    std::generate(individuals.begin(), individuals.end(), [this]()
                  { return Individual{generator, data}; });

    // na razie zakładamy że najlepszy jest pierwszy
    Individual bestIndividual = individuals[0];
    bestFitness = bestIndividual.calculateRoutesCost(data);

    // pętla iteracji naszego AG
    for (int iterIdx = 0; iterIdx < data.iterCount; iterIdx++)
    {
        std::vector<Individual> newPopulation; // nowa populacja stworzona z dzieci poprzedniej
        newPopulation.reserve(data.popSize);

        while (newPopulation.size() < data.popSize)
        {
            // wybór rodziców do krzyżowania
            double resFitnessFstParent;
            const Individual &fstParFstSample = individuals[randIndividual(generator)];
            const Individual &fstParSecSample = individuals[randIndividual(generator)];

            double resFitnessSecParent;
            const Individual &secParFstSample = individuals[randIndividual(generator)];
            const Individual &secParSecSample = individuals[randIndividual(generator)];

            const Individual &fstParent = chooseBetter(fstParFstSample, fstParSecSample, resFitnessFstParent);
            const Individual &secParent = chooseBetter(secParFstSample, secParSecSample, resFitnessSecParent);

            // jeżeli rodzice są lepsi niż poprzednie najlepsze rozwiązanie to trzeba ich dodać
            if (resFitnessFstParent < resFitnessSecParent)
            {
                if (resFitnessFstParent < bestFitness)
                {
                    bestFitness = resFitnessFstParent;
                    bestIndividual = fstParent;
                }
            }
            else
            {
                if (resFitnessSecParent < bestFitness)
                {
                    bestFitness = resFitnessSecParent;
                    bestIndividual = secParent;
                }
            }

            // krzyżowanie i dodawanie dzieci do nowej populacji
            auto children = Individual::cross(fstParent, secParent, generator, data);
            newPopulation.push_back(std::move(children.first));
            newPopulation.push_back(std::move(children.second));
        }

        individuals = std::move(newPopulation);
    }

    return bestIndividual;
}

const Individual &Evaluator::chooseBetter(const Individual &first, const Individual &second, double &resFitness)
{
    double fstFitness = first.calculateRoutesCost(data);
    double secFitness = second.calculateRoutesCost(data);

    if (fstFitness < secFitness)
    {
        resFitness = fstFitness;
        return first;
    }

    resFitness = secFitness;
    return second;
}
