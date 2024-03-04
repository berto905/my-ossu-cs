import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner {
    public double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }

    public int getNumPoints(Shape s) {
        // Put code here
        // Start with numPoints = 0
        int numPoints = 0;
        // For each point currPt in the shape
        for (Point currPt : s.getPoints()) {
            // Update numPoints
            numPoints = numPoints + 1;
        }
        return numPoints;
    }

    public double getAverageLength(Shape s) {
        // Put code here
        // Get the perimeter
        double totalPerim = getPerimeter(s);
        // Get the number of points
        double numPoints = getNumPoints(s);
        // Calculate the average length
        double averageLength = totalPerim / numPoints;
        return averageLength;
    }

    public double getLargestSide(Shape s) {
        // Put code here
        // Start with largestSide = 0
        double largestSide = 0;
        // Start with prevPt = the last point
        Point prevPt = s.getLastPoint();
        // For each point in the shape
        for (Point currPt : s.getPoints()) {
            // Get the distance from currPt to prevPt
            double currDist = prevPt.distance(currPt);
            // Check if it is larger than largestSide, if yes update largestSide
            if (currDist > largestSide) {
                largestSide = currDist;
            }
            // update prevPt to be currPt
            prevPt = currPt;
        }
        return largestSide;
    }

    public double getLargestX(Shape s) {
        // Put code here
        // Start with largestX = -1000000
        double largestX = -1000000;
        // For each point in the shape
        for (Point currPt : s.getPoints()) {
            // Check if x in currPt is larger than largest X, if yes update largestX
            double currX = currPt.getX();
            if (currX > largestX) {
                largestX = currX;
            }
        }
        return largestX;
    }

    public double getLargestPerimeterMultipleFiles() {
        // Put code here
        // Start with largestPerim = 0
        double largestPerim = 0;
        // Create a Directory Resource
        DirectoryResource dr = new DirectoryResource();
        // For each file in dr
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double perim = getPerimeter(s);
            // If perim > largestPerim, update largestPerim
            if (perim > largestPerim) {
                largestPerim = perim;
            }
        }
        return largestPerim;
    }

    public String getFileWithLargestPerimeter() {
        // Put code here
        File temp = null;    // replace this code
        double largestPerim = 0;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double perim = getPerimeter(s);
            if (perim > largestPerim) {
                largestPerim = perim;
                temp = f;
            }
        }
        return temp.getName();
    }

    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        System.out.println("perimeter = " + length);
        int numPoints = getNumPoints(s);
        System.out.println("number of points = " + numPoints);
        double averageLength = getAverageLength(s);
        System.out.println("average length of a side = " + averageLength);
        double largestSide = getLargestSide(s);
        System.out.println("largest side = " + largestSide);
        double largestX = getLargestX(s);
        System.out.println("largest X = " + largestX);
    }
    
    public void testPerimeterMultipleFiles() {
        // Put code here
        // Call the getLargestPerimeterMultipleFiles
        double largestPerim = getLargestPerimeterMultipleFiles();
        System.out.println("largest perimeter = " + largestPerim);
    }

    public void testFileWithLargestPerimeter() {
        // Put code here
        String largestPerim = getFileWithLargestPerimeter();
        System.out.println("file with largest perimeter = " + largestPerim);
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = "+peri);
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main (String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        pr.testPerimeter();
        pr.testPerimeterMultipleFiles();
        pr.testFileWithLargestPerimeter();
    }
}
