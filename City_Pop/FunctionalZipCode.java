//Maxwell Sanders
//10/26/17

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FunctionalZipCode {

	//this is the main method
	public static void main(String[] args) throws IOException {
		//File input
				BufferedReader in
					   = new BufferedReader(new FileReader("zip_code_database.csv"));
			
				//array lists that will hold all of the data
				ArrayList<zipCodeClass> zips = new ArrayList<zipCodeClass>();
				
				//takes all of the lines of the input and puts them into the array list
				in.lines().forEach((s)->zips.add(new zipCodeClass(s)));
				in.close();
				
				//making the file header
				FileWriter output = new FileWriter("Output.txt");
				output.write("County Name\tCounty Pop\tCity Name\tCity Pop\tNo. Zip Codes\n");
			
				//create an outer loop going through the counties
				zips.stream()
					.map((zip)->zip.getCountyName())
					.sorted()
					.distinct()
					//for each of those counties find all the cities and make another loop
					.forEach((county)->
						zips.stream()
							.filter((zip)->zip.getCountyName().equals(county))
							.map((zip)->zip.getCityName())
							.sorted()
							.distinct()
							//now go through all the cities and print the rows
							.forEach((city)-> {
								try {
									output.write(
											//county name
											county
											
											+ "\t" +
											
											//county population
											zips.stream()
												.filter((zip)->zip.getCountyName().equals(county))
												.mapToInt((zip)->zip.getPop())
												.sum()
											
											+ "\t" +
											
											//city name
											city

											+ "\t" +
											
											//city population
											zips.stream()
												.filter((zip)->zip.getCountyName().equals(county))
												.filter((zip)->zip.getCityName().equals(city))
												.mapToInt((zip)->zip.getPop())
												.sum()
												
											+ "\t" +
											
											//city population
											zips.stream()
												.filter((zip)->zip.getCountyName().equals(county))
												.filter((zip)->zip.getCityName().equals(city))
												.mapToInt((zip)->1)
												.sum()	
												
											+ "\n"
											
											);
								} catch (IOException e) {
									e.printStackTrace();
								}
							}
							)
					);
				
				
				output.close();
	}

}
