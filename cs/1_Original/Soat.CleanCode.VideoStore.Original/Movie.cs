namespace Soat.CleanCode.VideoStore.Original
{
    public class Movie
    {
        public MovieType Type { get; }
        public string Title { get; }

        public Movie(string title, MovieType movieType)
        {
            Title = title;
            Type = movieType;
        }
    }
}
