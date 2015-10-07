package net.ssanj.example

object PersonSolution3 extends App {
  final case class Person private(name: Name, age: Age)

  final case class Name private(value: String)
  final case class Age private(age: Int)

  object Name {
   def createName(name: String): Option[Name] =
     Option(name).
       map(_.trim).
       filterNot(_.isEmpty).
       filter(_.forall(_.isLetter)).
       map(Name(_))
  }

  object Age {
    def createAge(age: Int): Option[Age] = if (age >= 0 && age <= 120) Option(Age(age)) else None
  }

  object Person {
    def createPerson(name: String, age: Int): Option[Person] =
      for {
        n <- Name.createName(name)
        a <- Age.createAge(age)
       } yield Person(n, a)
  }

  def ageBracket(age: Age): String = {
    if (age.age <= 20) "young"
    else if (age.age <= 40) "prime"
    else "old"
  }

  println(s"invalid person1: ${Person.createPerson("", 23)}")
  println(s"invalid person2: ${Person.createPerson("Bob", 1000)}")

  val personOp = Person.createPerson("Bob", 23)
  println(s"valid person3: $personOp")

  personOp.foreach {p => println(s"ageBracket: ${ageBracket(p.age)}")}

  val fiftyOp = Age.createAge(50)
  val bobOp = Name.createName("Bob")

  fiftyOp match {
    case Some(Age(a)) => println(s"got age: $a")
    case None => println(s"could not match age")
  }

  bobOp match {
    case Some(Name(n)) => println(s"got name: $n")
    case None => println(s"could not match name")
  }

  personOp match {
    case Some(Person(Name(n), Age(a))) => println(s"got person with name:$n, age: $a")
    case None => println(s"could not match Person")
  }
}