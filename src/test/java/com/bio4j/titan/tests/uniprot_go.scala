package com.bio4j.titan.tests.uniprot_go

import com.bio4j.titan.tests._

class UniProtGoTestSuite extends org.scalatest.FunSuite {


  ignore("Importing UniProtGo") {

    val javaTestClass = new ImportUniProtGoTitanTest()

    javaTestClass.importUniProtGoTitanTest("raw/uniprot_go")
  }
}
