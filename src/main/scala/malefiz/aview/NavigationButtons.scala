package malefiz.aview

import scalafx.event.EventIncludes.handle
import scalafx.scene.layout.Priority
import scalafx.scene.control.Button
import scalafx.scene.layout.BorderPane

class NavigationButtons extends BorderPane {
  hgrow = Priority.Always
  top = new Button {
    text = "Up"
    maxWidth = Double.MaxValue
    onAction = handle {
      //TODO link up with up in controller
    }
  }
  bottom = new Button {
    text = "Down"
    maxWidth = Double.MaxValue
    onAction = handle {
      //TODO link up with down in controller
    }
  }
  right = new Button {
    text = "Right"
    maxWidth = Double.MaxValue
    onAction = handle {
      //TODO link up with right in controller
    }
  }
  left = new Button {
    text = "Left"
    maxWidth = Double.MaxValue
    onAction = handle {
      //TODO link up with left in controller
    }
  }
}
