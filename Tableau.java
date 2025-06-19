package affichage;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.*;
import javax.swing.*;

public class Tableau extends JPanel {
    Map <String, Map<String, Integer>> deputes_elus;
    public Tableau(Map <String, Map <String, Integer>> d)
    {
        this.deputes_elus = d;
    }
    public void paint(Graphics gP) {
        super.paint(gP);
        Graphics2D g = (Graphics2D) gP;
    
        int x = 50;
        int y = 50;
        int rowHeight = 30;
        int colWidth = 1366 / 3;
    
        g.setFont(new Font("Arial", Font.BOLD, 16));
        g.setColor(Color.BLACK);
        g.drawString("District", x, y);
        g.drawString("Député élu", x + colWidth, y);
        g.drawString("Nombre de votes", x + 2 * colWidth, y);
    
        y += rowHeight;
        g.setFont(new Font("Arial", Font.PLAIN, 14));
    
        System.out.println("Tableau drawn!");
    
        for (Map.Entry<String, Map<String, Integer>> entry : deputes_elus.entrySet()) {
            String district = entry.getKey();
            Map<String, Integer> elusMap = entry.getValue();
    
            boolean firstRow = true; 
    
            for (Map.Entry<String, Integer> eluEntry : elusMap.entrySet()) {
                String deputy = eluEntry.getKey();
                int votes = eluEntry.getValue();
    
                g.drawString(firstRow ? district : "", x, y);
                firstRow = false;
    
                g.drawString(deputy, x + colWidth, y);
                g.drawString(String.valueOf(votes), x + 2 * colWidth, y);
    
                y += rowHeight;
            }
        }
    }
}
