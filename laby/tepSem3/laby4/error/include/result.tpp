#pragma once
#include <result.h>
#include <errors.h>

template <typename T, typename E>
Result<T, E>::Result(const T &value)
{
    this->value = new T{value};
}

template <typename T, typename E>
Result<T, E>::Result(E *error)
{
    this->errors = std::vector<E *>{error};
}

template <typename E>
Result<void, E>::Result(E *error)
{
    this->errors = std::vector<E *>{error};
}

template <typename T, typename E>
Result<T, E>::Result(std::vector<E *> &errors)
{
    this->errors = errors;
}

template <typename E>
Result<void, E>::Result(std::vector<E *> &errors)
{
    this->errors = errors;
}


template <typename T, typename E>
Result<T, E>::Result(const Result<T, E> &other)
{
    this->errors = other.errors;
    this->success = other.success;
    if(other.value != nullptr)
    {
        this->value = new T{*other.value};
    }
}

template <typename T, typename E>
Result<T, E>::~Result()
{
    delete value;
    deleteErrors();
}

template <typename E>
Result<void, E> Result<void, E>::ok()
{
    Result<void, E> ok{};
    ok.success = true;
    return ok;
}

template <typename T, typename E>
Result<T, E> Result<T, E>::ok(const T &value)
{
    Result<T, E> ok{value};
    ok.success = true;
    return ok;
}

template <typename T, typename E>
Result<T, E> Result<T, E>::fail(E *error)
{
    return  Result<T, E>{error};
}

template <typename E>
Result<void, E> Result<void, E>::fail(E *error)
{
    return  Result<void, E>{error};
}


template <typename T, typename E>
Result<T, E> Result<T, E>::fail(std::vector<E *> &errors)
{
    return Result<T, E>{errors};
}

template <typename E>
Result<void, E> Result<void, E>::fail(std::vector<E *> &errors)
{
    return Result<void, E>{errors};
}

template <typename T, typename E>
Result<T, E> &Result<T, E>::operator=(const Result<T, E> &other)
{
    deleteErrors();
    this->errors.clear();
    this->errors.reserve(other.errors.size());
    for (Error *e: other.errors)
    {
        this->errors.push_back(new Error{*e});
    }
    
    delete this->value;
    this->value = nullptr;
    if (other.value != nullptr)
    {
        this->value = new T{*other.value};
    }
    this->success = other.success;
    return *this;
}


template <typename T, typename E>
bool Result<T, E>::isSuccess()
{
    return success;
}

template <typename E>
bool Result<void, E>::isSuccess()
{
    return success;
}

template <typename T, typename E>
T Result<T, E>::getValue()
{
    if(value == nullptr)
    {
        return T{};
    }

    return *value;
}

template <typename T, typename E>
inline T *Result<T, E>::getValuePtr()
{
    return value;
}

template <typename T, typename E>
std::vector<E *> &Result<T, E>::getErrors()
{
    return errors;
}

template <typename T, typename E>
void Result<T, E>::deleteErrors()
{
    for (E* err: errors)
    {
        delete err;
    }
}

template <typename E>
std::vector<E *> &Result<void, E>::getErrors()
{
    return errors;
}

