package ru.alishev.springcourse.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.alishev.springcourse.dao.PersonDAO;
import ru.alishev.springcourse.models.Person;

import javax.validation.Valid;

/**
 * @author Neil Alishev
 */
@Controller
@RequestMapping("/people")
public class PeopleController {

    private final PersonDAO personDAO;

    @Autowired
    public PeopleController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("people", personDAO.index());
        return "people/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", personDAO.show(id));
        return "people/show";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") Person person) {
        /*
        Добавление пустого объекта Person в модель через аннотацию @ModelAttribute("person") и получение html страницы
        с формой для создания нового человека
        */
        return "people/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("person") @Valid Person person,
                         BindingResult bindingResult) {
        /*
        @Valid проверяет на соответсвие поля объекта на этапе внедрения из формы
        BindingResult - специальный объект, который содержит объект с ошибками в случае не прохождения @Valid (в аргементе
        метода идет после объекта, который валидируется)
        С помощью аннотации @ModelAttribute создается пустой объект, происходит неявная инициализация его полей из формы,
        после чего с POST запросом этот объект передается в модель, и вызывается соответствующий метод контроллера
        */
        //проверка на ошибки валидации, в случае наличия, возвращаем форму создания нового человека (GET запрос), в которую
        //уже передан объект с ошибками
        if (bindingResult.hasErrors()) {
            return "people/new";
        }
        personDAO.save(person);
        return "redirect:/people";
    }


    @GetMapping("/{id}/edit")
    /*
    При переходе по URL "/{id}/edit" отправляется GET запрос на метод edit(), в который передается поле id, значение
    подставляется по ключу из {id}. По переданному id через метод DAO show() подставляется соответствующий объект Person,
    поля которого подставляются в форму для изменения. GET запрос создан для отображения формы и подстановки полей изменяемого
    объекта.
    */
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", personDAO.show(id));
        return "people/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") @Valid Person person,
                         BindingResult bindingResult,
                         @PathVariable("id") int id) {
        /*
        С помощью аннотации @ModelAttribute создается новый пустой объект, в которой неявно через сеттеры передаются значения
        полей из формы PATCH запроса, после чего этот объект передается в модель и вызывается соответствующий метод контроллера
        */
        if (bindingResult.hasErrors()) {
            return "people/edit";
        }
        personDAO.update(person, id);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        /*
        При переходе на URL /{id} в модель добавляется соответсвующий объект Person (метод show), в форме получаем его
        id и отправляем DELETE запрос на соответсвующий метод контроллера
        */
        personDAO.delete(id);
        return "redirect:/people";
    }
}
