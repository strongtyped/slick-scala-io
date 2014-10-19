package slick

object SlickExtensions {

  import DB.driver.simple._

  abstract class IdTable[M, I:BaseColumnType](tag: Tag, schemaName: Option[String], tableName: String)
    extends Table[M](tag, schemaName, tableName) {

    def id:Column[I]
    def this(tag: Tag, tableName: String) = this(tag, None, tableName)
  }


  abstract class BaseIdTableQuery[E, T <: Table[E]](cons: Tag => T) extends TableQuery(cons) {
    def save(model:E)(implicit sess: Session) : E
    def delete(model:E)(implicit sess:Session) : Boolean
  }


  /** A TableQuery aware of IdTable's id Column*/
  abstract class IdTableQuery[M, I:BaseColumnType, T <: IdTable[M, I]](cons: Tag => T) extends BaseIdTableQuery[M, T](cons) {

    def save(model:M)(implicit sess: Session) : M = {

      extractId(model) match {

        case Some(id) =>
          this.update(model) // returns the number of affected rows
          model // return the model

        case None => add(model) // insert and return a clone with an Id

      }
    }

    def add(model:M)(implicit sess: Session) : M = {
      val id = (this returning this.map(_.id)) += model
      withId(model, id)
    }

    def +=(model:M)(implicit sess: Session) : M = add(model)


    def delete(model:M)(implicit sess:Session) : Boolean = {
      extractId(model).map(filterById(_).delete).getOrElse(0) > 1
    }

    def filterById(id: I)(implicit sess: Session) = filter(_.id === id)
    def findById(id: I)(implicit sess: Session): M = findOptionById(id).get
    def findOptionById(id: I)(implicit sess: Session): Option[M] = filterById(id).firstOption

    def fetchAll(implicit sess: Session) : List[M] = this.list

    def withId(model: M, id: I): M
    def extractId(model:M) : Option[I]

  }

  trait ActiveRecord[M] {
    import scala.slick.jdbc.JdbcBackend
    type TableQuery = BaseIdTableQuery[M, _]

    def tableQuery: TableQuery
    def model: M

    def save(implicit session: JdbcBackend#Session): M = tableQuery.save(model)
    def delete(implicit session: JdbcBackend#Session): Boolean = tableQuery.delete(model)
  }
}