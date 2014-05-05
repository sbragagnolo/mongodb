package models

import play.modules.mongodb.jackson.MongoDB
import net.vz.mongodb.jackson.JacksonDBCollection
import net.vz.mongodb.jackson.Id
import net.vz.mongodb.jackson.ObjectId
import scala.collection.mutable.Buffer

import scala.collection.JavaConversions._


case class Excercise( var name: String, var description: String) {
  @Id @ObjectId var id: String = null
  def this() = this(null, null)
}

object ExcerciseObject {
  var excercises: JacksonDBCollection[Excercise, String] = MongoDB.getCollection("excercises", classOf[Excercise], classOf[String])
  def findById(id: String): Excercise = excercises.findOneById(id)
  def findAll(): Buffer[Excercise] = excercises.find().toArray()
  def delete(excercise: Excercise) = excercises.remove(excercise)
  def delete(id: String) = excercises.removeById(id)
  def save(excercise: Excercise) = excercises.save(excercise)
}
