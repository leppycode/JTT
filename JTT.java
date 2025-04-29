
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
      g.setColor(Color.blue);
      int ht=size;
      g.fillOval(x,y,size,ht);
      //1 to 2    
      g.drawLine(140, 140, 500, 140);
      //2 to 3
    

      g.drawLine(140, 140, 500,300);

      g.drawLine(140, 140, 310,300);

      g.drawLine(500, 140, 860, 140);

      //3 to 6
      g.drawLine(860, 140, 670, 300);

      g.drawLine(860, 140, 500,300);
      //6 to 5
      g.drawLine(670,300,500,300);
      //5 to 4
      g.drawLine(470,300, 310,300);

     
      //1 to 5
     
      g.drawLine(310,300, 120,440);

      g.drawLine(140,440,500,440);

      g.drawLine(500,440,670,440);

      g.drawLine(670,440,860,440);

      g.drawLine(860,440,500,300);

      g.drawLine(860,440,670, 300);

      g.drawLine(140,440, 500,300);

      g.drawLine(500,440,310,300);

      g.drawLine(500,440,670,300);

      g.drawLine(500,140,310,300);

      g.drawLine(500,140,670,300);




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
      g.setColor(Color.white);
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

      school=new fish[9];
      int fsize= (int)(100);
      int fx=(int)(90);
      int fy=(int)(90);
      Color fc=(Color.blue);


      school[0]=new fish((fx),(fy), fsize, fc);
      
      school[1]=new fish((5*fx),(fy), fsize, fc);

      school[2]=new fish((9*fx),(fy), fsize, fc);

      fy=(int)(250);

      school[3]=new fish(3*fx,fy, fsize, fc);

      school[4]=new fish((5*fx),(fy), fsize, fc);

      school[5]=new fish((7*fx),(fy), fsize, fc);

      fy = (int)(390);

      school[6]=new fish((fx),(fy), fsize, fc);

      school[7]=new fish((5*fx),(fy), fsize, fc);

      school[8]=new fish((9*fx),(fy), fsize, fc);

     


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
