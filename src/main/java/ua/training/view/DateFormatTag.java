package ua.training.view;

import ua.training.constants.Locales;
import ua.training.constants.Patterns;

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
            if (locale.equals(Locales.UA)) {
                pageContext.getOut()
                        .write(date.format(DateTimeFormatter.ofPattern(Patterns.DATE_UA)));
            } else {
                pageContext.getOut()
                        .write(date.format(DateTimeFormatter.ofPattern(Patterns.DATE_EN)));
            }
        } catch (IOException e) {
            throw new JspException(e);
        }
        return SKIP_BODY;
    }
}
