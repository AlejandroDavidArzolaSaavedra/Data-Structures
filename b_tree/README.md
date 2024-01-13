<h1 align="center">Development of a Container with a B-Tree</h1>

<p align="center">
  <img width="500px" src="https://github.com/AlejandroDavidArzolaSaavedra/Data-Structures/assets/90756437/6a265034-3432-40d5-bf09-a7391f882ff5"/>
</p>

This assignment focused on developing a container in Java using a B-tree.

## Objectives 🎯

Developed the "IntegerContainer" class in the "practica4" package with the following operations:

1. "create" procedure that created a B-tree and associated it with a file. The file path (String) and the tree order (int) were passed as parameters.
2. "open" procedure that opened the B-tree stored in the file passed as a parameter (a String with the file path) and associated it with the object.
3. "close" procedure that closed the associated file and dissociated the object from the file.
4. "cardinal" function that returned the number of elements in the container.
5. "insert" function to add elements to the container, returned true if added, and false otherwise.
6. "extract" function to remove elements from the container, returned true if removed, and false otherwise. The container remained unchanged if the element was not found.
7. "search" function that returned true if the value passed as a parameter belonged to the container and false otherwise.
8. "empty" procedure to leave the container without any elements.
9. "elements" function that returned an array of integers ordered from lowest to highest with the elements in the container.

## Implementation 🛠️

Implemented the container using a B-tree in secondary memory. Included the partitions, rotations, and recombinations discussed in class. To facilitate development, the classes "FicheroAyuda" and "Conversor" were provided. The "FicheroAyuda" class allowed manipulating direct access files that accept additional data besides reusable records (pages), and the "Conversor" class provided mechanisms for converting between various types and byte arrays to facilitate reading and writing elements in files. Downloaded these classes and incorporated them into the development environment without making any modifications. Did not open them with the browser as their encoding matched that of the environment and not the usual browsers. For the same reason, uploaded the files to be delivered as a submission and did not copy and paste in edit mode.

## Testing Instructions 🧪

1. Developed the `TestContainer` class with a main program that checked the correct functioning of all operations of `IntegerContainer` without using JUnit. Used the content provided in the `TestContainer.java` file as a starting point and ensured that the program compiled and ran without modification.

2. Performed performance tests using the files `data.dat` and `data_no.dat` for B-trees with orders 5, 7, 9, 11, 20, 25, 55, 75, 105, 201, and 301 to conduct a comparative study on the influence of the order on performance.
