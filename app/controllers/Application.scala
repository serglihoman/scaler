package controllers

import play.api.mvc._

object Application extends Controller {

  def index = Action {
    Ok(views.html.index())
  }

  def about = Action {
    Ok(views.html.about())
  }

  def post = Action {
    Ok(views.html.post())
  }

  def contact = Action {
    Ok(views.html.contact())
  }

}