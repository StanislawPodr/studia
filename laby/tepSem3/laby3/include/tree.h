#pragma once
#include <treeConstants.h>

typedef unsigned char node_t;
class TreeNode;
class TreeParser;

class Tree
{
    TreeNode *head = nullptr;
public:
    void merge(Tree &other);
    void createFromInput(TreeParser &parser);
    tree_value_t apply(TreeParser &valueProvider);
    Tree(TreeParser &parser);
    Tree(const Tree &other);
    Tree() = default;
    Tree& operator=(const Tree &second);
    Tree& operator=(const Tree &&second);
    ~Tree();
};