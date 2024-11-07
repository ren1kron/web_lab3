package org.ren1kron.beans;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Getter;
import org.ren1kron.models.Point;

import java.io.Serializable;
import java.util.List;

@Named("graphBean")
@RequestScoped
@Getter
public class GraphFormBean implements Serializable {
    private static final long serialVersionUID = 2L;



}
