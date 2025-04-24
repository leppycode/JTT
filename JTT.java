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
  JButton swim, magic;
  JTextField picker;
  ocean pacific;
  fish [] school;


  // this is the one function from ActionListener
  public void actionPerformed(ActionEvent e)
  {
    if(e.getSource()==swim) 
      pacific.swim();
    else if(e.getSource()==magic)
    {
      String numberstring=picker.getText();
      numberstring=numberstring.trim();
      int number=Integer.parseInt(numberstring);
System.out.println("Fish number "+number);
      if(0<=number && number<school.length) // is it in range?
        school[number].setRandomColor();
      pacific.repaint(); // redraw the ocean
    }
  }

  class fish
  {
    int x,y,size;
    Color color;

    public fish(int xin, int yin, int sizein, Color c)
    {
      x=xin; y=yin; size=sizein; color=c;
    }

    public void swim()
    {
      x=x+2+size/100;
      if(x>1000) x=-size;
    }

    public void setRandomColor()
    {
      color=new Color(
          (int)(256*Math.random()),
          (int)(256*Math.random()),
          (int)(256*Math.random())
        );
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
  class ocean extends JPanel
  {
    public ocean()
    {
      setSize(1000,500);
    }

    public void swim()
    {
      for(int i=0; i<school.length; i++)
        school[i].swim();
      repaint();
    }

    public void paintComponent(Graphics g)
    // Graphics g is like Harold's purple crayon
    {
      g.setColor(Color.blue);
      g.fillRect(0,0, 1000,500);
      for(int i=0; i<school.length; i++)
        school[i].draw(g);
    }
  }

  public Magic() 
  {
    setTitle("Our best fish program!");
    setSize(1000,600);
    setLocation(50,50);
    addWindowListener(new dad());

    Container glass=getContentPane();
    glass.setLayout( new BorderLayout() ); // Layout manager
    
    magic=new JButton("Magic Rainbow!");
    magic.addActionListener(this);
    swim=new JButton("Just Keep Swimming!");
    swim.addActionListener(this);
    picker=new JTextField("      0 ");
    pacific=new ocean();

    school=new fish[10];
    for(int i=0; i<school.length; i++)
    {
      int fsize= (int)(10+500*Math.random());
      int fx=(int)((1000-fsize)*Math.random());
      int fy=(int)((600-fsize/3)*Math.random());
      Color fc=new Color(
          (int)(256*Math.random()),
          (int)(256*Math.random()),
          (int)(256*Math.random())
        );
      school[i]=new fish(fx,fy, fsize, fc);
    }

    JPanel p=new JPanel();
    p.setLayout( new BorderLayout() );
    p.add(picker,"West");
    p.add(magic,"Center");

    glass.add(pacific,"Center");
    glass.add(swim,"North");
    glass.add(p,"South");

    setVisible(true);
  }

  public static void main(String [] args)
  {
    Magic snot=new Magic();
  }
}
