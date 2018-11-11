import java.awt.Color;

public class SeamCarver {
    private Picture pic;
    private Double[][] energymat;
    private int width, height;
    // create a seam carver object based on the given picture

    /**
     * Constructs the object.
     * Complexity (width*height)
     * @param      picture  The picture
     */
    public SeamCarver(Picture picture) throws Exception {
        if (picture == null) {
            throw new Exception("picture is null");
        }
        this.pic = picture;
        this.width = picture.width();
        this.height = picture.height();
    }
    // current picture
    public Picture picture() {
        return this.pic;
    }
    // width of current picture
    public int width() {
        return this.width;
    }

    // height of current picture
    public int height() {
        return this.height;
    }

    // energy of pixel at column x and row y
    public double energy(int x, int y) {
        if(x == 0 || x == width - 1 || y == 0 || y == height - 1) {
            return 1000.0;
        } else {
            Color lpix = pic.get(x - 1, y);
            Color rpix = pic.get(x + 1, y);
            double sum = Math.pow(rpix.getRed() - lpix.getRed(), 2)
                + Math.pow(rpix.getBlue() - lpix.getBlue(), 2)
                + Math.pow(rpix.getGreen() - lpix.getGreen(), 2);
            Color tpix = pic.get(x, y - 1);
            Color bpix = pic.get(x, y + 1);
            sum += Math.pow(tpix.getRed() - bpix.getRed(), 2)
                + Math.pow(tpix.getBlue() - bpix.getBlue(), 2)
                + Math.pow(tpix.getGreen() - bpix.getGreen(), 2);
            return Math.sqrt(sum);
        }
    }

    /**sequence of indices for horizontal seam
     *
     *time complexity is O(w*h)
     *w is the width and h is the height
     * @return  sequence of indices of horizontal seam
     */
    public int[] findHorizontalSeam() {
        int[][] edgeTo = new int[height][width];
        double[][] distTo = new double[height][width];
        reset(distTo);
        for (int row = 0; row < height; row++) {
            distTo[row][0] = 1000;
        }
        // this is for relaxation.
        for (int col = 0; col < width - 1; col++) {
            for (int row = 0; row < height; row++) {
                relaxH(row, col, edgeTo, distTo);
            }
        }
        double minDist = Double.MAX_VALUE;
        int minRow = 0;
        for (int row = 0; row < height; row++) {
            if (minDist > distTo[row][width - 1]) {
                minDist = distTo[row][width - 1];
                minRow = row;
            }
        }
        int[] indices = new int[width];
        //to find the horizontal seam.
        for (int col = width - 1, row = minRow; col >= 0; col--) {
            indices[col] = row;
            row -= edgeTo[row][col];
        }
        return indices;
    }
    /**
     *time complexity is O(W * H)
     *W is the width of image
     *H is the height of image
     * @param      distTo  The distance to
     */
    private void reset(double[][] distTo) {
        /**
         *reset all the values to maxvalue.
         */
        for(int i = 0; i < distTo.length; i++) {
            for(int j = 0; j < distTo[i].length; j++) {
                distTo[i][j] = Double.MAX_VALUE;
            }
        }
    }
    private void relaxH(int row, int col, int[][] edgeTo, double[][] distTo) {
        int nextcol = col + 1;
        for (int i = -1; i <= 1; i++) {
            int nextrow = row + i;
            if (nextrow < 0 || nextrow >= height) continue;
            if(i == 0) {
                if(distTo[nextrow][nextcol] >= distTo[row][col]  + energy(nextcol, nextrow)) {
                    distTo[nextrow][nextcol] = distTo[row][col]  + energy(nextcol, nextrow);
                    edgeTo[nextrow][nextcol] = i;
                }
            }
            if (distTo[nextrow][nextcol] > distTo[row][col]  + energy(nextcol, nextrow)) {
                distTo[nextrow][nextcol] = distTo[row][col]  + energy(nextcol, nextrow);
                edgeTo[nextrow][nextcol] = i;
            }
        }
    }
    /**
     *this method is to find the vertical seam.
     *first of all find the shortest path from top to.
     *bottom.
     *time complexity is O(w*h)
     *w is the width and h is the height
     * @return sequence of indices for vertical seam.
     */
    public int[] findVerticalSeam() {
        double[][] energy = new double[height][width];
        int[][] edgeTo = new int[height][width];
        double[][] distTo = new double[height][width];
        reset(distTo);
        int[] indices = new int[height];
        if(width == 1 || height == 1) {
            return indices;
        }
        for(int i = 0; i < width; i++) {
            distTo[0][i] = 1000.0;
        }
        // this is for relaxation.
        for (int i = 0; i < height - 1; i++) {
            for(int j = 0; j < width; j++) {
                relaxV(i, j, edgeTo, distTo);
            }
        }
        // calculating from last row
        // column wise
        double minDist = Double.MAX_VALUE;
        int minCol = 0;
        for (int col = 0; col < width; col++) {
            if (minDist > distTo[height - 1][col]) {
                minDist = distTo[height - 1][col];
                minCol = col;
            }
        }
        //indices values of shortest path.
        for (int row = height -1, col = minCol; row >= 0; row--) {
            indices[row] = col;
            col -= edgeTo[row][col];
        }
        indices[0] = indices[1];
        return indices;
    }

    private void relaxV(int row, int col, int[][] edgeTo, double[][] distTo) {
        int nextrow = row + 1;
        for (int i = -1; i <= 1; i++) {
            int nextcol = col + i;
            if (nextcol < 0 || nextcol >= width) {
                continue;
            }
            //spl case for bottom element.
            if(i == 0) {
                if(distTo[nextrow][nextcol] >= distTo[row][col] + energy(nextcol, nextrow)) {
                distTo[nextrow][nextcol] = distTo[row][col] + energy(nextcol, nextrow);
                edgeTo[nextrow][nextcol] = i;
                }
            }
            if (distTo[nextrow][nextcol] > distTo[row][col] + energy(nextcol, nextrow)) {
                distTo[nextrow][nextcol] = distTo[row][col] + energy(nextcol, nextrow);
                edgeTo[nextrow][nextcol] = i;
            }
        }
    }
    // remove horizontal seam from current picture
    //time complexity is O(width * height)
    public void removeHorizontalSeam(int[] seam) {
        //handle exceptions
        for(int col = 0; col < width; col++) {
            for(int row = seam[col]; row < height - 1; row++) {
                this.pic.set(col, row, this.pic.get(col, row + 1));
            }
        }
        height--;
    }
    // remove vertical seam from current picture
    //time complexity is O(width * height)
    public void removeVerticalSeam(int[] seam) {
        for(int row = 0; row < height; row++) {
            for(int col = seam[row]; col < width - 1; col++) {
            this.pic.set(col, row, this.pic.get(col + 1, row));
            }
        }
        width--;
    }
}
