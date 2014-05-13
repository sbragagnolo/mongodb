package mosaic.com.mongo.jackson.util

import net.vz.mongodb.jackson.JacksonDBCollection
import play.modules.mongodb.jackson.MongoDB
import scala.collection.mutable.Buffer
import scala.collection.JavaConversions._
import net.vz.mongodb.jackson.DBQuery
import com.mongodb.DBObject

abstract class MongoDBGate[ModelType, IndexType](
  implicit val model: reflect.ClassTag[ModelType],
  implicit val index: reflect.ClassTag[IndexType]) {

  def collectionName: String = model.runtimeClass.getName()

  var models: JacksonDBCollection[ModelType, IndexType] =
    MongoDB.getCollection(this.collectionName, model.runtimeClass.asInstanceOf[Class[ModelType]],
      index.runtimeClass.asInstanceOf[Class[IndexType]])

  def findById(id: IndexType): ModelType = models.findOneById(id)
  def findAll(): Buffer[ModelType] = models.find().toArray()
  def delete(excercise: ModelType) = models.remove(excercise)
  def deleteById(id: IndexType) = models.removeById(id)
  def save(excercise: ModelType) = models.save(excercise)
  def find(query: DBObject) = models.find(query)
}
