cmake_minimum_required(VERSION 3.0...4.0)
project(tree)
add_executable(main main.cpp tree.cpp include/tree.h treeCli.cpp include/treeCli.h)
target_include_directories(main PUBLIC include)