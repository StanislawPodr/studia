cmake_minimum_required(VERSION 3.20...4.0)
set(CMAKE_CXX_STANDARD 11)
set(CMAKE_BUILD_TYPE "Debug")
project(laby1)


add_subdirectory(lib)
add_executable(main main.cpp)
target_link_libraries(main PUBLIC table)