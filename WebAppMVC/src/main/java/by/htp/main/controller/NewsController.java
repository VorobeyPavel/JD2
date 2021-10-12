package by.htp.main.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import by.htp.main.entity.News;
import by.htp.main.service.NewsService;

@Controller
@RequestMapping("/news")
public class NewsController {
	
	// need to inject our customer service
	@Autowired
	private NewsService newsService;
	
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		dataBinder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}
	
	@RequestMapping("/list")
	public String listNewses(Model theModel) {
				
		// get customers from the service
		List<News> theNewses = newsService.getNewses();
				
		// add the customers to the model
		theModel.addAttribute("newses", theNewses);

		return "list-newses";
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		
		// create model attribute to bind form data
		News theNews = new News();
		
		theModel.addAttribute("news", theNews);
		
		return "news-form2";
	}
	
	@PostMapping("/saveNews")
	public String saveNews(@ Valid @ModelAttribute("news") News theNews, BindingResult bindingResult) {
		
		// save the customer using our service
		if(bindingResult.hasErrors()){
			return "/news-form2";
		}else {
			newsService.saveNews(theNews);	
			return "redirect:/news/list";
		}
	}
	
	@GetMapping("/showFormForRead")
	public String showFormForRead(@RequestParam("newsId") int theId,
									Model theModel) {
		
		// get the customer from our service
		News theNews = newsService.getNews(theId);	
		
		// set customer as a model attribute to pre-populate the form
		theModel.addAttribute("news", theNews);
		
		// send over to our form		
		return "news-read";
	}
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("newsId") int theId,
									Model theModel) {
		
		// get the customer from our service
		News theNews = newsService.getNews(theId);	
		
		// set customer as a model attribute to pre-populate the form
		theModel.addAttribute("news", theNews);
		
		// send over to our form		
		return "news-form2";
	}
	
	@GetMapping("/delete")
	public String deleteNews(@RequestParam("newsId") int theId) {
		
		// delete the customer
		newsService.deleteNews(theId);
		
		return "redirect:/news/list";
	}
}
