#include <cstddef>
#include <iostream>
#include <allocator.h>

bool allocTable2Dim(int **&table, std::size_t x, std::size_t y)
{
    if (x == 0 || y == 0)
    {
        table = nullptr;
        return true;
    }
    table = new (std::nothrow) int *[x];
    if (table == nullptr)
    {
        table = nullptr;
        return false;
    }
    for (int **xIter = table; xIter < table + x; xIter++)
    {
        *xIter = new (std::nothrow) int[y];
        if (*xIter == nullptr)
        {
            dealocateTable2Dim(table, xIter - table);
            return false;
        }
    }

    return true;
}

bool dealocateTable2Dim(int **&table, std::size_t x)
{
    if (x == 0 || table == nullptr)
        return true;
    for (int **tab = table; tab < table + x; tab++)
        delete[] *tab;
    delete[] table;
    table = nullptr;
    return true;
}