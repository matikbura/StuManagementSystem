package listener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
public class CountListener implements HttpSessionListener{
	private static long linedNumber=0;

	public void sessionCreated(HttpSessionEvent arg0) {
		linedNumber++;
	}

	public void sessionDestroyed(HttpSessionEvent arg0) {
		linedNumber--;
	}

	public static long getLinedNumber(){
		return linedNumber;
	}
}
