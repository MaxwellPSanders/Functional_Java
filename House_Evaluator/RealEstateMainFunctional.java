//maxwell Sanders
//11/9/17

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class RealEstateMainFunctional {
	public static void main(String[] args) throws IOException {
		//create the buffered readers
		BufferedReader inHouse
		   = new BufferedReader(new FileReader("House Data.csv"));
		BufferedReader inZip
		   = new BufferedReader(new FileReader("ZipRateTable.csv"));

		//array lists that will hold all of the data
		ArrayList<RealEstateClass> estates = new ArrayList<RealEstateClass>();
		ArrayList<ZipRateTable> zips = new ArrayList<ZipRateTable>();
	
		//takes all of the lines of the input and puts them into the array list
		inHouse.lines().forEach((s)->estates.add(new RealEstateClass(s)));
		inZip.lines().forEach((s)->zips.add(new ZipRateTable(s)));
		inHouse.close();
		inZip.close();
		
		//make the HouseAveragesByZip.txt
		FileWriter outZip = new FileWriter(new File("HouseAveragesByZip.txt"));
		outZip.write("Zip Code\tNo. Homes\tAve Price\tAve Sqft\tAve Beds\tAve Baths\tAve $/sqft\tAve DOM\tAve YrBlt\tAve HOA\n");
		
		//stream through all the zips and then write the inputs row by row
		zips.stream()	
			.mapToInt((zip)->zip.getZip())
			.sorted()
			.distinct()
			.forEach((zip)->
					{
						try {
							outZip.write(
									//zip code
									zip
									
									+ "\t" +
									
									//No. Homes (I will literally copy and paste this code multiple times
									estates.stream()
										.filter((house)->house.getZip() == zip)
										.mapToInt((house)->1)
										.sum()
										
									+ "\t" +
									
									//Ave Price
									String.format("$%,d", 
											//total price/number of houses
											estates.stream()
												.filter((house)->house.getZip() == zip)
												.mapToInt((house)->house.getPrice())
												.sum()/
												estates.stream()
												.filter((house)->house.getZip() == zip)
												.mapToInt((house)->1)
												.sum()
											)
									
									+ "\t" + 

									//Ave Sqft
									String.format("%,d", 
											//total sqft/number of houses
											estates.stream()
												.filter((house)->house.getZip() == zip)
												.mapToInt((house)->house.getSquare_Feet())
												.sum()/
												estates.stream()
												.filter((house)->house.getZip() == zip)
												.mapToInt((house)->1)
												.sum()
											)
									
									+ "\t" + 
									
									//Ave Beds
									String.format("%.1f", 
											//total beds/number of houses
											estates.stream()
												.filter((house)->house.getZip() == zip)
												.mapToDouble((house)->house.getBeds())
												.sum()/
												estates.stream()
												.filter((house)->house.getZip() == zip)
												.mapToInt((house)->1)
												.sum()
											)
									
									+ "\t" + 
									
									//Ave baths
									String.format("%.1f", 
											//total baths/number of houses
											estates.stream()
												.filter((house)->house.getZip() == zip)
												.mapToDouble((house)->house.getBaths())
												.sum()/
												estates.stream()
												.filter((house)->house.getZip() == zip)
												.mapToInt((house)->1)
												.sum()
											)
									
									+ "\t" + 
									
									//Ave $/Sqft
									String.format("$%.2f", 
											//total $/sqft/number of houses
											estates.stream()
												.filter((house)->house.getZip() == zip)
												.mapToDouble((house)->house.getDollar_Per_Sq_Ft())
												.sum()/
												estates.stream()
												.filter((house)->house.getZip() == zip)
												.mapToInt((house)->1)
												.sum()
											)
									
									+ "\t" + 
									
									//Ave DOM
									String.format("%.1f", 
											//total DOM/number of houses
											estates.stream()
												.filter((house)->house.getZip() == zip)
												.mapToDouble((house)->house.getDays_On_Market())
												.sum()/
												estates.stream()
												.filter((house)->house.getZip() == zip)
												.mapToInt((house)->1)
												.sum()
											)
									
									+ "\t" + 
									
									//Ave YrBlt
									String.format("%d", 
											//total Yrblt/number of houses
											estates.stream()
												.filter((house)->house.getZip() == zip)
												.mapToInt((house)->house.getYear_Built())
												.sum()/
												estates.stream()
												.filter((house)->house.getZip() == zip)
												.mapToInt((house)->1)
												.sum()
											)
									
									+ "\t" + 
									
									//Ave HOA
									String.format("$%.2f", 
											//total HOA/number of houses
											estates.stream()
												.filter((house)->house.getZip() == zip)
												.mapToDouble((house)->house.getHOA_Per_Month())
												.sum()/
												estates.stream()
												.filter((house)->house.getZip() == zip)
												.mapToInt((house)->1)
												.sum()
											)
									
									+ "\n");
						} catch (ArithmeticException A) {
							//if there was no houses it will fail but end up here, which works out
							try {
								outZip.write(zip + "\t0\t0\t0\t0\t0\t0\t0\t0\t0\n");
							} catch (IOException e) {
								e.printStackTrace();
							}
						} catch(IOException e) {
							e.printStackTrace();
						}
						
					}
					);
	
		
		//close the output
		outZip.close();
		
		//now we are making the HouseOrderByRating.txt and then making the header
		FileWriter outHouse = new FileWriter(new File("HouseOrderByRating.txt"));
		outHouse.write("Type	Address\tCity\tZip\tPrice\tBeds\tBaths\tLocation\tSqft\tLot size\tYrBlt\tDOM\t$/SqFt\tHOA/mth\tRank Grp\tPercnt SqFt\n");
		
		//stream through all the houses, filter out the ones that don't make the rating
		estates.stream()
			.filter((house)->house.getRating() >= 1 && house.getRating() <= 4)
			.filter((house)->zips.stream()
					.filter((zip)->zip.getZip() == house.getZip())
					.findFirst().get().getRating() > 6)
			.forEach((house)->{
				try {
					outHouse.write(
							//the type of the house
							house.getProperty_Type()
							
							+ "\t" +
							
							//the address of the house
							house.getAddress()
							
							+ "\t" +
							
							//the city the house is in
							house.getCity()
							
							+ "\t" +
							
							//the zip the house is in
							house.getZip()
							
							+ "\t" +
							
							//the price of the house
							String.format("$%,d", house.getPrice())
							
							+ "\t" +
							
							//get the amount of beds
							String.format("%.1f", house.getBeds())
							
							+ "\t" +
							
							//get the amount of baths
							String.format("%.1f", house.getBaths())
							
							+ "\t" +
							
							//get the houses location
							house.getLocation()
							
							+ "\t" +
							
							//get the house Sqft
							String.format("%,d", house.getSquare_Feet())
							
							+ "\t" +
							
							//get the house Lot Size
							String.format("%,d", house.getLot_Size())
							
							+ "\t" +
							
							//get the house Year built
							String.format("%4d", house.getYear_Built())
							
							+ "\t" +
							
							//get the Dom
							house.getDays_On_Market()
							
							+ "\t" +
							
							//get the $/Sqft
							String.format("$%,d",house.getDollar_Per_Sq_Ft())
							
							+ "\t" +
							
							//get the HOA/Month
							String.format("$%,d",house.getHOA_Per_Month())
							
							+ "\t" +
							
							//get the rating
							house.getRating()
							
							+ "\t" +
							
							//get the percent %$/Sqft (100*(Dollar Per Sqft/(total Dollar per sqft/total houses in zip)))
							String.format("%.1f", 100*(
									house.getDollar_Per_Sq_Ft()/(
											estates.stream()
											.filter((estate)->estate.getZip() == house.getZip())
											.mapToDouble((estate)->estate.getDollar_Per_Sq_Ft())
											.sum()/
												estates.stream()
												.filter((estate)->estate.getZip() == house.getZip())
												.mapToInt((estate)->1)
												.sum()
									)))
							
							+ "\n");
				} catch (IOException e) {
					e.printStackTrace();
				}
			});
					
			
		
		//close the output
		outHouse.close();
	}
}
