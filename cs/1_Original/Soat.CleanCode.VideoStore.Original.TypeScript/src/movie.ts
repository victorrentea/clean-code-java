export class Movie {
    static readonly REGULAR     = 0;
    static readonly NEW_RELEASE = 1;
    static readonly CHILDREN    = 2;

    constructor (
        readonly title: string,
        public priceCode: number,
    ) {}
}