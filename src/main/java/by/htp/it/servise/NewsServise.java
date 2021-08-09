package by.htp.it.servise;

import java.util.List;

import by.htp.it.bean.News;
import by.htp.it.servise.exception.ServiseException;

public interface NewsServise {

	List<News> addNewses(int quantity) throws ServiseException;

}
