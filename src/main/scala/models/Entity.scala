package models

trait Entity[E <: Entity[E]] {
  type Id
  def id : Option[E#Id]
  def withId(id:E#Id): E
}
