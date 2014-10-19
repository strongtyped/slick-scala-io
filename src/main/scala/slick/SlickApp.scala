package slick

import models.Conference
import Schema._
import DB.driver.simple._

object SlickApp extends App {

  DB { implicit session =>

    createSchema

    val scalaIo     = Conferences += Conference("Scala.IO 2014", "Paris")

    val scalaxNoId = Conference("ScalaExchange", "London")
    val scalaxId = (Conferences returning Conferences.map(_.id)) += scalaxNoId
    val scalax = scalaxNoId.copy(id = Some(scalaxId))

    val scalaDays = ((Conferences returning Conferences.map(_.id))
      into ((conf, id) => conf.copy(id = Some(id) ) )
      ) += Conference("Scala Days 2015", "SF")

    printBlock {
      s"""|Scala.IO        = $scalaIo
          |Scala eXchange  = $scalax
          |Scala Days      = $scalaDays """.stripMargin
    }
  }

  def printBlock(block: => String): Unit = {
    println()
    println("-------------------------------------")
    println(block)
    println("-------------------------------------")
  }
}
