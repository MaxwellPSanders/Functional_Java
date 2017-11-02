//Maxwell Sanders
//11/2/17

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
				output.write("County Name\tCity Name\tZip Type\tZip Pop\tFirst Zip\tNo. Zips\tCity Pop\n");
			
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
							//now go through all the cities and make another loop
							.forEach((city)-> 
								zips.stream()	
									.filter((zip)->zip.getCountyName().equals(county))
									.filter((zip)->zip.getCityName().equals(city))
									.map((zip)->zip.getTypeZip())
									.distinct()
									.sorted()
									//now go through all the zip types
									.forEach((zip)-> {
										try {
											output.write(
											
											//county name
											(zips.stream()
												.filter((temp)->temp.getCountyName().equals(county))
												.filter((temp)->temp.getCityName().equals(city))
												.map((temp)->temp.getTypeZip())
												.distinct()
												.sorted()
												.findFirst().get().equals(zip)?county:"")
															
											
											+ "\t" +
											
											//city name
											(zips.stream()
													.filter((temp)->temp.getCountyName().equals(county))
													.filter((temp)->temp.getCityName().equals(city))
													.map((temp)->temp.getTypeZip())
													.distinct()
													.sorted()
													.findFirst().get().equals(zip)?city:"")
											
											+ "\t" +
											
											//type zip
											zip
											
											+ "\t" +
											
											//zip pop
											String.format("%,d",zips.stream()
												.filter((temp)->temp.getCountyName().equals(county))
												.filter((temp)->temp.getCityName().equals(city))
												.filter((temp)->temp.getTypeZip().equals(zip))
												.mapToInt((temp)->temp.getPop())
												.sum())
											
											+ "\t" +
								
											//first zip
											zips.stream()
													.filter((temp)->temp.getCountyName().equals(county))
													.filter((temp)->temp.getCityName().equals(city))
													.filter((temp)->temp.getTypeZip().equals(zip))
													.mapToInt((temp)->temp.getZip())
													.sorted()
													.findFirst().getAsInt()
											
											+ "\t" +
											
											//number of zips
											zips.stream()
												.filter((temp)->temp.getCountyName().equals(county))
												.filter((temp)->temp.getCityName().equals(city))
												.filter((temp)->temp.getTypeZip().equals(zip))
												.mapToInt((temp)->1)
												.sum()
											
											+ "\t" +
											
											//city pop
											(zips.stream()
													.filter((temp)->temp.getCountyName().equals(county))
													.filter((temp)->temp.getCityName().equals(city))
													.map((temp)->temp.getTypeZip())
													.distinct()
													.sorted()
													.findFirst().get().equals(zip)?
											String.format("%,d", zips.stream()
												.filter((temp)->temp.getCountyName().equals(county))
												.filter((temp)->temp.getCityName().equals(city))
												.mapToInt((temp)->temp.getPop())
												.sum()):"")
											
											+ "\n"
													
											);
										} catch (IOException e) {
											e.printStackTrace();
										}
									}
									)
							)
					);
				
				
				output.close();
	}

}
