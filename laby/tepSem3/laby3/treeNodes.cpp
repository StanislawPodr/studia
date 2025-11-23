#include <treeNodes.h>
#include <cmath>


void OperatorNodeTwoNodes::addNext(TreeParser &parser)
{
    left = &parser.getToken();
    left->addNext(parser);

    right = &parser.getToken();
    right->addNext(parser);
}

void OperatorNodeOneNode::addNext(TreeParser &parser)
{
    child = &parser.getToken();
    child->addNext(parser);
}

void ValueNode::addNext(TreeParser &parser)
{
    return;
}

tree_value_t AddOperatorNode::apply(TreeParser &valueProvider)
{
    return left->apply(valueProvider) + right->apply(valueProvider);
}

TreeNode *AddOperatorNode::copy()
{
    AddOperatorNode* cp = new AddOperatorNode;
    cp->left = left->copy();
    cp->right = right->copy();
    return cp;
}

tree_value_t SubstractOperatorNode::apply(TreeParser &valueProvider)
{
    return left->apply(valueProvider) - right->apply(valueProvider);
}

TreeNode *SubstractOperatorNode::copy()
{
    SubstractOperatorNode* cp = new SubstractOperatorNode;
    cp->left = left->copy();
    cp->right = right->copy();
    return cp;
}

tree_value_t MultiplyOperatorNode::apply(TreeParser &valueProvider)
{
    return left->apply(valueProvider) * right->apply(valueProvider);
}

TreeNode *MultiplyOperatorNode::copy()
{
    MultiplyOperatorNode* cp = new MultiplyOperatorNode;
    cp->left = left->copy();
    cp->right = right->copy();
    return cp;
}

tree_value_t DivisionOperatorNode::apply(TreeParser &valueProvider)
{
    return left->apply(valueProvider) / right->apply(valueProvider);
}

TreeNode *DivisionOperatorNode::copy()
{
    DivisionOperatorNode* cp = new DivisionOperatorNode;
    cp->left = left->copy();
    cp->right = right->copy();
    return cp;
}

tree_value_t SinOperatorNode::apply(TreeParser &valueProvider)
{
    return sin(child->apply(valueProvider));
}

TreeNode *SinOperatorNode::copy()
{
    SinOperatorNode* cp = new SinOperatorNode;
    cp->child = child->copy();
    return cp;
}

tree_value_t CosOperatorNode::apply(TreeParser &valueProvider)
{
    return cos(child->apply(valueProvider));
}

TreeNode *CosOperatorNode::copy()
{
    CosOperatorNode* cp = new CosOperatorNode;
    cp->child = child->copy();
    return cp;
}

tree_value_t ValueExistingNode::apply(TreeParser &valueProvider)
{
    return value;
}

TreeNode *ValueExistingNode::copy()
{
    return new ValueExistingNode{value};
}

tree_value_t VariableNode::apply(TreeParser &valueProvider)
{
    return valueProvider.getVar(variableName);
}

TreeNode *VariableNode::copy()
{
    return new VariableNode{variableName};
}

ValueExistingNode::ValueExistingNode(tree_value_t val) : value(val)
{
}

VariableNode::VariableNode(std::string varName) : variableName(varName)
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
