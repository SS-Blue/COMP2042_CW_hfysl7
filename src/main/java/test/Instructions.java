package test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;


public class Instructions extends JComponent implements MouseListener, MouseMotionListener {

    private static final String TITLE = "How To Play";
    private static final String INSTRUCTION_1 = "1. Press SPACEBAR to start the game.";
    private static final String INSTRUCTION_2 = "2. Use A and D to move left and right.";
    private static final String INSTRUCTION_3 = "3. Press ESC to go to menu.";
    private static final String INSTRUCTION_4 = "4. ENJOY THE GAME! :)";
    private static final String START_TEXT = "Play";
    private static final String RETURN_TEXT = "Back";

    private static final Color BG_COLOR = new Color(60, 225, 240); //light blue
    private static final Color BORDER_COLOR = new Color(65, 15, 185); //purplish blue
    private static final Color DASH_BORDER_COLOR = new Color(150, 165, 255); //light purple
    private static final Color TEXT_COLOR = Color.WHITE; //white
    private static final Color CLICKED_BUTTON_COLOR = BG_COLOR.brighter();
    private static final Color CLICKED_TEXT = Color.WHITE;
    private static final int BORDER_SIZE = 5;
    private static final float[] DASHES = {12, 6};

    private Rectangle menuFace;
    private Rectangle startButton;
    private Rectangle returnButton;


    private BasicStroke borderStoke;
    private BasicStroke borderStoke_noDashes;

    private Font titleFont;
    private Font instruct1Font;
    private Font instruct2Font;
    private Font instruct3Font;
    private Font instruct4Font;
    private Font buttonFont;

    private GameFrame owner;

    private boolean startClicked;
    private boolean returnClicked;


    public Instructions(GameFrame owner, Dimension area) {

        this.setFocusable(true);
        this.requestFocusInWindow();

        this.addMouseListener(this);
        this.addMouseMotionListener(this);

        this.owner = owner;


        menuFace = new Rectangle(new Point(0, 0), area);
        this.setPreferredSize(area);

        Dimension btnDim = new Dimension(area.width / 3, area.height / 12);
        startButton = new Rectangle(btnDim);
        returnButton = new Rectangle(btnDim);

        borderStoke = new BasicStroke(BORDER_SIZE, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 0, DASHES, 0);
        borderStoke_noDashes = new BasicStroke(BORDER_SIZE, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);

        titleFont = new Font("Noto Mono", Font.PLAIN, 34);
        instruct1Font = new Font("Noto Mono", Font.PLAIN, 19);
        instruct2Font = new Font("Noto Mono", Font.PLAIN, 19);
        instruct3Font = new Font("Noto Mono", Font.PLAIN, 19);
        instruct4Font = new Font("Noto Mono", Font.PLAIN, 19);
        buttonFont = new Font("Monospaced", Font.PLAIN, startButton.height - 2);


    }


    public void paint(Graphics g) {
        drawMenu((Graphics2D) g);
    }


    public void drawMenu(Graphics2D g2d) {

        drawContainer(g2d);

        /*
        all the following method calls need a relative
        painting directly into the HomeMenu rectangle,
        so the translation is made here so the other methods do not do that.
         */
        Color prevColor = g2d.getColor();
        Font prevFont = g2d.getFont();

        double x = menuFace.getX();
        double y = menuFace.getY();

        g2d.translate(x, y);

        //method calls
        drawText(g2d);
        drawButton(g2d);
        //end of method calls

        g2d.translate(-x, -y);
        g2d.setFont(prevFont);
        g2d.setColor(prevColor);
    }

    private void drawContainer(Graphics2D g2d) {
        Image picture = Toolkit.getDefaultToolkit().getImage("bear.jpg");

        Color prev = g2d.getColor();

        g2d.setColor(BG_COLOR);
        g2d.fill(menuFace);

        Stroke tmp = g2d.getStroke();

        g2d.setStroke(borderStoke_noDashes);
        g2d.setColor(DASH_BORDER_COLOR);
        g2d.draw(menuFace);

        g2d.setStroke(borderStoke);
        g2d.setColor(BORDER_COLOR);
        g2d.draw(menuFace);

        g2d.setStroke(tmp);

        g2d.setColor(prev);

        g2d.drawImage(picture, 0, 0, this);
    }

    private void drawText(Graphics2D g2d) {

        g2d.setColor(TEXT_COLOR);

        FontRenderContext frc = g2d.getFontRenderContext();

        Rectangle2D titleRect = titleFont.getStringBounds(TITLE, frc);
        Rectangle2D instruct1Rect = instruct1Font.getStringBounds(INSTRUCTION_1, frc);
        Rectangle2D instruct2Rect = instruct2Font.getStringBounds(INSTRUCTION_2, frc);
        Rectangle2D instruct3Rect = instruct3Font.getStringBounds(INSTRUCTION_3, frc);
        Rectangle2D instruct4Rect = instruct4Font.getStringBounds(INSTRUCTION_4, frc);

        int sX, sY;

        sX = (int) (menuFace.getWidth() - titleRect.getWidth()) / 2;
        sY = (int) (menuFace.getHeight() / 6);

        g2d.setFont(titleFont);
        g2d.drawString(TITLE, sX, sY);

        sX = (int) (menuFace.getWidth() - instruct1Rect.getWidth()) / 4;
        sY += (int) instruct1Rect.getHeight() * 1.5;//add 10% of String height between the two strings

        g2d.setFont(instruct1Font);
        g2d.drawString(INSTRUCTION_1, sX, sY);

        sX = (int) (menuFace.getWidth() - instruct2Rect.getWidth()) / 5;
        sY += (int) instruct2Rect.getHeight() * 1.5;

        g2d.setFont(instruct2Font);
        g2d.drawString(INSTRUCTION_2, sX, sY);

        sX = (int) (menuFace.getWidth() - instruct3Rect.getWidth()) / 9;
        sY += (int) instruct3Rect.getHeight() * 1.5;

        g2d.setFont(instruct3Font);
        g2d.drawString(INSTRUCTION_3, sX, sY);

        sX = (int) (menuFace.getWidth() - instruct4Rect.getWidth()) / 13;
        sY += (int) instruct4Rect.getHeight() * 1.5;

        g2d.setFont(instruct4Font);
        g2d.drawString(INSTRUCTION_4, sX, sY);
    }

    private void drawButton(Graphics2D g2d) {

        FontRenderContext frc = g2d.getFontRenderContext();

        Rectangle2D txtRect = buttonFont.getStringBounds(START_TEXT, frc);
        Rectangle2D eTxtRect = buttonFont.getStringBounds(RETURN_TEXT, frc);

        g2d.setFont(buttonFont);

        int x = (menuFace.width - startButton.width) / 2;
        int y = (int) ((menuFace.height - startButton.height) * 0.75);

        startButton.setLocation(x, y);

        x = (int) (startButton.getWidth() - txtRect.getWidth()) / 2;
        y = (int) (startButton.getHeight() - txtRect.getHeight()) / 2;

        x += startButton.x;
        y += startButton.y + (startButton.height * 0.9);


        if (startClicked) {
            Color tmp = g2d.getColor();
            g2d.setColor(CLICKED_BUTTON_COLOR);
            g2d.draw(startButton);
            g2d.setColor(CLICKED_TEXT);
            g2d.drawString(START_TEXT, x, y);
            g2d.setColor(tmp);
        } else {
            g2d.draw(startButton);
            g2d.drawString(START_TEXT, x, y);
        }

        x = startButton.x;
        y = startButton.y;

        y *= 1.2;

        returnButton.setLocation(x, y);

        x = (int) (returnButton.getWidth() - eTxtRect.getWidth()) / 2;
        y = (int) (returnButton.getHeight() - eTxtRect.getHeight()) / 2;

        x += returnButton.x;
        y += returnButton.y + (returnButton.height * 0.9);

        if (returnClicked) {
            Color tmp = g2d.getColor();

            g2d.setColor(CLICKED_BUTTON_COLOR);
            g2d.draw(returnButton);
            g2d.setColor(CLICKED_TEXT);
            g2d.drawString(RETURN_TEXT, x, y);
            g2d.setColor(tmp);
        } else {
            g2d.draw(returnButton);
            g2d.drawString(RETURN_TEXT, x, y);
        }

    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if (startButton.contains(p)) {
            owner.enableGameBoard();
        } else if (returnButton.contains(p)) {
            owner.enableHomeMenu();
        }
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if (startButton.contains(p)) {
            startClicked = true;
            repaint(startButton.x, startButton.y, startButton.width + 1, startButton.height + 1);
        } else if (returnButton.contains(p)) {
            returnClicked = true;
            repaint(returnButton.x, returnButton.y, returnButton.width + 1, returnButton.height + 1);
        }
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        if (startClicked) {
            startClicked = false;
            repaint(startButton.x, startButton.y, startButton.width + 1, startButton.height + 1);
        } else if (returnClicked) {
            returnClicked = false;
            repaint(returnButton.x, returnButton.y, returnButton.width + 1, returnButton.height + 1);
        }
    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }


    @Override
    public void mouseDragged(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if (startButton.contains(p) || returnButton.contains(p))
            this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        else
            this.setCursor(Cursor.getDefaultCursor());

    }
}