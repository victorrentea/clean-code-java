import {Customer} from './customer';
import {Movie} from './movie';
import {Rental} from './rental';

describe('VideoStore', () => {
    let customer: Customer;

    beforeEach(() => {
        customer = new Customer('Fred');
    });

    it('test single NewRelease statement', () => {
        customer.addRental(new Rental(new Movie('The Cell', Movie.NEW_RELEASE), 3));

        expect(customer.statement())
            .toBe('Rental Record for Fred\n\tThe Cell\t9.0\nYou owed 9.0\nYou earned 2 frequent renter points \n');
    });

    it('test dual NewRelease statement', () => {
        customer.addRental(new Rental(new Movie('The Cell', Movie.NEW_RELEASE), 3));
        customer.addRental(new Rental(new Movie('The Tigger Movie', Movie.NEW_RELEASE), 3));

        expect(customer.statement())
            .toBe('Rental Record for Fred\n\tThe Cell\t9.0\n\tThe Tigger Movie\t9.0\nYou owed 18.0\nYou earned 4 frequent renter points \n');
    });

    it('test single children statement', () => {
        customer.addRental(new Rental(new Movie('The Tigger Movie', Movie.CHILDREN), 3));

        expect(customer.statement())
            .toBe('Rental Record for Fred\n\tThe Tigger Movie\t1.5\nYou owed 1.5\nYou earned 1 frequent renter points \n',);
    });

    it('test multiple regular statement', () => {
        customer.addRental(new Rental(new Movie('Plan 9 from Outer Space', Movie.REGULAR), 1));
        customer.addRental(new Rental(new Movie('8 1/2', Movie.REGULAR), 2));
        customer.addRental(new Rental(new Movie('Eraserhead', Movie.REGULAR), 3));

        expect(customer.statement())
            .toBe('Rental Record for Fred\n\tPlan 9 from Outer Space\t2.0\n\t8 1/2\t2.0\n\tEraserhead\t3.5\nYou owed 7.5\nYou earned 3 frequent renter points \n');
    });
});
