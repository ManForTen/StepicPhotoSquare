import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

public class Main extends Canvas {
    static int width = 300, height = 300;

    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g; // Сглаживание
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        try {
            URLConnection openConnection = new URL("https://i.downloadatoz.com/download/icon2/1/1/1/24bbf1ea7ddad40c6c8f520b40109011.jpg").openConnection();
            BufferedImage image = ImageIO.read(openConnection.getInputStream());
            int imageWidth = image.getWidth(), imageHeight=image.getHeight();
            int y = 50;
            int x = y;
            int d = y;
            while (true) { // Двигаемся по квадрату
                if (x < width-d-imageWidth && y == d)
                    x++;
                if (x == width-d-imageWidth && y < height-d-imageHeight)
                    y++;
                if (x > d && y == height-d-imageHeight)
                    x--;
                if (x == d && y > d)
                    y--;
                g2.drawImage(image, x, y, this);
                try
                {
                    Thread.sleep(50);
                }
                catch(InterruptedException ex)
                {
                    Thread.currentThread().interrupt();
                }
                g2.clearRect(x, y, imageWidth, imageHeight);

            }
        } catch (IOException e) {
            g.drawString("Ошибка", 10,10);
        }
    }

    public static void main(String[] args){
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Машина");
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setBounds(dim.width/2-width/2,dim.height/2-height/2, width,height+30);
        Main m = new Main();
        frame.add(m);
        frame.setVisible(true);
    }
}