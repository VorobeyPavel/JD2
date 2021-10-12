package by.htp.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.htp.main.dao.NewsDAO;
import by.htp.main.entity.News;

@Service
public class NewsServiceImpl implements NewsService {

	// need to inject customer dao
	@Autowired
	private NewsDAO newsDAO;
	
	//@Override
	@Transactional
	public List<News> getNewses() {
		
		System.out.println("ןמסכו גûחמגא List<News> getNewses()");
		
		return newsDAO.getNewses();
	}

	//@Override
	@Transactional
	public void saveNews(News theNews) {
		
		// log
		
		// check

		newsDAO.saveNews(theNews);
	}

	//@Override
	@Transactional
	public News getNews(int theId) {
		
		return newsDAO.getNews(theId);
	}

	//@Override
	@Transactional
	public void deleteNews(int theId) {
		
		newsDAO.deleteNews(theId);
	}
}





