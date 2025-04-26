import java.io.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

class dad extends WindowAdapter
{
  public void windowClosing(WindowEvent e)
  {
    System.out.println("It's closing time.");
    System.exit(0); // exits the program
  }
}

class Magic extends JFrame implements ActionListener 
// ActionListener listens for events like button pressing
{
  JButton makeMove;
  JTextField picker;
  gameBoard board;
  space [] spaces;
  int player=0;


  // this is the one function from ActionListener
  public void actionPerformed(ActionEvent e)
  {
    if(e.getSource()==makeMove)
    {
      String numberstring=picker.getText();
      numberstring=numberstring.trim();
      int number=Integer.parseInt(numberstring);
      if(0<=number && number<spaces.length) { // is it in range?
        if(spaces[number-1].isOpen())
          spaces[number-1].setNewColor(player);
      }
      board.repaint(); // redraw the gameBoard
      player=(player+1)%2;
    }
  }

  class space
  {
    int x,y,size;
    Color color;

    public space(int xin, int yin, int sizein, Color c)
    {
      x=xin; y=yin; size=sizein; color=c;
    }

    public void makeMove()
    {
      x=x+2+size/100;
      if(x>1000) x=-size;
    }

    public void setNewColor(int player)
    {
      if (player==0) {
        color=new Color(
          (int)(256),
          (int)(0),
          (int)(0)
        );
      }
      if (player==1) {
        color=new Color(
          (int)(0),
          (int)(256),
          (int)(0)
        );
      }
    }

    public void draw(Graphics g)
    {
      g.setColor(color);
      int ht=size/3;
      g.fillOval(x,y, size, ht);
      int [] xlist={x+size/5, x-size/5, x-size/5};
      int [] ylist={y+ht/2, y, y+ht};
      g.fillPolygon(xlist, ylist, 3);
      int eyex=x+size/2+size/8, eyey=y+size/12;
      int eyesize=ht/2;
      g.setColor(Color.white);
      g.fillOval(eyex,eyey, eyesize, eyesize);
      g.setColor(Color.black);
      g.fillOval(eyex+5,eyey+5, eyesize-10, eyesize-10);
    }
  }

  // artwork
  class gameBoard extends JPanel
  {
    public gameBoard()
    {
      setSize(1000,500);
    }

    public void swim()
    {
      for(int i=0; i<spaces.length; i++)
        spaces[i].swim();
      repaint();
    }

    public void paintComponent(Graphics g)
    // Graphics g is like Harold's purple crayon
    {
      g.setColor(Color.blue);
      g.fillRect(0,0, 1000,500);
      for(int i=0; i<spaces.length; i++)
        spaces[i].draw(g);
    }
  }

  public Magic() 
  {
    setTitle("Our best space program!");
    setSize(1000,600);
    setLocation(50,50);
    addWindowListener(new dad());

    Container glass=getContentPane();
    glass.setLayout( new BorderLayout() ); // Layout manager
    
    makeMove=new JButton("Magic Rainbow!");
    makeMove.addActionListener(this);
    swim=new JButton("Just Keep Swimming!");
    swim.addActionListener(this);
    picker=new JTextField("      0 ");
    board=new gameBoard();

    spaces=new space[10];
    for(int i=0; i<spaces.length; i++)
    {
      int fsize= (int)(10+500*Math.random());
      int fx=(int)((1000-fsize)*Math.random());
      int fy=(int)((600-fsize/3)*Math.random());
      Color fc=new Color(
          (int)(256*Math.random()),
          (int)(256*Math.random()),
          (int)(256*Math.random())
        );
      spaces[i]=new space(fx,fy, fsize, fc);
    }

    JPanel p=new JPanel();
    p.setLayout( new BorderLayout() );
    p.add(picker,"West");
    p.add(makeMove,"Center");

    glass.add(board,"Center");
    glass.add(swim,"North");
    glass.add(p,"South");

    setVisible(true);
  }

  public static void main(String [] args)
  {
    Magic snot=new Magic();
  }
}
