package net.ssanj.example

object PersonSolution2 extends App {
  final case class Person private(name: String, age: Int)

  object Person {
    def createPerson(name: String, age: Int): Option[Person] =
      for {
        n <- Option(name).
                map(_.trim).
                filterNot(_.isEmpty).
                filter(_.forall(_.isLetter))
        a <- if (age >= 0 && age <= 120) Option(age) else None
      } yield Person(n, a)
  }

  def ageBracket(age: Int): String = {
    if (age <= 20) "young"
    else if (age <= 40) "prime"
    else "old"
  }

  println(s"invalid person1: ${Person.createPerson("", 23)}")
  println(s"invalid person2: ${Person.createPerson("Bob", 1000)}")

  val personOp = Person.createPerson("Bob", 23)
  println(s"valid person3: $personOp")

  personOp.foreach {p => println(s"ageBracket: ${ageBracket(p.age)}")}
}