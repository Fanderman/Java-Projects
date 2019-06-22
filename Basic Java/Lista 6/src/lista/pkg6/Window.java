/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lista.pkg6;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.*;

/**
 *
 * @author jacek
 */
public class Window {
    
    public Window (int n, int size) {
        Frame frame = new Frame("Labyrinth");

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });

        Labyrinth lab = new Labyrinth(n);
        Canvas window = new Screen(size, lab);
        frame.add(window);
        window.addKeyListener(new KeyAdapter () {
            @Override
            public void keyPressed (KeyEvent event) {
                System.out.println("a");
                lab.clearUpdate();
                System.out.println("a");
                lab.move(event.getKeyChar());
                System.out.println("a");
                window.repaint(); 
                System.out.println("a");
            }
        });
        
        frame.setSize(n*size + 16, n*size + 38); // +22, bo top bar MacOS też się wlicza
        frame.setVisible(true);
    }
    
}

class Screen extends Canvas{
    
    private final int size;
    private final Labyrinth lab;
    private BufferedImage im;
    
    // bezczelnie ukradzione z https://memorynotfound.com/java-resize-image-fixed-width-height-example/
    private static BufferedImage resize(BufferedImage img, int height, int width) { 
        Image tmp = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resized.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        return resized;
    }
    
    public Screen (int size, Labyrinth lab) {
        this.size = size;
        this.lab = lab;
        try {
            this.im = resize(ImageIO.read(new File("Art.png")), size, size);
        } 
        catch (Exception e) {
            System.err.println(e.getStackTrace()); 
            System.exit(1);
        }
    }
    
    private void draw (Graphics g, int x, int y, Color c) {
        x *= size;
        y *= size;
        g.setColor(c);
        g.fillRect(x, y, size, size);
    }
    
    @Override
    public void update(Graphics g) {
        System.out.println("a");
        paint(g);
    }

    @Override
    public void paint (Graphics g) {
        for (Object e : lab.update()){
            Pair ref = (Pair)e;
            int x = (int)ref.getFirst();
            int y = (int)ref.getSecond();
            if(lab.tab[x][y] == 1)
                draw(g, x, y, Color.WHITE);
            if(lab.tab[x][y] == 2)
                draw(g, x, y, Color.BLACK);
        }
        g.drawImage(im, size * lab.px, size * lab.py, null);
    }
    
}
