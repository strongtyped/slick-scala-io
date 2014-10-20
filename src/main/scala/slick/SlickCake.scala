package slick

import scala.slick.driver.JdbcDriver
class SlickCake(val driver:  JdbcDriver) extends Profile with SlickExtensions with Schema

object SlickCake{
  val instance = new SlickCake(DB.driver)
}