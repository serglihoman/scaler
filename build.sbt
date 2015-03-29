import com.typesafe.sbt.uglify.Import._
import com.typesafe.sbt.web.SbtWeb.autoImport._
import com.typesafe.sbt.less.Import.LessKeys

name := "scaler"

version := "1.0"

lazy val `scaler` = (project in file(".")).enablePlugins(PlayScala, SbtWeb)

scalaVersion := "2.11.1"

libraryDependencies ++= Seq( jdbc , anorm , cache , ws )

unmanagedResourceDirectories in Test <+=  baseDirectory ( _ /"target/web/public/test" )

// Process Assets(except 'public/libs/**') for minified *.min.css and *.min.js files

includeFilter in (Assets, LessKeys.less) := "*.less"

excludeFilter in (Assets, LessKeys.less) := "_*.less"

excludeFilter in digest := {
  val libs = (baseDirectory.value /"public" / "libs").getCanonicalPath
  new SimpleFileFilter(_.getCanonicalPath startsWith libs)
}

excludeFilter in uglify := {
  val libs = (baseDirectory.value /"public" / "libs").getCanonicalPath
  new SimpleFileFilter(_.getCanonicalPath startsWith libs)
}

excludeFilter in rjs := {
  val libs = (baseDirectory.value /"public" / "libs").getCanonicalPath
  new SimpleFileFilter(_.getCanonicalPath startsWith libs)
}

LessKeys.compress := true

LessKeys.sourceMap := false

RjsKeys.generateSourceMaps := false

UglifyKeys.sourceMap := false

pipelineStages := Seq(rjs, uglify, digest, gzip)

