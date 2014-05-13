package controllers

import play.api._
import play.api.mvc._
import models.Excercise
import play.api.data._
import play.api.data.Forms._
import scala.collection.immutable.List
import models.ExcerciseObject

object Application extends Controller {

  val formExcercise: Form[Excercise] = Form[Excercise](
    mapping("name" -> text, "description" -> text)(Excercise.apply)(Excercise.unapply))

  def listExcercises = Action {
    Ok(views.html.index.render(List[Excercise]() ++ ExcerciseObject.findAll, formExcercise));
  }
  def deleteExcercise(id: String) = Action {
    ExcerciseObject.deleteById(id)
    Redirect(routes.Application.listExcercises)
  }

  def newExcercise() = Action { implicit request =>

    val data = formExcercise.bindFromRequest().fold(
      formWithErrors => {
        Redirect(routes.Application.listExcercises)
      },
      success => {
        ExcerciseObject.save(success)
        Redirect(routes.Application.listExcercises)
      })

    data
  }
}