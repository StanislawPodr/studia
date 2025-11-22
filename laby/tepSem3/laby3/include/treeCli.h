#pragma once
#include <tree.h>
#include <treeParser.h>
#include <string>
#include <sstream>
#include <treeCliParser.h>

class TreeCli
{
    std::string line = "";
    std::istringstream iss{};
    void vars();
    void enter();
    void print();
    void comp();
    void join();
public:
    TreeCliParser myTree;
    void cliInterfaceInit();
};