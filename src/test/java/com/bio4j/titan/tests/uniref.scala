package com.bio4j.titan.tests.uniref

import com.bio4j.titan.tests._

class UniRefTestSuite extends org.scalatest.FunSuite {


  test("Importing UniRef") {

    val javaTestClass = new ImportUniRefTitanTest()

    javaTestClass.importUniRefTitanTest("/tmp/uniref")
  }
}