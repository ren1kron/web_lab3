package org.ren1kron.models;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor

@Entity
public class Point implements Serializable {
    private static final long serialVersionUID = 123L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private float x;
    private float y;
    private float r;

    @Column(name = "hit_time")
    private Date hitTime;

    private boolean hit;

    public Point(float x, float y, float r) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.hitTime = new Date();
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
        if (x == 0 && y >= -r && y <= r) {
            return true;
        }
        if (y == 0 && x >= -r && x <= r) {
            return true;
        }

        return false;
    }
}
