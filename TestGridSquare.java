
import java.awt.Point;

public class TestGridSquare {
	public static void main(String [] args)
	{
		GridSquare g1 = new GridSquare(new Point(4,3));
		GridSquare g2 = new GridSquare(new Point(4,3), true);
		GridSquare g3 = new GridSquare(new Point(8,1));
		GridSquare g4 = new GridSquare(new Point(2,4), false);
		
		System.out.println("Test 1 (true): " + g1.equals(g2));
		System.out.println("Test 1 (false): " + g1.equals(g3));
		System.out.println("Test 1 (false): " + g1.equals(g4));
		System.out.println("Test 1 (true): " + g2.equals(g1));			
	}
}
