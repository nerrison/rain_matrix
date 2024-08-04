import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class matrixRain extends JPanel {
    private final int fontSize = 16;
    private final String chars = "0123456789/*-+/<>?;:[]~!@#$%^&*()+=abcdefghijklmnopqrstuvwxyz";
    private final int columns;
    private final int[] drops;
    private final int[] nameDrops;
    private final Random random;
    private final Timer timer;
    private boolean nameFallen = false;
    private final String authorName = "Nerrison";

    public matrixRain() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = screenSize.width;
        int height = screenSize.height;

        columns = width / fontSize;
        drops = new int[columns];
        nameDrops = new int[columns]; // Array for author's name drops
        random = new Random();

        setPreferredSize(new Dimension(width, height));
        setBackground(Color.BLACK);
        setDoubleBuffered(true); // Enable double buffering

        // Timer to control the animation frame rate
        timer = new Timer(50, e -> repaint());
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Draw a semi-transparent background to create a trailing effect
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.05f));
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, getWidth(), getHeight());

        if (!nameFallen) {
            // Draw the author's name falling from the top
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
            g2d.setColor(Color.GREEN);
            g2d.setFont(new Font("Monospaced", Font.PLAIN, fontSize));

            for (int x = 0; x < columns; x++) {
                int y = nameDrops[x] * fontSize;
                char text = authorName.charAt(x % authorName.length()); // Use modulus to cycle through the name
                g2d.drawString(String.valueOf(text), x * fontSize, y);

                if (y >= getHeight() || random.nextFloat() > 0.975) {
                    nameDrops[x] = 0;
                } else {
                    nameDrops[x]++;
                }
            }

            // Check if all parts of the name have fallen
            if (nameDrops[0] * fontSize >= getHeight()) {
                nameFallen = true;
            }
        } else {
            // Draw the characters for Matrix Rain
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
            g2d.setColor(Color.GREEN);
            g2d.setFont(new Font("Monospaced", Font.PLAIN, fontSize));

            for (int x = 0; x < columns; x++) {
                int y = drops[x] * fontSize;
                char text = chars.charAt(random.nextInt(chars.length()));
                g2d.drawString(String.valueOf(text), x * fontSize, y);

                if (y >= getHeight() || random.nextFloat() > 0.975) {
                    drops[x] = 0;
                } else {
                    drops[x]++;
                }
            }
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Matrix Rain Effect");
        matrixRain matrixRain = new matrixRain();

        // Set a medium window size
        frame.setSize(800, 600);

        frame.add(matrixRain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // Center the window on the screen
        frame.setVisible(true);
    }
}
