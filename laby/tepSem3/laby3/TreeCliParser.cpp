#include <treeCli.h>
#include <treeNodes.h>
#include <treeConstants.h>
#include <cmath>
#include <iostream>
#include "treeCliParser.h"


bool TreeCliParser::getNextStringToken(std::istringstream &iss, std::string &result)
{
    return static_cast<bool>(iss >> result);
}

bool TreeCliParser::readLine(std::istringstream &iss, std::string &line)
{
    if (!std::getline(std::cin, line))
    {
        return false;
    }
    iss.clear();
    iss.str(line);
    return true;
}

bool TreeCliParser::cvtStr(std::string &word, tree_value_t &result)
{
    const char *str = word.c_str();
    char *endOfChar = NULL;
    result = (tree_value_t)strtod(str, &endOfChar);
    if (endOfChar == NULL || *endOfChar != '\0')
    {
        return false;
    }

    if (errno == ERANGE)
    {
        fprintf(stdout, "range error, to big number, changing to infinity\n");
        result = INFINITY;
        errno = 0;
    }
    return true;
}

std::string TreeCliParser::getPolishNotationTree()
{
    return polishNotationTree;
}

bool TreeCliParser::getNextVarChar(char &result, const char *&current)
{
    current++;
    result = -1;
    while (*current != '\0' && result == -1)
    {
        while (*current != '\0' && !isalpha(*current))
            current++;

        if (*current != '\0' && *(current + 1) != ' ' && *(current + 1) != '\0')
        {
            while (*current != '\0' && *current != ' ')
                current++;
        }
        else
        {
            result = *current;
        }
    }
    return *current != '\0';
}

void TreeCliParser::setForTreeBuild(std::istringstream &iss)
{
    this->iss = &iss;
}

TreeCliParser::TreeCliParser(std::istringstream &iss)
{
    this->iss = &iss;
    this->tree = Tree{*this};
}

TreeNode &TreeCliParser::getToken()
{
    std::string nextToken;
    if (!getNextStringToken(*iss, nextToken))
    {
        polishNotationTree += std::string(" ") + std::to_string(FIX_VALUE);
        return *(new ValueExistingNode{FIX_VALUE});
    }
    else if (nextToken == ADD_OPERATOR)
    {
        polishNotationTree += " " ADD_OPERATOR;
        return *(new AddOperatorNode);
    }
    else if (nextToken == SUBSTRACT_OPERATOR)
    {
        polishNotationTree += " " SUBSTRACT_OPERATOR;
        return *(new SubstractOperatorNode);
    }
    else if (nextToken == MULTIPLY_OPERATOR)
    {
        polishNotationTree += " " MULTIPLY_OPERATOR;
        return *(new MultiplyOperatorNode);
    }
    else if (nextToken == DIVISION_OPERATOR)
    {
        polishNotationTree += " " DIVISION_OPERATOR;
        return *(new DivisionOperatorNode);
    }
    else if (nextToken == SIN_OPERATOR)
    {
        polishNotationTree += " " SIN_OPERATOR;
        return *(new SinOperatorNode);
    }
    else if (nextToken == COS_OPERATOR)
    {
        polishNotationTree += " " COS_OPERATOR;
        return *(new CosOperatorNode);
    }
    else if (nextToken.size() == 1 && isalpha(nextToken[0]))
    {
        polishNotationTree += " ";
        polishNotationTree += nextToken[0];
        tree_value_t *ptrToSet = &varsUsedSet[nextToken[0]];
        return *(new VariableNode{*this, (void *)ptrToSet});
    }

    // sprawdzanie czy wynik jest liczbą
    tree_value_t result;
    if (!cvtStr(nextToken, result))
    {
        fprintf(stdout, "Skipped: %s\n", nextToken);
        return getToken();
    }

    polishNotationTree += " ";
    polishNotationTree += result;
    return *(new ValueExistingNode{result});
}

tree_value_t TreeCliParser::getVar(void *variableName)
{
    return varsUsedSet[*(char *)variableName];
}

void TreeCliParser::vars()
{
    char result;
    const char *current = getPolishNotationTree().c_str();
    fprintf(stdout, "vars: ");
    while (getNextVarChar(result, current))
    {
        fprintf(stdout, "%c ", result);
    }
    fprintf(stdout, "\n");
}


void TreeCliParser::enter()
{
    tree = Tree{*this};
}

void TreeCliParser::comp()
{
    char var;
    const char *currentVar = getPolishNotationTree().c_str();
    bool allVars = true;
    while (allVars && getNextVarChar(var, currentVar))
    {
        std::string word;
        tree_value_t val;
        allVars = TreeCliParser::getNextStringToken(*iss, word) && TreeCliParser::cvtStr(word, val);
        varsUsedSet[word[0]] = 
    }
}

void TreeCliParser::join(TreeCliParser &secondTree)
{
    tree.merge(secondTree.tree);
    polishNotationTree += secondTree.polishNotationTree;
}
