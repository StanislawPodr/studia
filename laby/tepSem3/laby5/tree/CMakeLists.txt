add_library (
    tree 
    tree.cpp 
    treeCli.cpp 
    treeCliParser.cpp 
    treeNodes.cpp 
    include/tree.h 
    include/treeCli.h 
    include/treeCliParser.h 
    include/treeConstants.h 
    include/treeNode.h 
    include/treeNodes.h 
    include/treeParser.h
)

target_include_directories(error PUBLIC include)
target_link_libraries(tree PUBLIC error)