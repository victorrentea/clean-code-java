using System.Collections.Generic;
using System.Globalization;
using System.Linq;

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
        public int GetFrequentRenterPoints()
        {
            int points = _rentals.Count;
            int extraPoints = _rentals.Where(x => x.Movie.PriceCode == Movie.NEW_RELEASE && x.DaysRented > 1).Count();
            return points + extraPoints;
        }

        public void AddRental(Rental rental)
        {
            _rentals.Add(rental);
        }

        public string Statement()
        {
            var totalAmount = 0m;
            var result = "Rental Record for " + Name + "\r\n";
            foreach (var rental in _rentals)
            {
                result += "\t" + rental.Movie.Title + "\t" + rental.Amount.ToString("0.0", CultureInfo.InvariantCulture) + "\r\n";
                totalAmount += rental.Amount;
            }
            
            var frequentRenterPoints = GetFrequentRenterPoints();

            result += "You owed " + totalAmount.ToString("0.0", CultureInfo.InvariantCulture) + "\r\n";
            result += "You earned " + frequentRenterPoints.ToString() + " frequent renter points \r\n";

            return result;
        }

        
    }
}
