package slick

import scala.slick.driver.JdbcDriver

trait Profile {
  val driver: JdbcDriver
}