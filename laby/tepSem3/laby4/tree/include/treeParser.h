#pragma once

#include <treeNode.h>
#include <treeConstants.h>
#include <string>
#include <tree.h>

class TreeParser
{
public:
    virtual TreeNode *getToken() = 0;
    virtual tree_value_t getVar(std::string &value) = 0;
    virtual ~TreeParser() = default;
    virtual void finished(Tree &result) = 0;
    virtual void failed() = 0;
};