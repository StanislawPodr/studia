#pragma once

#include "BorrowingPointer.h"
#include <vector>

template <typename T>
class RefCounter
{
public:
    T *ptr;

    RefCounter(T *pointer) : ptr(pointer) {}
    int add() { return (++count); }
    int dec()
    {
        if (--count == 0)
        {
            notifyPtrs();
            delete ptr;
            delete this;
        }

        return count;
    }

    void addBorrowing(BorrowingPointer<T> &borrowed)
    {
        ptrs.push_back(&borrowed);
    }

    int get() { return (count); }

    void removeBorrowing(const BorrowingPointer<T> &other)
    {
        for (int i = 0; i < ptrs.size(); i++)
        {
            if (&other == ptrs[i])
            {
                ptrs.erase(i);
                return;
            }
        }
    }

private:
    void notifyPtrs()
    {
        for (auto ptr : ptrs)
        {
            ptr->notify();
        }
    }

    std::vector<BorrowingPointer<T> *> ptrs;
    int count = 0;
};

template <typename T>
class SharedPointer
{
public:
    SharedPointer(T *pointer)
    {
        counter = new RefCounter<T>(pointer);
        counter->add();
    }

    SharedPointer(const SharedPointer<T> &other)
    {
        clone(other);
    }

    ~SharedPointer()
    {
        dispose();
    }

    T &operator*() { return (*counter->ptr); }
    T *operator->() { return (counter->ptr); }

    SharedPointer &operator=(const SharedPointer<T> &other)
    {
        dispose();
        clone(other);
    }

    BorrowingPointer<T> borrow()
    {
        return BorrowingPointer<T>(*this);
    }

private:
    void clone(const SharedPointer<T> &other)
    {
        counter = other.counter;
        counter->add();
    }

    void dispose()
    {
        counter->dec();
    }

    RefCounter<T> *counter;
    friend class BorrowingPointer<T>;
};