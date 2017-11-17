//maxwell Sanders
//11/9/17

import java.util.Scanner;

public class ZipRateTable {
	int zip;
	int rating;
	
	//constructor that takes into a string
	public ZipRateTable(String input) {
		//create and delimit the Scanner using ,
		Scanner in = new Scanner(input);
		in.useDelimiter(",");
		
		//parse the string into the variables
		zip = in.nextInt();
		rating = in.nextInt();
		
		//close the scanner
		in.close();
	}
	
	int getZip() {
		return zip;
	}
	
	int getRating() {
		return rating;
	}
}
