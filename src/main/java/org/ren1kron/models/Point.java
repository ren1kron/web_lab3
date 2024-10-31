package org.ren1kron.models;

import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.time.LocalDateTime;

@Setter
@Getter
public class Point implements Serializable {
    private static final long serialVersionUID = 123L;
    private float x;
    private float y;
    private float r;
    private LocalDateTime hitTime;
    private boolean hit;

    public Point(float x, float y, float r) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.hitTime = LocalDateTime.now();
        this.hit = calculate();
    }

    private boolean calculate() {
        // 1st quarter (square)
        if (x > 0 && y > 0 && x <= r && y <= r) {
            return true;
        }
        // 2nd quarter (circle)
        if (x < 0 && y > 0 && x * x + y * y <= r * r) {
            return true;
        }
        // 3rd quarter (triangle)
        if (x < 0 && y < 0 && y >= -x - r) {
            return true;
        }
        // critical points
        if (x == 0 && (y >= -r || y <= r)) {
            return true;
        }
        if (y == 0 && (x >= -r || x <= r)) {
            return true;
        }

        return false;
    }
}
