/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import icons.Icon;

/**
 *
 * @author Suthasoma
 */
public class ButtonRounded extends JButton{

    /**
     * @return the over
     */
    public boolean isOver() {
        return over;
    }

    /**
     * @param over the over to set
     */
    public void setOver(boolean over) {
        this.over = over;
    }


    /**
     * @return the radius
     */
    public int getRadius() {
        return radius;
    }

    /**
     * @param radius the radius to set
     */
    public void setRadius(int radius) {
        this.radius = radius;
    }
    
    public ButtonRounded() {
//        setColor(Color.WHITE);
        setBackgroundImg(Icon.BUTTON1);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorder(null);
        
        addMouseListener( new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent me) {
                over = true;
            }

            @Override
            public void mouseExited(MouseEvent me) {
                setBackgroundImg(Icon.BUTTON1);
                over = false;
            }

            @Override
            public void mousePressed(MouseEvent me) {
                setBackgroundImg(Icon.BUTTON2);
            }

            @Override
            public void mouseReleased(MouseEvent me) {
                setBackgroundImg(Icon.BUTTON1);
            }

            public void mouseClicked(MouseEvent me){
                setBackgroundImg(Icon.BUTTON2);
            }

        });
    }
    
    private boolean over;
    private int radius = 20;
    private Image backgroundImage;

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (backgroundImage != null) {
            g2.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }

        g2.setColor(getForeground());
        g2.setFont(getFont());
        FontMetrics fm = g2.getFontMetrics();
        Rectangle r = getBounds();
        String text = getText();
        int x = (r.width - fm.stringWidth(text)) / 2;
        int y = (r.height - fm.getHeight()) / 2 + fm.getAscent();
        g2.drawString(text, x, y);
    }

    public void setBackgroundImg(Image image){
        backgroundImage = image;
        repaint();
    }
}
