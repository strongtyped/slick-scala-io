package slick

import models.Conference

object Schema {

  import SlickExtensions._
  import DB.driver.simple._

  class ConferenceTable(tag: Tag) extends IdTable[Conference](tag, "CONFERENCE") {
    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
    def name = column[String]("name", O.NotNull)
    def location = column[String]("location", O.NotNull)
    def * = (name, location, id.?) <> (Conference.tupled, Conference.unapply)
  }

  val Conferences = new IdTableQuery[Conference, ConferenceTable](tag => new ConferenceTable(tag)) {
    override def withId(model: Conference, id: Long): Conference = model.copy(id = Some(id))
    override def extractId(model: Conference): Option[Long] = model.id
  }

  implicit class ConferenceRecord(val model:Conference) extends ActiveRecord[Conference] {
    val tableQuery = Conferences
  }


	def createSchema(implicit sess: Session) = Conferences.ddl.create

}