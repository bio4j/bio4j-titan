package com.bio4j.titan.tests.indices

import com.bio4j.titan.tests._

/**
 * Created by ppareja on 12/9/2014.
 */
class IndexTestSuite extends org.scalatest.FunSuite {

  test("Testing indices") {

    val javaTestClass = new IndicesTest()

    javaTestClass.indicesTest("/tmp/indices")
  }
}