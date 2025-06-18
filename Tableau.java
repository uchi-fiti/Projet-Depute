package affichage;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.*;
import javax.swing.*;

public class Tableau extends JPanel{
    Map <String, Integer> deputes;
    public Tableau(Map <String, Integer> d)
    {
        this.deputes = d;
    }
    public void paint(Graphics gP) {
        super.paint(gP);
        Graphics2D g = (Graphics2D) gP;
    
        g.setFont(new Font("Arial", Font.PLAIN, 14));
        g.setColor(Color.BLACK);
    
        int xStart = 20;
        int yStart = 20;
        int columnWidth = (getWidth() - 40) / 2;
        int rowHeight = 25; // fixed height per row
    
        int numRows = deputes.size() + 1; // +1 for header
        int tableHeight = rowHeight * numRows;
        int tableWidth = columnWidth * 2;
    
        // Draw outer table rectangle
        g.drawRect(xStart, yStart, tableWidth, tableHeight);
    
        // Draw horizontal lines (including top and bottom)
        for (int i = 0; i <= numRows; i++) {
            int y = yStart + i * rowHeight;
            g.drawLine(xStart, y, xStart + tableWidth, y);
        }
    
        // Draw vertical line separating columns
        g.drawLine(xStart + columnWidth, yStart, xStart + columnWidth, yStart + tableHeight);
    
        // Draw header
        g.drawString("Deputes: ", xStart + 10, yStart + rowHeight - 8);
        g.drawString("Nombre de votes: ", xStart + columnWidth + 10, yStart + rowHeight - 8);
    
        // Draw data rows
        int rowIndex = 1;
        for (Map.Entry<String, Integer> entry : deputes.entrySet()) {
            int y = yStart + rowIndex * rowHeight + rowHeight - 8;
            g.drawString(entry.getKey(), xStart + 10, y);
            g.drawString(String.valueOf(entry.getValue()), xStart + columnWidth + 10, y);
            rowIndex++;
        }
    }
}
