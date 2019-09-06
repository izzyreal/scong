package scong

class Player(val index: Int) extends Actor {

  var pos: Float = (Constants.arenaHeight / 2) - (Constants.playerHeight / 2)

  def move(d: Direction.Value): Unit = {
    if (d == Direction.Up) {
      if (pos <= 0) return
      pos -= Constants.paddleVelocity
    } else {
      if (pos >= Constants.arenaHeight - Constants.playerHeight) return
      pos += Constants.paddleVelocity
    }
  }

  def defends(b: Ball): Boolean = {
    if ((b.xVelo < 0 && index == 1) || (b.xVelo > 0 && index == 0))
      return false

    if (b.y < pos || b.y > pos + Constants.playerHeight)
      return false

    true
  }

  def getOffsetFromMiddle(b: Ball): Float = {
    val middle = pos + (Constants.playerHeight / 2)
    b.y - middle
  }

  override def reset(): Unit = {
    pos = (Constants.arenaHeight / 2) - (Constants.playerHeight / 2)
  }
}
