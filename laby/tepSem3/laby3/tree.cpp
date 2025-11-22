#include "tree.h"
#include <treeParser.h>

void Tree::merge(Tree &other)
{
    if(head == nullptr || head->getNext() == nullptr)
    {
        head = other.head;
        other.head = nullptr;
        return;
    }

    TreeNode *previous = nullptr;
    TreeNode *current = head;

    while(current->getNext() != nullptr)
    {
        previous = current;
        current = current->getNext();
    }
    delete current;
    previous->setNext(other.head);
    other.head = nullptr;
}

void Tree::createFromInput(TreeParser &parser)
{
    head = &parser.getToken();
    head->addNext(parser);
}

Tree::Tree(TreeParser &parser)
{
    head = &parser.getToken();
    head->addNext(parser);
}

Tree::Tree()
{
}

Tree::~Tree()
{
    delete head;
}
