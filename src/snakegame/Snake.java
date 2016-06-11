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

}
