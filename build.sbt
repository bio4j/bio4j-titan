import AssemblyKeys._

Nice.javaProject

Nice.fatArtifactSettings

name := "bio4j-titandb"

description := "bio4j-titandb project"

organization := "ohnosequences"

bucketSuffix := "era7.com"

scalaVersion := "2.10.2"

libraryDependencies += "junit" % "junit" % "3.8.1" % "test"

libraryDependencies += "com.tinkerpop.blueprints" % "blueprints-core" % "2.4.0"

libraryDependencies += "ohnosequences" % "bioinfo-util" % "1.2.0"

libraryDependencies += "ohnosequences" % "bio4j-model" % "0.2.0"

libraryDependencies += "com.thinkaurelius.titan" % "titan-berkeleyje" % "0.4.0"

dependencyOverrides += "commons-codec" % "commons-codec" % "1.7"

dependencyOverrides += "com.fasterxml.jackson.core" % "jackson-core" % "2.1.2"

dependencyOverrides += "com.fasterxml.jackson.core" % "jackson-databind" % "2.1.2"

// fat jar merge settings
mergeStrategy in assembly <<= (mergeStrategy in assembly) { (old) =>
  {
    case PathList("index.html") => MergeStrategy.first
    case x => old(x)
  }
}
