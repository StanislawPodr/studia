#pragma once
#include <treeConstants.h>

class TreeNode
{
public:
    virtual void addNext(TreeParser &const parser) = 0;
    virtual tree_value_t apply() = 0;
    virtual ~TreeNode() = 0;
    virtual TreeNode *getNext() = 0;
    virtual void setNext(TreeNode *other) = 0;
};