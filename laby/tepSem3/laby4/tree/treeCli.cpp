#include <treeCli.h>
#include <treeNodes.h>
#include <treeConstants.h>
#include <cmath>
#include <iostream>
#include <errorToFile.h>


void TreeCli::vars()
{
    myTree.vars();
}

void TreeCli::enter()
{
    myTree = TreeCliParser{iss};
}

void TreeCli::print()
{
    std::cout << "tree: "<< myTree.getPolishNotationTree() << "\n";
}

void TreeCli::comp()
{
   myTree.comp();
}

void TreeCli::join()
{
    TreeCliParser newParser{iss};
    if(newParser.getResult().isSuccess())
    {
        myTree.join(newParser);
    }
}

void TreeCli::cliInterfaceInit()
{
    while (TreeCliParser::readLine(iss, line))
    {
        std::string command;
        if (TreeCliParser::getNextStringToken(iss, command))
        {
            if (command == VARS)
            {
                vars();
            }
            else if (command == ENTER)
            {
                enter();
            }
            else if (command == PRINT)
            {
                print();
            }
            else if (command == COMP)
            {
                comp();
            }
            else if (command == JOIN)
            {
                join();
            }
            else 
            {
                std::cout << "No instruction: " <<command <<" found\n";
            }
        }
    }
    ResultToFile<Tree>::saveToFile(myTree, "savedTree.txt");
}

TreeCli::TreeCli() = default;
