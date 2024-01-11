<h1 align="center">Development of a Container with a Binary Search Tree</h1>

<p align="center">
  <img width="400px" src="https://github.com/AlejandroDavidArzolaSaavedra/Data-Structures/assets/90756437/4b5adaaa-15f2-4cd1-816d-e1a4f8dbdd53"/>
</p>

This assignment focuses on developing a container in Java using a binary search tree.

## Objectives 🎯

Develop the "IntegerContainer" class in the "practica3" package with the following operations:

1. Default constructor to create empty containers.
2. "cardinal" function to get the number of elements in the container.
3. "insert" function to add elements to the container.
4. "extract" function to remove elements from the container.
5. "search" function to check the existence of an element in the container.
6. "empty" procedure to leave the container without elements.
7. "elements" function to get an array of integers ordered from lowest to highest.

## Implementation 🛠️

Implement the container using a binary search tree in dynamic memory without rebalancing operations.

## Testing Instructions 🧪

1. Develop the `TestContainer` class with a main program that checks the correct functioning of all operations of `IntegerContainer`, without using JUnit.

2. Perform performance tests using the files `data.dat` and `data_no.dat`:
   - Insertions and extractions of elements.
   - Successful and unsuccessful searches.

## Final Report 📊

Prepare a report that includes:

1. Graph of the average insertion time in the increasing phase.
2. Graph of the average extraction time in the decreasing phase.
3. Graph of the average successful search time for different sizes of the structure.
4. Graph of the average unsuccessful search time for different sizes of the structure.
5. Comparison between insertion and extraction times.
6. Comparison between times of successful and unsuccessful searches.

Each graph should be accompanied by a justification and analysis of possible causes of the observed behavior.
