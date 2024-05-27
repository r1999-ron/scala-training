package controllers

import javax.inject._
import play.api._
import play.api.mvc._

class StoreController @Inject()(val controllerComponents: ControllerComponents) extends BaseController{
  def store() = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.store())
  }

  def report() = Action{ implicit  request: Request[AnyContent] =>
    Ok(views.html.report())
  }
}