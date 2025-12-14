#pragma once
#include <treeConstants.h>
#include <cstddef>

typedef unsigned char node_t;
class TreeNode;
class TreeParser;

class Tree
{
    TreeNode *head = nullptr;
    size_t noCopies = 0;

public:
    bool isEmpty();
    void merge(Tree &other);
    void createFromInput(TreeParser &parser);
    tree_value_t apply(TreeParser &valueProvider);
    Tree(TreeParser &parser);
    Tree(const Tree &other);
    Tree(Tree &&rvalue);
    Tree() = default;
    Tree &operator=(const Tree &second);
    Tree &operator=(Tree &&second);
    Tree operator+(Tree second);
    Tree operator+(Tree &&second);
    size_t getNoCopies() { return noCopies; }
    ~Tree();
};