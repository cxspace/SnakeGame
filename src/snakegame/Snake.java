package snakegame;

import java.awt.*;
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

        //获取绝对坐标
        point = clientPoint(point);
        x = point.x;
        y = point.y;

        /*
        定义三个元素的x,y坐标数组，蛇头由三个顶点构成一个等要三角形
         */

        

    }







}
