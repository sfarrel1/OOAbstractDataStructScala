package iostreams.drawing

import scalafx.scene.canvas.GraphicsContext
import scalafx.scene.Node

abstract class Drawable(val drawing: Drawing) extends Serializable {
  def draw(gc: GraphicsContext): Unit
  def propertiesPanel(): Node
  def toXML: xml.Node
}

object Drawable {
  def makeDrawable(n: xml.Node, d: Drawing): Drawable = {
    (n \ "@type").text match {
      case "rectangle" => DrawRectangle(n, d)
      case "text" => DrawText(n, d)
      case "transform" => DrawTransform(n, d)
      case "maze" => DrawMaze(n, d)
      case "mandelbrot" => DrawMandelbrot(n, d)
      case "julia" => DrawJulia(n, d)
    }
  }
}