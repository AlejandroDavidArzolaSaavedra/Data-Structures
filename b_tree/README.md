
<h1 align="center">Development of a Container with a B-Tree</h1>

<p align="center">
  <img width="400px" src="https://github.com/AlejandroDavidArzolaSaavedra/Data-Structures/assets/90756437/4d93cd90-dcb9-48cc-ab25-9650ea0c2dc9"/>
</p>

This assignment focuses on developing a container in Java using a B-tree.

## Objectives 🎯

Develop the "IntegerContainer" class in the "practica4" package with the following operations:

1. "create" procedure that creates a B-tree and associates it with a file. The file path (String) and the tree order (int) are passed as parameters.
2. "open" procedure that opens the B-tree stored in the file passed as a parameter (a String with the file path) and associates it with the object.
3. "close" procedure that closes the associated file and dissociates the object from the file.
4. "cardinal" function that returns the number of elements in the container.
5. "insert" function to add elements to the container, returns true if added, and false otherwise.
6. "extract" function to remove elements from the container, returns true if removed, and false otherwise. The container remains unchanged if the element is not found.
7. "search" function that returns true if the value passed as a parameter belongs to the container and false otherwise.
8. "empty" procedure to leave the container without any elements.
9. "elements" function that returns an array of integers ordered from lowest to highest with the elements in the container.

## Implementation 🛠️

Implement the container using a B-tree in secondary memory. Include the partitions, rotations, and recombinations discussed in class. To facilitate development, the classes "FicheroAyuda" and "Conversor" are provided. The "FicheroAyuda" class allows manipulating direct access files that accept additional data besides reusable records (pages), and the "Conversor" class provides mechanisms for converting between various types and byte arrays to facilitate reading and writing elements in files. Download these classes and incorporate them into the development environment without making any modifications. Do not open them with the browser as their encoding matches that of the environment and not the usual browsers. For the same reason, upload the files to be delivered as a submission and do not copy and paste in edit mode.

## Testing Instructions 🧪

1. Develop the `TestContainer` class with a main program that checks the correct functioning of all operations of `IntegerContainer` without using JUnit. You can use the content provided in the `TestContainer.java` file as a starting point, and ensure that the program compiles and runs without modification.

2. Perform performance tests using the files `data.dat` and `data_no.dat` for B-trees with orders 5, 7, 9, 11, 20, 25, 55, 75, 105, 201, and 301 to conduct a comparative study on the influence of the order on performance.

## Final Report 📊

Prepare a report that includes:

1. Graph of the average insertion time in the increasing phase.
2. Graph of the average extraction time in the decreasing phase.
3. Graph of the average successful search time for different sizes of the structure.
4. Graph of the average unsuccessful search time for different sizes of the structure.
5. Comparison between insertion and extraction times.
6. Comparison between times of successful and unsuccessful searches.
7. Two additional graphs:
   - Average time for each operation (insertion, extraction, successful search, and unsuccessful search) versus the order (X-axis: different orders proposed, Y-axis: average time of the operation for all sizes).
   - Average time for each operation versus the size of the structure (X-axis: different sizes, Y-axis: average time of the operation for all orders).

Use the above graphs, along with any others you consider appropriate, to select the best representative of the B-tree for comparison with the other structures and justify your choice.

For each graph, provide a justification of its content and possible causes of the observed behavior.
