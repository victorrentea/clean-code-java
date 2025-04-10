using System.Collections.Generic;

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
            var totalAmount = 0m;
            var result = $"Rental Record for {Name}\n";

            foreach (var rental in _rentals)
            {
                frequentRenterPoints += RentalHelper.CalculateFrequentRenterPoints(rental);

                var thisAmount = RentalHelper.CalculateRentalAmount(rental);

                result += $"\t{rental.Movie.Title} \t {thisAmount.ToOneDecimalString()}\n";

                totalAmount += thisAmount;
            }

            result += $"You owed {totalAmount.ToOneDecimalString()}\nYou earned {frequentRenterPoints} frequent renter points \n";

            return result;
        }
    }
}
