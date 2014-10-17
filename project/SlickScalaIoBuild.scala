import sbt._
import sbt.Keys._


object SlickScalaIoBuild extends Build {

  //-- Project Settings -------------------------------------------------------------------
  val projectSettings = Seq(
      organization    := "io.strongtyped"
    , name        := "slick-scala-io"
    , scalacOptions   := Seq("-unchecked", "-deprecation", "-feature", "-Xlint")
  ) 
  //---------------------------------------------------------------------------------------

    
  //-- Dependencies -----------------------------------------------------------------------
  val slick         =   "com.typesafe.slick"          %%    "slick"            %   "2.1.0"
  val h2database    =   "com.h2database"              %     "h2"               %   "1.4.181"

  val monocleVersion  = "0.5.1"
  val monocleCore     =   "com.github.julien-truffaut"  %%    "monocle-core"      % monocleVersion
  val monocleMacro    =   "com.github.julien-truffaut"  %%    "monocle-macro"     % monocleVersion
  //---------------------------------------------------------------------------------------


  //-- Project Def ------------------------------------------------------------------------
  lazy val root = Project(
    id = "slick-scala-io",
    base = file("."),
    settings = projectSettings ++ Seq(
      libraryDependencies ++= Seq(slick, h2database, monocleCore, monocleMacro)
    )
  ) 
  //---------------------------------------------------------------------------------------
}