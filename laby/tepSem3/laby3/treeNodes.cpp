#include <treeNodes.h>
#include <cmath>


void OperatorNodeTwoNodes::addNext(TreeParser &const parser)
{
    left = &parser.getToken();
    left->addNext(parser);

    right = &parser.getToken();
    right->addNext(parser);
}

void OperatorNodeOneNode::addNext(TreeParser &const parser)
{
    child = &parser.getToken();
    child->addNext(parser);
}

void ValueNode::addNext(TreeParser &const parser)
{
    return;
}

tree_value_t AddOperatorNode::apply()
{
    return left->apply() + right->apply();
}

tree_value_t SubstractOperatorNode::apply()
{
    return left->apply() - right->apply();
}

tree_value_t MultiplyOperatorNode::apply()
{
    return left->apply() * right->apply();
}

tree_value_t DivisionOperatorNode::apply()
{
    return left->apply() / right->apply();
}

tree_value_t SinOperatorNode::apply()
{
    return sin(child->apply());
}

tree_value_t CosOperatorNode::apply()
{
    return cos(child->apply());
}

tree_value_t ValueExistingNode::apply()
{
    return value;
}

tree_value_t VariableNode::apply()
{
    return valueProvider.getVar(variableName);
}

ValueExistingNode::ValueExistingNode(tree_value_t val) : value(val)
{
}

VariableNode::VariableNode(TreeParser &valProvider, void *varName) : valueProvider(valProvider), variableName(varName)
{
}

OperatorNodeOneNode::~OperatorNodeOneNode()
{
    delete child;
}

OperatorNodeTwoNodes::~OperatorNodeTwoNodes()
{
    delete left;
    delete right;
}

TreeNode *OperatorNodeTwoNodes::getNext()
{
    return right;
}

void OperatorNodeTwoNodes::setNext(TreeNode *other)
{
    right = other;
}

TreeNode *OperatorNodeOneNode::getNext()
{
    return child;
}

void OperatorNodeOneNode::setNext(TreeNode *other)
{
    child = other;
}

TreeNode *ValueNode::getNext()
{
    return nullptr;
}

void ValueNode::setNext(TreeNode *other)
{
    return;
}
