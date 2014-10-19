package slick

import models.Conference
import Schema._

object SlickApp extends App {

  DB { implicit session =>

    createSchema

    val scalaIo     = Conference("Scala.IO 2014", "Paris").save
    val scalax      = Conference("ScalaExchange", "London").save
    val scalaDays   = Conference("Scala Days 2015", "SF").save

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
