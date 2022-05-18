package by.vorobey.myProject.controller;

import by.vorobey.myProject.bean.News;
import by.vorobey.myProject.dao.NewsDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/news")
public class NewsController {

    private final NewsDAO newsDAO;

    @Autowired
    public NewsController(NewsDAO newsDAO) {
        this.newsDAO = newsDAO;
    }

    @GetMapping("/pageNews")
    public String pageNews( Model model) {
        Iterable <News> news = newsDAO.getNews();
        model.addAttribute("posts", news);
        return "pageNews";
    }

    @GetMapping("/add")
    public String blogAdd(Model model){
        return "newsAdd";
    }

    @PostMapping("/add")
    public String blogPostAdd(@RequestParam String title,
                              @RequestParam String anons,
                              @RequestParam String full_text,
                              Model model){
        News news = new News(title, anons , full_text);
        newsDAO.addNews(news);
        return "redirect:/news/pageNews";
    }


    // не работает. title не передается и ссыслка не рабочая
    @GetMapping("/details/{id}")
    public String blogDetails(@PathVariable(value = "id") int id,
                              Model model){
        //проверяем есть ли статья с таким id
        /*if (!newsRepository.existsById(id)){
            return "redirect:/pageNews";
        }*/
        System.out.println("detal!");
        News news = newsDAO.newsDetails(id);
        model.addAttribute("posts", news);
        return "newsDetails";
    }

    @GetMapping("/{id}/edit")
    public String blogEdit(@PathVariable(value = "id") int id,
                           Model model){
        //проверяем есть ли статья с таким id
        if (newsDAO.newsDetails(id)==null){
            return "redirect:/news/pageNews";
        }
        System.out.println("Edit!");
        News news = newsDAO.newsDetails(id);
        model.addAttribute("posts", news);
        return "newsEdit";
    }

    @PostMapping("/{id}/edit")
    public String blogPostUpdate(@PathVariable(value = "id") int id,
                                 @RequestParam String title,
                                 @RequestParam String anons,
                                 @RequestParam String full_text,
                                 Model model){
        newsDAO.updateNews(id, title, anons, full_text);
        return "redirect:/news/pageNews";
    }

    @PostMapping("/{id}/remove")
    public String deleteNews(@PathVariable(value = "id") int id,
                                 Model model){
        newsDAO.deleteNews(id);
        return "redirect:/news/pageNews";
    }
}
