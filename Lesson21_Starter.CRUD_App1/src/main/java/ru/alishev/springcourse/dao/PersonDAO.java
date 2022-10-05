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
        Person galina = new Person(++PEOPLE_COUNT, "Galina", "Belova", 59, new Person("No person data"));
        Person anton = new Person(++PEOPLE_COUNT, "Anton", "Belov", 28, galina);
        people.add(galina);
        people.add(anton);
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

    public void save(Person person) {
        //При создании новой сущности такие поля, как id должны присваиваться автоматически, а не вводиться пользователем
        person.setId(++PEOPLE_COUNT);
        people.add(person);
    }
}
