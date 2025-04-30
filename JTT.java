import java.util.*;
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
  int computerTurn=0;
  int moveCount=1;
  int lastMove=0;


  // this is the one function from ActionListener
  public void actionPerformed(ActionEvent e)
  {
    if(e.getSource()==makeMove)
    {
      if (player==computerTurn)
		  computerMove();
	  else {
		String numberstring=picker.getText();
		numberstring=numberstring.trim();
		int number=Integer.parseInt(numberstring);
		if(0<=number && number<=spaces.length) { // is it in range?
			if(spaces[number-1].isOpen()) {
			spaces[number-1].setNewColor(player);
			checkGameEnd();
			lastMove=(number-1);
			if (player==1)
				player++;
			else
				player--;
			}
		}
	  }
      board.repaint(); // redraw the gameBoard
    }
  }
  public void computerMove() {
	  if (player==1) {
		  if (moveCount==1) {
			  setStrat();
		  }
		  if (lastMove==1)
			  nodestart=nodestart.next1;
		  if (lastMove==2)
			  nodestart=nodestart.next2;
		  if (lastMove==3)
			  nodestart=nodestart.next3;
		  if (lastMove==4)
			  nodestart=nodestart.next4;
		  if (lastMove==5)
			  nodestart=nodestart.next5;
		  if (lastMove==6)
			  nodestart=nodestart.next6;
		  if (lastMove==7)
			  nodestart=nodestart.next7;
		  if (lastMove==8)
			  nodestart=nodestart.next8;
		  spaces[nodestart.move].setNewColor(player);
		  checkGameEnd();
	  }
	  if (player==2) {
	  for (int i=0	;i<9;i++){
		  if(0<=i && i<=spaces.length) { // is it in range?
			if(spaces[i].isOpen()) {
			spaces[i].setNewColor(player);
			checkGameEnd();
			break;
			}
		}
	  }
	  }
      if (player==1)
			player++;
	  else
			player--;
	  board.repaint(); // redraw the gameBoard
  }
  
	public void checkGameEnd () {
		if (checkLine(0,1,2)) {
			setVisible(false);
			System.out.println("Victory to player "+player);
			System.out.println("Type 1 to play again, 0 to quit");
			dispose();	
		}	
		if (checkLine(3,4,5)){
			setVisible(false);
			System.out.println("Victory to player "+player);
		System.out.println("Type 1 to play again, 0 to quit");			
			dispose();
		}
		if (checkLine(6,7,8)){
			setVisible(false); 
			System.out.println("Victory to player "+player);
			System.out.println("Type 1 to play again, 0 to quit");
			dispose();
		}
		if (checkLine(0,4,8)){
			setVisible(false);
			System.out.println("Victory to player "+player);
			System.out.println("Type 1 to play again, 0 to quit");
			dispose();
		}
		if (checkLine(2,4,6)){
			setVisible(false); 
			System.out.println("Victory to player "+player);
			System.out.println("Type 1 to play again, 0 to quit");
			dispose();
		}
		if (checkLine(0,3,7)){
			setVisible(false);
			System.out.println("Victory to player "+player);
			System.out.println("Type 1 to play again, 0 to quit");
			dispose();
		}
		if (checkLine(2,5,7)){
			setVisible(false); 
			System.out.println("Victory to player "+player);
			System.out.println("Type 1 to play again, 0 to quit");
			dispose();
		}
		if (checkLine(1,3,6)){
			setVisible(false); 
			System.out.println("Victory to player "+player);
			System.out.println("Type 1 to play again, 0 to quit");
			dispose();
		}
		if (checkLine(1,5,8)){
			setVisible(false); 
			System.out.println("Victory to player "+player);
			System.out.println("Type 1 to play again, 0 to quit");
			dispose();
		}
		int thing=1;
		for (int i=0;i<9;i++) {
			thing*=spaces[i].getOwner();
		}
		if (thing!=0){
			setVisible(false); 
			System.out.println("It's a tie");
			System.out.println("Type 1 to play again, 0 to quit");
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

  public Magic(int computer) 
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
	computerTurn=computer;
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
    int playAgain=-1; int computer=0; 
	Scanner scan=new Scanner(System.in);
	System.out.println("Would you like the comptuer to play first (1) or second (2)?");
	computer=scan.nextInt();
	Magic snot=new Magic(computer);
	while ((playAgain!=0)&&(playAgain!=1)) {
		playAgain=scan.nextInt();
	}
	while (playAgain==1) {
		playAgain=-1;
		newGame();
		while ((playAgain!=0)&&(playAgain!=1)) {
			playAgain=scan.nextInt();
		}
	}
  }
  public static void newGame() {
	int computer=0;
	Scanner scan=new Scanner(System.in);
	System.out.println("Would you like the comptuer to play first (1) or second (2)?");
	computer=scan.nextInt(); 
	Magic beans=new Magic(computer);
  }
  class node {
	public int move;
	public node next1,next2,next3,next4,next5,next6,next7,next8;
	public node() {
		move=-1;
		next1=next2=next3=next4=next5=next6=next7=next8=null;
	}
}

node nodestart=new node();
node node1=new node();
node node2=new node();
node node3=new node();
node node4=new node();
node node5=new node();
node node6=new node();
node node7=new node();
node node8=new node();
node node1else=new node();
node node14=new node();
node node14else=new node();
node node146=new node();
node node2else=new node();
node node28=new node();
node node28else=new node();
node node287=new node();
node node3else=new node();
node node32=new node();
node node32else=new node();
node node324=new node();
node node4else=new node();
node node47=new node();
node node47else=new node();
node node472=new node();
node node51=new node();
node node51else=new node();
node node517=new node();
node node52=new node();
node node52else=new node();
node node528=new node();
node node53=new node();
node node53else=new node();
node node538=new node();
node node54=new node();
node node54else=new node();
node node541=new node();
node node57=new node();
node node57else=new node();
node node571=new node();
node node58=new node();
node node58else=new node();
node node582=new node();
node node61=new node();
node node61else=new node();
node node614=new node();
node node62=new node();
node node62else=new node();
node node623=new node();
node node63=new node();
node node63else=new node();
node node632=new node();
node node64=new node();
node node64else=new node();
node node641=new node();
node node67=new node();
node node67else=new node();
node node671=new node();
node node68=new node();
node node68else=new node();
node node682=new node();
node node7else=new node();
node node74=new node();
node node74else=new node();
node node742=new node();
node node8else=new node();
node node82=new node();
node node82else=new node();
node node827=new node();

public void setStrat() {
nodestart.move=0;
nodestart.next1=node1;
node1.move=8;
nodestart.next2=node2;
node2.move=4;
nodestart.next3=node3;
node3.move=1;
nodestart.next4=node4;
node4.move=3;
nodestart.next5=node5;
node5.move=6;
nodestart.next6=node6;
node6.move=5;
nodestart.next7=node7;
node7.move=8;
nodestart.next8=node8;
node8.move=1;

node1.next2=node1.next3=node1.next5=node1.next6=node1.next7=node1.next8=node1else;
node1else.move=4;
node1.next4=node14;
node14.move=7;
node14.next2=node14.next3=node14.next5=node14else;
node14else.move=6;
node14.next6=node146;
node146.move=3;

node2.next1=node2.next3=node2.next5=node2.next6=node2.next7=node2else;
node2else.move=8;
node2.next8=node28;
node28.move=3;
node28.next1=node28.next5=node28.next6=node28else;
node28else.move=7;
node28.next7=node287;
node287.move=5;

node3.next4=node3.next5=node3.next6=node3.next7=node3.next8=node3else;
node3else.move=2;
node3.next2=node32;
node32.move=8;
node32.next5=node32.next6=node3.next7=node32else;
node32else.move=4;
node32.next4=node324;
node324.move=5;

node4.next1=node4.next2=node4.next5=node4.next6=node4.next8=node4else;
node4else.move=7;
node4.next7=node47;
node47.move=1;
node47.next5=node47.next6=node47.next8=node47else;
node47else.move=2;
node47.next2=node472;
node472.move=6;

node5.next1=node51;
node51.move=8;
node51.next2=node51.next3=node51.next4=node51else;
node51else.move=7;
node51.next7=node517;
node517.move=4;

node5.next2=node52;
node52.move=7;
node52.next1=node52.next3=node52.next4=node52else;
node52else.move=8;
node52.next8=node528;
node528.move=3;

node5.next3=node53;
node53.move=4;
node53.next1=node53.next2=node53.next7=node53else;
node53else.move=8;
node53.next8=node538;
node538.move=2;

node5.next4=node54;
node54.move=3;
node54.next2=node54.next7=node54.next8=node54else;
node54else.move=1;
node54.next1=node541;
node541.move=7;

node5.next7=node57;
node57.move=2;
node57.next3=node57.next4=node7.next8=node57else;
node57else.move=1;
node57.next1=node571;
node571.move=4;

node5.next8=node58;
node58.move=1;
node58.next3=node58.next4=node58.next7=node58else;
node58else.move=2;
node58.next2=node582;
node582.move=3;

node6.next1=node61;
node61.move=3;
node61.next2=node61.next7=node61.next8=node61else;
node61else.move=4;
node61.next4=node614;
node614.move=7;

node6.next2=node62;
node62.move=4;
node62.next1=node62.next7=node62.next8=node62else;
node62else.move=3;
node62.next3=node623;
node623.move=8;

node6.next3=node63;
node63.move=1;
node63.next4=node63.next7=node63.next8=node63else;
node63else.move=2;
node63.next2=node632;
node632.move=8;

node6.next4=node64;
node64.move=2;
node64.next3=node64.next7=node64.next8=node64else;
node64else.move=1;
node64.next1=node641;
node641.move=7;

node6.next7=node67;
node67.move=8;
node67.next2=node67.next3=node67.next4=node67else;
node67else.move=1;
node67.next1=node671;
node671.move=4;

node6.next8=node68;
node68.move=7;
node68.next1=node68.next3=node68.next4=node68else;
node68else.move=2;
node68.next2=node682;
node682.move=3;

node7.next1=node7.next2=node7.next3=node7.next5=node7.next6=node7else;
node7else.move=4;
node7.next4=node74;
node74.move=1;
node74.next3=node74.next5=node74.next6=node74else;
node74else.move=2;
node74.next2=node742;
node742.move=5;

node8.next3=node8.next4=node8.next5=node8.next6=node8.next7=node8else;
node8else.move=2;
node8.next2=node82;
node82.move=3;
node82.next4=node82.next5=node82.next6=node82else;
node82else.move=7;
node82.next7=node827;
node827.move=6;
}
}
