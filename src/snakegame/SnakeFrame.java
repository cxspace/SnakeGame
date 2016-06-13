package snakegame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by cxspace on 16-6-13.
 */
public class SnakeFrame extends JFrame{

    private final int WIDTH = 338;
    private final int HEIGHT = 380;

    private JMenuBar menuBar = new JMenuBar();
    private JMenu setMenu = new JMenu("Set");
    private JMenu helpMenu = new JMenu("help");

    private JMenuItem startMI = new JMenuItem("Start");
    private JMenuItem pauseMI = new JMenuItem("Pause");

    private JMenu speedMenu = new JMenu("Speed");

    private JMenuItem exitMI = new JMenuItem("Exit");
    private JMenuItem aboutMI = new JMenuItem("About");

    private JMenu restMenu = new JMenu("reset");

    private JMenuItem restMI = new JMenuItem("rest");

    private JRadioButtonMenuItem speedMI1 = new JRadioButtonMenuItem("speed1", true);

    private JRadioButtonMenuItem speedMI2 = new JRadioButtonMenuItem("speed2", false);

    private JRadioButtonMenuItem speedMI3 = new JRadioButtonMenuItem("speed3", false);

    private JRadioButtonMenuItem speedMI4 = new JRadioButtonMenuItem("speed4", false);

    private JRadioButtonMenuItem speedMI5 = new JRadioButtonMenuItem("speed5", false);

    public int speedFlag = 1;
    public boolean runFlag = false;
    public SnakePanel panel;


    public SnakeFrame(){
        setTitle("疯狂的贪吃蛇");
        setSize(WIDTH,HEIGHT);
        setResizable(false); //设置用户是否可以调整窗口大小

        setJMenuBar(menuBar);

        setMenu.setMnemonic('s');
        helpMenu.setMnemonic('h');
        restMenu.setMnemonic('r');


        menuBar.add(setMenu);
        menuBar.add(helpMenu);
        menuBar.add(restMenu);

        setMenu.add(startMI);
        setMenu.add(pauseMI);
        setMenu.addSeparator();
        setMenu.add(speedMenu);
        setMenu.addSeparator();
        setMenu.add(exitMI);

        ButtonGroup group = new ButtonGroup();
        group.add(speedMI1);
        group.add(speedMI2);
        group.add(speedMI3);
        group.add(speedMI4);
        group.add(speedMI5);


        speedMenu.add(speedMI1);

        speedMenu.add(speedMI2);

        speedMenu.add(speedMI3);

        speedMenu.add(speedMI4);

        speedMenu.add(speedMI5);


        helpMenu.add(aboutMI);
        restMenu.add(restMI);

        startMI.addActionListener(new StartAction());
        pauseMI.addActionListener(new PauseAction());
        speedMI1.addActionListener(new SpeedAction());

        speedMI2.addActionListener(new SpeedAction());

        speedMI3.addActionListener(new SpeedAction());

        speedMI4.addActionListener(new SpeedAction());

        speedMI5.addActionListener(new SpeedAction());

        exitMI.addActionListener(new ExitAction());
        aboutMI.addActionListener(new AboutAction());


        Container contentPane = getContentPane();
        panel = new SnakePanel(this);
        contentPane.add(panel);

        startMI.setEnabled(true);
        pauseMI.setEnabled(false);

        runFlag = true;

    }

    private class StartAction implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            startMI.setEnabled(false);
            pauseMI.setEnabled(true);
            runFlag = true;
        }

    }

    private class PauseAction implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            startMI.setEnabled(true);
            pauseMI.setEnabled(false);
            runFlag=false;
        }
    }

    private class SpeedAction implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == speedMI1)
            {
                speedFlag = 1;
            }else if(e.getSource() == speedMI2) {
                speedFlag = 2;
            }else if(e.getSource() == speedMI3)
            {
                speedFlag = 3;
            }else if(e.getSource() == speedMI4)
            {
                speedFlag = 4;
            }else if(e.getSource() == speedMI5)
            {
                speedFlag = 5;
            }
        }
    }

    private class ExitAction implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }

    private class AboutAction implements  ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }


}
