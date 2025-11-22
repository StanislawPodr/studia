#pragma once

#include <treeNode.h>
#include <treeConstants.h>

class TreeParser
{
public:
    virtual TreeNode &getToken() = 0;
    virtual tree_value_t getVar(void *variableName) = 0;
    virtual ~TreeParser() = 0;
};