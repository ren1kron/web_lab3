package org.ren1kron.beans;


import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

@Named("clockBean")
@ViewScoped
public class ClockBean implements Serializable {
    private static final long serialVersionUID = 1243214234234L;

    public String getCurrentDateTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
//        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        return formatter.format(new Date());
    }
}
