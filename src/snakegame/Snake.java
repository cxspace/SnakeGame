package snakegame;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

/**
 * Created by cxspace on 16-6-11.
 */
public class Snake implements Data{

    //实例化一个可以任意增大或减小组成蛇元素的个数
    public ArrayList array = new ArrayList();

    //蛇初始化方向
    public int currentFlag = UPFLAG;


    //蛇尾坐标
    public Point tair;

    public boolean lifeFlag;

    //构造,初始化蛇
    public Snake()
    {
        //蛇头
        array.add(new Point(10,10));
        array.add(new Point(10,11));
        array.add(new Point(10,12));
        array.add(new Point(10,13));
        //蛇尾
        array.add(new Point(10,14));

        //初始化方向
        currentFlag = UPFLAG;
        lifeFlag = true;
    }

    public void move()
    {
        tair = (Point)array.get(array.size()-1); //拿到蛇尾部的坐标

        int len = array.size() - 2 ; //蛇身体 = 总长 - 2

        //移动array数组元素移动蛇的身体

        for (int i = len ; i >= 0 ; i--)
        {
            Point leftPoint = (Point)array.get(i);
            Point rightPoint = (Point)array.get(i+1);

            rightPoint.x = leftPoint.x;
            rightPoint.y = leftPoint.y;
        }
    }

    public void moveHeadLeft(){

        if (currentFlag == RIGHTFLAG || currentFlag == LEFTFLAG)
        {
            return;
        }

        move();

        Point point = (Point)(array.get(0)); //拿到蛇头坐标

        point.x = point.x - 1;
        point.y = point.y;

        if(point.x < LEFT) //判断x-1后是否出界
        {
            lifeFlag = false;
        }

        currentFlag = LEFTFLAG; //当前朝向为向左

    }

    public void moveHeadRight()
    {
        if(currentFlag == LEFTFLAG || currentFlag == RIGHTFLAG)
        {
            return;
        }
        move();

        Point point = (Point)(array.get(0)); //拿到移动前蛇头坐标

        point.x = point.x + 1;
        point.y = point.y;

        if (point.x > RIGHT)
        {
            lifeFlag = false;
        }

        currentFlag = RIGHTFLAG;
    }

    public void moveHeadUp()
    {
        if(currentFlag == DOWNFLAG || currentFlag == UPFLAG)
        {
            return;
        }

        move();

        //拿到移动前蛇头坐标值
        Point point = (Point)(array.get(0));
        //根据变化改变相应的蛇头坐标
        point.x = point.x;
        point.y = point.y - 1;

        if(point.y < UP)
        {
            lifeFlag = false;
        }

        currentFlag = UPFLAG;
    }


    public void moveHeadDown() {
        if (currentFlag == UPFLAG || currentFlag == DOWNFLAG) {
            return;
        }

        move();

        Point point = (Point) (array.get(0));

        point.x = point.x;
        point.y = point.y + 1;

        if (point.y > DOWN)
        {
            lifeFlag = false;
        }

        currentFlag = DOWNFLAG;

    }


    //返回蛇头在界面中绝对坐标
    public  Point clientPoint(int x , int y)
    {
        int clientX = ACTUALLEFT + x * LONG;
        int clientY = ACTUALUP + y * LONG;

        return new Point(clientX , clientY);
    }


    public  Point clientPoint(Point point)
    {
        return clientPoint(point.x , point.y);
    }



    private void drawHead(Graphics2D g2)
    {
        int x = 0;
        int y = 0;

        Point point = (Point)array.get(0);

        //获取蛇头绝对坐标
        point = clientPoint(point);
        x = point.x;
        y = point.y;

        /*
        定义三个元素的x,y坐标数组，蛇头由三个顶点构成一个等要三角形
         */

        int clientX[] = new int [4];
        int clientY[] = new int [4];

         switch (currentFlag)
         {
             case RIGHTFLAG:

                 /*
                     *     ( x , y )
                     * *
                   *** * *      ( x + LONG , y + LONG / 2)
                     * *
                     *     ( x , y + LONG )
                  */


                 //上顶点

                 /*
                 clientX[0] = x;
                 clientY[0] = y;



                 //右顶点
                 clientX[1] = x + LONG;
                 clientY[1] = y ;

                 //下顶点
                 clientX[2] = x + LONG;
                 clientY[2] = y + LONG;

                 clientX[3] = x;
                 clientY[3] = y + LONG;

             //    System.out.println(x+":"+y);


                 break;
              */
             case DOWNFLAG:

                 /*
                           *
             ( x , y ) * * * * *  ( x + LONG , y)
                         * * *
                           *   ( x + LONG / 2 , y + LONG)
                  */

                 /*
                 clientX[0] = x;
                 clientX[0] = y;

                 clientX[1] = x - LONG;
                 clientY[1] = y ;

                 clientX[2] = x - LONG;
                 clientY[2] = y - LONG;

                 clientX[3] = x ;
                 clientY[3] = y - LONG;
             */
                 break;
               /*
                       同上，定位以左上角坐标为 X Y
               */


             case LEFTFLAG:
          /*
                 clientX[0] = x;
                 clientY[0] = y + LONG / 2;

                 clientX[1] = x + LONG;
                 clientY[1] = y;

                 clientX[2] = x + LONG;
                 clientY[2] = y + LONG;
              */
             break;

             case UPFLAG:
             /*
                 //只要找到三点的位置，在client数组中存储的顺序不是问题
                 clientX[0] = x + LONG / 2;
                 clientY[0] = y;

                 clientX[1] = x + LONG;
                 clientY[1] = y + LONG;

                 clientX[2] = x;
                 clientY[2] = y + LONG;
                 */
             break;

             default:
                 break;

         }

        //调用API画出蛇头
        /*
            Polygon 绘制多边形的类

            够造方法中 第一个参数是存储各点 x 坐标的数组
                      第二个参数是存储各点 y 坐标的数组
                      第三个坐标是多边形顶点的个数

         */

        Polygon polygon = new Polygon(clientX , clientY , clientX.length);

        g2.setColor(Color.RED);
        g2.fill(polygon);  //填充蛇头
        g2.setColor(Color.BLACK);
        g2.draw(polygon);  //绘制边框

    }


    private  void drawBody(Graphics2D g2)
    {
        for(int i = 1 ; i < array.size() ; i++)
        {
            //遍历蛇身坐标值
            Point point = (Point)(array.get(i));

            //获取绝对坐标值
            point = clientPoint(point);

            //画矩形的类
            Rectangle2D.Double rect = new Rectangle2D.Double(point.x , point.y , LONG , LONG);

            g2.setColor(Color.GREEN);
            g2.fill(rect);

            g2.setColor(Color.BLACK);
            g2.draw(rect);

        }
    }

    public boolean isLife()
    {
        return lifeFlag;
    }

     //增长蛇身
    public void addNode()
    {
        array.add(new Point(tair.x , tair.y));
    }

    //蛇向右移动的逻辑
    public void moveRight()
    {
        //拿到蛇尾
        tair = (Point)array.get(array.size()-1);

        //拿到蛇头
        Point point = (Point)array.get(0);

        //设置右移之后蛇头坐标值
        int tempX = point.x + 1;
        int tempY = point.y;

        //蛇头是否碰撞到蛇身体的标志
        boolean flag = false;


        //判断蛇头坐标是否碰到蛇的身体
        for (int i = 1 ;  i < array.size() ; i++)
        {
            Point tempPoint = (Point)array.get(i);

            if(tempX == tempPoint.x && tempY == tempPoint.y)
            {
                flag = true;
                break;
            }

        }

        //判断蛇头坐标tempX是否超出窗口坐标RIGHT，且蛇头是否触碰到蛇的身体

        if (tempX <= RIGHT && !flag)
        {
            for (int i = array.size() - 1 ; i > 0 ; i --)
            {
                Point point1 = (Point)(array.get(i - 1));
                Point point2 = (Point)(array.get(i));

                point2.x = point1.x;
                point2.y = point1.y;

                //蛇头右移

            }

            point.x = tempX;
        }
        else {
            lifeFlag = false; //否则，说明蛇已经死亡

        }


    }


    public void moveDown()
    {
        tair = (Point)array.get(array.size()-1);

        Point point = (Point)array.get(0);
        int tempX = point.x;
        int tempY = point.y + 1;

        boolean flag = false;

        //同上逻辑
        for (int i = 1 ; i < array.size(); i ++)
        {
            Point tempPoint = (Point)array.get(i);

            if (tempX == tempPoint.x && tempY == tempPoint.y)
            {
                flag = true;
                break;
            }
        }

        if (tempY <= DOWN && !flag)
        {
            for (int i = array.size() - 1 ; i > 0 ; i --)
            {
                Point point1 = (Point)(array.get(i-1));
                Point point2 = (Point)(array.get(i));

                point2.x = point1.x;
                point2.y = point1.y;

            }

            //蛇头坐标y值改变
            point.y = tempY;
        }
        else {
            lifeFlag = false;
        }
    }

    public void moveLeft()
    {
        tair = (Point) array.get(array.size() - 1);
        Point point = (Point)array.get(0);

        int tempX = point.x - 1;
        int tempY = point.y;

        boolean flag = false;

        for (int i = 1 ; i < array.size() ; i ++)
        {
            Point tempPoint = (Point)array.get(i);
            if (tempX == tempPoint.x && tempY == tempPoint.y)
            {
                flag = true;
                break;
            }
        }

        if(tempX >= LEFT && !flag)
        {

            for (int i = array.size() - 1 ; i > 0 ; i--)
            {
                Point point1 = (Point) (array.get(i - 1));
                Point point2 = (Point) (array.get(i));

                point2.x = point1.x;
                point2.y = point1.y;
            }

            point.x = tempX;

        }else {
            lifeFlag = false;
        }
    }

    public void moveUp()
    {
        tair = (Point)array.get(array.size()-1);
        Point point = (Point)array.get(0);

        int tempX = point.x;
        int tempY = point.y - 1;

        boolean flag = false;

        for (int i = 1 ; i < array.size() ; i++)
        {
            Point tempPoint = (Point)array.get(i);
            if (tempX == tempPoint.x && tempY == tempPoint.y)
            {
                flag = true;
                break;
            }
        }

        if (tempY >= UP && !flag)
        {
            for (int i = array.size() - 1 ; i > 0 ; i--)
            {
                Point point1 = (Point)(array.get(i-1));
                Point point2 = (Point)(array.get(i));

                point2.x = point1.x;
                point2.y = point1.y;

            }

            point.y = tempY;
        }
        else {
            lifeFlag = false;
        }
    }

    public void draw(Graphics2D g2)
    {
        drawHead(g2);
        drawBody(g2);
    }
}
