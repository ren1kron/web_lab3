package org.ren1kron.beans;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.ren1kron.dao.PointDao;
import org.ren1kron.models.Point;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named("formBean")
@SessionScoped
@Getter
@Setter
@Slf4j
public class FormBean implements Serializable {
    private static final long serialVersionUID = 12L;

    private float x;
    private float y;
    private float r;

    private float graph_x;
    private float graph_y;
    private float graph_r;

    private List<Point> points;
//    private List<Point> points = new ArrayList<>();

    private PointDao dbCommunicator;


    @PostConstruct
    public void init() {
        log.info("formBean init...");
        x = 0;
        y = 0;
        r = 2;

        dbCommunicator = PointDao.getInstance();

        points = dbCommunicator.getPoints();

        if (points == null) {
            points = new ArrayList<>();
            log.info("DB table is empty, init new list...");
        }
        else {
            log.info("Loaded {} points from db", points.size());
        }
    }

    /**
     * Method for submitting form
     */
    public String submit(boolean isGraphSubmit) {
        log.info("\"Submit\" click processing...");
        log.info("got point with coords x={}, y={}, r={}", x, y, r);

//        Point point = new Point(x, y, r);

        Point point;

        if (isGraphSubmit)
            point = new Point(graph_x, graph_y, graph_r);
        else
            point = new Point(x, y, r);
        points.add(point);

        // DB COMMUNICATION
        dbCommunicator.addPoint(point);
        log.info("Point added to DB");
        // DB COMMUNICATION

        // Создаем сообщение
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Jsf - хуйня", "");

        // Добавляем сообщение в FacesContext
        FacesContext.getCurrentInstance().addMessage(null, message);

        return null; // Остаемся на той же странице
    }
//
//    /**
//     * Method for submitting form by clicking graph
//     * P.S. Of course, I could
//     */
//    public String submit_graph() {
//        log.info("\"Submit\" click processing...");
//        log.info("got point with coords x={}, y={}, r={}", x, y, r);
//
//        Point point = new Point(graph_x, graph_y, graph_r);
//        points.add(point);
//
//        // DB COMMUNICATION
//        dbCommunicator.addPoint(point);
//        log.info("Point added to DB");
//        // DB COMMUNICATION
//
//        // Создаем сообщение
//        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Jsf - хуйня", "");
//
//        // Добавляем сообщение в FacesContext
//        FacesContext.getCurrentInstance().addMessage(null, message);
//
//        return null; // Остаемся на той же странице
//    }

    /**
     * Clear the list of points and DB
     */
    public void clear() {
        log.info("\"clear\" click processing...");
        log.info("clearing list and DB...");
        dbCommunicator.clear();
        points.clear();
        log.info("List and DB cleared");
    }


}
