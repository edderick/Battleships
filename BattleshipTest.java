import java.awt.Point;


public class BattleshipTest {
	
	public static void main(String [] args)
	{
		Battleship b1 = new Battleship(new Point(5,3), true, 3);
		
		//	Check locations - output should be false true true true false
		System.out.println("Checking position 1 (false true true true false)");
		for (int x = 4; x < 9; x++)
		{
			System.out.print(b1.isAtPoint(new Point(x,3)) + " ");
		}
		System.out.println();
		System.out.println();
		
		//	Check locations - output should be false true true true true false
		Battleship b2 = new Battleship(new Point(6,2), false, 4);
		System.out.println("Checking position 2 (false true true true true false)");
		for (int y = 1; y < 7; y++)
		{
			System.out.print(b2.isAtPoint(new Point(6,y)) + " ");
		}
		System.out.println();
		System.out.println();
		
		//	Check if collide - should be true
		System.out.println("b1 and b2 in same locations? (true): " + b1.inSameLocation(b2));
		System.out.println();
		System.out.println();
		
		//	Check rotate
		b1.rotate(10, 10);	//	param: width, height
			
		System.out.println("Checking position 3 (false true true true false)");
		for (int y = 2; y < 7; y++)
		{
			System.out.print(b1.isAtPoint(new Point(5,y)) + " ");
		}
		System.out.println();
		
		//	Check rotate 2 (should not rotate this time)
		b1.rotate(6, 10);	//	param: width, height
		
		
		System.out.println("Checking position 3 (false true true true false)");
		for (int y = 2; y < 7; y++)
		{
			System.out.print(b1.isAtPoint(new Point(5,y)) + " ");
		}
		System.out.println();
	}
}
