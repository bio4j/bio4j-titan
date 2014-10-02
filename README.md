## Bio4j Titan DB backend

This is an implementation of the [Bio4j model](https://github.com/bio4j/bio4j) using the [Titan database](https://github.com/thinkaurelius/titan). 

> Titan is a highly scalable graph database optimized for storing and querying large graphs with billions of vertices and edges distributed across a multi-machine cluster. Titan is a transactional database that can support thousands of concurrent users.

We reuse here the default [blueprints implementation](https://github.com/bio4j/blueprints), but also take advantage of the technology specific features of Titan, which allow us to improve the way we import and store the data. For example, we use [Titan types](https://github.com/thinkaurelius/titan/wiki/Type-Definition-Overview) for representing the rich structure of the Bio4j domain model and [vertex-centric indices](https://github.com/thinkaurelius/titan/wiki/Vertex-Centric-Indices) for effective local indexing which allows to avoid the supe-node problem.

### SBT dependency

To use it in you sbt-project, add this to you `build.sbt`:

```scala
resolvers += "Era7 maven releases" at "http://releases.era7.com.s3.amazonaws.com"

libraryDependencies += "bio4j" %% "bio4j-titan" % "0.3.1"
```
