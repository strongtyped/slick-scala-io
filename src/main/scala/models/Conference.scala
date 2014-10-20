package models

case class Conference(name: String, location:String, id: Option[Long] = None) extends Entity {
  type Id = Long
}
