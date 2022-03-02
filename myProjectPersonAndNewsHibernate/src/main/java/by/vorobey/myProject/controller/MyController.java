package by.vorobey.myProject.controller;

import by.vorobey.myProject.bean.Person;
import by.vorobey.myProject.dao.PersonDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.beans.PropertyVetoException;
import java.sql.SQLException;

@Controller
@RequestMapping("/people")
public class MyController {

    private final PersonDAO personDAO;

    @Autowired
    public MyController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @GetMapping("/startPage")
    public String startPage(){
        return "startPage";
    }

    @GetMapping("/registration")
    public String registration(){
        return "registration";
    }

    @GetMapping("/authorization")
    public String authorization(){
        return "authorization";
    }

    @PostMapping("/registration")
    public String registrationPage( @RequestParam String name,
                                    @RequestParam String surname,
                                    @RequestParam String email,
                                    @RequestParam String login,
                                    @RequestParam String password,
                                    Model model) {
        Person person = new Person(name, surname, email, login, password);
        personDAO.registration(person);
        return "redirect:/people/authorization";

    }

    @PostMapping("/authorization")
    public String authorizationPage(@RequestParam String login,
                                    @RequestParam String password){
        Person person = personDAO.authorization(login,password);
        if (person!=null){
           return "redirect:/news/pageNews";
        }
        return "startPage";
    }

}
