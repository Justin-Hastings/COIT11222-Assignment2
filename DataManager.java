//Programmer: Justin Hastings 12147349
//File: DataManager.java
//Date: October 2 2020
//Purpose: COIT11222 assignment two T2-20
//Read inputs from user and store them in separate arrays,
//Displays the data in the arrays, search the arrays, alter
//Array data, calculate statistics from the data in the array and exit the program.

import java.util.Scanner;
import java.lang.*;

public class DataManager
{
	//Highest digit in my student ID + 2
	final int N = 11;
	//This sets the dashes for when displaying the statistics
	final String statsDashes = "----------------------------------------------------";

	//Declare 2 arrays for storing data
	//Stores the cities
	private String [] cities = new String[N];
	//Stores the prices
	private double [] prices = new double[N];

	//Declare scanners for inputting data
	Scanner inCityData = new Scanner (System.in);
	Scanner inPriceData = new Scanner (System.in);

	//Declare scanners for searching and altering the data
	Scanner inSearchCity = new Scanner (System.in);
	Scanner inAlterPrice = new Scanner (System.in);

	//Define read data method
	private void enterDataMethod()
	{
		//Prompt user to insert data
		System.out.println("Enter " + N + " data entries one by one");
		System.out.println();

		//Loop to insert user data until the array is full
		for (int i = 0; i < cities.length; i++)
		{
			System.out.print("Enter a city name: ");
			//Declares city and sets it to the value entered by the user
			String city = inCityData.nextLine();

			System.out.print("Enter " + city + " city petrol price($): ");
			//Declares petrolPrice and sets it to the value entered by the user
			double petrolPrice = inPriceData.nextDouble();

			System.out.println();

			//Populates the array
			cities[i] = city;
			prices[i] = petrolPrice;

		}

		System.out.println();
		System.out.println("Data entered process has completed");
		System.out.println();

	} //End of enter data method

	//Define display method
	private void displayDataMethod()
	{
		//Displays the heading for the display data table
		System.out.println();
		System.out.printf("%s%s %n" , "   City", "     Petrol price");
		System.out.println("--------------------");

		//Gets the data from the arrays and displays it
		for (int i = 0; i < cities.length; i++)
		{
			System.out.printf("%10s%s$%.2f %n", cities[i], "   ", prices[i]);
		}

		System.out.println();

	}	//End of display data method

	//Define search method
	private void searchDataMethod()
	{
		//To determine whether the city exists
		Boolean doesExist = false;
		//Sets the current price of the found city to 0
		double searchPrice = 0;
		//Declares cityName to be later set to the city entered by the user
		String cityName;
		//Declares checkCity to be later set to the current city being checked in the array
		String checkCity = "";

		//Get the city to search for
		System.out.println();
		System.out.print("Enter the city name to be searched: ");
		cityName = inSearchCity.nextLine();

		//Searches the array by checking if the given city name matches one stored in the array
		for (int i = 0; i < cities.length; i++)
		{
			//Sets checkCity to the current city name being checked in the array
			checkCity = cities[i];
			//Converts both the user's inputted city name and the city name retrieved from the array to all lowercase letters
			//this allows the search feature to be case insensitive
			if (cityName.toLowerCase().equals(checkCity.toLowerCase()) )
			{
				doesExist = true;
				searchPrice = prices[i];
				break;
			}
		}
		//Displays the petrol price of the city if it's found
		if (doesExist)
		{
			System.out.printf("%s$%.2f%s %n", "The " + checkCity + " city has the petrol price ", searchPrice, "/L" );
		}
		//Displays a "not existed" message if the city wasn't found
		else
		{
			System.out.println("The entry of this city not existed.");
		}

		System.out.println();

	}	//End of search data method

	//Define alter method
	private void alterDataMethod()
	{
		//Where the city is located in the array
		int indexLocation = -1; //Not found
		//To determine whether the city exists
		Boolean doesExist = false;
		//Sets the current price of the found city to 0
		double alterPrice = 0;
		//Declares cityName to be later set to the city entered by the user
		String cityName;

		//Get the city to alter for
		System.out.println();
		System.out.print("Enter the city name to update petrol price: ");
		cityName = inSearchCity.nextLine();

		//searches for the city to alter
		for (int i = 0; i < cities.length; i++)
		{
			if (cityName.equals(cities[i]) )
			{
				doesExist = true;
				indexLocation = i;
				break;
			}
		}
		//Alters the price and displays a success message
		if (doesExist)
		{
			System.out.print("Enter the new price for " + cityName + ": ");
			alterPrice = inAlterPrice.nextDouble();

			//Alter the price in the array
			prices[indexLocation] = alterPrice;

			System.out.printf("%s$%.2f %n%n","Update the city " + cityName + " with the new petrol price ", alterPrice);

		}
		//Displays a "not existed" message if the city wasn't found
		else
		{
			System.out.println("The entry of this city not existed.");
			System.out.println();
		}
	}	//End of alter data method

	//Define statistics method
	private void statisticsDataMethod()
	{
		//Start calculating average ---------------------------------------
		//Sets the total petrol price to 0
		double totalPetrolPrice = 0;
		//Sets the average petrol price to 0
		double avgPetrolPrice = 0;

		//Loop to retrieve all the petrol prices in the array and add the current price to the total price as it runs through
		for (int i = 0; i < cities.length; i++)
		{
			totalPetrolPrice = totalPetrolPrice + prices[i];
		}
		//Calculates the average by dividing the total price by the number of cities
		avgPetrolPrice = (totalPetrolPrice / cities.length);
		//Finishes calculating the average ------------------------

		//Sorts the loop from smallest to largest in terms of prices ------------------------
		//loop for data.length - 1 passes
		for (int pass = 1; pass < prices.length; pass++ )
		{
			//loop all elements in the array
			for (int index = 0; index < prices.length - pass; index++)
			{
				//swap adjacent elements if first(index) is greater than second(index+1)
				if (prices[index] > prices[index + 1])
				{
					double temp1 = prices[index];
					prices[index] = prices[index + 1];
					prices[index + 1] = temp1;

					String temp2 = cities[index]; //store first in temp
					cities[index] = cities[index + 1]; //replace the first with the second
					cities [index + 1] = temp2;
				} //end if statement
			} //end inner for
		} //end outer for
		//End sort --------------------------------------------

		//Determines the highest and lowest prices and corresponding cities
		//For storing the cheapest city and its price
		String cheapestCity;
		double cheapestPrice = 0;

		//For storing the most expensive city and its price
		String expensiveCity;
		double expensivePrice = 0;

		//location of the cheapest city and price in the arrays after being sorted
		int cheapestIndex = 0;
		//The index is lenght - 1 becuase the lenght doesn't include the initial position being 0
		int expensiveIndex = prices.length - 1;

		//sets the cheapest city and price
		cheapestCity = cities[cheapestIndex];
		cheapestPrice = prices[cheapestIndex];

		//sets the most expensive city and price
		expensiveCity = cities[expensiveIndex];
		expensivePrice = prices[expensiveIndex];
		// Finishes determining the highest and lowest prices

		//Display the statistics data
		System.out.println();
		System.out.println(statsDashes);

		System.out.printf("%s$%.2f %n", "The average price for all cities is ", avgPetrolPrice);
		System.out.printf("%s$%.2f%s %n", "The lowest price among " + N + " cities is ", cheapestPrice, " " + cheapestCity);
		System.out.printf("%s$%.2f%s %n%n", "The highest price among " + N + " cities is ", expensivePrice, " " + expensiveCity);

		//For loops to determine and print the line for average and above/below
		//Below average - displays all the prices below the average:
		System.out.print("City & price below average price:");
		for (int i = 0; i < prices.length; i++)
		{
			if (prices[i] < avgPetrolPrice)
			{
				System.out.printf("%s$%.2f%s", " " + cities[i] + " ", prices[i], " ");
			}
		}
		System.out.println();

		//Sorts the loop from largest to smallest in terms of prices ------------------------
		//loop for data.length - 1 passes
		for (int pass = 1; pass < prices.length; pass++ )
		{
			//loop all elements in the array
			for (int index = 0; index < prices.length - pass; index++)
			{
				//swap adjacent elements if first(index) is greater than second(index+1)
				if (prices[index] < prices[index + 1])
				{
					double temp1 = prices[index];
					prices[index] = prices[index + 1];
					prices[index + 1] = temp1;

					String temp2 = cities[index]; //store first in temp
					cities[index] = cities[index + 1]; //replace the first with the second
					cities [index + 1] = temp2;
				} //end if statement
			} //end inner for
		} //end outer for
		//End sort --------------------------------------------

		//Above average - Displays all the prices above or equal to the average
		System.out.print("City & price above & equal average price:");
		for (int i = 0; i < prices.length; i++)
		{
			if (prices[i] >= avgPetrolPrice)
			{
				System.out.printf("%s$%.2f%s", " " + cities[i] + " ", prices[i], " ");
			}
		}
		System.out.println();

		System.out.println(statsDashes);
		System.out.println();
		//End of outputs

	}	//End of statistics data method

	//Define exit method
	private void exitProgramMethod()
	{
		//Displays a message before breaking the loop
		System.out.println("Thank you very much for using the system, 12147349");
		System.out.println();
	}	//End of exit program method

	//Define menu method
	private void menuMethod()
	{
		final String menuOffset = "                         ";

		System.out.println(menuOffset + "A Miniature Data Management System");
		System.out.println(menuOffset + "-----------------------------------");
		System.out.println(menuOffset + "           Menu Options");
		System.out.println();
		System.out.println(menuOffset + "1.Enter data entries");
		System.out.println(menuOffset + "2.Display all entries");
		System.out.println(menuOffset + "3.Search");
		System.out.println(menuOffset + "4.Alter an entry");
		System.out.println(menuOffset + "5.Statistics");
		System.out.println(menuOffset + "6.Exit the program");
		System.out.println(menuOffset + "-----------------------------------");
		System.out.println();
	}	//End of menu method

	public static void main (String [] args)
	{
		int menuSelection;

		//Create a scanner object to allow reading menu choice
		Scanner menuSelect = new Scanner (System.in);

		//Create a DataManager object
		DataManager dataManager = new DataManager();


		//Call menu method
		dataManager.menuMethod();

		System.out.print("Please enter your menu choice (1-6): ");
		menuSelection = menuSelect.nextInt();

		while (menuSelection != 6)
		{
			switch(menuSelection)
			{
				case 1:
					dataManager.enterDataMethod();
					break;

				case 2:
					dataManager.displayDataMethod();
					break;
				case 3:
					dataManager.searchDataMethod();
					break;
				case 4:
					dataManager.alterDataMethod();
					break;
				case 5:
					dataManager.statisticsDataMethod();
					break;
				default:
					System.out.println("The menu choice is invalid, please select again"); //Error msg - invalid menu choice
			}

			System.out.print("Please enter your menu choice (1-6): ");
			menuSelection = menuSelect.nextInt();
		}

		//This is option 6 - Called outside of the switch statement because it needs to break the while loop
		//call exit program method
		dataManager.exitProgramMethod();

	}	//End of main method
}