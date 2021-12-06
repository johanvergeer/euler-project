from dataclasses import dataclass
from datetime import date
from typing import Iterable, TypeVar


@dataclass
class Person:
    name: str
    date_of_birth: date

    @property
    def age(self) -> int:
        today = date.today()
        return (
            today.year
            - self.date_of_birth.year
            - (
                (today.month, today.day)
                < (self.date_of_birth.month, self.date_of_birth.day)
            )
        )

    def __str__(self):
        return f"name={self.name}, age={self.age}"


P = TypeVar("P", bound=Person)


def add_person(person: P) -> None:
    return repo.add(person)


class PeopleCollection(list):
    def __init__(self, *args):
        super(PeopleCollection, self).__init__(args)

    def append(self, person: Person) -> None:
        if person.name == "":
            raise ValueError("A person must have a name")
        super(PeopleCollection, self).append(person)


def print_people(people: Iterable[Person]) -> None:
    for person in people:
        print(person)


if __name__ == "__main__":
    people = (
        Person("Guido van Rossum", date(1956, 1, 31)),
        Person("Linus Torvalds", date(1969, 12, 28)),
    )

    print_people(people)
