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
            var statement = RentalHelper.InitialStatement(Name);

            _rentals.ForEach(r => { frequentRenterPoints += RentalHelper.CalculateFrequentRenterPoints(r);});

            foreach (var rental in _rentals)
            {
                

                var thisAmount = RentalHelper.CalculateRentalAmount(rental);

                statement += RentalHelper.RentalStatement(rental, thisAmount);

                totalAmount += thisAmount;
            }

            statement += RentalHelper.EndStatement(frequentRenterPoints, totalAmount);

            return statement;
        }

        


    }
}
