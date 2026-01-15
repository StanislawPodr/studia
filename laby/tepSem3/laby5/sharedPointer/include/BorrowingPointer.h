#pragma once

#include "SharedPointer.h"

template <typename T>
class BorrowingPointer
{
public:
    BorrowingPointer() = default;

    BorrowingPointer(const BorrowingPointer<T> &other)
    {
        ptr = other.ptr;
        counter = other.counter;
        if (ptr != nullptr)
        {
            counter->addBorrowing(*this);
        }
    }

    BorrowingPointer(const SharedPointer<T> &sharedPtr)
    {
        ptr = sharedPtr.counter->ptr;
        sharedPtr.counter->addBorrowing(*this);
    }

    ~BorrowingPointer()
    {
        if (ptr != nullptr)
        {
            counter->removeBorrowing(*this);
        }
    }

    BorrowingPointer<T> &operator=(const BorrowingPointer<T> &other)
    {
        if (other.counter == counter)
        {
            return;
        }

        if (other.ptr != nullptr)
        {
            other.counter->addBorrowing(*this);
        }

        if (ptr != nullptr)
        {
            counter->removeBorrowing(*this);
        }

        ptr = other.ptr;
        counter = other.counter;
    }

    T &operator*() { return (*ptr); }
    T *operator->() { return (ptr); }

private:
    BorrowingPointer(RefCounter<T> *counter) : counter(counter), ptr(counter->ptr) {}

    void notify()
    {
        ptr = nullptr;
    }

    T *ptr = nullptr;
    RefCounter<T> *counter = nullptr;
    friend class SharedPointer<T>;
};