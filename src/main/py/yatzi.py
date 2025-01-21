# spec: https://sammancoaching.org/kata_descriptions/yatzy.html

class Yatzy:
    def __init__(self, d1=0, d2=0, d3=0, d4=0, d5=0):
        self.dice = [d1, d2, d3, d4, d5]

    @staticmethod
    def chance(d1, d2, d3, d4, d5):
        return sum([d1, d2, d3, d4, d5])

    @staticmethod
    def yatzy(dice):
        counts = [0] * 7
        for die in dice:
            counts[die] += 1
        # TODO puteti cu dictioar in loc de array. adic in loc de [0X,5,0,0,0,0,0] sa tineti {1:5}?
        print(counts)

        return 50 if counts.count(5) == 1 else 0

    # idee FOARTE BUNA !
    def count_appearances(self, dice_face):
        return self.dice.count(dice_face) * dice_face

    def ones(self):
        return self.count_appearances(1)

    def twos(self):
        return self.count_appearances(2)

    def threes(self):
        return self.count_appearances(3)

    def fours(self):
        return self.count_appearances(4)

    def fives(self):
        return self.count_appearances(5)

    def sixes(self):
        return self.count_appearances(6)

    def score_pair(self):
        counts = {face: self.dice.count(face) for face in range(1, 7)}
        pair_faces = [face for face, count in counts.items() if count >= 2]
        return 2 * max(pair_faces) or 0

    def two_pair(self):
        counts = {face: self.dice.count(face) for face in range(1, 7)}
        pair_faces = [face for face, count in counts.items() if count >= 2]
        return 2 * sum(pair_faces) if len(pair_faces) == 2 else 0

    def four_of_a_kind(self):
        counts = {face: self.dice.count(face) for face in range(1, 7)}
        face4 = [face for face, count in counts.items() if count >= 4]
        return 4 * face4[0] if face4 else 0

    def three_of_a_kind(self):
        counts = {face: self.dice.count(face) for face in range(1, 7)}
        face3 = [face for face, count in counts.items() if count >= 3]
        return 3 * face3[0] if face3 else 0

    # https://chatgpt.com/share/678fb401-c858-8006-8fc0-2483318ee125

    @staticmethod
    def smallStraight(d1, d2, d3, d4, d5):
        tallies = [0] * 6
        tallies[d1 - 1] += 1
        tallies[d2 - 1] += 1
        tallies[d3 - 1] += 1
        tallies[d4 - 1] += 1
        tallies[d5 - 1] += 1
        if (tallies[0] == 1 and
                tallies[1] == 1 and
                tallies[2] == 1 and
                tallies[3] == 1 and
                tallies[4] == 1):
            return 15
        return 0

    @staticmethod
    def largeStraight(d1, d2, d3, d4, d5):
        tallies = [0] * 6
        tallies[d1 - 1] += 1
        tallies[d2 - 1] += 1
        tallies[d3 - 1] += 1
        tallies[d4 - 1] += 1
        tallies[d5 - 1] += 1
        if (tallies[1] == 1 and
                tallies[2] == 1 and
                tallies[3] == 1 and
                tallies[4] == 1
                and tallies[5] == 1):
            return 20
        return 0

    @staticmethod
    def fullHouse(d1, d2, d3, d4, d5):
        tallies = []
        _2 = False
        i = 0
        _2_at = 0
        _3 = False
        _3_at = 0

        tallies = [0] * 6
        tallies[d1 - 1] += 1
        tallies[d2 - 1] += 1
        tallies[d3 - 1] += 1
        tallies[d4 - 1] += 1
        tallies[d5 - 1] += 1

        for i in range(6):
            if (tallies[i] == 2):
                _2 = True
                _2_at = i + 1

        for i in range(6):
            if (tallies[i] == 3):
                _3 = True
                _3_at = i + 1

        if (_2 and _3):
            return _2_at * 2 + _3_at * 3
        else:
            return 0


def test_chance_scores_sum_of_all_dice():
    expected = 15
    actual = Yatzy.chance(2, 3, 4, 5, 1)
    assert expected == actual
    assert 16 == Yatzy.chance(3, 3, 4, 5, 1)


def test_yatzy_scores_50():
    expected = 50
    actual = Yatzy.yatzy([4, 4, 4, 4, 4])
    assert expected == actual
    assert 50 == Yatzy.yatzy([6, 6, 6, 6, 6])
    assert 0 == Yatzy.yatzy([6, 6, 6, 6, 3])


def test_1s():
    assert Yatzy(1, 2, 3, 4, 5).ones() == 1
    assert 2 == Yatzy(1, 2, 1, 4, 5).ones()
    assert 0 == Yatzy(6, 2, 2, 4, 5).ones()
    assert 4 == Yatzy(1, 2, 1, 1, 1).ones()


def test_2s():
    assert 4 == Yatzy(1, 2, 3, 2, 6).twos()
    assert 10 == Yatzy(2, 2, 2, 2, 2).twos()


def test_threes():
    assert 6 == Yatzy(1, 2, 3, 2, 3).threes()
    assert 12 == Yatzy(2, 3, 3, 3, 3).threes()


def test_fours_test():
    assert 12 == Yatzy(4, 4, 4, 5, 5).fours()
    assert 8 == Yatzy(4, 4, 5, 5, 5).fours()
    assert 4 == Yatzy(4, 5, 5, 5, 5).fours()


def test_fives():
    assert 10 == Yatzy(4, 4, 4, 5, 5).fives()
    assert 15 == Yatzy(4, 4, 5, 5, 5).fives()
    assert 20 == Yatzy(4, 5, 5, 5, 5).fives()


def test_sixes_test():
    assert 0 == Yatzy(4, 4, 4, 5, 5).sixes()
    assert 6 == Yatzy(4, 4, 6, 5, 5).sixes()
    assert 18 == Yatzy(6, 5, 6, 6, 5).sixes()  # de ce e altfel? ctor


def test_one_pair():
    assert 6 == Yatzy(3, 4, 3, 5, 6).score_pair()  # de ce e altfel? param de metoda
    assert 10 == Yatzy(5, 3, 3, 3, 5).score_pair()
    assert 12 == Yatzy(5, 3, 6, 6, 5).score_pair()


def test_two_Pair():
    assert 16 == Yatzy(3, 3, 5, 4, 5).two_pair()
    assert 18 == Yatzy(3, 3, 6, 6, 6).two_pair()
    assert 0 == Yatzy(3, 3, 6, 5, 4).two_pair()


def test_three_of_a_kind():
    assert 9 == Yatzy(3, 3, 3, 4, 5).three_of_a_kind()
    assert 15 == Yatzy(5, 3, 5, 4, 5).three_of_a_kind()
    assert 9 == Yatzy(3, 3, 3, 3, 5).three_of_a_kind()


def test_four_of_a_knd():
    assert 12 == Yatzy(3, 3, 3, 3, 5).four_of_a_kind()
    assert 20 == Yatzy(5, 5, 5, 4, 5).four_of_a_kind()
    assert 12 == Yatzy(3, 3, 3, 3, 3).four_of_a_kind()
    assert 0 == Yatzy(3, 3, 3, 2, 1).four_of_a_kind()


def test_smallStraight():
    assert 15 == Yatzy.smallStraight(1, 2, 3, 4, 5)
    assert 15 == Yatzy.smallStraight(2, 3, 4, 5, 1)
    assert 0 == Yatzy().smallStraight(1, 2, 2, 4, 5)


def test_largeStraight():
    assert 20 == Yatzy.largeStraight(6, 2, 3, 4, 5)
    assert 20 == Yatzy().largeStraight(2, 3, 4, 5, 6)
    assert 0 == Yatzy.largeStraight(1, 2, 2, 4, 5)


def test_fullHouse():
    assert 18 == Yatzy.fullHouse(6, 2, 2, 2, 6)
    assert 0 == Yatzy.fullHouse(2, 3, 4, 5, 6)
