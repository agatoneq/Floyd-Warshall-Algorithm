
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

## ▶️ Running the Project

### Option 1: Using NetBeans (recommended)
1. Clone the repository:
   ```bash
   git clone https://github.com/agatoneq/Floyd-Warshall-Algorithm.git
   ```
2. Open the folder in **NetBeans** as a Maven project.
3. Right-click the project and choose **Run**.
4. Open `site/index.html` in your browser to see the interface.

### Option 2: From command line (Maven)
1. Make sure you have **Java** and **Maven** installed.
2. Navigate to the project folder and run:
   ```bash
   mvn compile
   mvn exec:java
   ```
> Note: you may need to configure the main class in `pom.xml`.

### Option 3: Manual run of HTML interface
- Open the file `site/index.html` directly in your browser.
- The algorithm logic will still be computed via Java.

## 💡 Possible Extensions

- Add step-by-step animation of algorithm
- Support for dynamic graph input from user
- Improve visualization and UI/UX

## 📜 License

This project is educational and open-source.
