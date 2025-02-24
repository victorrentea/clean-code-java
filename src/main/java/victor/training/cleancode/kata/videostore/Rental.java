package victor.training.cleancode.kata.videostore;

public record Rental(Movie movie, int days )
{
    public double getCost()
    {
        double cost = 0;
        switch ( movie.priceCategory() )
        {
            case REGULAR ->
            {
                cost += 2;
                if ( days> 2 )
                {
                    cost += ( days - 2 ) * 1.5;
                }
            }
            case NEW_RELEASE -> cost += days * 3;
            case CHILDREN ->
            {
                cost += 1.5;
                if ( days > 3 )
                {
                    cost += ( days - 3 ) * 1.5;
                }
            }
        }
        return cost;
    }

    @Override
    public String toString()
    {
        return String.format("\t%s\t%s\n", movie().title(), getCost());
    }
}
