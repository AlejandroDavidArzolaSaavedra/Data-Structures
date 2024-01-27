# Comparison of Insertions in Data Structures 📊📉

In the "doc" folder, there is a report that provides a brief description of observations and conclusions obtained from analyzing insertions in different data structures: linked list, sorted vector, binary search tree, and B-tree.

## Linked List 🧐

Upon observing the graphs, it is evident that insertion in a linked list is the slowest. This is because searches for the element to be inserted are performed sequentially, comparing values one by one. This linear approach results in a longer execution time, especially as the container size increases.

<p align="center">
  <img width="600px" src="https://github.com/AlejandroDavidArzolaSaavedra/Data-Structures/assets/90756437/9817aff4-f28a-44cf-ba30-ab53dfca5b53">
</p>

## Sorted Vector 🚀

In the case of the sorted vector, there is a significant reduction in insertion time. This is due to binary searches, which imply a more efficient search by dealing with fewer elements to find the desired value. However, it's important to note that this container requires data to be sorted, which can be a drawback.

<p align="center">
  <img width="600px" src="https://github.com/AlejandroDavidArzolaSaavedra/Data-Structures/assets/90756437/5ea5d77b-65b1-4c62-afd5-3457c1836dce">
</p>

## Binary Search Tree 🌳

Insertion in a binary search tree shows considerably lower times compared to the previous practices. The efficiency is attributed to the absence of element shifts, resulting in lower computational overhead. However, caution is needed as the lack of rebalancing operations can lead the tree to degrade into a structure similar to a linked list if not managed properly.

<p align="center">
  <img width="300px" src="https://github.com/AlejandroDavidArzolaSaavedra/Data-Structures/assets/90756437/4b3ee775-ef5a-45b7-a58a-5b27b40049bde">
</p>

## B-tree 🌳⚖️

Insertion in a B-tree shows considerably lower times compared to the previous practices. The efficiency is attributed to the balanced structure of the B-tree, allowing for efficient search and insertion. The balance property helps maintain performance even as elements are added and removed. The hierarchical structure of the B-tree facilitates the search and insertion of elements, resulting in optimized execution times.

<p align="center">
  <img width="400px" src="https://github.com/AlejandroDavidArzolaSaavedra/Data-Structures/assets/90756437/4406207d-c62e-4f5a-ab20-af5457a2e36c">
</p>

## Graphs 📈

Graphs representing the execution time in milliseconds for each thousand insertion operations based on container size are attached. Comparisons are made between previous practices (PRCT 1 and PRCT 2) and the data structures used (PRCT 2, PRCT 3, and PRCT 4).

This analysis provides an overview of the efficiency of different data structures in terms of insertion operations, offering valuable information for selecting the most suitable structure based on system requirements. 🤖
