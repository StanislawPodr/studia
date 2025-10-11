add_library(allocator allocator.cpp)
add_library(table table.cpp)

target_include_directories(allocator PUBLIC include)
target_link_libraries(table PUBLIC allocator)
