import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

class CoreGame extends JPanel implements ActionListener, KeyListener {

    static final int swidth=600;
    static final int sheight=600;
    static final int usize=25;
    static final int gunits=(swidth+sheight)/usize;
    static final int delay=150;
    Timer t;
    Random r;
    int ax,ay,bodyparts=6,score=0;
    char dir='R';
    boolean running=false;
    int x[]=new int[gunits];
    int y[]=new int[gunits];
    JLabel label;

    CoreGame(){
       this.setPreferredSize(new Dimension(swidth,sheight));
       this.setBackground(Color.BLACK);
       this.setFocusable(true);
       this.setVisible(true);
       this.addKeyListener(this);
       r=new Random();
       label=new JLabel("Score "+score);
       label.setFont(new Font("MV Boli",Font.BOLD,26));
       label.setForeground(Color.lightGray);
       label.setHorizontalAlignment(JLabel.CENTER);
       label.setHorizontalTextPosition(JLabel.CENTER);

       this.add(label);
       start();
    }

    public void start(){
        running=true;
        t=new Timer(delay,this);
        newapple();
        t.start();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g){

       if(running) {
//            for (int i = 0; i < sheight; i++) {
//                g.drawLine(i * usize, 0, i * usize, sheight);
//                g.drawLine(0, i * usize, swidth, i * usize);
//            }
            g.setColor(Color.red);
            g.fillOval(ax, ay, usize, usize);

            for (int i = 0; i < bodyparts; i++) {
                if (i == 0) {
                    g.setColor(Color.MAGENTA);
                    g.fillRect(x[0], y[0], usize, usize);
                } else {
                    g.setColor(Color.green);
                    g.fillRect(x[i], y[i], usize, usize);
                }
                if(!running)start();

            }
        }
    }

    public void move(){
        for(int i=bodyparts;i>0;i--){
            x[i]=x[i-1];
            y[i]=y[i-1];
        }
        switch (dir){
            case 'U':
                y[0]=y[0]-usize;
                break;
            case 'D':
                y[0]=y[0]+usize;
                break;
            case 'L':
                x[0]=x[0]-usize;
                break;
            case 'R':
                x[0]=x[0]+usize;
                break;
            case ' ':
                if(!running)start();
                break;
        }
    }

    public void newapple(){
        ax=r.nextInt((int)(swidth/usize))*usize;
//        System.out.println(ax/usize);
//        System.out.println((ax/usize)*(usize/swidth));
        ay=r.nextInt((int)(swidth/usize))*usize;
//        System.out.println(ay/usize);
//        System.out.println((ay/usize)*(usize/swidth));
    }

    public void checkapple(){
        if(x[0]==ax && y[0]==ay){
            score++;
            bodyparts++;
            System.out.println(score);
            label.setText("Score "+score);
            newapple();
        }
    }

    public void checkcollision(){
        for(int i=bodyparts;i>0;i--){
            if(x[0]==x[i] && y[0]==y[i]){
                running=false;
            }
        }

        if(x[0]>swidth)running=false;
        if(x[0]<0)running=false;
        if(y[0]>sheight)running=false;
        if(y[0]<0)running=false;

        if(!running){
            t.stop();
            running=false;
            gameover();
        }
    }

    public void gameover(){
        if(!running){
            System.out.println("Stopped");
            this.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 300));
            label.setText("Game Over !!! And Your Score is " + score);
            label.setForeground(Color.BLUE);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(running){
            move();
            checkapple();
            checkcollision();
        }
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        switch (e.getKeyChar()){
            case 'A':
            case 'a':
                if(dir!='R'){
                    dir='L';
                }
                break;
            case 'W':
            case 'w':
                if(dir!='D'){
                    dir='U';
                }
                break;
            case 'D':
            case 'd':
                if(dir!='L'){
                    dir='R';
                }
                break;
            case 'S':
            case 's':
                if(dir!='U'){
                    dir='D';
                }
                break;
            case 'r':
                start();
                break;
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()){
            case 37:
                if(dir!='R'){
                    dir='L';
                }
                break;
            case 38:
                if(dir!='D'){
                    dir='U';
                }
                break;
            case 39:
                if(dir!='L'){
                    dir='R';
                }
                break;
            case 40:
                if(dir!='U'){
                    dir='D';
                }
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}
}