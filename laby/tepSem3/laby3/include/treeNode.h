#pragma once

class TreeNode 
{
    protected:
        TreeNode *left, *right;
    public:
        virtual void apply();
        virtual void add();
};

class OperatorNode : public TreeNode
{
    typedef unsigned char operator_t;
    

};

class ValueNode : public TreeNode
{
    typedef int value_t;
    private:
        value_t value;
        
};