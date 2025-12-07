#pragma once
#include <vector>
#include <errors.h>
#include <string>
#include <cstdio>
#include <result.h>
#include <tree.h>
#include <treeCliParser.h>

class ResultToFileBase
{
protected:
    static int saveErrors(std::vector<Error *> &errors, const char *fileName, FILE *fp);
};

template <typename T>
class ResultToFile : public ResultToFileBase
{
public:
    static bool saveToFile(Result<T, Error> &result, const char *fileName)
    {
        FILE *fp = fopen(fileName, "w");
        if (fp == NULL)
        {
            return false;
        }

        return ResultToFileBase::saveErrors(result.getErrors(), fileName, fp) >= 0;
    }
};

template <>
class ResultToFile<Tree> : public ResultToFileBase
{
public:
    static bool saveToFile(TreeCliParser &parser, const char *fileName)
    {
        FILE *fp = fopen(fileName, "w");
        if (fp == NULL)
        {
            return false;
        }

        bool savedErr = ResultToFileBase::saveErrors(*parser.getErrors(), fileName, fp) >= 0;
        return savedErr && fprintf(fp, "%s", (parser.getPolishNotationTree() + "\n").c_str()) >= 0;
    }
};