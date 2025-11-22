#include <tree.h>
#include <treeParser.h>
#include <string>
#include <sstream>

class TreeCliParser : public TreeParser
{
    std::string polishNotationTree = "";
    tree_value_t varsUsedSet[128] = {0};
    std::istringstream *iss = nullptr;

public:
    virtual TreeNode &getToken();
    virtual tree_value_t getVar(void *variableName);
    static bool getNextStringToken(std::istringstream &iss, std::string &result);
    static bool readLine(std::istringstream &iss, std::string &line);
    static bool cvtStr(std::string &word, tree_value_t &result);
    std::string getPolishNotationTree();
    bool getNextVarChar(char &result, const char *&current);
    void setForTreeBuild(std::istringstream &iss);
    TreeCliParser(std::istringstream &iss);
    Tree tree;
    void vars();
    void enter();
    void comp();
    void join(TreeCliParser &secondTree);
};