#include <iostream>
#include <cstddef>
#include <table.h>

void allocTableWith34(std::size_t size)
{
    int *tableOnHeap = new (std::nothrow) int[size];
    if (tableOnHeap == nullptr)
        return;
    for (std::size_t i = 0; i < size; i++)
        tableOnHeap[i] = 34;
    for (std::size_t i = 0; i < size; i++)
        std::cout << tableOnHeap[i] << std::endl;
    delete[] tableOnHeap;
}

int main()
{
    allocTableWith34(5);
    return 0;
}