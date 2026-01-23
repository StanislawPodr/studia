#pragma once

#include <string>
#include <cstddef>
#include <vector>
#include <limits>
#include <cmath>
#include <functional>

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

    // obsługa strategii wyliczania koordynatów
    using DistCalculator = double (*)(const size_t from, const size_t to, const Data &data);
    DistCalculator calculatorFunction = Data::calculateDistByEdgeWeights;

    double calculateDist(const size_t fromIdx, const size_t toIdx) const
    {
       return calculatorFunction(fromIdx, toIdx, *this);
    }

    static double calculateDistByCoords(const size_t from, const size_t to, const Data &data)
    {
        // pary koordynatów
        const auto &coordsFrom = data.coordinates[from];
        const auto &coordsTo = data.coordinates[to];
        // odległość euklidesowa
        double dx = coordsFrom.first - coordsTo.first;
        double dy = coordsFrom.second - coordsTo.second;
        return sqrt(dx * dx + dy * dy);
    }

    static double calculateDistByEdgeWeights(const size_t from, const size_t to, const Data &data)
    {
        // jeżeli punkty są takie same to zwróć 0 w tym przypadku jest to niemożliwe
        // ale w razie czego się zabezpieczamy
        if (from == to) [[unlikely]]
        {
            return 0;
        }

        // maicierz jest trójkątna, więc większy indeks będzie wierszem
        size_t high = std::max(from, to);
        size_t low = std::min(from, to);
        // dystans bierzemy z tej macierzy trójkątnej, gdzie kolejność nie ma znaczenia
        // (odległość jest symetryczna). Pierwszego wiersza w ogóle nie ma (jest tam tylko 0)
        double dist = data.edgeWeights[high - 1][low];
        return dist;
    }
};