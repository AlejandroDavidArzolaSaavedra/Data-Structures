<h1 align="center">Development of a Linear Container with an Ordered Vector</h1>

<p align="center">
  <img width="400px" src="https://github.com/AlejandroDavidArzolaSaavedra/Data-Structures/assets/90756437/aac444fe-27af-4e95-a183-7269514b708c"/>
</p>

This assignment focuses on developing a linear container in Java using an ordered vector as its internal representation. The container performs searches using a binary search approach.

## Objectives 🎯

1. Create the "IntegerContainer" class with the following operations:
   - Constructor with an integer parameter to create empty containers with a maximum size defined by the parameter.
   - "cardinal" function to get the number of elements in the container.
   - "insert" function to add elements to the container.
   - "extract" function to remove elements from the container.
   - "search" function to check the existence of an element in the container.
   - "empty" procedure to leave the container without elements.
   - "elements" function to get an array of integers with the elements of the container ordered from lowest to highest.

2. Implement the internal structure of the container using an ordered vector. Perform binary searches for element retrieval.

## Required Files 📄

- IntegerContainer.java
- TestContainer.java
- output2.txt (download from the provided link)

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
