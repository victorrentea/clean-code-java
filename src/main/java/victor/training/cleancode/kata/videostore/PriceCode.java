package victor.training.cleancode.kata.videostore;
public enum PriceCode {
    CHILDREN(1.5,3, 1.5),
    REGULAR(2,2, 1.5),
    NEW_RELEASE(0, 0, 3);

    final double baseFee;
    final int baseFeeDays;
    final double additionalFeeRate;

    PriceCode(double baseFee, int baseFeeDays, double additionalFeeRate) {
        this.baseFee = baseFee;
        this.baseFeeDays = baseFeeDays;
        this.additionalFeeRate = additionalFeeRate;
    }


}