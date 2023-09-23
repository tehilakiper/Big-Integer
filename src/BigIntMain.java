import java.util.Scanner;

public class BigIntMain {

	public static void main(String[] args) {

		String A;
		
		String B;
		
		IllegalArgumentException d;
		
		Scanner scan = new Scanner(System.in);
			
	while (true) {
            try {
                System.out.print("Enter a number and add '+' or '-' sign: \n");
                 A = scan.nextLine();
                System.out.println("You entered: " + A);
                BigInt a = new BigInt(A);
                break; // break out of loop if input is valid
            } catch (IllegalArgumentException e) {
            	System.out.println("Invalid input, press enter");
            	scan.nextLine(); // clear input buffer
                System.out.println("please enter a valid number.");
            }
		}
		
		
		
		
		while (true) {
            try {
                System.out.print("Enter another number: ");
                 B = scan.nextLine();
                System.out.println("You entered: " + B);
                BigInt b = new BigInt(B);
                break; // break out of loop if input is valid
            } catch (IllegalArgumentException e) {
            	System.out.println("Invalid input, press enter");
            	scan.nextLine(); // clear input buffer
                System.out.println("please enter a valid number.");
            }
		} 

		
		
		BigInt a = new BigInt(A);

		BigInt b = new BigInt(B);
		
		
		if(b.toString() == "+0")

			System.out.println("you can't dvide by zero");

		
		else
			
			System.out.println("if you divide 'this' by 'other' the result is: "+a.divide(b));
	
			
		System.out.println("'this' representation by toString(): "+a.toString());
		
		System.out.println("'other' representation by toString(): "+b.toString());

		System.out.println("the sum of 'this' plus 'other is: " +a.plus(b).toString());

		System.out.println("for those numbers, compareTo() returns: "+a.compareTo(b));

		System.out.println("the result of  'this' minus 'other' is: "+a.minus(b).toString());

		System.out.println("'this' equals to 'other'? :"+a.equals(b));

		System.out.println("the result of multiplying 'this' by 'other' is: "+a.multiply(b).toString());
	
	
	}

}
