package victor.training.cleancode.videostore

object Movie {
  val CHILDRENS = 2
  val REGULAR = 0
  val NEW_RELEASE = 1
}

class Movie(val _title: String, var _priceCode: Int) {

  def getPriceCode(): Int = {
    _priceCode
  }

  def setPriceCode(arg: Int): Unit = {
    _priceCode = arg
  }

  def getTitle(): String = {
    _title
  }
}