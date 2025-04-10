namespace Soat.CleanCode.VideoStore.Original
{
    public class Movie
    {
        public MovieType Type { get; }
        public int PriceCode { get; set; }
        public string Title { get; }

        public Movie(string title, int priceCode, MovieType movieType)
        {
            Title = title;
            PriceCode = priceCode;
            Type = movieType;
        }
    }
}
