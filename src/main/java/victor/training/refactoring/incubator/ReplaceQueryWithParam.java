package victor.training.refactoring.incubator;

public class ReplaceQueryWithParam {
    private final UserRepo userRepo;
    private final OfferService offerService;

    public ReplaceQueryWithParam(UserRepo userRepo, OfferService offerService) {
        this.userRepo = userRepo;
        this.offerService = offerService;
    }

    public void cancelOffer(int offerId, int userId) {
        User user = userRepo.findById(userId);
        if (user.getProfile().canCancelOffers()) {
            offerService.cancelOffer(userId, offerId);
        }
    }
}

class OfferService {
    private final UserRepo userRepo;
    private final OfferRepo  offerRepo;

    OfferService(UserRepo userRepo, OfferRepo offerRepo) {
        this.userRepo = userRepo;
        this.offerRepo = offerRepo;
    }

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

class UserRepo {

    public User findById(int userId) {
        return null;
    }
}