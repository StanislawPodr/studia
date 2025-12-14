class RefCounter
{
public:
    RefCounter() { count; }
    int add() { return (++count); }
    int dec() { return (--count); };
    int get() { return (count); }

private:
    int count;
};

template <typename T>
class SharedPointer
{
public:
    SharedPointer(T *pointer)
    {
        pointer = pointer;
        counter = new RefCounter();
        counter->add();
    }

    SharedPointer(const SharedPointer &other)
    {
        clone(other);
    }

    ~SharedPointer()
    {
        dispose();
    }
    T &operator*() { return (*pointer); }
    T *operator->() { return (pointer); }

    SharedPointer& operator=(const SharedPointer &other)
    {
        dispose();
        clone(other);
    }

private:

    void clone(const SharedPointer &other) 
    {
        pointer = other.pointer;
        counter = other.counter;
        counter->add();
    }

    void dispose()
    {
        if (counter->dec() == 0)
        {
            delete pointer;
            delete counter;
        }
    }

    RefCounter *counter;
    T *pointer;
};