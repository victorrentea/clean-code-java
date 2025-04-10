import {Movie} from "./movie";

export class Rental {
    public constructor(
        readonly movie: Movie,
        readonly daysRented: number,
    ) {}
}