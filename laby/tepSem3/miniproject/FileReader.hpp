#pragma once

#include <string>
#include "Data.hpp"

class FileReader
{
public:
    static void getData(std::string &file, Data &result);

private:
    static void readFromFile(std::string &file, Data &result);
    static void validateLogic(const Data &toValidation);
    static void readName(std::istringstream &iss, Data &result);
    static void readType(std::istringstream &iss, Data &result);
    static void readDimension(std::istringstream &iss, Data &result);
    static void readEdgeWeightType(std::istringstream &iss, Data &result);
    static void readCapacity(std::istringstream &iss, Data &result);
    static void readPermutation(std::istringstream &iss, Data &result);
    static void readNodeCoordSection(std::ifstream &file, Data &result);
    static void readDemandSection(std::ifstream &file, Data &result);
    static void readDepotSection(std::ifstream &file, Data &result);
    static void readEdgeWeightSection(std::ifstream &file, Data &result);
    static constexpr int END_OF_DEPOT_SECTION = -1;
    static constexpr std::string_view LCVRP_EOF = "EOF";
    static constexpr std::string_view NAME = "NAME";
    static constexpr std::string_view TYPE = "TYPE";
    static constexpr std::string_view DIMENSION = "DIMENSION";
    static constexpr std::string_view EDGE_WEIGHT_TYPE = "EDGE_WEIGHT_TYPE";
    static constexpr std::string_view CAPACITY = "CAPACITY";
    static constexpr std::string_view PERMUTATION = "PERMUTATION";
    static constexpr std::string_view NODE_COORD_SECTION = "NODE_COORD_SECTION";
    static constexpr std::string_view DEMAND_SECTION = "DEMAND_SECTION";
    static constexpr std::string_view DEPOT_SECTION = "DEPOT_SECTION";
    static constexpr std::string_view EDGE_WEIGHT_SECTION = "EDGE_WEIGHT_SECTION";
};