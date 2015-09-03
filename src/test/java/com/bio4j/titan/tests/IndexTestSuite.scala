package com.bio4j.titan.tests.indices

import com.bio4j.titan.tests._

class IndexTestSuite extends org.scalatest.FunSuite {

  test("Testing indices") {

    val javaTestClass = new IndicesTest()

    javaTestClass.indicesTest("raw/indices")
  }
}
