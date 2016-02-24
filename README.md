## Bio4j Titan

This is the [Titan DB](http://titandb.io) distribution of [Bio4j](https://github.com/bio4j/bio4j).

> Titan is a highly scalable graph database optimized for storing and querying large graphs with billions of vertices and edges distributed across a multi-machine cluster. Titan is a transactional database that can support thousands of concurrent users.

We use here our open source library **[angulillos-titan](https://github.com/bio4j/angulillos-titan)** which in turn depends on **[angulillos](https://github.com/bio4j/angulillos)**, _a Java 8 library for working with strongly typed graph data in a generic way_. This allows us to keep in different layers on the one hand Titan technology-specific stuff and, on the other hand, the logic regarding how to deal with graphs in a generic way. Thanks to that we have an effective and organized way to import and store the data.

In the specific case of Titan, we use [Titan types](https://github.com/thinkaurelius/titan/wiki/Type-Definition-Overview) for representing the rich structure of the Bio4j domain model and [vertex-centric indices](https://github.com/thinkaurelius/titan/wiki/Vertex-Centric-Indices) for effective local indexing which allows to avoid the [super-node problem](http://thinkaurelius.com/2012/10/25/a-solution-to-the-supernode-problem/).


### SBT dependency

To use it in an sbt-project, add the following to `libraryDependencies` in your `build.sbt` file:

``` scala
"bio4j" % "bio4j-titan" % "0.4.0-RC3"
```


### Using a pre-built release in AWS

Please go to **[this section](/docs/Bio4jAWSReleases.md)** if you want to use an already pre-built Titan Bio4j database in Amazon Web Services.


### Importing Titan Bio4j

Please refer to **[this section](/docs/ImportingTitanBio4j.md)** if you want to import your own Titan Bio4j database.
