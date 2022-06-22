import play.api.libs.json._
import scala.xml.*

val mylist: Array[String] = Array("a", "b", "c")

def atoXML(el: String) =
  <el>{el}</el>

def toXML(param: Array[String]) = {
  <entry>
    {for {x <- param.indices}
    yield atoXML(param(x))}
  </entry>
}

def toJSON(param: Array[String]) = {
  Json.obj(
    "data" -> {
      param
    }
  )
}

val resultXML = toXML(mylist)
val resultJSON = toJSON(mylist)
print(resultJSON \\ "data")

def parseBackXML(xml: NodeSeq)=
  (xml \\ "entry" \\ "el").text.toArray

def parseBackJSON(json: JsValue)=
  (json \ "data").as[Array[String]]

val parsedXML = parseBackXML(resultXML)
val parsedJSON = parseBackJSON(resultJSON)