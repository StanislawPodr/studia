#include <iostream>
#include <cstddef>
#include <table.h>
#include <allocator.h>
#include <cassert>

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
    Table table;
    assert(table.name == DEFAULT_NAME);
    Table table1("tablica", 4);
    table1.setName("tablicaa");
    table1.setNewSize(10);
    for(int i = 0; i < 10; i++) 
        table1.table[i] = i;
    Table table2(table1);
    Table* table3 = table1.getClone();
    assert(table3->name == "tablicaa");
    for(int i = 0; i < 10; i++)
    {
        assert(table1.table[i] == i);
        assert(table2.table[i] == i);
        assert(table3->table[i] == i);
    }
    assert(table1.table[3] == 3);
    delete table3;
    return 0;
}