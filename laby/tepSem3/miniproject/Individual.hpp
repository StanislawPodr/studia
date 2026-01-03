#pragma once

#include <vector>
#include <random>
#include "Data.hpp"

class Individual
{

public:
    Individual(std::vector<int> &groups);
    Individual() = default;
    Individual(std::mt19937 &generator, const Data &data);
    double calculateRoutesCost(const Data &data) const;
    static std::pair<Individual, Individual> cross(const Individual &firstParent, const Individual &secondParent,
                                                   std::mt19937 &generator, const Data &data);
    std::string toString();


private:
    std::vector<int> groups; // grupa indeksowana tak jak permutacje
    void addToGroups(std::mt19937 &generator, const Data &data) noexcept;
    std::vector<std::vector<int>> getRoutes(const Data &data) const;
    static std::pair<Individual, Individual> crossInPoint(const Individual &firstParent,
                                                   const Individual &secondParent,
                                                   const int crossIndex);
    static void mutate(Individual &toMutate, std::mt19937 &generator, const Data &data);

    // struktura reprezentująca licznik przebytego dystansu i kosztu
    struct Traveller
    {
        const std::pair<double, double> *positionPtr;
        const std::pair<double, double> &depot;
        double cost = 0;     // całościowy koszt naszej wyprawy
        double distUsed = 0; // droga przebyta od wizyty w depocie
        Traveller(const std::pair<double, double> &startingPos) : positionPtr(&startingPos), depot(startingPos) {}
        Traveller(const std::vector<std::pair<double, double>> locations, const std::pair<double, double> &startingPos);
        void travel(const std::pair<double, double> &to);
        void travel(const std::vector<std::pair<double, double>> nxtLocations);
        void goBackToDepot();
        double travelCost(const std::vector<std::pair<double, double>> &nxtLocations) const;
    };
};