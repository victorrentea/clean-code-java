using System.Collections.Generic;
using System.Globalization;

namespace Soat.CleanCode.VideoStore.Original
{
    public class Customer
    {
        private List<Rental> _rentals = new List<Rental>();
        public string Name { get; }

        public Customer(string name)
        {
            Name = name;
        }

        public void AddRental(Rental rental)
        {
            _rentals.Add(rental);
        }

        public string CalculateRentalPointsStatement()
        {
            var frequentRenterPoints = 0;
            var totalAmount          = 0m;
            var result               = $"Rental Record for {Name}\n";

            foreach (var rental in _rentals)
            {

                var thisAmount = CalculateRentalPoints(rental);

                frequentRenterPoints++;

                if (rental.Movie.Type == MovieType.NEW_RELEASE
                    && rental.DaysRented > 1)
                {
                    frequentRenterPoints++;
                }

                result += $"\t{rental.Movie.Title} \t {thisAmount.ToString("0.0", CultureInfo.InvariantCulture)}\n";
                totalAmount += thisAmount;
            }

            result += $"You owed {totalAmount.ToString("0.0", CultureInfo.InvariantCulture)}\n";
            result += $"You earned {frequentRenterPoints.ToString()} frequent renter points \n";

            return result;
        }

        private static decimal CalculateRentalPoints(Rental rental)
        {
            var thisAmount = 0m;
            //dtermines the amount for each line
            switch (rental.Movie.Type)
            {
                case MovieType.REGULAR:
                    thisAmount += 2;
                    if (rental.DaysRented > 2)
                    {
                        thisAmount += (rental.DaysRented - 2) * 1.5m;
                    }
                    break;
                case MovieType.NEW_RELEASE:
                    thisAmount += rental.DaysRented * 3;
                    break;
                case MovieType.CHILDREN:
                    thisAmount += 1.5m;
                    if (rental.DaysRented > 3)
                    {
                        thisAmount += (rental.DaysRented - 3) * 1.5m;
                    }
                    break;
            }

            return thisAmount;
        }
    }
}
