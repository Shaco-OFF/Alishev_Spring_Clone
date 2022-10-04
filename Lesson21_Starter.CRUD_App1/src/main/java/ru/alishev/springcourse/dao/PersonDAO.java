package ru.alishev.springcourse.dao;

import org.springframework.stereotype.Component;
import ru.alishev.springcourse.models.Person;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

//Класс для взаимодействия моделей с базой данных
//Создаем БИН этого класса для внедрения его в класс-контроллер. Создание БИНов Person не требуется.
@Component
public class PersonDAO {
    private static int PEOPLE_COUNT;
    private List<Person> people;
    //Блок инициализации (аналог конструктора)
    {
        people = new ArrayList<>();
        people.add(new Person(++PEOPLE_COUNT, "Anton"));
        people.add(new Person(++PEOPLE_COUNT, "Egor"));
        people.add(new Person(++PEOPLE_COUNT, "Ruslan"));
        people.add(new Person(++PEOPLE_COUNT, "Andrey"));
    }
    public List<Person> index() {
        return people;
    }

    public Person show(int id) {

        /*Optional<Person> person = people.stream()
                .filter(x -> x.getId() == id)
                .findAny();

        return person.orElse(null);*/

        return people.stream().filter(person -> person.getId() == id).findAny().orElse(null);
    }
}
