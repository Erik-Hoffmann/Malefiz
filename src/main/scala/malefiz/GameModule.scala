package malefiz

import com.google.inject.AbstractModule
import controller.ControllerInterface
import controller.Controller
import model.{GameBoard, GameBoardInterface}

class GameModule extends AbstractModule {
  bind(classOf[ControllerInterface]).to(classOf[Controller])
  bind(classOf[GameBoardInterface]).to(classOf[GameBoard])
}
