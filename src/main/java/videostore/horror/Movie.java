package videostore.horror;


import lombok.NonNull;

public record Movie(@NonNull String title,
                    @NonNull PriceCode priceCode) {
}