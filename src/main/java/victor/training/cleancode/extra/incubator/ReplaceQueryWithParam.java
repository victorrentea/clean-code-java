package victor.training.cleancode.extra.incubator;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ReplaceQueryWithParam {
    private final UserRepo userRepo;
    private final OfferService offerService;

    public void cancelOffer(int offerId, int userId) {
        User user = userRepo.findById(userId);
        if (user.getProfile().canCancelOffers()) {
            offerService.cancelOffer(userId, offerId);
        }
    }
}

@RequiredArgsConstructor
class OfferService {
    private final UserRepo userRepo;
    private final OfferRepo offerRepo;

    public void cancelOffer(int userId, int offerId) {
        Offer offer = offerRepo.findById(offerId);
        if (offer.hasSpecialDeal()) {
            User user = userRepo.findById(userId); // Performance hit
            if (user.getProfile().isAdmin()) {
                offerRepo.setCancelled(offerId);
            }
        }
    }
}


// ========== supporting dummy code
class User {
    public Profile getProfile() {
        return new Profile();
    }
}

class OfferRepo {

    public Offer findById(int offerId) {
        return null;
    }

    public void setCancelled(int offerId) {
    }
}

class Offer {
    public boolean hasSpecialDeal() {
        return true;
    }
}

class Profile {

    public boolean canCancelOffers() {
        return true;
    }

    public boolean isAdmin() {
        return false;
    }
}

