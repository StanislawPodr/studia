#include "Individual.hpp"
#include <random>

void Individual::addToGroups(std::mt19937 &generator, const Data &data) noexcept
{
    std::uniform_int_distribution<int> groupsIdxDist(0, data.numberOfGroups - 1); // chcemy indeksy grup

    for (const auto &perm : data.permutation)
    {
        groups.push_back(groupsIdxDist(generator)); // dodajemy dla każdej permutacji losową grupę (indeksami)
    }
}

std::vector<std::vector<int>> Individual::getRoutes(const Data &data) const
{
    // wektor ścieżek
    std::vector<std::vector<int>> result(data.numberOfGroups, std::vector<int>{});

    for (int permIdx = 0; permIdx < groups.size(); permIdx++)
    {
        const int groupIdx = groups[permIdx];
        const int coordIndex = data.permutation[permIdx] - 1; // odejmujemy 1 bo chcemy indeks nie lp
        result[groupIdx].push_back(coordIndex);
    }

    return result;
}

Individual::Individual(std::vector<int> &groups) : groups(groups)
{
}

Individual::Individual(std::mt19937 &generator, const Data &data)
{
    groups.reserve(data.permutation.size());
    addToGroups(generator, data);
}

std::pair<Individual, Individual> Individual::cross(const Individual &firstParent, const Individual &secondParent,
                                                    std::mt19937 &generator, const Data &data)
{
    const double crossDraw = std::generate_canonical<double,
                                                     std::numeric_limits<double>::digits>(generator);

    std::pair<Individual, Individual> children;

    /* połączenie genu zachodzi, gdy prawdopodobieństwo połączenia jest
         większe lub równe od losowej liczby z zakresu 0-1 */
    if (crossDraw <= data.crossProb)
    {
        const int shopsNumber = data.dimension - 1;

        // liczba punktów do "przecięcia" to liczba punktów - 1
        std::uniform_int_distribution<int> crossPointDist(1, shopsNumber - 1);
        children = crossInPoint(firstParent, secondParent, crossPointDist(generator));
    }
    else 
    {
        // gdy nie udało się skrzyżować zwracamy rodziców
        children.first = firstParent;
        children.second = secondParent;
    }

    // mutujemy dzieci :3
    mutate(children.first, generator, data);
    mutate(children.second, generator, data);

    return children;
}

std::string Individual::toString()
{
    std::string result;
    for (int grpIdx : groups)
    {
        result += std::to_string(grpIdx);
        result += " ";
    }
    
    return result;
}

double Individual::calculateRoutesCost(const Data &data) const
{
    auto routes = getRoutes(data); // wektory reprezentujące każdą trasę

    size_t depot = data.depot - 1;
    Traveller costCounter{depot, data};

    for (auto &route : routes)
    {
        int currentLoad = 0;

        for (int customerIdx : route)
        {
            int demand = data.demand[customerIdx];
            const unsigned nxtCustomer = customerIdx;

            // Jeżeli dystans będzie za duży to nie da rady się wrócić
            std::vector<size_t> distanceToAchieve{nxtCustomer, depot};
            double distToNxtAndDepot = costCounter.travelCost(distanceToAchieve);
            bool distanceTooHigh = costCounter.distUsed + distToNxtAndDepot > data.maxDistance;

            // jak za dużo załadujemy to musimy wrócić do depotu
            bool loadTooHigh = currentLoad + demand > data.capacity;

            // Jeśli któraś z tych rzeczy się zdarzy musimy się wrócić (resetując load)
            if (distanceTooHigh || loadTooHigh)
            {
                costCounter.goBackToDepot();
                currentLoad = 0;
            }

            currentLoad += demand;
            costCounter.travel(nxtCustomer);
        }

        costCounter.goBackToDepot(); // po zakończonej pracy wracamy
    }

    return costCounter.cost;
}

std::pair<Individual, Individual> Individual::crossInPoint(const Individual &firstParent,
                                                    const Individual &secondParent,
                                                    const int crossIndex)
{
    Individual firstChild;
    Individual secondChild;

    const auto &groupFstParent = firstParent.groups;
    const auto &groupSecParent = secondParent.groups;
    auto &groupFstChild = firstChild.groups;
    auto &groupSecChild = secondChild.groups;

    std::size_t size = groupFstParent.size(); // rozmiar, musi być taki sam dla każdego osobnika

    groupFstChild.reserve(size);
    groupSecChild.reserve(size);

    // łączenie genów rodziców w geny dzieci (łączymy przydział ścieżek)
    groupFstChild.insert(groupFstChild.end(), groupFstParent.begin(),
                         groupFstParent.begin() + crossIndex);
    groupFstChild.insert(groupFstChild.end(), groupSecParent.begin() + crossIndex,
                         groupSecParent.end());

    groupSecChild.insert(groupSecChild.end(), groupSecParent.begin(),
                         groupSecParent.begin() + crossIndex);
    groupSecChild.insert(groupSecChild.end(), groupFstParent.begin() + crossIndex,
                         groupFstParent.end());

    return std::pair{firstChild, secondChild};
}

void Individual::mutate(Individual &toMutate, std::mt19937 &generator, const Data &data)
{
    // dystrybucja rozkładu jednostajnego indeksów grup
    std::uniform_int_distribution<int> groupsIdxDist(0, data.numberOfGroups - 1);

    auto &mutationVctr = toMutate.groups;

    for (int &gene : mutationVctr)
    {
        const double mutationDraw = std::generate_canonical<double,
                                                            std::numeric_limits<double>::digits>(generator);

        /* mutacja genu zachodzi, gdy prawdopodobieństwo mutacji jest
         większe lub równe od losowej liczby z zakresu 0-1 */
        if (mutationDraw <= data.mutationProb)
        {
            gene = groupsIdxDist(generator);
        }
    }
}
