package ru.alishev.springcourse.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.alishev.springcourse.dao.PersonDAO;
import ru.alishev.springcourse.models.Person;

@Controller
@RequestMapping("/people")
public class PeopleController {

    //@Autowired данная аннотация над полем не рекомендуется, лучше использовать над конструктором
    private PersonDAO personDAO;

    @Autowired
    public PeopleController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }


    @GetMapping() //В данном случае пустая директория запроса, т.к. используется директория из @RequestMapping("/people")
    public String index(Model model) {
        //Получим всех людей из DAO и передадим на отображение в представление (view) под ключем "people"
        model.addAttribute("people", personDAO.index());
        return "people/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        //Получим одного человека по его id из DAO и передадим на отображение в представление (view)
        model.addAttribute("person", personDAO.show(id));
        return "people/show";
    }

    //Запрос HTML формы для создания нового человека
    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") Person person) {
        //передаем модели пустой объект класса Person с значениями полей по умолчанию
        //Это можно сделать с помощью аннотации @ModelAttribute("person") Person person или через добавление в аргумент
        //метода Model model и вызове метода model.addAttribute("person", new Person());

        return "people/new";
    }

    //Создание человека и добавление его в БД
    @PostMapping
    public String create(@ModelAttribute("person") Person person) {
        //добавление в БД с помощью паттерна DAO
        personDAO.save(person);
        //механизм редирект (указание к переходу на другую страницу). В данном случае после добавления человека в БД (List<Person>)
        //указание к переходу на страницу со списком людей
        return "redirect:/people";
    }
}
