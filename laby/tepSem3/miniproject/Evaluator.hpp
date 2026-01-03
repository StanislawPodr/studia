#pragma once

#include "Individual.hpp"
#include <random>
#include <vector>

class Evaluator
{
public:
    Evaluator(const Data &data);
    Individual generateIndividual();
    Individual calculate(double &resFitness);

private:
    std::mt19937 generator;
    const Data &data;
    const Individual &chooseBetter(const Individual &first, const Individual &second, double &resFitness);
};