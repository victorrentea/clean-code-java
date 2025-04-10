namespace Soat.CleanCode.VideoStore.Original
{
    public class RentalHelper
    {
        public static int CalculateFrequentRenterPoints(Rental rental)
        {
            return (rental.Movie.Type == MovieType.NEW_RELEASE && rental.DaysRented > 1) ? 2 : 1;  
        }

        public static decimal CalculateRentalAmount(Rental rental)
        {
            var thisAmount = 0m;

            //determines the amount for each line
            switch (rental.Movie.Type)
            {
                case MovieType.REGULAR:
                    thisAmount = 2;
                    if (rental.DaysRented > 2)
                    {
                        thisAmount += (rental.DaysRented - 2) * 1.5m;
                    }
                    break;
                case MovieType.NEW_RELEASE:
                    thisAmount = rental.DaysRented * 3;
                    break;
                case MovieType.CHILDREN:
                    thisAmount = 1.5m;
                    if (rental.DaysRented > 3)
                    {
                        thisAmount += (rental.DaysRented - 3) * 1.5m;
                    }
                    break;
            }

            return thisAmount;
        }

        public static string InitialStatement(string Name)
        {
            return $"Rental Record for {Name}\n";
        }

        public static string EndStatement(int frequentRenterPoints, decimal totalAmount)
        {
           return $"You owed {totalAmount.ToOneDecimalString()}\nYou earned {frequentRenterPoints} frequent renter points \n";
           
        }

        public static string RentalStatement(Rental rental, decimal thisAmount)
        {
            return $"\t{rental.Movie.Title} \t {thisAmount.ToOneDecimalString()}\n";
        }
    }
}