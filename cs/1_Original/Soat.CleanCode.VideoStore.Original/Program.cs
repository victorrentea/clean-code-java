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
            customer.AddRental(new Rental(new Movie("Sofia", MovieType.CHILDREN), 7));
            customer.AddRental(new Rental(new Movie("Inception", MovieType.REGULAR), 5));
            customer.AddRental(new Rental(new Movie("Wicked", MovieType.CHILDREN), 3));

            var expected =
                "Rental Record for John Doe\n\tStar Wars \t 18.0\n\tSofia \t 7.5\n\tInception \t 6.5\n\tWicked \t 1.5\nYou owed 33.5\nYou earned 5 frequent renter points \n";
            var actual = customer.CalculateRentalPointsStatement();

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