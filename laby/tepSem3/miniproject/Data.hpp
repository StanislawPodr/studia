#pragma once

#include <string>
#include <cstddef>
#include <vector>
#include <limits>
#include <cmath>

struct Data
{
    std::string name = "";
    std::string type = "";
    int dimension = 1;
    std::string edgeWeightType = "";
    int capacity = 1;
    std::vector<int> permutation;
    std::vector<std::pair<double, double>> coordinates;
    std::vector<int> demand;
    int depot;
    std::vector<std::vector<double>> edgeWeights;
    int numberOfGroups = 6;
    double maxDistance = std::numeric_limits<double>::max();
    double mutationProb = 0.05;
    double crossProb = 0.5;
    int popSize = 100;
    int iterCount = 1000;
    static double calculateDist(const std::pair<double, double> &from, const std::pair<double, double> &to)
    {
        double dx = from.first - to.first;
        double dy = from.second - to.second;
        return sqrt(dx * dx + dy * dy);
    }
};