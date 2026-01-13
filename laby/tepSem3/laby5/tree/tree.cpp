#include <tree.h>
#include <treeParser.h>
#include <treeNodes.h>
#include <treeNode.h>

bool Tree::isEmpty()
{
    return head == nullptr;
}

void Tree::merge(Tree &other)
{
    noCopies += other.noCopies;
    if (head == nullptr || head->getNext() == nullptr)
    {
        head = other.head;
        other.head = nullptr;
        return;
    }

    TreeNode *previous = nullptr;
    TreeNode *current = head;

    while (current->getNext() != nullptr)
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
    head = parser.getToken();
    if (head == nullptr)
    {
        parser.failed();
        return;
    }

    if (!head->addNext(parser))
    {
        delete head;
        head = nullptr;
        parser.failed();
        return;
    }

    parser.finished(*this);
}

tree_value_t Tree::apply(TreeParser &parser)
{
    if (head == nullptr)
    {
        return 0;
    }

    return head->apply(parser);
}

Tree::Tree(TreeParser &parser)
{
    createFromInput(parser);
}

Tree::Tree(const Tree &other)
{
    noCopies += other.noCopies + 1;
    if (other.head != nullptr)
    {
        this->head = other.head->copy();
    }
}

Tree::Tree(Tree &&rvalue)
{
    delete this->head;
    this->head = rvalue.head;
    rvalue.head = nullptr;
    this->noCopies = rvalue.noCopies;
}

Tree &Tree::operator=(const Tree &other)
{
    this->noCopies += other.noCopies + 1;
    delete this->head;
    this->head = nullptr;
    if (other.head != nullptr)
    {
        this->head = other.head->copy();
    }
    return *this;
}

Tree &Tree::operator=(Tree &&other)
{
    delete this->head;
    this->head = other.head;
    other.head = nullptr;
    this->noCopies = other.noCopies;
    return *this;
}

Tree Tree::operator+(Tree second) &
{
    Tree result{*this};
    AddOperatorNode *addNode = new AddOperatorNode{result.head, second.head};
    result.head = addNode;
    second.head = nullptr;
    result.noCopies += second.noCopies;
    return result;
}

Tree Tree::operator+(Tree second) &&
{
    Tree &result = *this;
    AddOperatorNode *addNode = new AddOperatorNode{result.head, second.head};
    result.head = addNode;
    second.head = nullptr;
    result.noCopies += second.noCopies;
    return result;
}

Tree::~Tree()
{
    delete head;
}
