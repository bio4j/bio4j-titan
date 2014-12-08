package com.bio4j.titan.tests.go

import com.bio4j.titan.tests._

class GOTestSuite extends org.scalatest.FunSuite {


  test("Importing GO") {

    val javaTestClass = new ImportGOTitanTest()

    javaTestClass.importGOTitanTest("/tmp/go")
  }
}