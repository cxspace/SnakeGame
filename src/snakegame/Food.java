package snakegame;

import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * Created by cxspace on 16-6-11.
 */
public class Food implements Data{

    private  int x;
    private  int y;
    private boolean isLife;

    //构造食物
    public Food()
    {
        //随机产生坐标
        x = (int)(Math.random() * RIGHT);

        y = (int)(Math.random() * DOWN);

        isLife = true;
    }

    //画出食物
    public void draw(Graphics2D g2)
    {
        Point point = absolutePoint(x,y);


        Rectangle2D.Double rect = new Rectangle2D.Double(point.x , point.y , LONG , LONG);

        g2.setColor(Color.YELLOW);
        g2.fill(rect);  //使用黄色填充

        g2.setColor(Color.BLACK);
        g2.draw(rect);  //使用黑色描轮廓

    }


    //构造食物在界面中的绝对位置
    public Point absolutePoint(int x , int y)
    {
        //绝对左值 = 实际左值 + x个食物单位长度
        int absoluteX = ACTUALLEFT +  x * LONG;
        int absoluteY = ACTUALUP + y * LONG;

        return  new Point(absoluteX, absoluteY);

    }

}
