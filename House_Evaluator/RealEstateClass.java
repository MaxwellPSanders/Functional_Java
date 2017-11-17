//Maxwell Sanders
//11/9/17

import java.util.Scanner;

public class RealEstateClass {
	//all of the variables that this class holds
	String property_Type;
	String address;
	String city;
	int zip;
	int price;
	double beds;
	double baths;
	String location;
	int square_Feet;
	int lot_Size;
	int year_Built;
	int days_On_Market;
	int dollar_Per_Sq_Ft;
	int HOA_Per_Month;
	int rating;

	
	
	//the constructor, one that uses a string input and parses it
	public RealEstateClass(String input) {
		//create scanner and set it to comma since it is going to parse a csv
		Scanner in = new Scanner(input);
		in.useDelimiter(",");
		
		//put all of the code into the class
		property_Type = in.next();
		address = in.next();
		city = in.next();
		zip = in.nextInt();
		price = in.nextInt();
		beds = in.nextDouble();
		baths = in.nextDouble();
		location = in.next();
		square_Feet = in.nextInt();
		lot_Size = in.nextInt();
		year_Built = in.nextInt();
		days_On_Market = in.nextInt();
		dollar_Per_Sq_Ft = in.nextInt();
		HOA_Per_Month = in.nextInt();
		
		in.close();
		
		//calculate rating
		//only single family residential
		if(property_Type.equals("Single Family Residential")) {
			//price is less than 200k
			if(price < 200000) {
				//dollar per square foot is less than 110
				if(dollar_Per_Sq_Ft < 110) {
					//the square feet is less than 2500 and greater than 1750
					if(square_Feet <=2500 && square_Feet >= 1750) {
						//if it is built after 2007
						if(year_Built >= 2007) {
							//Hoa per month is less than $25
							if(HOA_Per_Month <= 25) {
								rating = 1;
							}
							//Hoa per month is less than $30
							else if(HOA_Per_Month <=30) {
								rating = 2;
							}
							else {
								//we don't give it a don't show rating
								rating = 0;
							}
						}
						//if it is built after 2000
						else if(year_Built >= 2000) {
							//Hoa per month is less than $25
							if(HOA_Per_Month <= 25) {
								rating = 3;
							}
							//Hoa per month is less than $30
							else if(HOA_Per_Month <=30) {
								rating = 4;
							}
							else {
								//we don't give it a don't show rating
								rating = 0;
							}
						}
						else {
							//we don't give it a don't show rating
							rating = 0;
						}
					}
					else {
						//we don't give it a don't show rating
						rating = 0;
					}
				}
			}
			else {
				//we don't give it a don't show rating
				rating = 0;
			}
		}
		else {
			//we don't give it a don't show rating
			rating = 0;
		}
	}
	
	//all of the getters
	String getProperty_Type() {
		return property_Type;
	}
	
	String getAddress() {
		return address;
	}
	
	String getCity() {
		return city;
	}
	
	String getLocation() {
		return location;
	}
	
	int getPrice() {
		return price;
	}
	
	int getZip() {
		return zip;
	}
	
	int getSquare_Feet() {
		return square_Feet;
	}
	
	int getLot_Size() {
		return lot_Size;
	}
	
	int getYear_Built() {
		return year_Built;
	}
	
	int getDays_On_Market() {
		return days_On_Market;
	}
	
	int getDollar_Per_Sq_Ft() {
		return dollar_Per_Sq_Ft;
	}
	
	int getHOA_Per_Month() {
		return HOA_Per_Month;
	}
	
	int getRating() {
		return rating;
	}
	
	double getBeds() {
		return beds;
	}
	
	double getBaths() {
		return baths;
	}
}
