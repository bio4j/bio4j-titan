import AssemblyKeys._

Nice.javaProject

Nice.fatArtifactSettings

name := "bio4j-titandb"

description := "bio4j-titandb project"

organization := "ohnosequences"

bucketSuffix := "era7.com"

scalaVersion := "2.10.2"

mainClass in assembly := Some("com.era7.bioinfo.bio4j.titan.program.ImportTitanDB")

libraryDependencies += "junit" % "junit" % "3.8.1" % "test"

libraryDependencies += "com.tinkerpop.blueprints" % "blueprints-core" % "2.4.0"

libraryDependencies += "ohnosequences" % "bioinfo-util" % "1.2.0"

libraryDependencies += "ohnosequences" % "bio4j-model" % "0.3.1-SNAPSHOT"

libraryDependencies += "com.thinkaurelius.titan" % "titan-berkeleyje" % "0.4.1"

dependencyOverrides += "commons-codec" % "commons-codec" % "1.7"

dependencyOverrides += "com.fasterxml.jackson.core" % "jackson-core" % "2.1.2"

dependencyOverrides += "com.fasterxml.jackson.core" % "jackson-databind" % "2.1.2"

// needed for fat jar
dependencyOverrides += "commons-beanutils" % "commons-beanutils" % "1.8.2"

dependencyOverrides += "commons-beanutils" % "commons-beanutils-core" % "1.8.2"


// fat jar merge settings
// see http://stackoverflow.com/questions/14402745/duplicate-classes-in-commons-collections-and-commons-beanutils
mergeStrategy in assembly <<= (mergeStrategy in assembly) { (old) =>
  {
    case PathList("META-INF", "CHANGES.txt")                            => MergeStrategy.discard
    case PathList("META-INF", "LICENSES.txt")                           => MergeStrategy.discard
    case "log4j.properties"                                             => MergeStrategy.filterDistinctLines
    case PathList("org", "apache", "commons", "collections",  xs @ _*)  => MergeStrategy.first
    case PathList("index.html")                                         => MergeStrategy.first
    case x                                                              => old(x)
  }
}

// get rid of the Scala library
assemblyOption in assembly ~= { _.copy(includeScala = false) }
