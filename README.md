# Assignment 3: Minimum Spanning Tree Algorithms

Implementation of Prim's and Kruskal's algorithms for optimizing city transportation networks by finding the Minimum Spanning Tree (MST).

## 📋 Overview

This project solves the problem of connecting all city districts with roads while minimizing total construction costs. The solution implements two classic MST algorithms:

- **Prim's Algorithm** - Uses a priority queue to grow the MST from a starting vertex
- **Kruskal's Algorithm** - Uses Union-Find to build the MST by selecting edges in ascending weight order

## Project Structure

```
Assignment3_DAA/
│
├── src/
│   └── main/
│       └── java/
│           └── com/
│               └── daa/
│                   └── assignment3/
│                       ├── Main.java          # Entry point - orchestrates execution
│                       ├── Graph.java         # Graph representation with adjacency list
│                       ├── Edge.java          # Edge data structure
│                       ├── Prim.java          # Prim's algorithm implementation
│                       ├── Kruskal.java       # Kruskal's algorithm implementation
│                       └── DisjointSet.java   # Union-Find for Kruskal's algorithm
│
├── pom.xml                    # Maven configuration
├── ass_3_input.json           # Input graphs (7 test cases)
├── ass_3_output.json          # Generated results (created after running)
├── README.md                  # This file
└── report/
    └── report_daa_assignment3.pdf             # Analytical report with performance analysis
```

## 🚀 Getting Started

### Prerequisites

- **Java 11** or higher
- **Maven 3.6+**
- **IntelliJ IDEA** (recommended) or any Java IDE

### Installation

1. Clone the repository:
   ```bash
   git clone <repository-url>
   cd Assignment3_DAA
   ```

2. Open the project in IntelliJ IDEA:
   - File → Open → Select the project folder
   - Maven will automatically download dependencies

## ▶️ How to Run

### Method 1: Using IntelliJ IDEA (Recommended)

1. Open the project in IntelliJ
2. Navigate to `src/main/java/com/daa/assignment3/Main.java`
3. Right-click on the file
4. Select **"Run 'Main.main()'"**

### Method 2: Using Maven Command Line

```bash
mvn clean compile exec:java
```

### Output

After running the program:
- **Console output**: Shows processing details and results for each graph
- **`ass_3_output.json`**: Generated in the project root with complete results

##  Input Format

The input file `ass_3_input.json` contains multiple graphs with the following structure:

```json
{
  "graphs": [
    {
      "id": 1,
      "nodes": ["A", "B", "C", "D", "E"],
      "edges": [
        {"from": "A", "to": "B", "weight": 4},
        {"from": "A", "to": "C", "weight": 3}
      ]
    }
  ]
}
```

### Test Cases Included

The project includes **7 test graphs**:

| Graph ID | Vertices | Edges | Type | Description |
|----------|----------|-------|------|-------------|
| 1 | 5 | 7 | Small | Dense graph |
| 2 | 4 | 5 | Small | Dense graph |
| 3 | 10 | 14 | Medium | Sparse graph |
| 4 | 12 | 25 | Medium | Dense graph |
| 5 | 20 | 28 | Large | Sparse graph |
| 6 | 25 | 48 | Large | Moderately dense |
| 7 | 30 | 43 | Large | Sparse graph |

##  Output Format

The generated `ass_3_output.json` contains results for each graph:

```json
{
  "results": [
    {
      "graph_id": 1,
      "input_stats": {
        "vertices": 5,
        "edges": 7
      },
      "prim": {
        "mst_edges": [...],
        "total_cost": 16,
        "operations_count": 34,
        "execution_time_ms": 0.37
      },
      "kruskal": {
        "mst_edges": [...],
        "total_cost": 16,
        "operations_count": 68,
        "execution_time_ms": 0.44
      }
    }
  ]
}
```

##  Algorithm Implementations

### Prim's Algorithm
- **Data Structure**: Priority Queue (Min-Heap)
- **Graph Representation**: Adjacency List
- **Time Complexity**: O(E log V)
- **Key Operations**: Vertex selection, edge relaxation

### Kruskal's Algorithm
- **Data Structure**: Disjoint Set (Union-Find)
- **Graph Representation**: Edge List
- **Time Complexity**: O(E log E)
- **Key Operations**: Edge sorting, cycle detection

## 📄 Documentation

For detailed performance analysis, algorithm comparison, and conclusions, see:
- **report/report_daa_assignment.pdf** - Complete analytical report

The report includes:
- Summary of results for all test cases
- Performance comparison (operations count, execution time)
- Analysis of when to use each algorithm
- Scalability evaluation

## 🛠️ Technologies Used

- **Java 11** - Programming language
- **Maven** - Build and dependency management
- **Gson 2.10.1** - JSON parsing and generation
- **IntelliJ IDEA** - Development environment

## 📝 Features

- ✅ Complete implementation of Prim's and Kruskal's algorithms
- ✅ Efficient data structures (Priority Queue, Union-Find)
- ✅ Operation counting for performance analysis
- ✅ Execution time measurement
- ✅ JSON input/output for easy testing
- ✅ Support for graphs of varying sizes (4-30 vertices)
- ✅ Comprehensive test suite (7 graphs)

##  Testing

The program automatically verifies that both algorithms produce the same MST cost. If costs differ, a warning is displayed in the console.

##  Course

**Design and Analysis of Algorithms Course**  
Assignment 3 - Minimum Spanning Tree Implementation


## 🎯 Assignment Requirements Met

- ✅ Read input from JSON file
- ✅ Implement Prim's algorithm
- ✅ Implement Kruskal's algorithm
- ✅ Record MST edges and total cost
- ✅ Count algorithm operations
- ✅ Measure execution time
- ✅ Generate output JSON file
- ✅ Create analytical report
- ✅ Compare algorithm performance
- ✅ Test on small, medium, and large graphs