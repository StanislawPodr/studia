#pragma once
#include <treeNode.h>
#include <treeConstants.h>
#include <treeParser.h>

class OperatorNode : public TreeNode
{
public:
    virtual ~OperatorNode() = 0;
};

class OperatorNodeTwoNodes : public OperatorNode
{
protected:
    TreeNode *left = nullptr;
    TreeNode *right = nullptr;

public:
    virtual void addNext(TreeParser &const parser);
    virtual ~OperatorNodeTwoNodes();
    virtual TreeNode *getNext();
    virtual void setNext(TreeNode *other);
};

class OperatorNodeOneNode : public OperatorNode
{
protected:
    TreeNode *child = nullptr;

public:
    virtual void addNext(TreeParser &const parser);
    virtual ~OperatorNodeOneNode();
    virtual TreeNode *getNext();
    virtual void setNext(TreeNode *other);
};

class ValueNode : public TreeNode
{
    
public:
    virtual void addNext(TreeParser &const parser);
    virtual TreeNode *getNext();
    virtual void setNext(TreeNode *other);
};


class AddOperatorNode : public OperatorNodeTwoNodes
{
public:
    virtual tree_value_t apply();
};

class SubstractOperatorNode : public OperatorNodeTwoNodes
{
public:
    virtual tree_value_t apply();
};

class MultiplyOperatorNode : public OperatorNodeTwoNodes
{
public:
    virtual tree_value_t apply();
};

class DivisionOperatorNode : public OperatorNodeTwoNodes
{
public:
    virtual tree_value_t apply();
};

class SinOperatorNode : public OperatorNodeOneNode
{
public:
    virtual tree_value_t apply();
};

class CosOperatorNode : public OperatorNodeOneNode
{
public:
    virtual tree_value_t apply();
};


class ValueExistingNode : public ValueNode
{
    tree_value_t value;
public:
    ValueExistingNode(tree_value_t val);
    virtual tree_value_t apply();
};

class VariableNode : public ValueNode
{
    TreeParser &valueProvider;
    void* variableName;
public:
    VariableNode(TreeParser &valProvider, void *varName);
    virtual tree_value_t apply();
};