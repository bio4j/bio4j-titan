package com.bio4j.titan.tests.enzymedb

import com.bio4j.titan.tests._

class EnzymeDBTestSuite extends org.scalatest.FunSuite {


  test("I'm so happy!") {

    val javaTestClass = new ImportEnzymeDBTitanTest()

    javaTestClass.importEnzymeDBTitanTest("/tmp/enzymedb")
  }
}