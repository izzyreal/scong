package scong

import java.awt.Color

import scala.swing.event.KeyPressed
import scala.swing.{Component, Graphics2D}

class Canvas(world: World) extends Component {
  listenTo(keys)
  focusable = true
  requestFocus()

  reactions += {
    case KeyPressed(_, v, _, _) =>
      if (v == KeyConfig.p1Up) {
        world.movePlayer(0, Direction.Up)
      } else if (v == KeyConfig.p1Down) {
        world.movePlayer(0, Direction.Down)
      } else if (v == KeyConfig.p2Up) {
        world.movePlayer(1, Direction.Up)
      } else if (v == KeyConfig.p2Down) {
        world.movePlayer(1, Direction.Down)
      }
  }

  private def drawPlayers(g: Graphics2D, s: World): Unit = {
    drawPlayer(g, s, 0)
    drawPlayer(g, s, 1)
  }

  private def drawPlayer(g: Graphics2D, s: World, p: Int): Unit = {
    val x = if (p == 0) 0 else Constants.arenaWidth - Constants.playerWidth
    val y = s.players(p).pos
    g.setColor(Color.WHITE)
    g.drawRect(x, y.intValue, Constants.playerWidth, Constants.playerHeight)
    g.fillRect(x, y.intValue, Constants.playerWidth, Constants.playerHeight)
  }

  private def drawBall(g: Graphics2D, s: World): Unit = {
    val x = s.ball.x
    val y = s.ball.y
    g.setColor(Color.PINK)
    val bs = Constants.ballSize
    g.drawOval(x.intValue - (bs / 2), y.intValue - (bs / 2), bs, bs)
    g.fillOval(x.intValue - (bs / 2), y.intValue - (bs / 2), bs, bs)
  }

  override def paintComponent(g: Graphics2D): Unit = {
    g.setColor(Color.BLACK)
    g.fillRect(0, 0, Constants.canvasWidth, Constants.canvasHeight)
    drawPlayers(g, world)
    drawBall(g, world)
  }
}
