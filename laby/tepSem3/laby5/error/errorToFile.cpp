#include <errorToFile.h>
#include <cstdio>
#include <string>

int ResultToFileBase::saveErrors(std::vector<Error*> &errors, const char *fileName, FILE *fp)
{
    int i = 0;
    int res = 0;
    while (i < errors.size() && res >= 0)
    {
        res = fprintf(fp, "%s", ((*errors[i++]).getDescription() + "\n").c_str());
    }

    return res;
}