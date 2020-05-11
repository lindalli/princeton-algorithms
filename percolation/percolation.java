public class percolation {

    private int[][] nGrid;
    private int[][] nGridMapToParentChild;
    private int numOfOpenSites = 0;
    private int[] parentChildMapping;

    // creates n-by-n grid, with all sites initially blocked
    public percolation(int n) {
        nGrid = new int[n][n];
        parentChildMapping = new int[n*n];
        nGridMapToParentChild = new int[n][n];

        int counter=0;
        //initializes all sites to closed (0)
        for(int row=0;row<nGrid.length;row++) {
            for (int column=0; column< nGrid[row].length; column++) {
                nGrid[row][column] = 0;
                nGridMapToParentChild[row][column] = counter;
                counter++;
            }
        }

        //initializes parent node to itself
        for(int index=0;index<parentChildMapping.length; index++) {
            parentChildMapping[index] = index;
        }
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if(!isOpen(row,col)) {
            nGrid[row][col] = 1; //opens the row by setting it = 1
            numOfOpenSites++;
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if(nGrid[row][col] == 1) {
            return true;
        }
        return false;
    }

    // is the site (row, col) full? x
    public boolean isFull(int row, int col) {
        if(isOpen(row-1, col) && isOpen(row+1, col)
                && isOpen(row, col-1) && isOpen(row,col+1)) {
            return true;
        }
        return false;
    }

    // returns the number of open sites x
    public int numberOfOpenSites() {
        return numOfOpenSites;
    }

    // does the system percolate?
    public boolean percolates() {
        boolean percol = false;
        for(int row=0;row< nGrid.length; row++) {
            for(int col=0;col<nGrid[row].length;col++) {
                if(row > 0 && row < nGrid.length) {
                    int getIndexValue = nGridMapToParentChild[row][col];
                    if(nGrid[row-1][col] == 1) {
                        parentChildMapping[getIndexValue] = nGridMapToParentChild[row-1][col];
                    }
                }
            }
        }

        int i = parentChildMapping.length-1;
        while(i > 0) {
            if(i != parentChildMapping[i]) {
                i = parentChildMapping[i];
            }else{
                i--;
            }
        }

        return true;
    }

    // test client (optional)
    public static void main(String[] args){
        percolation percolate = new percolation(5);

        for(int row=0;row<percolate.nGrid.length;row++) {
            for (int column=0; column< percolate.nGrid[row].length; column++) {
                System.out.print(percolate.nGrid[row][column]);
            }
            System.out.println("\n");
        }

        System.out.println("-------------");

        percolate.open(0,2);
        percolate.open(1,2);
        percolate.open(3,4);
        percolate.open(0,3);
        percolate.open(2,4);
        percolate.open(2,2);
        percolate.open(3,2);
        percolate.open(4,2);
        percolate.open(4,3);


        for(int row=0;row<percolate.nGrid.length;row++) {
            for (int column=0; column< percolate.nGrid[row].length; column++) {
                System.out.print(percolate.nGrid[row][column]);
            }
            System.out.println("\n");
        }

        percolate.percolates();

        for(int i=0; i<percolate.parentChildMapping.length;i++) {
            System.out.println(i+ " " + percolate.parentChildMapping[i]);
        }


    }
}