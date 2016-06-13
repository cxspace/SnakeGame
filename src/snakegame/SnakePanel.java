package snakegame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;

/**
 * Created by cxspace on 16-6-13.
 */
public class SnakePanel extends JPanel implements Data {

    private Snake snake;
    private Timer timer;
    private  Food food;
    private SnakeFrame frame;
    private boolean endFlag = false;

    public SnakePanel(SnakeFrame frame)
    {
      //将传入的frame赋值给本例的frame
        this.frame = frame;

        init();

        addKeyListener(new KeyHandler());

        setFocusable(true); //设置允许获得焦点

        timer = new Timer(1000, new TimerAction());

        timer.start();
        this.setBackground(Color.CYAN);

    }

    public void  init()
    {
        snake = new Snake();
        food = new Food();
        endFlag = false;
    }

    //重写JPanel父类的paintComponet方法，来实现自己的绘制方案
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;
        drawFrame(g2);

        if (snake.isLife())
        {
            snake.draw(g2);
            food.draw(g2);
        }

    }


    public void drawFrame(Graphics2D g2)
    {
        Rectangle2D.Double frame = new Rectangle2D.Double(15,10,300,300);

        g2.setColor(Color.ORANGE);
        g2.draw(frame);
    }


    public void eatFood()
    {
        Point point = (Point)snake.array.get(0);

        if (point.x == food.x && point.y == food.y)
        {
            food = new Food();
            snake.addNode();
        }
    }

    private class KeyHandler implements KeyListener
    {
        @Override
        public void keyPressed(KeyEvent e) {
            if (!frame.runFlag)
            {
                return;
            }

            int keyCode = e.getKeyCode();

            switch (keyCode)
            {
                case KeyEvent.VK_LEFT:
                    snake.moveHeadLeft();
                    break;
                case KeyEvent.VK_RIGHT:
                    snake.moveHeadRight();
                    break;
                case KeyEvent.VK_UP:
                    snake.moveHeadUp();
                    break;
                case KeyEvent.VK_DOWN:
                    snake.moveHeadDown();
                    break;

                default:
                    break;
            }

            eatFood();

            repaint();
        }



        @Override
        public void keyReleased(KeyEvent e) {

        }

        @Override
        public void keyTyped(KeyEvent e) {

        }
    }

    private class TimerAction implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(!frame.runFlag)
            {
                return;
            }

            if (!endFlag&&!snake.isLife()) {
                endFlag = true;

                int result = JOptionPane.showConfirmDialog(null, "Game over! Continue?", "贪吃蛇游戏", JOptionPane.YES_NO_OPTION);

                if (result == JOptionPane.YES_OPTION) {
                    init();
                } else {
                    System.exit(0);
                }
            }
                timer.setDelay(1000 - 200*(frame.speedFlag - 1));

                switch (snake.currentFlag)
                {
                    case RIGHTFLAG:
                        snake.moveRight();

                        break;
                    case LEFTFLAG:
                        snake.moveLeft();
                        break;
                    case UPFLAG:
                        snake.moveUp();
                        break;
                    case DOWNFLAG:
                        snake.moveDown();
                        break;
                    default:
                        break;
                }

                eatFood();

                repaint();
        }

    }

}
