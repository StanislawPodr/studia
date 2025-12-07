#pragma once
#include <treeConstants.h>

class TreeParser;

class TreeNode
{
public:
    virtual bool addNext(TreeParser &parser) = 0;
    virtual tree_value_t apply(TreeParser &valueProvider) = 0;
    virtual ~TreeNode() = default;
    virtual TreeNode *getNext() = 0;
    virtual void setNext(TreeNode *other) = 0;
    virtual TreeNode* copy() = 0;
    TreeNode() = default;
};