package slick

import models.Conference

object Schema {

  import DB.driver.simple._

	class ConferenceTable(tag: Tag) extends Table[Conference](tag, "CONFERENCE") {
	  def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
	  def name = column[String]("name", O.NotNull)
    def location = column[String]("location", O.NotNull)
	  def * = (name, location, id.?) <> (Conference.tupled, Conference.unapply)
	}

	val Conferences = TableQuery[ConferenceTable]

	def createSchema(implicit sess: Session) = Conferences.ddl.create

}