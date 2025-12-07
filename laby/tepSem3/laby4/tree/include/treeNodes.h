#pragma once
#include <treeNode.h>
#include <treeConstants.h>
#include <treeParser.h>
#include <string>

class OperatorNode : public TreeNode
{
public:
    virtual ~OperatorNode() = default;
};

class OperatorNodeTwoNodes : public OperatorNode
{
protected:
    TreeNode *left = nullptr;
    TreeNode *right = nullptr;

public:
    virtual bool addNext(TreeParser &parser);
    virtual ~OperatorNodeTwoNodes();
    virtual TreeNode *getNext();
    virtual void setNext(TreeNode *other);
};

class OperatorNodeOneNode : public OperatorNode
{
protected:
    TreeNode *child = nullptr;

public:
    virtual bool addNext(TreeParser &parser);
    virtual ~OperatorNodeOneNode();
    virtual TreeNode *getNext();
    virtual void setNext(TreeNode *other);
};

class ValueNode : public TreeNode
{
    
public:
    virtual bool addNext(TreeParser &parser);
    virtual TreeNode *getNext();
    virtual void setNext(TreeNode *other);
};


class AddOperatorNode : public OperatorNodeTwoNodes
{
public:
    virtual tree_value_t apply(TreeParser &valueProvider);
    virtual TreeNode* copy();
};

class SubstractOperatorNode : public OperatorNodeTwoNodes
{
public:
    virtual tree_value_t apply(TreeParser &valueProvider);
    virtual TreeNode* copy();
};

class MultiplyOperatorNode : public OperatorNodeTwoNodes
{
public:
    virtual tree_value_t apply(TreeParser &valueProvider);
    virtual TreeNode* copy();
};

class DivisionOperatorNode : public OperatorNodeTwoNodes
{
public:
    virtual tree_value_t apply(TreeParser &valueProvider);
    virtual TreeNode* copy();
};

class SinOperatorNode : public OperatorNodeOneNode
{
public:
    virtual tree_value_t apply(TreeParser &valueProvider);
    virtual TreeNode* copy();
};

class CosOperatorNode : public OperatorNodeOneNode
{
public:
    virtual tree_value_t apply(TreeParser &valueProvider);
    virtual TreeNode* copy();
};


class ValueExistingNode : public ValueNode
{
    tree_value_t value;
public:
    ValueExistingNode(tree_value_t val);
    virtual tree_value_t apply(TreeParser &valueProvider);
    virtual TreeNode* copy();
};

class VariableNode : public ValueNode
{
    std::string variableName;
public:
    VariableNode(std::string varName);
    virtual tree_value_t apply(TreeParser &valueProvider);
    virtual TreeNode* copy();
};