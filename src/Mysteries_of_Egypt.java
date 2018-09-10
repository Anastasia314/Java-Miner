import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import mystery.Box;
import mystery.Cell;
import mystery.Game;
import mystery.Ranges;
import javax.swing.UIManager;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;


public class Mysteries_of_Egypt extends JFrame
{
    private Game game;
    private JPanel panel;
    private final int COLS=23;
    private final int ROWS=11;
    private final int BOMBS=20;
    private final int IMAGE_SIZE=50;
    private JLabel label;

    public static void main(String[] args)
    {
        new Mysteries_of_Egypt().setVisible(true);

    }
    private Mysteries_of_Egypt()
    {
        game=new Game(COLS,ROWS,BOMBS);
        game.start();
        Ranges.setSize(new Cell(COLS,ROWS));
        setImages();
        initLabel();
        initPanel();
        initFrame();
    }

    private void initLabel()
    {
       label= new JLabel("Welcome!");
       add(label,BorderLayout.SOUTH);
    }

    private void initPanel()
    {
        panel=new JPanel(){
            @Override
            protected void paintComponent(Graphics g)
            {
                super.paintComponent(g);
                for(Cell cell : Ranges.getAllCells())
                {

                    g.drawImage((Image) game.getBox(cell).image,
                            cell.x*IMAGE_SIZE, cell.y*IMAGE_SIZE, this);
                }
            }
        };

        panel.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mousePressed(MouseEvent e)
            {
                int x = e.getX()/IMAGE_SIZE;
                int y = e.getY()/IMAGE_SIZE;
                Cell cell =new Cell(x,y);
                if(e.getButton()== MouseEvent.BUTTON1)
                game.pressLeftButton(cell);
                if(e.getButton()== MouseEvent.BUTTON3)
                    game.pressRightButton(cell);
                if(e.getButton()== MouseEvent.BUTTON2)
                    game.start();
                label.setText(getMessage());
                panel.repaint();
            }
        });

        panel.setPreferredSize(new Dimension(
                Ranges.getSize().x * IMAGE_SIZE,
                Ranges.getSize().y * IMAGE_SIZE));
        add(panel);
    }

    private String getMessage()
    {
        UIManager UI=new UIManager();
        UI.put("OptionPane.background", Color.white);
        UI.put("Panel.background", Color.white);
        switch (game.getState())
        {
            case PLAYED: return "BE CAREFUL, the Egyptian kings are near!";
            case BOMBED:{
                try
                {
                    AudioInputStream inAudio = AudioSystem.getAudioInputStream(getClass().getResource("img/lose.wav"));
                    Clip clip = AudioSystem.getClip();
                    clip.open(inAudio);
                    clip.setFramePosition(0);
                    clip.start();
                }
                catch (Exception e){ }
                ImageIcon icon=new ImageIcon(getClass().getResource("img/mbomb.png"));
                JOptionPane.showMessageDialog(null,
                       "Sometimes you win sometimes you LEARN!","Failure ",JOptionPane.INFORMATION_MESSAGE,icon);
            return "You lose!";
            }
            case WINNER: {
                try
                {
                    AudioInputStream inAudio = AudioSystem.getAudioInputStream(getClass().getResource("img/win.wav"));
                    Clip clip = AudioSystem.getClip();
                    clip.open(inAudio);
                    clip.setFramePosition(0);
                    clip.start();
                }
                catch (Exception e){ }
                ImageIcon icon=new ImageIcon(getClass().getResource("img/mbomb.png"));
                JOptionPane.showMessageDialog(null,
                    "BELIEVING IN YOURSELF is the first step to SUCCESS!!!","Congratualations",JOptionPane.INFORMATION_MESSAGE,icon);
                          return "Congratualations!!!";
            }
               default:return "Welcome!";
        }
    }

    private void initFrame()
    {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Mysteries of Egypt");
        setResizable(false);
        setVisible(true);
        setIconImage(getImage("micon"));
        pack();
        setLocationRelativeTo(null);
    }
    private void setImages(){
        for(Box box: Box.values())
            box.image=getImage(box.name().toLowerCase());
    }


    private Image getImage(String name){
        String filename = "img/"+name+".png";
        ImageIcon icon=new ImageIcon(getClass().getResource(filename));
        return icon.getImage();
    }
}

