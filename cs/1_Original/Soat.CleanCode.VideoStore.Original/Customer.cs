using System.Collections.Generic;
using System.Globalization;

namespace Soat.CleanCode.VideoStore.Original
{
    public class Customer
    {
        private readonly List<Rental> _rentals = new List<Rental>();
        public string Name { get; }

        public Customer(string name)
        {
            Name = name;
        }

        public void AddRental(Rental rental)
        {
            _rentals.Add(rental);
        }

        public string Statement()
        {
            var frequentRenterPoints = 0;
            var totalAmount = 0m;
            var result = "Rental Record for " + Name + "\r\n";
            foreach (var rental in _rentals)
            {
                decimal thisAmount = rental.GetAmmount();

                frequentRenterPoints++;

                if (rental.Movie.PriceCode == Movie.NEW_RELEASE
                    && rental.DaysRented > 1)
                {
                    frequentRenterPoints++;
                }

                result += "\t" + rental.Movie.Title + "\t" + thisAmount.ToString("0.0", CultureInfo.InvariantCulture) + "\r\n";
                totalAmount += thisAmount;
            }

            result += "You owed " + totalAmount.ToString("0.0", CultureInfo.InvariantCulture) + "\r\n";
            result += "You earned " + frequentRenterPoints.ToString() + " frequent renter points \r\n";

            return result;
        }

        
    }
}
