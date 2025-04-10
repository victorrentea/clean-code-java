using System.Collections.Generic;
using System.Globalization;

namespace Soat.CleanCode.VideoStore.Original
{
    public class Customer
    {
        //hello
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
            var totalAmount          = 0m;
            var result               = "Rental Record for " + Name + "\n";
            foreach (var each in _rentals)
            {
                var thisAmount = 0m;

                //dtermines the amount for each line
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

                frequentRenterPoints++;

                if (each.Movie.PriceCode == Movie.NEW_RELEASE
                    && each.DaysRented > 1)
                {
                    frequentRenterPoints++;
                }

                result      += "\t" + each.Movie.Title + "\t" + thisAmount.ToString("0.0", CultureInfo.InvariantCulture) + "\n";
                totalAmount += thisAmount;
            }

            result += "You owed " + totalAmount.ToString("0.0", CultureInfo.InvariantCulture) + "\n";
            result += "You earned " + frequentRenterPoints.ToString() + " frequent renter points \n";

            return result;
        }
    }
}
