package scong

class World extends Actor {

  private val p1 = new Player(0)
  private val p2 = new Player(1)

  def players: Array[Player] = Array[Player](p1, p2)
  val ball = new Ball

  var exit = false

  def movePlayer(p: Int, d: Direction.Value): Unit =
    players(p).move(d)

  override def reset(): Unit = {
    exit = false
    players.foreach(_.reset())
    ball.reset()
  }
}