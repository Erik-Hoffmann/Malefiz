package malefiz

import com.google.inject.AbstractModule
import controller.ControllerInterface
import controller.Controller
import model.{GameBoard, GameBoardInterface}
import model.fileIO.{ fileIOInterface, serializeJSON, serializeXML }

class GameModule extends AbstractModule {
  override def configure(): Unit = {
    //bind(classOf[fileIOInterface]).to(classOf[serializeJSON.serialize])
    bind(classOf[fileIOInterface]).to(classOf[serializeXML.serialize])
  }
}
