using System.Collections.Generic;
using System.Globalization;

namespace Soat.CleanCode.VideoStore.Original
{
    public class Customer
    {
        private readonly List<Rental> _rentals = new List<Rental>();
        private int FrequentRenterPoints = 0;
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
            var (totalAmount, result) = DeterminesAmountOfRentals();

            result += "You owed " + totalAmount.ToString("0.0", CultureInfo.InvariantCulture) + "\n";
            result += "You earned " + FrequentRenterPoints.ToString() + " frequent renter points \n";

            return result;
        }

        private (decimal, string) DeterminesAmountOfRentals()
        {
            var totalAmount = 0m;
            var result = "Rental Record for " + Name + "\n";
            foreach (var rental in _rentals)
            {
                //dtermines the amount for each line
                var thisAmount = CalculateThisAmount(rental);
                CalculateFrequentRenterPoints(rental);

                result += "\t" + rental.Movie.Title + "\t" + thisAmount.ToString("0.0", CultureInfo.InvariantCulture) + "\n";
                totalAmount += thisAmount;
            }
            return (totalAmount, result);
        }

        private void CalculateFrequentRenterPoints(Rental each)
        {
            FrequentRenterPoints++;

            if (each.Movie.PriceCode == Movie.NEW_RELEASE
                && each.DaysRented > 1)
            {
                FrequentRenterPoints++;
            }
        }

        private decimal CalculateThisAmount(Rental each)
        {
            var thisAmount = 0m;

            switch (each.Movie.PriceCode)
            {
                case Movie.REGULAR:
                    thisAmount += 2;
                    if (each.DaysRented > 2)
                    {
                        thisAmount += (each.DaysRented - 2) * 1.5m;
                    }
                    break;
                case Movie.NEW_RELEASE:
                    thisAmount += each.DaysRented * 3;
                    break;
                case Movie.CHILDREN:
                    thisAmount += 1.5m;
                    if (each.DaysRented > 3)
                    {
                        thisAmount += (each.DaysRented - 3) * 1.5m;
                    }
                    break;
            }
            return thisAmount;
        }
    }
}
