//Maxwell Sanders
//11/2/17

import java.util.Scanner;
	
public class zipCodeClass{
	
		int zipCode;
		String typeZip;
		String cityName;
		String countyName;
		int estPop;
		
		//constructor which takes in every value and sets them
		public zipCodeClass(String input) {
			Scanner scan = new Scanner(input);
			scan.useDelimiter(",");
			zipCode = scan.nextInt();
			typeZip = scan.next();
			cityName = scan.next();
			countyName = scan.next();
			estPop = scan.nextInt();
			scan.close();
		}
		
		//all of the getters
		//getter for the county
		int getZip() {
			return zipCode;
		}
		
		//getter for the county
		String getCityName() {
			return cityName;
		}
		
		//getter for the county
		String getCountyName() {
			return countyName;
		}
		
		//getter for the county
		String getTypeZip() {
			return typeZip;
		}
		
		//getter for the county
		int getPop() {
			return estPop;
		}
}
