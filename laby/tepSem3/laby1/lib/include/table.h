#pragma once;
#include <string>

class Table
{
    std::string name;
    int *table;
    public:
    Table();
    Table(std::string name, int tableLen);
    Table(Table &other);
    ~Table();
    void setName(std::string name);
    bool setNewSize(std::size_t newLen);
    Table* getClone();
};

