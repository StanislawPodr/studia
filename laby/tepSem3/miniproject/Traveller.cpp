#include "Individual.hpp"

Individual::Traveller::Traveller(const std::vector<size_t> &locations, const size_t startingPos, const Data &data)
    : position(startingPos), depot(startingPos), data(data)
{
    travel(locations);
}

void Individual::Traveller::travel(const size_t to)
{
    double toPosition = data.calculateDist(position, to);
    cost += toPosition;
    distUsed += toPosition;
    position = to;
}

void Individual::Traveller::travel(const std::vector<size_t> &nxtLocations)
{
    for (auto &nxtLocation : nxtLocations)
    {
        travel(nxtLocation);
    }
}

void Individual::Traveller::goBackToDepot()
{
    cost += data.calculateDist(position, depot);
    distUsed = 0;
    position = depot;
}

double Individual::Traveller::travelCost(const std::vector<size_t> &nxtLocations) const
{
    double localCost = 0;
    size_t localPositionIdx = position;

    for (auto &nxtLocation : nxtLocations)
    {
        localCost += data.calculateDist(localPositionIdx, nxtLocation);
        localPositionIdx = nxtLocation;
    }

    return localCost;
}