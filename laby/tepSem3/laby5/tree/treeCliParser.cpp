#include <treeCli.h>
#include <treeNodes.h>
#include <treeConstants.h>
#include <cmath>
#include <iostream>
#include <treeCliParser.h>


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


void TreeCliParser::setForTreeBuild(std::istringstream &iss)
{
    this->iss = &iss;
    this->result = Result<Tree, Error>{Tree{}};
}

TreeCliParser::TreeCliParser(std::istringstream &iss) : result{Tree{}}
{
    this->iss = &iss;
    Tree{*this};
}

TreeCliParser::TreeCliParser() = default;


TreeNode *TreeCliParser::getToken()
{
    std::string nextToken;
    if (!getNextStringToken(*iss, nextToken))
    {
        // polishNotationTree += std::string(" ") + std::to_string(FIX_VALUE);
        // std::cout << "Too few arguments, filling with: " << FIX_VALUE << "\n";
        //return new ValueExistingNode{FIX_VALUE};
        std::cout << "Too few arguments, tree creation aborted\n";
        return nullptr;
    }
    else if (nextToken == ADD_OPERATOR)
    {
        polishNotationTree += " " ADD_OPERATOR;
        return new AddOperatorNode;
    }
    else if (nextToken == SUBSTRACT_OPERATOR)
    {
        polishNotationTree += " " SUBSTRACT_OPERATOR;
        return new SubstractOperatorNode;
    }
    else if (nextToken == MULTIPLY_OPERATOR)
    {
        polishNotationTree += " " MULTIPLY_OPERATOR;
        return new MultiplyOperatorNode;
    }
    else if (nextToken == DIVISION_OPERATOR)
    {
        polishNotationTree += " " DIVISION_OPERATOR;
        return new DivisionOperatorNode;
    }
    else if (nextToken == SIN_OPERATOR)
    {
        polishNotationTree += " " SIN_OPERATOR;
        return new SinOperatorNode;
    }
    else if (nextToken == COS_OPERATOR)
    {
        polishNotationTree += " " COS_OPERATOR;
        return new CosOperatorNode;
    }
    else if (isalpha(nextToken[0]))
    {
        polishNotationTree += " ";
        polishNotationTree += nextToken;
        varsUsed[nextToken] = 0;
        return new VariableNode{nextToken};
    }

    // sprawdzanie czy wynik jest liczbą
    tree_value_t result;
    if (!cvtStr(nextToken, result))
    {
        std::cout << "Skipped: "<< nextToken << "\n";
        return getToken();
    }

    polishNotationTree += " ";
    polishNotationTree += std::to_string(result);
    return new ValueExistingNode{result};
}

tree_value_t TreeCliParser::getVar(std::string &variableName)
{
    return varsUsed[variableName];
}

void TreeCliParser::vars()
{
    printf("vars: ");
    for (const auto &var : varsUsed)
    {
        std::cout << var.first << " ";
    }
    putchar('\n');
}


void TreeCliParser::enter()
{
    Tree{*this};
}

void TreeCliParser::comp()
{
    if(!result.isSuccess() || result.getValuePtr()->isEmpty())
    {
        std::cout << "Can't comp, tree is not set\n";
        return;
    }

    std::string word;
    bool notAllFromInput = TreeCliParser::getNextStringToken(*iss, word);
    auto iterator = varsUsed.begin();
    while (notAllFromInput && iterator != varsUsed.end())
    {
        tree_value_t val;
        notAllFromInput = TreeCliParser::cvtStr(word, val) && TreeCliParser::getNextStringToken(*iss, word);
        varsUsed[iterator->first] = val;
        iterator++;
    }
    
    if (!notAllFromInput && iterator == varsUsed.end())
    {
        std::cout << result.getValuePtr()->apply(*this) << "\n";
    }
    else
    {
        std::cout << "Wrong arguments\n";
    }
}

void TreeCliParser::join(TreeCliParser &secondTree)
{
    if(!result.isSuccess() || result.getValuePtr()->isEmpty())
    {
        std::cout << "Can't join, tree is not set. \n";
        return;
    }

    result.getValuePtr()->merge(*secondTree.result.getValuePtr());
    std::string possibleVar = "";

    while(polishNotationTree != "" && !std::isspace(static_cast<unsigned char>(polishNotationTree.back())))
    {
        possibleVar += polishNotationTree.back();
        polishNotationTree.pop_back();
    }

    while(std::isspace(static_cast<unsigned char>(polishNotationTree.back())))
    {
        polishNotationTree.pop_back();
    }
    
    polishNotationTree += secondTree.polishNotationTree;
    varsUsed.erase(possibleVar);
    varsUsed.merge(secondTree.varsUsed);
}

void TreeCliParser::add(TreeCliParser &secondTree)
{
    if(!result.isSuccess() || result.getValuePtr()->isEmpty())
    {
        std::cout << "Can't add, tree is not set. \n";
        return;
    }

    if (!secondTree.result.isSuccess() || secondTree.result.getValuePtr()->isEmpty())
    {
        std::cout << "Can't add, wrong input. \n";
        return;
    }

    polishNotationTree = " +" + polishNotationTree + secondTree.polishNotationTree;

    *result.getValuePtr() = *result.getValuePtr() + *secondTree.result.getValuePtr();
    varsUsed.merge(secondTree.varsUsed);
}

std::vector<Error *> *TreeCliParser::getErrors()
{
    return &result.getErrors();
}

Result<Tree, Error> &TreeCliParser::getResult()
{
    return result;
}

void TreeCliParser::finished(Tree &res)
{
    result = Result<Tree, Error>::ok(res);
}

void TreeCliParser::failed()
{
    result = Result<Tree, Error>::fail(new Error{"Failed to create tree, wrong input. Aborted. \n"});
    for (Error *e: result.getErrors())
    {
        std::cout << e->getDescription() << std::endl;
    }
    polishNotationTree = "";
    varsUsed.clear();
}
