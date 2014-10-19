package slick

object SlickExtensions {

  import DB.driver.simple._

  /** A Table with a well known id Column*/
  abstract class IdTable[M](tag: Tag, schemaName: Option[String], tableName: String)
    extends Table[M](tag, schemaName, tableName) {

    def id:Column[Long]
    def this(tag: Tag, tableName: String) = this(tag, None, tableName)

  }

  /** A TableQuery aware of IdTable's id Column*/
  abstract class IdTableQuery[M, T <: IdTable[M]](cons: Tag => T) extends TableQuery(cons) {

    def add(model:M)(implicit sess: Session) : M = {
      val id = (this returning this.map(_.id)) += model
      withId(model, id)
    }

    def +=(model:M)(implicit sess: Session) : M = add(model)

    def withId(model: M, id: Long): M
  }

}