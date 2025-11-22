#pragma once

typedef unsigned char node_t;
class TreeNode;
class TreeParser;

class Tree
{
    TreeNode *head = nullptr;
public:
    void merge(Tree &other);
    void createFromInput(TreeParser &parser);
    Tree(TreeParser &parser);
    Tree();
    ~Tree();
};