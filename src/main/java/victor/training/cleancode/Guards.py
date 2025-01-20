DEAD_PAY_AMOUNT = 1

class Guards:
    def get_pay_amount(self, marine, bonus_package):
        # Check the conditions before proceeding with pay calculation
        if marine is not None and not (bonus_package.value < 10 or bonus_package.value > 100):
            if not marine.dead:
                if not marine.retired:
                    if marine.years_of_service is not None:
                        result = marine.years_of_service * 100 + bonus_package.value
                        if marine.awards:
                            result += 1000
                        if len(marine.awards) >= 3:
                            result += 2000
                    else:
                        raise ValueError("Any marine should have the years of service set")
                else:
                    result = self.retired_amount()
            else:
                result = DEAD_PAY_AMOUNT
        else:
            raise ValueError("Not applicable!")
        return result

    def retired_amount(self):
        return 2

class Marine:
    def __init__(self, dead, retired, years_of_service, awards):
        self.dead = dead
        self.retired = retired
        self.years_of_service = years_of_service
        self.awards = awards

class BonusPackage:
    def __init__(self, value):
        self.value = value

class Award:
    pass