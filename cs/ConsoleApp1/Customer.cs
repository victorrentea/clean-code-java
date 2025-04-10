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
            var statement = RentalHelper.InitialStatement(Name);

            var frequentRenterPoints =  _rentals.Select(r =>  RentalHelper.CalculateFrequentRenterPoints(r)).Sum();
            var totalAmount =  _rentals.Select(r =>  RentalHelper.CalculateRentalAmount(r)).Sum();
            _rentals.ForEach(r => { statement += RentalHelper.RentalStatement(r); });

            statement += RentalHelper.EndStatement(frequentRenterPoints, totalAmount);

            return statement;
        }

        


    }
}
