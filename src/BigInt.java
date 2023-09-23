import java.util.ArrayList;

public class BigInt {

	protected ArrayList <Integer> digits ;

	protected String s; 

	protected boolean positive;

	public BigInt(String s) {

		IllegalArgumentException e = new IllegalArgumentException("Invalid BigInt string: " + s);

		if (s == null || s.isEmpty()) {
			throw new IllegalArgumentException("Invalid BigInt string: " + s);
		}

		if (s.charAt(0) != '+' && s.charAt(0) != '-') {
			throw new IllegalArgumentException("Invalid BigInt string: " + s);
		}



		// Determine if the number is positive or negative
		if (s.charAt(0) == '+') {
			this.positive = true;
			s = s.substring(1);
		} else if (s.charAt(0) == '-') {
			this.positive = false;
			s = s.substring(1);
		} else {
			this.positive = true;
		}

		// Convert the string to an array of digits
		this.digits = new ArrayList<Integer>();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c >= '0' && c <= '9') {
				this.digits.add(c - '0');
			} else {
				throw new IllegalArgumentException("Invalid BigInt string: " + s);
			}
		}

		// Remove leading zeros
		while (this.digits.size() > 1 && this.digits.get(0) == 0) {
			this.digits.remove(0);
		}
	}


	public String toString() {
		
		String s = "";
		
		for ( int i=0; i<digits.size(); i++) {
			
			s = s + digits.get(i);
		
		}
		
		if(positive == true)
			
			s = "+"+s;
		
		else 
			
			s ="-"+s;
			
		return s;
	}
	
	
	// change - to + and + to - in the string representation
	public BigInt changeSign() {

		String s = this.toString();

		if(s.charAt(0) == '+') {

			s = "-"+s.substring(1);

			this.positive = false;

		}

		else if(s.charAt(0) == '-') {

			s = "+"+s.substring(1);

			this.positive = true;
		}

		return new BigInt(s);

	}

	public boolean isPositive() {

		if(this.positive == true)

			return true;

		return false;

	}


	public BigInt plus (BigInt other) {

		int carry = 0;

		String s = "";

		int i = this.digits.size() - 1;

		int j = other.digits.size() - 1;

		int digitA = 0;

		int digitB = 0;

		int sum = 0;

		BigInt sumBig = new BigInt ("+0");

		BigInt temp1 = this;

		BigInt temp2 = other;
		

		if(this.positive == other.positive) {

			//loop through both both BigInts from right to left
			while (i >= 0 || j >= 0 ) {
				//get the digits at the current indices or 0 if index is out of bounds

				if(i >= 0)

					digitA = this.digits.get(i) ;

				else digitA =0;

				if(j >= 0)

					digitB = other.digits.get(j) ;

				else digitB =0;


				//calculate the sum and carry
				sum = digitA + digitB + carry ;

				if(sum >= 10)

					carry = sum /10;
				else

					carry = 0;

				int digit = sum % 10 ;

				s = digit + s;

				//move to the next indices 
				i--;
				j--;

			}

			//if there is a final carry, add to the result ArrayList
			if(carry != 0) {

				s = carry + s;

			}

			//set the BigInt right sign
			if(this.positive == true) {

				sumBig.positive = true;

				sumBig = new BigInt("+"+s);
			}

			else  {

				sumBig.positive = false;

				sumBig = new BigInt("-"+s);
			}

		}

		if(this.positive == true && other.positive == false) {



			sumBig = temp1.minus(temp2.changeSign());	

			temp2.changeSign();

		}

		if(this.positive == false && other.positive == true) {


			sumBig = temp2.minus(temp1.changeSign());	

			temp1.changeSign();
		}


		return sumBig;
	}



	public BigInt minus (BigInt other) {

		int carry = 0;

		String s = "";

		int i = this.digits.size() - 1;

		int j = other.digits.size() - 1;

		int digitA = 0;

		int digitB = 0;

		int digit = 0;

		int sum = 0;

		BigInt temp1 = this;

		BigInt temp2 = other;

		BigInt sumBig = new BigInt ("+0");

		if( this.positive == other.positive && this.positive == true) {
			//loop through both both BigInts from right to left
			while (i >= 0 || j >= 0 ) {
				//get the digits at the current indices or 0 if index is out of bounds

				if(i >= 0)

					digitA = this.digits.get(i) ;

				else digitA =0;

				if(j >= 0)

					digitB = other.digits.get(j) ;

				else digitB =0;


				//calculate the sum and carry
				if(this.compareTo(other) == 1)

					sum = digitA - digitB +carry ;

				else if (this.compareTo(other) == -1)

					sum = digitB - digitA +carry;

				if(sum >= 0) {

					carry = 0;

					digit = sum;
				}

				else {

					carry = -1;

					digit = 10 + sum; 

				}


				s = digit + s;

				//move to the next indices 
				i--;
				j--;

			}

			//if there is a final carry, add to the result ArrayList
			if(carry != 0) {

				s = carry + s;

				sumBig = new BigInt(s);
			}


			//set the sign
			else if ((this.compareTo(other) == 1))

				sumBig = new BigInt("+"+s);


			else if ((this.compareTo(other) == -1))

				sumBig = new BigInt("-"+s);
		}


		if(this.positive == true && other.positive == false) {


			sumBig = temp1.plus(temp2.changeSign());	

			temp2.changeSign();

		}

		if(this.positive == false && other.positive == true) {


			sumBig = temp1.plus(temp2.changeSign());	

			temp2.changeSign();

		}



		return sumBig;

	}



	public int compareTo ( BigInt other) {

		int i = 0;

		boolean a = this.isPositive();

		boolean b = other.isPositive();


		if(this.positive == true && other.positive == false)

			return 1;


		else if(this.positive == false && other.positive == true)

			return -1;



		if(this.digits.size() > other.digits.size())

			return 1;


		else if(this.digits.size() < other.digits.size())

			return -1;

		if (this.digits.size() == other.digits.size()) {


			while (i <= this.digits.size() - 1 ) {



				if (this.digits.get(i) > other.digits.get(i))

					return 1;

				if (this.digits.get(i) < other.digits.get(i))

					return -1;

				if(this.digits.get(i) == other.digits.get(i))

					i++;
			}

		}	
		return 0;

	}



	public boolean equals ( BigInt other) {

		if(this.compareTo(other)==0)

			return true;

		return false;
	}




	public BigInt multiply (BigInt other) {

		int carry = 0;

		String s = "", z = "", k = "";

		int i = this.digits.size() - 1;

		int j = other.digits.size() - 1;

		int digitA = 0;

		int digitB = 0;

		int sum = 0;

		int digit = 0;

		BigInt sumBig = new BigInt ("+0");

		//loop through both both BigInts from right to left
		while ( i >= 0 ) {

			//get the digits at the current indices or 0 if index is out of bounds

			digitA = this.digits.get(i) ;

			while ( j >= 0 ) {

				digitB = other.digits.get(j) ;

				//calculate the sum and carry

				sum = digitA * digitB + carry ;

				if(sum >= 10)

					carry = sum /10;
				else

					carry = 0;

				digit = sum % 10 ;

				s = digit + s ;

				sumBig = sumBig.plus(new BigInt("+"+s));

				z += "0";

				s = "" + z;

				if(carry != 0 && j == 0) {

					sumBig = sumBig.plus(new BigInt("+"+carry+""+z));

					carry = 0;

				}

				//move to the next indices 
				j--;

			}

			k += "0";

			z = k;

			s = "" + z;

			j = other.digits.size() - 1;			

			i--;
		}

		//if there is a final carry, add to the result ArrayList
		if(carry != 0) {

			s = "+"+carry +""+ sumBig.toString().substring(1) ;

			sumBig = new BigInt(s);

		}


		if(this.isPositive() && !other.isPositive())

			sumBig = sumBig.changeSign();


		else if(!this.isPositive() && other.isPositive())

			sumBig = sumBig.changeSign();


		return sumBig;

	}


	public BigInt divide (BigInt other) {

		String s = "";

		int i = this.digits.size() - 1;

		int sum = 0;

		BigInt sumBig = new BigInt ("+0");

		BigInt temp1 = new BigInt ("+"+this.toString().substring(1));

		BigInt temp2 = new BigInt ("+"+other.toString().substring(1));
		

		if(other.toString().equals("+0")){

			throw new ArithmeticException("can't divide by zero");

		}
		
		if(temp1.digits.size() < temp2.digits.size())

			return new BigInt ("+0");

		if(this.equals(other))

			return new BigInt ("+1");

		//loop through both both BigInts from right to left
		while ( sumBig.compareTo(temp1) == -1) {

			sumBig = sumBig.plus(temp2);

			if(sumBig.compareTo(temp1) == -1 || sumBig.equals(temp1))

				sum++;

		}

		if(this.isPositive() && !other.isPositive())


			s = "-"+sum;

		else if(!this.isPositive() && other.isPositive())

			s = "-"+sum;

		else

			s = "+"+sum;


		return new BigInt (s);

	}

}

