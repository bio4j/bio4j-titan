Nice.javaProject

organization  := "bio4j"
name          := "bio4j-titan"
description   := "Titan implementation of the Bio4j model"
javaVersion   := "1.8"

libraryDependencies ++= Seq(
  "bio4j"                   % "bio4j"             % "0.12.0-SNAPSHOT",
  "bio4j"                   % "angulillos-titan"  % "0.2.1",
  "com.thinkaurelius.titan" % "titan-berkeleyje"  % "0.5.2",
  // test deps
  "junit"           %   "junit"     % "3.8.1" % "test",
  "org.scalatest"   %%  "scalatest" % "2.2.2" % "test"
)

dependencyOverrides ++= Set(
  "commons-codec"               % "commons-codec"           % "1.7"
  // "com.fasterxml.jackson.core"  % "jackson-core"            % "2.1.2",
  // "com.fasterxml.jackson.core"  % "jackson-databind"        % "2.1.2",
  // "com.fasterxml.jackson.core"  % "jackson-annotations"     % "2.1.1",
  // "commons-beanutils"           % "commons-beanutils"       % "1.8.3",
  // "commons-beanutils"           % "commons-beanutils-core"  % "1.8.3"
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
