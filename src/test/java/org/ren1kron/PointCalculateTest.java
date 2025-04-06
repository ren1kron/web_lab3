package org.ren1kron;

import org.junit.jupiter.api.Test;
import org.ren1kron.models.Point;

import static org.junit.jupiter.api.Assertions.*;

public class PointCalculateTest {

    /**
     * Проверяем попадание в квадратную область (1-я четверть):
     * x > 0, y > 0 и x, y не больше r.
     */
    @Test
    public void testSquareAreaHit() {
        Point point = new Point(1, 1, 2);
        assertTrue(point.isHit(), "Точка (1,1) при r=2 должна попадать в квадратную область");
    }

    /**
     * Проверяем попадание в круглую область (2-я четверть):
     * x < 0, y > 0 и x^2+y^2 <= r^2.
     */
    @Test
    public void testCircleAreaHit() {
        Point point = new Point(-1, 1, 2);
        assertTrue(point.isHit(), "Точка (-1,1) при r=2 должна попадать в круглую область");
    }

    /**
     * Проверяем попадание в треугольную область (3-я четверть):
     * x < 0, y < 0 и y >= -x - r.
     */
    @Test
    public void testTriangleAreaHit() {
        Point point = new Point(-1, -1, 2);
        assertTrue(point.isHit(), "Точка (-1,-1) при r=2 должна попадать в треугольную область");
    }

    /**
     * Проверяем критическую точку по вертикальной оси: x == 0 и y между -r и r.
     */
    @Test
    public void testVerticalCriticalHit() {
        Point point = new Point(0, 1, 2);
        assertTrue(point.isHit(), "Точка (0,1) при r=2 должна считаться попаданием (вертикальная критическая линия)");
    }

    /**
     * Проверяем критическую точку по горизонтальной оси: y == 0 и x между -r и r.
     */
    @Test
    public void testHorizontalCriticalHit() {
        Point point = new Point(1, 0, 2);
        assertTrue(point.isHit(), "Точка (1,0) при r=2 должна считаться попаданием (горизонтальная критическая линия)");
    }

    /**
     * Проверяем ситуацию, когда точка не попадает ни в одну из областей.
     */
    @Test
    public void testMiss() {
        Point point = new Point(3, 3, 2);
        assertFalse(point.isHit(), "Точка (3,3) при r=2 не должна попадать ни в одну из областей");
    }
}
