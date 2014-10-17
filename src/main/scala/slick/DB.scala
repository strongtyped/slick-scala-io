package slick

object DB {

  import scala.slick.driver.H2Driver
  import H2Driver.simple._
  val driver = H2Driver

  private val db = Database.forURL("jdbc:h2:mem:active-slick", driver = "org.h2.Driver")

  val keepAliveSession = db.createSession()
  keepAliveSession.force() // keep the database in memory with an extra connection
  

  def apply[T](block: Session => T): T = {
    db.withTransaction { implicit session =>
      block(session)
    }
  }
}
