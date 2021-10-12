package by.htp.main.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import by.htp.main.entity.News;

@Repository
public class NewsDAOImpl implements NewsDAO {

	// need to inject the session factory
	@Autowired
	private SessionFactory sessionFactory;
			
	//@Override
	public List<News> getNewses() {
		
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
				
		// create a query  ... sort by last name
		Query<News> theQuery = 
				currentSession.createQuery("from News order by briefNews",
											News.class);
		
		// execute query and get result list
		List<News> newses = theQuery.getResultList();
				
		// return the results		
		return newses;
	}

	//@Override
	public void saveNews(News theNews) {

		// get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// save/upate the customer ... finally LOL
		currentSession.saveOrUpdate(theNews);
		
	}

	//@Override
	public News getNews(int theId) {

		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// now retrieve/read from database using the primary key
		News theNews = currentSession.get(News.class, theId);
		
		return theNews;
	}

	//@Override
	public void deleteNews(int theId) {

		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// delete object with primary key
		Query theQuery = 
				currentSession.createQuery("delete from News where id=:newsId");
		theQuery.setParameter("newsId", theId);
		
		theQuery.executeUpdate();		
	}

}
