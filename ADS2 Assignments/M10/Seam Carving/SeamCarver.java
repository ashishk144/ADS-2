import java.awt.Color;

public class SeamCarver {
    private Picture pic;
    private double[][] energymat;
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
        } else {
            this.pic = picture;
            this.width = picture.width();
            this.height = picture.height();
            this.energymat = new double[this.height][this.width];
            for (int i = 0; i < this.height; i++) {
                for (int j = 0; j < this.width; j++) {
                    if(i == 0 || i == width - 1 || j == 0 || j == height - 1) {
                        energymat[i][j] = 1000.0;
                    } else {
                        Color lpix = pic.get(i - 1, j);
                        Color rpix = pic.get(i + 1, j);
                        double sum = Math.pow(rpix.getRed() - lpix.getRed(), 2)
                            + Math.pow(rpix.getBlue() - lpix.getBlue(), 2)
                            + Math.pow(rpix.getGreen() - lpix.getGreen(), 2);
                        Color tpix = pic.get(i, j - 1);
                        Color bpix = pic.get(i, j + 1);
                        sum += Math.pow(tpix.getRed() - bpix.getRed(), 2)
                            + Math.pow(tpix.getBlue() - bpix.getBlue(), 2)
                            + Math.pow(tpix.getGreen() - bpix.getGreen(), 2);
                        energymat[i][j] = Math.sqrt(sum);
                    }                
                }
            }
        }
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
        return energymat[x][y];
    }

    // sequence of indices for horizontal seam
    public int[] findHorizontalSeam() {
        return new int[0];
    }

    // sequence of indices for vertical seam
    public int[] findVerticalSeam() {
        return new int[0];
    }

    // remove horizontal seam from current picture
    public void removeHorizontalSeam(int[] seam) {

    }

    // remove vertical seam from current picture
    public void removeVerticalSeam(int[] seam) {

    }
}