#include "Individual.hpp"

Individual::Traveller::Traveller(const std::vector<std::pair<double, double>> locations, const std::pair<double, double> &startingPos)
    : positionPtr(&startingPos), depot(startingPos)
{
    travel(locations);
}

void Individual::Traveller::travel(const std::pair<double, double> &to)
{
    double toPosition = Data::calculateDist(*positionPtr, to);
    cost += toPosition;
    distUsed += toPosition;
    positionPtr = &to;
}

void Individual::Traveller::travel(const std::vector<std::pair<double, double>> nxtLocations)
{
    for (auto &nxtLocation : nxtLocations)
    {
        travel(nxtLocation);
    }
}

void Individual::Traveller::goBackToDepot()
{
    cost += Data::calculateDist(*positionPtr, depot);
    distUsed = 0;
    positionPtr = &depot;
}

double Individual::Traveller::travelCost(const std::vector<std::pair<double, double>> &nxtLocations) const
{
    double localCost = 0;
    auto *localPositionPtr = positionPtr;

    for (auto &nxtLocation : nxtLocations)
    {
        localCost += Data::calculateDist(*localPositionPtr, nxtLocation);
        localPositionPtr = &nxtLocation;
    }

    return localCost;
}