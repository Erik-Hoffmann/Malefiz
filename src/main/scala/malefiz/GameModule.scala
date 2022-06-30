package malefiz

import com.google.inject.AbstractModule
import controller.ControllerInterface
import controller.Controller
import model.{GameBoard, GameBoardInterface}
import model.fileIO.{ fileIOInterface, serializeJSON, serializeXML }

class GameModule extends AbstractModule {
  //bind(classOf[ControllerInterface]).to(classOf[Controller])
  //bind(classOf[GameBoardInterface]).to(classOf[GameBoard])
  //bind(classOf[fileIOInterface]).to(classOf([serializeJSON.serialize]))
}
