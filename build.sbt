Nice.javaProject

organization  := "bio4j"
name          := "bio4j-titan"
description   := "Titan implementation of the Bio4j model"
javaVersion   := "1.8"

libraryDependencies ++= Seq(
  "bio4j"                   % "bio4j"             % "0.12.0-SNAPSHOT",
  "bio4j"                   % "angulillos-titan"  % "0.3.0",
  "com.thinkaurelius.titan" % "titan-berkeleyje"  % "0.5.4",
  // test deps
  "junit"           %   "junit"     % "3.8.1" % "test",
  "org.scalatest"   %%  "scalatest" % "2.2.5" % "test"
)

dependencyOverrides ++= Set(
  "commons-codec" % "commons-codec" % "1.7"
)


bucketSuffix := "era7.com"
// fat jar assembly settings
fatArtifactSettings
mainClass in assembly := Some("com.bio4j.titan.programs.ImportTitanDB")

assemblyOption in assembly ~= { _.copy(includeScala = false) }

mergeStrategy in assembly ~= { old => {
    case "log4j.properties"                       => MergeStrategy.filterDistinctLines
    case PathList("org", "apache", "commons", _*) => MergeStrategy.first
    case x                                        => old(x)
  }
}
