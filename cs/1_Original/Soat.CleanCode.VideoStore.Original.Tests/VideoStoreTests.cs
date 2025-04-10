using Xunit;

namespace Soat.CleanCode.VideoStore.Original.Tests
{

    public class VideoStoreTests 
    {
        private readonly Customer _customer;

        public VideoStoreTests()
        {
            _customer = new Customer("Fred");
        }

        [Fact]
        public void TestSingleNewReleaseStatement()
        {
            _customer.AddRental(new Rental(new Movie("The cell", Movie.NEW_RELEASE), 3));
            Assert.Equal(_customer.Statement(),
                         "Rental Record for Fred\n\tThe cell\t9.0\nYou owed 9.0\nYou earned 2 frequent renter points \n");
        }

        [Fact]
        public void TestDualNewReleaseStatement()
        {
            _customer.AddRental(new Rental(new Movie("The cell",         Movie.NEW_RELEASE), 3));
            _customer.AddRental(new Rental(new Movie("The Tigger Movie", Movie.NEW_RELEASE), 3));
            //test
            Assert.Equal(_customer.Statement(),
                         "Rental Record for Fred\n\tThe cell\t9.0\n\tThe Tigger Movie\t9.0\nYou owed 18.0\nYou earned 4 frequent renter points \n");
        }
        //push
        [Fact]
        public void TestSingleChildrensStatement()
        {
            _customer.AddRental(new Rental(new Movie("The Tigger Movie", Movie.CHILDREN), 3));

            Assert.Equal(_customer.Statement(),
                         "Rental Record for Fred\n\tThe Tigger Movie\t1.5\nYou owed 1.5\nYou earned 1 frequent renter points \n");
        }

        [Fact]
        public void TestMultipleRegularStatement()
        {
            _customer.AddRental(new Rental(new Movie("Plan 9 from Outer Space", Movie.REGULAR), 1));
            _customer.AddRental(new Rental(new Movie("8 1/2",                   Movie.REGULAR), 2));
            _customer.AddRental(new Rental(new Movie("Eraserhead",              Movie.REGULAR), 3));

            Assert.Equal(_customer.Statement(),
                         "Rental Record for Fred\n\tPlan 9 from Outer Space\t2.0\n\t8 1/2\t2.0\n\tEraserhead\t3.5\nYou owed 7.5\nYou earned 3 frequent renter points \n");
        }
    }
}
