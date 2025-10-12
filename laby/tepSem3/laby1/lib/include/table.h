#pragma once
#include <string>
#define DEFAULT_NAME "name"
#define DEFAULT_SIZE 0

class Table
{
    std::size_t size;
    void init(std::string name, std::size_t tableLen);
    int *copyTable(std::size_t size, int *table);
    Table(std::string name, std::size_t size, int *table);

public:
    Table();
    Table(std::string name, std::size_t tableLen);
    Table(Table &other);
    ~Table();
    void setName(std::string name);
    bool setNewSize(std::size_t newLen);
    Table *getClone();
    int *table;
    std::string name;
};
