#include "FileReader.hpp"
#include <sstream>
#include <fstream>
#include <stdexcept>
#include <algorithm>
#include <unordered_set>
#include <numeric>

void FileReader::readFromFile(std::string &name, Data &result)
{
    std::ifstream file(name);
    std::string line;

    if (!file)
    {
        throw std::invalid_argument("Can't open a file");
    }

    while (std::getline(file, line))
    {
        std::istringstream iss(line);
        std::string word = "";

        if (iss >> word && word != LCVRP_EOF)
        {
            if (word == NAME)
            {
                readName(iss, result);
            }
            else if (word == TYPE)
            {
                readType(iss, result);
            }
            else if (word == DIMENSION)
            {
                readDimension(iss, result);
            }
            else if (word == EDGE_WEIGHT_TYPE)
            {
                readEdgeWeightType(iss, result);
            }
            else if (word == CAPACITY)
            {
                readCapacity(iss, result);
            }
            else if (word == PERMUTATION)
            {
                readPermutation(iss, result);
            }
            else if (word == NODE_COORD_SECTION)
            {
                readNodeCoordSection(file, result);
            }
            else if (word == DEMAND_SECTION)
            {
                readDemandSection(file, result);
            }
            else if (word == DEPOT_SECTION)
            {
                readDepotSection(file, result);
            }
            else if (word == EDGE_WEIGHT_SECTION)
            {
                readEdgeWeightSection(file, result);
            }
        }
    }
}

void FileReader::validateLogic(const Data &toValidation)
{
    // sprawdzanie czy capacity jest większe od największego demandu
    auto &demandVctr = toValidation.demand;
    if (toValidation.capacity < *std::max_element(demandVctr.begin(), demandVctr.end()))
    {
        throw std::invalid_argument("Exception in testing demand. Max demand higher than capacity.");
    }

    // sprawdzanie czy permutacje są permutacją
    auto &permVctr = toValidation.permutation;
    std::unordered_set<int> permutations(permVctr.begin(), permVctr.end());
    for (int shopIdx = 1; shopIdx <= toValidation.dimension; shopIdx++)
    {
        if (permutations.find(shopIdx) == permutations.end() &&
            shopIdx != toValidation.depot)
        {
            throw std::invalid_argument("Exception in testing permutation. "
                                        "The permutation does not contain all values.");
        }
    }

    //sprawdzenie czy mamy z czego wyznaczyć ścierzki
    size_t coordVctrSize = toValidation.coordinates.size();
    if (coordVctrSize < toValidation.dimension)
    {
        // + 1 ponieważ macierz jest trójkątna i nie ma jednego indeksu
        size_t edgeWeightsVctrSize = toValidation.edgeWeights.size() + 1;
        if (edgeWeightsVctrSize < toValidation.dimension)
        {
            throw std::invalid_argument("Exception in testing routes. "
                                        "Please specify coordinates or triangle matrix");
        }
    }

    // sprawdzanie czy z każdego punktu można wrócić do depotu (czy maxDist się zgadza)
    const auto &shops = toValidation.permutation;

    const size_t depotIdx = toValidation.depot - 1;
    const double maxDistance = toValidation.maxDistance;

    // sprawdzanie po kolei koordynatów czy wyjazd tam i spowrotem przekracza zakres
    bool cantGoBack = std::any_of(shops.begin(), shops.end(),
                                  [depotIdx, maxDistance, &toValidation](const size_t shopNo)
                                  {
                                      double distance = toValidation.calculateDist(depotIdx, shopNo - 1);
                                      return 2 * distance > maxDistance;
                                  });

    if (cantGoBack)
    {
        throw std::invalid_argument("Exception in testing coordinates. "
                                    "Road from depot is too high for current maxDistance.");
    }

    // sprawdzenie parametrów użytkownika

    if (toValidation.mutationProb < 0 || toValidation.mutationProb > 1)
    {
        throw std::invalid_argument("Mutation prob is not valid");
    }

    if (toValidation.crossProb < 0 || toValidation.crossProb > 1)
    {
        throw std::invalid_argument("Cross prob is not valid");
    }

    if (toValidation.popSize < 1)
    {
        throw std::invalid_argument("PopSize is not valid");
    }

    if (toValidation.iterCount < 1)
    {
        throw std::invalid_argument("IterCount is not valid");
    }

    if (toValidation.numberOfGroups < 2)
    {
        throw std::invalid_argument("numberOfGroups is not valid");
    }
}

void FileReader::readName(std::istringstream &iss, Data &result)
{
    std::string word;
    if (!(iss >> word && iss >> word))
    {
        throw std::invalid_argument("Exception in specifying result name. You forgot a separator?");
    }

    result.name = word;
}

void FileReader::readType(std::istringstream &iss, Data &result)
{
    std::string word;
    if (!(iss >> word && iss >> word))
    {
        throw std::invalid_argument("Exception in specifying result type. You forgot a separator?");
    }

    result.type = word;
}

void FileReader::readDimension(std::istringstream &iss, Data &result)
{
    std::string word;
    int inputDim;

    if (!(iss >> word && iss >> inputDim))
    {
        throw std::invalid_argument("Exception in specifying result dimension. You forgot a separator?");
    }

    if (inputDim < 3)
    {
        throw std::invalid_argument("Exception in specifying result dimension. Dimension can't be less than 3.");
    }

    result.dimension = inputDim;
}

void FileReader::readEdgeWeightType(std::istringstream &iss, Data &result)
{
    std::string word;
    if (!(iss >> word && iss >> word))
    {
        throw std::invalid_argument("Exception in specifying result type. You forgot a separator?");
    }

    result.edgeWeightType = word;
}

void FileReader::readCapacity(std::istringstream &iss, Data &result)
{
    std::string word;
    int inputCapacity;

    if (!(iss >> word >> inputCapacity))
    {
        throw std::invalid_argument("Exception in specifying result capacity. You forgot a separator?");
    }

    if (inputCapacity < 0)
    {
        throw std::invalid_argument("Exception in specifying result capacity. Capacity can't be negative.");
    }

    result.capacity = inputCapacity;
}

void FileReader::readPermutation(std::istringstream &iss, Data &result)
{
    std::string word;
    int inputCapacity;
    bool success = static_cast<bool>(iss >> word);

    result.permutation.reserve(result.dimension - 1);

    int dimension;
    for (int dimIndex = 1; dimIndex < result.dimension && success; dimIndex++)
    {
        success = static_cast<bool>(iss >> dimension);
        result.permutation.push_back(dimension);
    }

    if (!success)
    {
        throw std::invalid_argument("Exception in specifying result permutation. Too few arguments");
    }
}

void FileReader::readNodeCoordSection(std::ifstream &file, Data &result)
{
    result.calculatorFunction = Data::calculateDistByCoords;

    result.coordinates.reserve(result.dimension);

    int dimIndex = 1;
    bool linesCorrect = true;
    std::string line;
    while (dimIndex <= result.dimension &&
           linesCorrect &&
           (linesCorrect = static_cast<bool>(std::getline(file, line))))
    {
        std::istringstream iss{line};

        int lineNum;
        double x = 0, y = 0;
        linesCorrect = iss >> lineNum >> x >> y && dimIndex == lineNum;

        result.coordinates.push_back(std::pair<double, double>{x, y});

        dimIndex++;
    }

    if (!linesCorrect)
    {
        throw std::invalid_argument("Exception in specifying result coordinates. Too few or wrong arguments");
    }
}

void FileReader::readDemandSection(std::ifstream &file, Data &result)
{
    result.demand.reserve(result.dimension);

    int dimIndex = 1;
    bool linesCorrect = true;
    std::string line;
    while (dimIndex <= result.dimension &&
           linesCorrect &&
           (linesCorrect = static_cast<bool>(std::getline(file, line))))
    {
        std::istringstream iss{line};

        int lineNum;
        double demand = 0;
        linesCorrect = iss >> lineNum >> demand && dimIndex == lineNum;

        if (demand < 0)
        {
            throw std::invalid_argument("Exception in specifying result demand. Demand can't be negative");
        }

        result.demand.push_back(demand);

        dimIndex++;
    }

    if (!linesCorrect)
    {
        throw std::invalid_argument("Exception in specifying result demand. Too few or wrong arguments");
    }
}

void FileReader::readDepotSection(std::ifstream &file, Data &result)
{
    int depot, end;
    if (!(file >> depot >> end && end == END_OF_DEPOT_SECTION) ||
        depot < 1 ||
        depot > result.dimension)
    {
        throw std::invalid_argument("Exception in specifying result depot. Wrong section");
    }

    result.depot = depot;
}

void FileReader::readEdgeWeightSection(std::ifstream &file, Data &result)
{
    result.calculatorFunction = Data::calculateDistByEdgeWeights;


    result.edgeWeights.reserve(result.dimension - 1);

    int dimIndex = 1;
    bool linesCorrect = true;
    std::string line;
    while (dimIndex < result.dimension &&
           linesCorrect &&
           (linesCorrect = static_cast<bool>(std::getline(file, line))))
    {
        std::istringstream iss{line};

        std::vector<double> nextWeights;
        nextWeights.reserve(dimIndex);

        double weight;
        while (iss >> weight && weight >= 0)
        {
            nextWeights.push_back(weight);
        }

        if (weight < 0)
        {
            throw std::invalid_argument("Exception in specifying result edgeWeights. Weights should be positive");
        }

        result.edgeWeights.push_back(std::move(nextWeights));

        linesCorrect = (result.edgeWeights.back().size() == dimIndex);
        dimIndex++;
    }

    if (!linesCorrect)
    {
        throw std::invalid_argument("Exception in specifying result edgeWeights. Too few or wrong arguments");
    }
}

void FileReader::getData(std::string &name, Data &result)
{
    readFromFile(name, result);
    validateLogic(result);
}