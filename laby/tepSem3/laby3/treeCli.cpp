#include <treeCli.h>
#include <treeNodes.h>
#include <treeConstants.h>
#include <cmath>
#include <iostream>


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
    fprintf(stdout, "tree: %s\n", myTree.getPolishNotationTree());
}

void TreeCli::comp()
{
   myTree.comp();
}

void TreeCli::join()
{
    TreeCliParser newParser{iss};
    myTree.join(newParser);
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
        }
    }
}
