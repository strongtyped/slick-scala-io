package slick

import models.Conference
import Schema._
import DB.driver.simple._

object SlickApp extends App {

  DB { implicit session =>

    createSchema

    val scalaIo     = Conferences += Conference("Scala.IO 2014", "Paris")
    val scalax      = Conferences += Conference("ScalaExchange", "London")
    val scalaDays   = Conferences += Conference("Scala Days 2015", "SF")

    println(
      s"""
         |
         |-------------------------------------
         |Scala.IO        = $scalaIo
         |Scala eXchange  = $scalax
         |Scala Days      = $scalaDays
         |-------------------------------------
       """.stripMargin
    )
  }
}
