package by.myproject.news.servise;

import java.util.List;

import by.myproject.news.bean.News;

import by.myproject.news.servise.exception.ServiseException;

public interface NewsServise {

	List<News> addNewses(int quantity) throws ServiseException;

}
