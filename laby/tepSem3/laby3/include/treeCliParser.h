#pragma once
#include <tree.h>
#include <treeParser.h>
#include <string>
#include <sstream>
#include <unordered_map>

class TreeCliParser : public TreeParser
{
    std::string polishNotationTree = "";
    std::istringstream *iss = nullptr;
    std::unordered_map<std::string, tree_value_t> varsUsed;
    Tree tree;

public:
    virtual TreeNode &getToken();
    virtual tree_value_t getVar(std::string &variableName);
    static bool getNextStringToken(std::istringstream &iss, std::string &result);
    static bool readLine(std::istringstream &iss, std::string &line);
    static bool cvtStr(std::string &word, tree_value_t &result);
    std::string getPolishNotationTree();
    void setForTreeBuild(std::istringstream &iss);
    TreeCliParser(std::istringstream &iss);
    TreeCliParser();
    void vars();
    void enter();
    void comp();
    void join(TreeCliParser &secondTree);
    ~TreeCliParser();
};