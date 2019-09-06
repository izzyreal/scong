package scong

class Ball extends Actor {

  var someoneLost = false
  var x: Float = Constants.playerWidth
  var y: Float = Constants.arenaHeight / 2
  var xVelo: Float = 1.0f
  var yVelo: Float = 0.0f

  def calculateNextPos(p: Array[Player]): Unit = {

    if (x == Constants.playerWidth && xVelo <= 0) {
      if (p(0).defends(this)) {
        xVelo = 1.0f
        yVelo = p(0).getOffsetFromMiddle(this) / Constants.deflectionThingie
      }
    }

    if (x == Constants.arenaWidth - Constants.playerWidth && xVelo >= 0) {
      if (p(1).defends(this)) {
        xVelo = -1.0f
        yVelo = p(1).getOffsetFromMiddle(this) / Constants.deflectionThingie
      }
    }

    if (y <= 0 && yVelo <= 0) yVelo = -yVelo
    if (y >= Constants.arenaHeight && yVelo >= 0) yVelo = -yVelo

    x += xVelo
    y += yVelo

    if (x < 0) {
      println("Player 1 lost!")
      someoneLost = true
    }
    if (x > Constants.arenaWidth) {
      println("Player 2 lost!")
      someoneLost = true
    }
  }

  override def reset(): Unit = {
    someoneLost = false
    x = Constants.playerWidth
    y = Constants.arenaHeight / 2
    xVelo = 1.0f
    yVelo = 0.0f
  }
}
