cmake_minimum_required(VERSION 3.0.0...4.0.0)

project("limitedCapacitatedVehicleRoutingProblem")
add_executable(main main.cpp Evaluator.cpp FileReader.cpp Individual.cpp Traveller.cpp)