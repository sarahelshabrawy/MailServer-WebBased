package Filter;

import java.util.LinkedList;
import com.example.mail_server.Model.Mail;

public interface FilterType {
    public LinkedList<Mail> filter(LinkedList<Mail> mails);
    public void setFilter(String subject);
}
