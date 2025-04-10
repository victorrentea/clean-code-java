import {Movie} from './movie';
import {Rental} from './rental';

export class Customer {
    private _rentals: Rental[] = [];

    constructor(readonly name: string) {
    }

    addRental(rental: Rental) {
        this._rentals.push(rental);
    }

    statement(): string {
        let frequentRenterPoints = 0;
        let totalAmount = 0;
        let result = 'Rental Record for ' + this.name + '\n';
        for (let each of this._rentals) {
            let thisAmount = 0;

            // dtermines the amount for each line
            switch (each.movie.priceCode) {
                case Movie.REGULAR:
                    thisAmount += 2;
                    if (each.daysRented > 2) {
                        thisAmount = thisAmount + (each.daysRented - 2) * 1.5;
                    }
                    break;
                case Movie.NEW_RELEASE:
                    thisAmount = thisAmount + each.daysRented * 3;
                    break;
                case Movie.CHILDREN:
                    thisAmount += 1.5;
                    if (each.daysRented > 3) {
                        thisAmount = thisAmount + (each.daysRented - 3) * 1.5;
                    }
                    break;
            }

            frequentRenterPoints++;
            if (each.movie.priceCode == Movie.NEW_RELEASE && each.daysRented > 1) {
                frequentRenterPoints++;
            }

            result += '\t' + each.movie.title + '\t' + thisAmount.toFixed(1) + '\n';
            totalAmount += thisAmount;
        }

        result += 'You owed ' + totalAmount.toFixed(1) + '\n';
        result += 'You earned ' + frequentRenterPoints + ' frequent renter points \n';

        return result;
    }
}