using System;
using Soat.CleanCode.VideoStore.Original;

namespace MyApp
{
    class Program
    {
        static void Main(string[] args)
        {

            var customer = new Customer("John Doe");
            customer.AddRental(new Rental(new Movie("Star Wars", MovieType.NEW_RELEASE), 6));
            customer.AddRental(new Rental(new Movie("Sofia", Movie.CHILDREN), 7));
            customer.AddRental(new Rental(new Movie("Inception", Movie.REGULAR), 5));
            customer.AddRental(new Rental(new Movie("Wicked", Movie.CHILDREN), 3));

            var expected =
                @"Rental Record for John Doe
	Star Wars	18.0
	Sofia	7.5
	Inception	6.5
	Wicked	1.5
You owed 33.5
You earned 5 frequent renter points 
";

            var actual = customer.Statement();



            if (actual.Equals(expected))
            {
                Console.WriteLine("Test passed");
            }
            else
            {
                Console.WriteLine("Tests failed.\n***Expected output:");
                Console.WriteLine(expected);
                Console.WriteLine("\n***Actual output:");
                Console.WriteLine(actual);
            }


        }
    }

}