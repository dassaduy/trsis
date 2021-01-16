//Задание на запуск сервера
//Описание задания в task.txt

import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.webapp.WebAppContext;

import javax.servlet.ServletRequest;
import java.util.logging.Logger;

public class JettyServer {

    public static void main(String[] args) throws Exception {


        RestService restService = new RestService();
        org.eclipse.jetty.server.Server server = new org.eclipse.jetty.server.Server(8080);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        server.setHandler(context);
        context.addServlet(new ServletHolder(restService), "/");

        server.start();
        Logger.getGlobal().info("Server started");
        server.join();
    }
}
