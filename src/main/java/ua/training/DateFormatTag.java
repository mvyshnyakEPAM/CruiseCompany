package ua.training;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Максим
 * 12.05.2018
 */
public class DateFormatTag extends TagSupport {
    private LocalDate date;
    private String locale;

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    @Override
    public int doStartTag() throws JspException {
        try {
            if (locale.equals("ua")) {
                pageContext.getOut().write(date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
            } else {
                pageContext.getOut().write(date.format(DateTimeFormatter.ofPattern("MM.dd.yyyy")));
            }
        } catch (IOException e) {
            throw new JspException(e);
        }
        return SKIP_BODY;
    }
}
