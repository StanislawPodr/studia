cmake_minimum_required(VERSION 3.0...4.0)
project(tree)
add_executable(main main.cpp tree.cpp treeCli.cpp treeCliParser.cpp treeNodes.cpp include/tree.h include/treeCli.h include/treeCliParser.h include/treeConstants.h include/treeNode.h include/treeNodes.h include/treeParser.h)
target_include_directories(main PUBLIC include)