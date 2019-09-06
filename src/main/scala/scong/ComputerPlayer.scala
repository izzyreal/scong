package scong

class ComputerPlayer extends Player(1) {

  def calcNextPos(b: Ball): Unit = {
    pos = b.y - (Constants.playerHeight / 2)
  }

}
