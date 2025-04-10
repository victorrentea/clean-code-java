namespace Soat.CleanCode.VideoStore.Original
{
    public class Rental
    {
        public int DaysRented { get; }
        public Movie Movie { get; }

        public Rental(Movie movie, int daysRented)
        {
            Movie      = movie;
            DaysRented = daysRented;
        }
    }
}
