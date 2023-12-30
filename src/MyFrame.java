import javax.swing.*;
import java.awt.*;

public class MyFrame extends JFrame {
    MyFrame(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.add(new CoreGame());
        ImageIcon img=new ImageIcon("snake.png");
        this.setIconImage(img.getImage());
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
