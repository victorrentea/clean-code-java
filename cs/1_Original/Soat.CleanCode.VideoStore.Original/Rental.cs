using System;

namespace Soat.CleanCode.VideoStore.Original
{
    public class Rental
    {
        public int DaysRented { get; }
        public decimal Amount { get; }
        public virtual Movie Movie { get; }

        public Rental(Movie movie, int daysRented)
        {
            Movie      = movie;
            DaysRented = daysRented;
            Amount = GetAmount();
        }

        public decimal GetAmount()
        {
            var thisAmount = 0m;

            switch (Movie.PriceCode)
            {
                case Movie.REGULAR:
                    thisAmount = 2;
                    if (DaysRented > 2)
                    {
                        thisAmount += (DaysRented - 2) * 1.5m;
                    }
                    return thisAmount;
                case Movie.NEW_RELEASE:
                    return DaysRented * 3;
                case Movie.CHILDREN:
                    thisAmount = 1.5m;
                    if (DaysRented > 3)
                    {
                        thisAmount += (DaysRented - 3) * 1.5m;
                    }
                    return thisAmount;
                default:
                     throw new Exception("Wrong type");
            }

        }
    }
}
