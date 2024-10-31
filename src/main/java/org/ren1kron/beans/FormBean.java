package org.ren1kron.beans;

import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;
import org.ren1kron.models.Point;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named("formBean")
@SessionScoped
@Getter
@Setter
public class FormBean implements Serializable {
    private static final long serialVersionUID = 12L;


    private float x;
    private float y;
    private float r = 2; // Значение по умолчанию

    private List<Point> points = new ArrayList<>();

    // Метод обработки отправки формы
    public String submit() {

        Point point = new Point(x, y, r);
        points.add(point);

        // Создаем сообщение
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Jsf - хуйня", "");

        // Добавляем сообщение в FacesContext
        FacesContext.getCurrentInstance().addMessage(null, message);

        return null; // Остаемся на той же странице
    }
}
