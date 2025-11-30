#pragma once

template <typename T, typename E>
class Result
{
public:
    Result(const T &value);
    Result(E *error);
    Result(std::vector<E *> &errors);
    Result(const Result<T, E> &other);
    ~Result();
    static Result<T, E> ok(const T &value);
    static Result<T, E> fail(E *error);
    static Result<T, E> fail(std::vector<E *> &errors);
    Result<T, E> &operator=(const Result<T, E> &other);
    bool isSuccess();
    T getValue();
    std::vector<E *> &getErrors();

private:
    T *value;
    vector<E *> errors;
};
