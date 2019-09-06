package scong

import scala.swing._

trait Actor {
  def reset(): Unit
}

object Direction extends Enumeration {
  type Direction = Value
  val Up, Down = Value
}

class UI(w: World) extends MainFrame {
  title = "Scong"
  preferredSize = new Dimension(Constants.arenaWidth, Constants.arenaHeight)
  resizable = false
  peer.setUndecorated(true)
  contents = new Canvas(w)
}

object Scong {
  def main(args: Array[String]) {
    val world = new World
    val ui = new UI(world)
    ui.visible = true
    while (!world.exit) {
      if (world.ball.someoneLost) world.reset()
      world.ball.calculateNextPos(world.players)
      world.players.foreach {
        case p: ComputerPlayer =>
          p.calcNextPos(world.ball)
        case _ =>
      }
      ui.repaint()
      Thread.sleep(Constants.tickDuration)
    }
  }
}