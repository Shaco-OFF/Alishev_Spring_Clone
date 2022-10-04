package ru.alishev.springcourse.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.alishev.springcourse.dao.PersonDAO;

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
        //Получим одногочеловека по его id из DAO и передадим на отображение в представление (view)
        model.addAttribute("person", personDAO.show(id));
        return "people/show";
    }
}
