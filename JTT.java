
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
  int player=1;


  // this is the one function from ActionListener
  public void actionPerformed(ActionEvent e)
  {
    if(e.getSource()==makeMove)
    {
      String numberstring=picker.getText();
      numberstring=numberstring.trim();
      int number=Integer.parseInt(numberstring);
      if(0<=number && number<=spaces.length) { // is it in range?
        if(spaces[number-1].isOpen()) {
          spaces[number-1].setNewColor(player);
		  checkGameEnd();
		  if (player==1)
			player++;
		  else
		    player--;
		}
      }
      board.repaint(); // redraw the gameBoard
    }
  }
	public void checkGameEnd () {
		if (checkLine(0,1,2)) {
			setVisible(false);
			System.out.println("Victory to player "+player);			
			dispose();	
		}	
		if (checkLine(3,4,5)){
			setVisible(false);
			System.out.println("Victory to player "+player);			
			dispose();
		}
		if (checkLine(6,7,8)){
			setVisible(false); 
			System.out.println("Victory to player "+player);
			dispose();
		}
		if (checkLine(0,4,8)){
			setVisible(false);
			System.out.println("Victory to player "+player);
			dispose();
		}
		if (checkLine(2,4,6)){
			setVisible(false); 
			System.out.println("Victory to player "+player);
			dispose();
		}
		if (checkLine(0,3,7)){
			setVisible(false);
			System.out.println("Victory to player "+player);
			dispose();
		}
		if (checkLine(2,5,7)){
			setVisible(false); 
			System.out.println("Victory to player "+player);
			dispose();
		}
		if (checkLine(1,3,6)){
			setVisible(false); 
			System.out.println("Victory to player "+player);
			dispose();
		}
		if (checkLine(1,5,8)){
			setVisible(false); 
			System.out.println("Victory to player "+player);
			dispose();
		}
		int thing=1;
		for (int i=0;i<9;i++) {
			thing*=spaces[i].getOwner();
		}
		if (thing!=0){
			setVisible(false); 
			System.out.println("It's a tie");
			dispose();
		}
	}
	public boolean checkLine(int a, int b, int c) {
		if (spaces[a].getOwner()==spaces[b].getOwner()){
			if (spaces[c].getOwner()!=0) {
				if (spaces[a].getOwner()==spaces[c].getOwner())
					return true;
			}
		}
		return false;
	}
 

  class space
  {
    int x,y,size, owner;
    Color color;

    public space(int xin, int yin, int sizein, Color c)
    {
      x=xin; y=yin; size=sizein; color=Color.blue; owner=0;
    }

    public void swim()
    {
      x=x+2+size/100;
      if(x>1000) x=-size;
    }

    public void setNewColor(int player)
    {
      if (player==1) {
        color=new Color(
          (int)(255),
          (int)(0),
          (int)(0)
        );
		owner=1;
      }
      if (player==2) {
        color=new Color(
          (int)(0),
          (int)(255),
          (int)(0)
        );
		owner=2;
      } 
    }

    public void draw(Graphics g)
    {
      g.setColor(color);
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
	public boolean isOpen() {
		if (owner==0) {
			return true;
		}
		return false;
	}
	public int getOwner() {
		return owner;
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
      g.setColor(Color.white);
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
    picker=new JTextField("      0 ");
    board=new gameBoard();

      spaces=new space[9];
      int fsize= (int)(100);
      int fx=(int)(90);
      int fy=(int)(90);
      Color fc=(Color.blue);


      spaces[0]=new space((fx),(fy), fsize, fc);
      
      spaces[1]=new space((5*fx),(fy), fsize, fc);

      spaces[2]=new space((9*fx),(fy), fsize, fc);

      fy=(int)(250);

      spaces[3]=new space(3*fx,fy, fsize, fc);

      spaces[4]=new space((5*fx),(fy), fsize, fc);

      spaces[5]=new space((7*fx),(fy), fsize, fc);

      fy = (int)(390);

      spaces[6]=new space((fx),(fy), fsize, fc);

      spaces[7]=new space((5*fx),(fy), fsize, fc);

      spaces[8]=new space((9*fx),(fy), fsize, fc);

     


    JPanel p=new JPanel();
    p.setLayout( new BorderLayout() );
    p.add(picker,"West");
    p.add(makeMove,"Center");

    glass.add(board,"Center");
 
    glass.add(p,"South");

    setVisible(true);
  }

  public static void main(String [] args)
  {
    Magic snot=new Magic();
  }
}
