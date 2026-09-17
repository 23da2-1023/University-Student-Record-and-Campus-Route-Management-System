# University Student Record and Campus Route Management System

**Module:** CIT300 – Data Structures and Algorithms
**Assignment:** Graded Practical Assignment 1 (Week 10)
**Contribution:** 10% of final module grade

## 1. Project Overview
A Java console application that manages university student records and
models the university campus as a network of locations and roads. Built to
demonstrate practical use of linked lists, stacks, queues, trees, hashing,
and graphs.

## 2. Group Members

| Name | Student ID | 
| Assigned Responsibility |
| Individual Contribution |

| M.S.A. Shihani | 23DA2-1023 | 
|Linked list implementation and student-record management | 
|Designed and implemented the StudentLinkedList class using a custom singly linked list to store, add, update, delete, search, and display student records. Integrated linked list operations into the main menu (options 1–4) and added duplicate-ID validation. |

| M.I.A. Manoona | 23DA2-1054 | 
| Stack and queue implementation and related operations | 
| Implemented ActionStack (custom linked-node stack) for recent-action/undo history, and ServiceQueue (custom linked-node queue) for FIFO student service requests. Wired both into the main menu (options 5–7) so every add/update/delete/queue action is logged to the stack. |

| M.M.F. Shahra | 23DA2-1056 | 
| BST/AVL tree implementation and hashing/search functionality |
| Implemented StudentBST for organizing and searching student records by ID with in-order sorted display, and StudentHashTable using separate chaining with a custom hash function for O(1) average-case ID search. Kept both structures synchronized with the linked list on add/update/delete. |

| A.K. Jesla | 23DA2-0995 | 
| Graph implementation, campus locations, connections, and BFS/DFS traversal | 
| Implemented CampusGraph using an adjacency list to represent campus locations and roads, with add/remove operations for both locations and connections, a network display, and BFS and DFS traversal. Integrated all graph operations into the main menu (options 10–15). |

| All Members |
| Merged all four components into a single working Main.java with a 16-option menu, added shared input validation helpers and tested the integrated system end-to-end |


## 3. Data Structures Used

| Data Structure | Purpose | Class |
|---|---|---|
| Linked List | Store and manage student records | `StudentLinkedList.java` |
| Stack | Recent actions / undo history | `ActionStack.java` |
| Queue | Student service requests (FIFO) | `ServiceQueue.java` |
| BST | Organize/search students by ID | `StudentBST.java` |
| Hash Table | Fast student ID search | `StudentHashTable.java` |
| Graph (Adjacency List) | Campus locations & roads, BFS/DFS | `CampusGraph.java` |

## 4. How to Compile and Run

```bash
cd src
javac *.java -d ../bin
cd ../bin
java Main
```

## 5. Menu Options
1. Add Student Record
2. Update Student Record
3. Delete Student Record
4. Display All Records using Linked List
5. Add Service Request to Queue
6. Process Next Service Request
7. Display Recent Actions using Stack
8. Display Students using BST/AVL
9. Search Student using Hashing
10. Add Campus Location
11. Remove Campus Location
12. Add Campus Connection/Road
13. Remove Campus Connection/Road
14. Display Campus Connections
15. Traverse Campus Locations using BFS or DFS
16. Exit

## 6. GitHub Collaboration
- Each member have committed progressively on their own feature branch
| M.S.A. Shihani | 23DA2-1023 |  |
| M.I.A. Manoona | 23DA2-1054 |  | 
| M.M.F. Shahra | 23DA2-1056 |  |
| A.K. Jesla | 23DA2-0995 |  |