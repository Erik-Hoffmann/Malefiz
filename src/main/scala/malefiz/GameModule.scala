package malefiz

import model.BaseImpl.Gameboard
import model.GameboardInterface
import controller.ControllerInterface
import controller.BaseImpl.Controller

import com.google.inject.name.Names
import com.google.inject.AbstractModule

class GameModule extends AbstractModule {
  bind(classOf[ControllerInterface]).to(classOf[Controller])
  bind(classOf[GameboardInterface]).to(classOf[Gameboard])
}
