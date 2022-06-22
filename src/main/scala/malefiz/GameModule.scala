package malefiz

import com.google.inject.AbstractModule

import malefiz.controller.ControllerInterface
import malefiz.controller.Controller
import malefiz.model.GameBoardInterface
import malefiz.model.GameBoard

class GameModule extends AbstractModule {
  bind(classOf[ControllerInterface]).to(classOf[Controller])
  bind(classOf[GameBoardInterface]).to(classOf[GameBoard])
}
