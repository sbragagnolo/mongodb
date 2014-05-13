package models

import play.modules.mongodb.jackson.MongoDB
import net.vz.mongodb.jackson.JacksonDBCollection
import net.vz.mongodb.jackson.Id
import scala.collection.mutable.Buffer
import scala.collection.JavaConversions._
import mosaic.com.mongo.jackson.util.MongoDBGate
import net.vz.mongodb.jackson.ObjectId
import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Bean
import scala.beans.BeanProperty

object Excercise {
  def apply(name: String, description: String) = {
    var e = new Excercise()
    
    e.name = name
    e.description = description
    e
  }
  def unapply(ex: Excercise) = {
    if (ex == null) None
    else Some(ex.name, ex.description)
  }

}
class Excercise() {
  @Id @ObjectId @BeanProperty var id: String = null
  @BeanProperty var name: String = null
  @BeanProperty var description: String = null
}

object ExcerciseObject extends MongoDBGate[Excercise, String]
