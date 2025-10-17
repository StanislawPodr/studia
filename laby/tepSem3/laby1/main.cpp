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
    assert(table.getName() == DEFAULT_NAME);
    Table table1("tablica", 4);
    table1.setName("tablicaa");
    table1.setNewSize(10);
    for(int i = 0; i < 10; i++) 
        table1.setIndex(i, i);
    Table table2(table1);
    Table* table3 = table1.getClone();
    assert(table3->getName() == "tablicaa");
    for(int i = 0; i < 10; i++)
    {
        assert(table1.getIndex(i) == i);
        assert(table2.getIndex(i) == i);
        assert(table3->getIndex(i) == i);
    }
    assert(table1.getIndex(3) == 3);
    table.insertHere(table1, 3);
    table.printTable();
    table1.insertHere(table2, 2);
    table1.printTable();
    Table table4("test", 4);
    table4.setIndex(0, 45);
    table4.setIndex(1, -14);
    table4.setIndex(2, 78);
    table4.setIndex(3, -199);
    table1.insertHere(table4, 8);
    table1.printTable();
    delete table3;
    return 0;
}