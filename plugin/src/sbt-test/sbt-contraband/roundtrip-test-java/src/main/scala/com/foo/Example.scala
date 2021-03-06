package com.foo

import sjsonnew.JsonFormat
import sjsonnew.support.scalajson.unsafe.{ Converter, CompactPrinter }
import com.example._

object Example extends App {
  import generated.CustomProtocol._
  val g0: Greeting = new SimpleGreeting("Hello")
  val g1: Greeting = new SimpleGreeting("Hello", Maybe.nothing[Integer]())
  val g21: Greeting = new GreetingWithAttachments("Hello", Array.empty)
  val g3: Greeting = new GreetingWithOption("Hello", Maybe.just("foo"))

  println(CompactPrinter(Converter.toJson(g0).get))
  println(Converter.fromJson[Greeting](Converter.toJson(g0).get).get)

  assert(Converter.fromJson[Greeting](Converter.toJson(g0).get).get == g0)
  assert(Converter.fromJson[Greeting](Converter.toJson(g1).get).get == g1)
  assert(Converter.fromJson[Greeting](Converter.toJson(g21).get).get == g21)
  assert(Converter.fromJson[Greeting](Converter.toJson(g3).get).get == g3)
}
