//Maxwell Sanders
//10/12/17
//This is a class to put inside of the arraylist to do functional stuff with

import java.util.Scanner;

public class texasCitiesClass {
	String city;
	String county;
	int pop;
	
	//constructor takes a line and then creates a table entry
	public texasCitiesClass(String line){
		Scanner split = new Scanner(line);
		split.useDelimiter(",");
		city = split.next();
		county = split.next();
		pop = split.nextInt();
		split.close();
	}
	
	public String getCityName() {
		return city;
	}
	
	public String getCountyName() {
		return county;
	}
	
	public int getPop() {
		return pop;
	}
	
}
