#pragma once
#include <cstddef>

bool allocTable2Dim(int **&table, std::size_t x, std::size_t y);
bool dealocateTable2Dim(int **&table, std::size_t x);