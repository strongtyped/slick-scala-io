package models

case class Conference(name: String, location:String, id: Option[Long] = None) extends Entity[Conference] {
  type Id = Long
  override def withId(id: Long): Conference = this.copy(id = Some(id))
}
