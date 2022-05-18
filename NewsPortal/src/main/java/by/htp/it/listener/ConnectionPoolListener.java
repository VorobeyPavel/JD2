/*
 * package by.htp.it.listener;
 * 
 * import by.htp.it.dao.cp.ConnectionPool; import
 * by.htp.it.dao.exception.ConnectionPoolException; import
 * jakarta.servlet.ServletContextEvent; import
 * jakarta.servlet.ServletContextListener;
 * 
 * public class ConnectionPoolListener implements ServletContextListener {
 * 
 * ConnectionPool con;
 * 
 * public void contextInitialized(ServletContextEvent servletContextEvent) {
 * 
 * con = ConnectionPool.getInstance();
 * 
 * }
 * 
 * public void contextDestroyed(ServletContextEvent servletContextEvent) {
 * 
 * try { con.dispose(); } catch (ConnectionPoolException e) { throw new
 * RuntimeException("Exception: close connection queue", e); }
 * 
 * }
 * 
 * }
 */