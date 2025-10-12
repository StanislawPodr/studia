#include <table.h>
#include <iostream>
#include <cstddef>

void Table::setName(std::string name)
{
    this->name = name;
}

Table *Table::getClone()
{
    return new Table(name, size, table);
}

bool Table::setNewSize(std::size_t newSize)
{
    delete[] table;
    table = nullptr;
    if (newSize == 0)
        return true;
    bool res = (table = new (std::nothrow) int[newSize]) != nullptr;
    size = res ? newSize : 0;
    return res;
}

Table::Table(Table &other) : Table(other.name + "_copy", other.size, other.table)
{
    std::cout << "kopiuj: '" << name << "'\n";
}

Table::Table()
{
    init(DEFAULT_NAME, DEFAULT_SIZE);
    std::cout << "bezp: '" << DEFAULT_NAME << "'\n";
}

Table::Table(std::string name, std::size_t tableLen)
{
    init(name, tableLen);
    std::cout << "parametr: '" << name << "'\n";
}

Table::~Table()
{
    delete[] table;
    std::cout << "usuwam: '" << this->name << "'\n";
}

void Table::init(std::string name, std::size_t tableLen)
{
    this->name = name;
    this->size = tableLen;
    if (tableLen == 0)
        return;
    if ((table = new (std::nothrow) int[tableLen]) == nullptr)
        this->size = 0;
}

int* Table::copyTable(std::size_t size, int *table) 
{
    if(size == 0)
        return nullptr;
    int* res = new (std::nothrow) int[size];
    if(res == nullptr)
        return res;
    for(int i = 0; i < size; i++)
        res[i] = table[i];
    return res;
}

Table::Table(std::string name, std::size_t size, int *table)
{
    this->table = copyTable(size, table);
    this->name = name;
    this->size = table == nullptr ? 0 : size;
}
