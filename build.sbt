Nice.scalaProject

name := "bio4j-titandb"

description := "bio4j-titandb project"

organization := "era7"

bucketSuffix := "era7.com"

libraryDependencies += "ohnosequences" % "bioinfo-utils" % "1.1.0"

libraryDependencies += "bio4j" % "bio4j-model" % "1.1.0"

libraryDependencies += "com.thinkaurelius.titan" % "titan-berkeleyje" % "0.3.2"

dependencyOverrides += "commons-logging" % "commons-logging" % "1.1.3"
