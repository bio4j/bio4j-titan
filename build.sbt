import AssemblyKeys._

Nice.javaProject

javaVersion := "1.8"

fatArtifactSettings

organization := "bio4j"

name := "bio4j-titan"

description := "Titan implementation of the Bio4j model"

bucketSuffix := "era7.com"


libraryDependencies ++= Seq(
  "bio4j" % "bio4j" % "0.12.0-SNAPSHOT",
  "bio4j" % "angulillos-titan" % "0.2.0-SNAPSHOT",
  "com.thinkaurelius.titan" % "titan-berkeleyje" % "0.4.4",
  "junit" % "junit" % "3.8.1" % "test"
)

dependencyOverrides ++= Set(
  "commons-codec" % "commons-codec" % "1.7",
  "com.fasterxml.jackson.core" % "jackson-core" % "2.1.2",
  "com.fasterxml.jackson.core" % "jackson-databind" % "2.1.2",
  "com.fasterxml.jackson.core" % "jackson-annotations" % "2.1.1",
  "commons-beanutils" % "commons-beanutils" % "1.8.3",
  "commons-beanutils" % "commons-beanutils-core" % "1.8.3",
  "com.tinkerpop.blueprints" % "blueprints-core" % "2.5.0"
)


// fat jar assembly settings
mainClass in assembly := Some("com.bio4j.titan.programs.ImportTitanDB")

assemblyOption in assembly ~= { _.copy(includeScala = false) }

mergeStrategy in assembly ~= { old => {
    case PathList("META-INF", "CHANGES.txt")                      => MergeStrategy.rename
    case PathList("META-INF", "LICENSES.txt")                     => MergeStrategy.rename
    case "log4j.properties"                                       => MergeStrategy.filterDistinctLines
    case PathList("org", "apache", "commons", "collections", _*)  => MergeStrategy.first
    case x                                                        => old(x)
  }
}
