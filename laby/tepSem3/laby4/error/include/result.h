#pragma once
#include <vector>
#include <errors.h>

template <typename T, typename E>
class Result
{
public:
    Result(const T &value);
    Result(E *error);
    Result(std::vector<E *> &errors);
    Result(const Result<T, E> &other);
    Result();
    ~Result();
    static Result<T, E> ok(const T &value);
    static Result<T, E> fail(E *error);
    static Result<T, E> fail(std::vector<E *> &errors);
    Result<T, E> &operator=(const Result<T, E> &other);
    bool isSuccess();
    T getValue();
    T *getValuePtr();
    std::vector<E *> &getErrors();
    void deleteErrors();

private:
    bool success = false;
    T *value = nullptr;
    std::vector<E *> errors;
};

template <typename E>
class Result<void, E>
{
public:
    Result() = default;
    Result(E *error);
    Result(std::vector<E *> &errors);
    Result(const Result<void, E> &other) = default;
    ~Result() = default;
    static Result<void, E> ok();
    static Result<void, E> fail(E *error);
    static Result<void, E> fail(std::vector<E *> &errors);
    Result<void, E> &operator=(const Result<void, E> &other) = default;
    bool isSuccess();
    std::vector<E *> &getErrors();

private:
    std::vector<E *> errors;
    bool success = false;
};

template<typename T>
class Result<T, T>
{
    Result() = delete;
};

#include <result.tpp>