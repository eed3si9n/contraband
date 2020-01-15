package sbt.contraband

import org.scalatest._
import sbt.contraband.ast._
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

abstract class GCodeGenSpec(language: String) extends AnyFlatSpec with Matchers with EqualLines {
  implicit def typeDefinitions2Document(ds: List[TypeDefinition]): Document =
    Document(None, ds, Directive.codecPackage("generated") :: Nil, Nil, None)
  implicit def typeDefinition2Document(d: TypeDefinition): Document =
    Document(None, List(d), Directive.codecPackage("generated") :: Nil, Nil, None)

  "generate(Enumeration)" should "generate a simple enumeration" in enumerationGenerateSimple

  "generate(Interface)" should "generate a simple interface" in interfaceGenerateSimple
  it should "generate a simple interface with one child" in interfaceGenerateOneChild
  it should "generate nested interfaces" in interfaceGenerateNested
  it should "generate messages" in interfaceGenerateMessages

  "generate(Record)" should "generate a simple record" in recordGenerateSimple
  it should "grow a record from 0 to 1 field" in recordGrowZeroToOneField
  it should "grow a record from 0 to 1 to 2 fields" in recordGrowZeroToOneToTwoFields
  it should "generate correct types" in recordPrimitives
  it should "generate with different modifier" in recordWithModifier

  "generate(Schema)" should "generate a complete schema" in schemaGenerateComplete
  it should "generate and indent a complete schema" in schemaGenerateCompletePlusIndent
  it should "generate correct type references" in schemaGenerateTypeReferences
  it should "generate correct type references (no lazy)" in schemaGenerateTypeReferencesNoLazy

  def enumerationGenerateSimple: Unit

  def interfaceGenerateSimple: Unit
  def interfaceGenerateOneChild: Unit
  def interfaceGenerateNested: Unit
  def interfaceGenerateMessages: Unit

  def recordGenerateSimple: Unit
  def recordGrowZeroToOneField: Unit
  def recordGrowZeroToOneToTwoFields: Unit
  def recordPrimitives: Unit
  def recordWithModifier: Unit

  def schemaGenerateComplete: Unit
  def schemaGenerateCompletePlusIndent: Unit
  def schemaGenerateTypeReferences: Unit
  def schemaGenerateTypeReferencesNoLazy: Unit
}
