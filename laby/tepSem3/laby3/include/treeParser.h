#pragma once

#include <treeNode.h>
#include <treeConstants.h>
#include <string>

class TreeParser
{
public:
    virtual TreeNode &getToken() = 0;
    virtual tree_value_t getVar(std::string &value) = 0;
    virtual ~TreeParser() = default;
};