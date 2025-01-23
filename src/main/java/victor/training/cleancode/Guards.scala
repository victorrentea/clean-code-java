package victor.training.cleancode


object Guards {
  val DEAD_PAY_AMOUNT: Int = 1
}


class Guards {

  def getPayAmount(marine: Marine, bonusPackage: BonusPackage): Int = {
    if (marine == null || (bonusPackage.value < 10 || bonusPackage.value > 100)) {
      throw new IllegalArgumentException("Not applicable!")
    }
    if (marine.dead) {
      return Guards.DEAD_PAY_AMOUNT // early return
    }
    if (marine.retired) {
      return retiredAmount()
    }
    if (marine.yearsOfService == null) { // early return
      throw new IllegalArgumentException("Any marine should have the years of service set")
    }
    var result = marine.yearsOfService * 100 + bonusPackage.value
    if (!marine.awards.isEmpty) {
      result += 1000
    }
    if (marine.awards.size >= 3) {
      result += 2000
    }
    return result
  }

  private def retiredAmount(): Int = 2
}

case class Marine(dead: Boolean, retired: Boolean, yearsOfService: Integer, awards: List[Award]) {
}

case class BonusPackage(value: Int) {
}

class Award {
}