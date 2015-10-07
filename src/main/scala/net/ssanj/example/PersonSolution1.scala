package net.ssanj.example

object PersonSolution1 extends App {

  final case class Person private(name: String, age: Int)

  object Person {
    def createPerson(name: String, age: Int): Person = {
      if (name.trim.isEmpty || !name.forall(_.isLetter)) {
        throw new IllegalStateException(s"Expected a non empty alphabetic name but got: [$name]")
      }

      if (age < 0 || age > 120) {
        throw new IllegalStateException(s"Expected age between 0 and 120 but got: $age")
      }

      Person(name, age)
    }
  }

    //constructor Person in class Person cannot be accessed
    //Person("", 1000)

    //throws exception
    // println(s"invalid Person1: ${Person.createPerson("", 23)}")//throws exception

    println(s"invalid Person1: ${Person.createPerson("Bob", 1000)}")
}