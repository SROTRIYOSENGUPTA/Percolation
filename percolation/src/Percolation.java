/*
 * 
 * Percolation
 * 
 * @author Ana Paula Centeno
 * @author Haolin (Daniel) Jin
 * Name: Srotriyo Sengupta; Email: ss3414@scarletmail.rutgers.edu; Net Id: ss3414
 */
public class Percolation {

	private boolean[][] grid;          // gridSize by gridSize grid of sites; 
	                                   // true = open site, false = closed or blocked site
	private WeightedQuickUnionFind wquFind; // 
	private int gridSize;      // gridSize by gridSize is the size of the grid/system 
	private int gridSquared;
	private int virtualTop;    // virtual top index on WeightedQuckUnionFind arrays
	private int virtualBottom; // virtual bottom index on WeightedQuckUnionFind arrays
	/**
	* Constructor.
	* Initializes all instance variables
	*/
	public Percolation (int n){
		gridSize 	  = n;
		gridSquared   = gridSize * gridSize;
		wquFind       = new WeightedQuickUnionFind(gridSquared + 2);
		grid          = new boolean[gridSize][gridSize];   // every site is initialized to closed/blocked
		virtualTop    = gridSquared;
		virtualBottom = gridSquared + 1;
	} 
	/**
	* Getter method for GridSize 
	* @return integer representing the size of the grid.
	*/
	public int getGridSize () {
		return gridSize;
	}
	/**
	 * Returns the grid array
	 * @return grid array
	 */
	public boolean[][] getGridArray () {
		return grid;
	}
	/**
	* Open the site at postion (x,y) on the grid to true and add an edge
	* to any open neighbor (left, right, top, bottom) and/or top/bottom virtual sites
	* Note: diagonal sites are not neighbors
	*
	* @param row grid row
	* @param col grid column
	* @return void
	*/
	public void openSite(int row, int col) 
	{
		if(row==gridSize-1)
		{
        	wquFind.union(virtualBottom,gridSize* row + col);
        }
		// checking if site already open and if not, open a site

		if(!grid[row][col]) 
		{
            grid[row][col]=true;
        } 
		// this here will open the box

        if(row==0)
		{
            wquFind.union(virtualTop,gridSize* row + col);
        }
		// checking if site already open and if not, open a site

        if(col<gridSize-1&&grid[row][col + 1]) 
		{
            wquFind.union(gridSize* row + (col + 1),gridSize* row + col);
        }
		// checking if site already open and if not, open a site

		if(row<gridSize-1&&grid[row + 1][col]) 
		{
            wquFind.union(gridSize* row + col,gridSize* (row + 1) + col);
        }
		// checking if site already open and if not, open a site

        if(col>0&&grid[row][col - 1]) 
		{
            wquFind.union(gridSize* row + (col - 1),gridSize* row + col); 
		}
		// checking if site already open and if not, open a site

		if(row>0&&grid[row - 1][col]) 
		{
            wquFind.union(gridSize* (row - 1) + col,gridSize* row + col);
        }
		// this will also check if site is already open and if not, open a site

		// this method openSite will open a site at (row,col) and do nothing if the site is already open
	}   // this is the end of method openSite
	/**
	* Check if the system percolates (any top and bottom sites are connected by open sites)
	* @return true if system percolates, false otherwise
	*/
	public boolean percolationCheck() 
	{
		if(wquFind.find(virtualTop)==wquFind.find(virtualBottom))
		{
			return true;
		}
		else
		{
			return false;
		} // this method will return true if the system percolates otherwise false
	} // this is the end of method percolationCheck
	/**
	 * Iterates over the grid array openning every site. 
	 * Starts at [0][0] and moves row wise 
	 * @param probability
	 * @param seed
	 */
	public void openAllSites(double probability, long seed) 
	{
		// Setting the same seed before generating random numbers ensure that
		// the same numbers are generated between different runs
		StdRandom.setSeed(seed); 
		for(int pqrs1 = 0;pqrs1<gridSize;pqrs1++)
		{
			for(int pqrs2 = 0;pqrs2<gridSize;pqrs2++)
			{
				if(StdRandom.uniform()<probability)
				openSite(pqrs1,pqrs2);
			}
		}
		// this method will open all sites in the grid
	} // this is the end of method openAllSites

	/**
	* Open up a new window and display the current grid using StdDraw library.
	* The output will be colored based on the grid array. Blue for open site, black for closed site.
	* @return: void 
	*/
	public void displayGrid () {
		double blockSize = 0.9 / gridSize;
		double zeroPt =  0.05+(blockSize/2), x = zeroPt, y = zeroPt;

		for ( int i = gridSize-1; i >= 0; i-- ) {
			x = zeroPt;
			for ( int j = 0; j < gridSize; j++) {
				if ( grid[i][j] ) {
					StdDraw.setPenColor( StdDraw.BOOK_LIGHT_BLUE );
					StdDraw.filledSquare( x, y ,blockSize/2);
					StdDraw.setPenColor( StdDraw.BLACK);
					StdDraw.square( x, y ,blockSize/2);		
				} else {
					StdDraw.filledSquare( x, y ,blockSize/2);
				}
				x += blockSize; 
			}
			y += blockSize;
		}
	}
	/**
	* Main method, for testing only, feel free to change it.
	*/
	public static void main ( String[] args ) {

		double p = 0.47;
		Percolation pl = new Percolation(5);

		/* 
		 * Setting a seed before generating random numbers ensure that
		 * the same numbers are generated between runs.
		 *
		 * If you would like to reproduce Autolab's output, update
		 * the seed variable to the value Autolab has used.
		 */
		long seed = System.currentTimeMillis();
		pl.openAllSites(p, seed);

		System.out.println("The system percolates: " + pl.percolationCheck());
		pl.displayGrid();
	}
}
// this is the end of Percolation.java

