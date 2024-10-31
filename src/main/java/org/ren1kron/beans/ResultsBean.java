package org.ren1kron.beans;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Getter;
import org.ren1kron.models.Point;

import java.io.Serializable;
import java.util.List;

@Named("resultsBean")
@RequestScoped
@Getter
public class ResultsBean implements Serializable {
    private static final long serialVersionUID = 2L;

    @Inject
    private FormBean formBean;

    public List<Point> getPoints() {
        return formBean.getPoints();
    }
}
