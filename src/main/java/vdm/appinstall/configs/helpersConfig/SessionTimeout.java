package vdm.appinstall.configs.helpersConfig;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionTimeout implements HttpSessionListener{

    private int seconds;

    public SessionTimeout(int seconds) {
        this.seconds = seconds;
    }

    @Override
    public void sessionCreated(HttpSessionEvent event) {
        event.getSession().setMaxInactiveInterval(seconds); //60 seconds
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {

    }
}
