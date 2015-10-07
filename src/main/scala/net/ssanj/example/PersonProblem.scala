package net.ssanj.example

object PersonProblem extends App {
  final case class Person(name: String, age: Int)

  println(s"invalid Person1: ${Person("", 1000)}")
  println(s"invalid Person2: ${Person("12345", -1)}")
}

