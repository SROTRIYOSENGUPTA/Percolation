An abstract data type called Union Find organizes data into disjoint sets and allow us to: (1) unify two sets, and (2) given an  element of a set it finds the representative of  the set. With that knowledge, together with some clever tricks, we can test if a n-by-n grid percolates. In this project we utilize percolation to achieve this.

Percolation is an abstract model for many physical systems. Given a n-by-n grid of sites, each site is open with probability p, the system percolates if and only if any open site in the top row in the grid is connected to any open site in the bottom row by open sites.

We model percolation as a dynamic-connectivity problem and use union-find to check if the system percolates. 

![image](https://github.com/SROTRIYOSENGUPTA/Percolation/assets/69280834/6cd65456-85a4-4ac2-ae7e-7b9e7123eaa1)


How to model Percolation as a dynamic-connectivity problem?
Initialize a n-by-n grid of sites, where all sites are closed/blocked.
Initialize a weighted quick union-find object containing all the sites in the grid plus two additional sites: a virtual top and a virtual bottom.
Open all sites.
Check if the system percolates.
Opening all sites
Starting at the first site (0,0), one row at a time, open each site with probability p.
Open site
If site is closed, open. Otherwise, do nothing.
If opened site is in the first row then connect to virtual top.
If opened site is in the last row then connect to virtual bottom.
Connect opened site to any adjacent site that is open. An adjacent site is a site to the left, right, top, or bottom of the site. (Not diagonals).
Does the system percolate?
The system percolates if virtual top site is connected to virtual bottom site.


StdDraw.java here is used by Percolation.java to draw the grid.
StdRandom.java is used by Percolation.java to generate random numbers.
WeightedQuickUnionFind.java is used by Percolation.java to store information about which open sites are connected or not.
Percolation.java contains information for the grid.
Percolation.java
Instance variables
boolean[][] grid: boolean 2D array representing the grid. Each (row, col) is a site. (row, col) is true if the site is open, false if the site is closed.
int gridSize: the size of the grid.
int gridSquared: the number of sites in a grid.
WeightedQuickUnionFind wquFind: Weighted quick union-find object used to keep track of all connected/opened sites.
int virtualTop: index of a virtual top in the size and parent arrays in WeightedQuickUnionFind. Connect the virtual top to every open site in the first row of the grid. 
int virtualBottom: index of a virtual bottom in the size and parent arrays in WeightedQuickUnionFind.
Methods
constructor: initializes the object’s instance variables. 
openSite(): opens a site at (row, col). If the site is already open, it does nothing.
openAllSites(): opens all sites in the grid.  Starting at the first site at index (0,0) and moving row wise through all the sites, each site is opened with probability p.  Use StdRandom.uniform() here generates a random number.
percolationCheck(): returns true if the system percolates. 
displayGrid(): displays the grid. An open site is colored blue, a closed site is colored black.
main(): is used for testing only.
The following picture depicts the relationship of the grid sites to the size and parent arrays in the WeightedQuickUnionFind object.

The 2D array grid is flattened into a 1D array.
The 1D array has an additional two sites: the virtual top and bottom sites.
The virtual top corresponds to the next to last index in size/parent array, the virtual bottom corresponds to the last index in the size/parent array.

![image](https://github.com/SROTRIYOSENGUPTA/Percolation/assets/69280834/1e9a8012-f588-4fb8-96f7-04f880703294)




