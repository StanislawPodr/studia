#include <tree.h>
#include <treeParser.h>

bool Tree::isEmpty()
{
    return head == nullptr;
}

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
    head = parser.getToken();
    if(head == nullptr)
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
    if(head == nullptr) 
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
    if(other.head != nullptr)
    {
        this->head = other.head->copy();
    }
}

Tree &Tree::operator=(const Tree &other)
{
    delete this->head;
    this->head = nullptr;
    if(other.head != nullptr)
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
    return *this;
}

Tree::~Tree()
{
    delete head;
}
