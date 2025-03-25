
# 🔁 Floyd-Warshall Pathfinder

This project implements the **Floyd-Warshall algorithm** for finding shortest paths in a weighted graph.  
It also includes a basic **web interface** for interaction and visualization.

## 📌 About the Project

- Calculates the shortest distances between all pairs of nodes in a directed graph.
- Visualizes the adjacency matrix before and after running the algorithm.
- Built using Java (backend logic) and HTML/CSS/JS (frontend).

## 🚀 Technologies Used

- Java (core algorithm logic)
- HTML, CSS, JavaScript (simple UI)
- NetBeans project structure (Maven)

## 🧠 Algorithm Overview

Floyd-Warshall is a **dynamic programming algorithm** that:
- Handles negative edge weights (but no negative cycles)
- Works in O(n³) time complexity
- Produces a distance matrix between all node pairs

## 📁 Structure

- `src/` – Java implementation of the algorithm and data structures
- `site/` – Frontend files (HTML/CSS/JS)
- `pom.xml` – Maven project configuration

## 💡 Possible Extensions

- Add step-by-step animation of algorithm
- Support for dynamic graph input from user
- Improve visualization and UI/UX

## 📜 License

This project is educational and open-source.
