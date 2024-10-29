package org.ren1kron.beans;

import lombok.Data;

import java.io.Serializable;

@Data
public class RCoordinateBean implements Serializable {
    private float r = 0;

    public void validate() {

    }
}
