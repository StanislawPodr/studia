cmake_minimum_required(VERSION 3.0...4.0)

project(exceptions)
add_subdirectory(error)
add_subdirectory(tree)


add_executable(main main.cpp)

target_link_libraries(main PUBLIC error tree)