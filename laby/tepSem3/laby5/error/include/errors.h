#pragma once
#include <string>

class Error
{
    std::string description;
    public:
        Error(const std::string &errorMessage);
        std::string getDescription();
};
