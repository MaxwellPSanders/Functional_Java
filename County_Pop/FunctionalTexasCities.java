//Maxwell Sanders
//10/12/17
//this is the main program where are the magic happens

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FunctionalTexasCities {

	public static void main(String[] args) throws IOException {
		//File input
		BufferedReader in
			   = new BufferedReader(new FileReader("Texas_Cities_Input.csv"));
	
		//array list that will hold all of the data
		ArrayList<texasCitiesClass> cities = new ArrayList<texasCitiesClass>();
		
		//takes all of the lines of the input and puts them into the array list
		in.lines().forEach((s)->cities.add(new texasCitiesClass(s)));
		in.close();
		
		//making the file header
		FileWriter output = new FileWriter("Texas_Cities_Output.txt");
		output.write("County Name\tNo. Cities\tTotal Pop\tAve Pop\tLargest City\tPopulation\n");
		
		//array list to hold all of the county names in order
		ArrayList<String> counties = new ArrayList<String>();
		cities.stream()
				.map((city)->city.getCountyName())
				.sorted((county1, county2)->county1.compareTo(county2))
				.distinct()
				.forEach((city)-> counties.add(city));
		
		//while loop that keeps going till we get through all the counties
		counties.stream()
			.forEach( (county) -> 
			
			//write the county name
			{
				try {
					output.write(county + "\t"
					
					//get the city count
					+ cities.stream()
							.filter((city) -> city.getCountyName().equals(county))
							.mapToInt((city) -> 1).sum() + "\t" 

					//get the total population
					+ String.format("%,d", cities.stream()
							.filter((city) -> city.getCountyName().equals(county))
							.mapToInt((city) -> city.getPop())
							.sum()) + "\t"
					
					//average population
					+ String.format("%,d", cities.stream()
							.filter((city) -> city.getCountyName().equals(county))
							.mapToInt((city) -> city.getPop())
							.sum()/cities.stream()
							.filter((city) -> city.getCountyName().equals(county))
							.mapToInt((city) -> 1).sum()) + "\t"
					
					//get the largest city population			
					+ cities.stream()
							.filter((city) -> city.getCountyName().equals(county))
							.sorted((city1,city2)-> - Integer.compare(city1.getPop(), city2.getPop()))
							.findFirst().get().getCityName() + "\t"
					
					//get the largest city population
					+ String.format("%,d", cities.stream()
							.filter((city)->city.getCityName().equals(cities.stream()
									.filter((cit) -> cit.getCountyName().equals(county))
									.sorted((city1,city2)-> - Integer.compare(city1.getPop(), city2.getPop()))
									.findFirst().get().getCityName()))
							.findFirst().get().getPop()) + "\n");
				} catch (IOException e) {
				}
			}
		);
	
		output.close();
	}

}
