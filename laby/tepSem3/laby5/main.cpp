#include <error.h>
#include <result.h>
#include <iostream>
#include <tree.h>
#include <treeCli.h>
#include <errorToFile.h>
#include "sharedPointer/include/BorrowingPointer.h"

int main()
{
    // TreeCli cli{};
    // cli.cliInterfaceInit();
    BorrowingPointer<int> borrowing;
    {
        SharedPointer<int> myShared(new int{8});
        borrowing = BorrowingPointer{myShared};
        {
            SharedPointer second = myShared;
        }

        {
            SharedPointer third(myShared);
            BorrowingPointer<int> borrowed = third.borrow();
            std::cout << *borrowed;
        }

    }

    return 0;
}