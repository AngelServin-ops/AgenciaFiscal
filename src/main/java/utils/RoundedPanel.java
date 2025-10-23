/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JPanel;

/**
 *
 * @author Angel
 */
public class RoundedPanel extends JPanel {

    /**
     * Metodo que obtiene la medida de la esquina redondeada superior izquierda
     *
     * @return la medida de esquina redondeada
     */
    public int getRoundTopLeft() {
        return roundTopLeft;
    }

    /**
     * Metodo que establece la medida de la esquina superior izquierda
     *
     * @param roundTopLeft la medida de esquina recondeada
     */
    public void setRoundTopLeft(int roundTopLeft) {
        this.roundTopLeft = roundTopLeft;
        repaint();
    }

    /**
     * Metodo que obtiene la esquina redondeada superior derecha
     *
     * @return la medida redondeada superiior derecha
     */
    public int getRoundTopRight() {
        return roundTopRight;
    }

    /**
     * Metodo que establece la esquina redondeada superior derecha
     *
     * @param roundTopRight la medida redondeada superior derecha
     */
    public void setRoundTopRight(int roundTopRight) {
        this.roundTopRight = roundTopRight;
        repaint();
    }

    /**
     * Metodo que obtiene el valor del radio de esquina inferior izquierda.
     *
     * @return el valor del radio de esquina inferior izquierda
     */
    public int getRoundBottomLeft() {
        return roundBottomLeft;
    }

    /**
     * Metodo que establece el valor del radio de esquina inferior izquierda.
     *
     * @param roundBottomLeft el valor del radio de esquina inferior izquierda
     */
    public void setRoundBottomLeft(int roundBottomLeft) {
        this.roundBottomLeft = roundBottomLeft;
        repaint();
    }

    /**
     * Metodo que obtiene el radio de la esquina inferior derecha
     *
     * @return el radio de la esquina inferior derecha
     */
    public int getRoundBottomRight() {
        return roundBottomRight;
    }

    /**
     * Metodo que establece el radio de la esquina inferior derecha
     *
     * @param roundBottomRight el radio de la esquina inferior derecha
     */
    public void setRoundBottomRight(int roundBottomRight) {
        this.roundBottomRight = roundBottomRight;
        repaint();
    }
    /**
     * Variables de las esquinas redondeadas
     */
    private int roundTopLeft = 0;
    private int roundTopRight = 0;
    private int roundBottomLeft = 0;
    private int roundBottomRight = 0;

    /**
     * Metodo constructor que al crear un nuevo objeto se llama automaticamente
     * y establece la opacidad del panel
     */
    public RoundedPanel() {
        setOpaque(false);
    }

    /**
     * Metodo que implementa paintComponent para dibujar el panel redondeado
     *
     * @param grphcs los graficos a dibujar
     */
    @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        Area area = new Area(createRoundTopLeft());
        if (roundTopRight > 0) {
            area.intersect(new Area(createRoundTopRight()));
        }
        if (roundBottomLeft > 0) {
            area.intersect(new Area(createRoundBottomLeft()));
        }
        if (roundBottomRight > 0) {
            area.intersect(new Area(createRoundBottomRight()));
        }
        g2.fill(area);
        g2.dispose();
        super.paintComponent(grphcs);
    }

    /**
     * Metodo que devuelve el objeto del tipo Shape que representa la forma de
     * la esquina superior izquierda redondeada de un componente
     *
     * @return el objeto area que representa la forma completa
     */
    private Shape createRoundTopLeft() {
        int width = getWidth();
        int height = getHeight();
        int roundX = Math.min(width, roundTopLeft);
        int roundY = Math.min(height, roundTopLeft);
        Area area = new Area(new RoundRectangle2D.Double(0, 0, width, height, roundX, roundY));
        area.add(new Area(new Rectangle2D.Double(roundX / 2, 0, width - roundX / 2, height)));
        area.add(new Area(new Rectangle2D.Double(0, roundY / 2, width, height - roundY / 2)));
        return area;
    }

    /**
     * Metodo el cual crea una forma "Shape" que representa la esquina superior
     * derecha redondeada del panel
     *
     * @return el objeto area que representa la esquina superior derecha
     * redondeada
     */
    private Shape createRoundTopRight() {
        int width = getWidth();
        int height = getHeight();
        int roundX = Math.min(width, roundTopRight);
        int roundY = Math.min(height, roundTopRight);
        Area area = new Area(new RoundRectangle2D.Double(0, 0, width, height, roundX, roundY));
        area.add(new Area(new Rectangle2D.Double(0, 0, width - roundX / 2, height)));
        area.add(new Area(new Rectangle2D.Double(0, roundY / 2, width, height - roundY / 2)));
        return area;
    }

    /**
     * Metodo el cual crea una forma para dibujar una esquina inferior izquierda
     * redondeada de un panel
     *
     * @return el objeto area que representa la forma esquina inferior izquierda
     * redondeada
     */
    private Shape createRoundBottomLeft() {
        int width = getWidth();
        int height = getHeight();
        int roundX = Math.min(width, roundBottomLeft);
        int roundY = Math.min(height, roundBottomLeft);
        Area area = new Area(new RoundRectangle2D.Double(0, 0, width, height, roundX, roundY));
        area.add(new Area(new Rectangle2D.Double(roundX / 2, 0, width - roundX / 2, height)));
        area.add(new Area(new Rectangle2D.Double(0, 0, width, height - roundY / 2)));
        return area;
    }

    /**
     * Metodo el cual crea y devuelve una forma "shape" que representa la
     * esquina inferior derecha redondeada del panel
     *
     * @return el objeto area que representa la esquina inferior derecha del
     * panel con esquinas redondeadas
     */
    private Shape createRoundBottomRight() {
        int width = getWidth();
        int height = getHeight();
        int roundX = Math.min(width, roundBottomRight);
        int roundY = Math.min(height, roundBottomRight);
        Area area = new Area(new RoundRectangle2D.Double(0, 0, width, height, roundX, roundY));
        area.add(new Area(new Rectangle2D.Double(0, 0, width - roundX / 2, height)));
        area.add(new Area(new Rectangle2D.Double(0, 0, width, height - roundY / 2)));
        return area;
    }
}
