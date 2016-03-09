import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;

import java.util.*;
import java.io.*;

public class BasicEdit extends Basic
{
  public static void main(String[] args)
  {
    // example: hard-coded location and size of window:
    BasicEdit a = new BasicEdit("Basic Edit", 0, 0, 600, 400);

  }

  // instance variables for the application:
  //------------------------------------------------------------------
  private SList content;
  private int cursor, firstLine, lastLine;
  private String filename;

  //------------------------------------------------------------------

  public BasicEdit( String title, int ulx, int uly, int pw, int ph )
  {
    super(title,ulx,uly,pw,ph);

    // code to initialize instance variables before animation begins:
    cursor = 0;
    firstLine = 20;
    lastLine = 0;
    content = new SList(21);
    //for(int i = 0; i<21; i++)
      //content.append(""+i);

    // code to set up camera(s)
    //------------------------------------------------------------------

    cameras.add( new Camera( 10, 30, pixelWidth-20, pixelHeight-43,
                         0, 34, 0,
                         new Color( 255, 255, 255 ) ) );

    //------------------------------------------------------------------



    //------------------------------------------------------------------

    // code to finish setting up entire window:
    //------------------------------------------------------------------

    setBackgroundColor( new Color( 255, 255, 255 ) );


    // start up the animation:
    super.start();

    Camera cam = cameras.get(0);
    //save a filename from the user
    filename = FileBrowser.chooseFile( true );
    try //attempt to open and read the file the user entered
    {
      File fileToRead = new File(filename);
      Scanner readFile = new Scanner(fileToRead);
      while(readFile.hasNext())
      {
          String temp = readFile.nextLine(), temp2 = temp;
          int num = temp.length();
          boolean shortened = false;
          while(cam.getWidth(temp)>32)
          {
            num-=1;
            temp = temp.substring(0,num);
            shortened = true;
          }
          content.append(temp);
          if(shortened)
            content.append(temp2.substring(num));

      }
      readFile.close();
    }
    catch(Exception e) //if the user enters a invalid file, do noting and continue, the program content will be save upon exit to the users enterd filename
    {
      content.append("");
    }
  }

  public void step()
  {
    // code to update the world and display the world:
    //------------------------------------------------------------------

    Camera cam = cameras.get(0);
    cam.activate();

    cam.setColor(Color.black);
    cam.setFont( new Font("Arial", Font.PLAIN, 15) );

    for(int i=firstLine, j=cursor-10; i>=lastLine; i--, j++)
    {
      if(j==cursor)
        cam.drawText(">" , 0, i);
      if(j<0 || j>=content.size())
        cam.drawText("", 1, i);
      else
        cam.drawText(content.get(j), 1, i);
    }


    //------------------------------------------------------------------
  }

  public void keyTyped( KeyEvent e )
  {
    char key = e.getKeyChar();

    // code to change instance variables depending on key:
    //------------------------------------------------------------------
    if(' '<=key && key<='~') //record only the printable characters
    {
      System.out.println("typed");
      if(cursor<content.size())
      {
        String temp = content.get(cursor);
        temp += key;
        Camera cam = cameras.get(0);
        if(cam.getWidth(temp)<32)
        {
          content.remove(cursor);
          content.insert(cursor, temp);
        }
        else
        {
          cursor++;
          content.insert(cursor, ""+key);
        }
      }
      else
        content.append(""+key);
    }


    //------------------------------------------------------------------
  }

  public void keyPressed( KeyEvent e )
  {
    int code = e.getKeyCode();

    // code to change instance variables depending on which key pressed (code):
    //------------------------------------------------------------------
    System.out.println("pressed");

      if(code == KeyEvent.VK_UP && cursor>0)
        cursor--;
      else if(code == KeyEvent.VK_DOWN && cursor<content.size())
        cursor++;
      else if(code == KeyEvent.VK_ENTER)
        content.insert(cursor, "");
      else if(code == KeyEvent.VK_BACK_SPACE)
      {
        if(cursor<content.size())
        {
          String temp = content.get(cursor);
          content.remove(cursor);
          if(temp.length()>0)
          {
            content.insert(cursor, temp.substring(0,temp.length()-1));
            cursor++;
          }
        }
        if(cursor>0)
          cursor--;

      }
      else if(code == KeyEvent.VK_ESCAPE)
      {
        filename = FileBrowser.chooseFile( false );
        //save the program content to the file the user entered and exit
        try
        {
          FileWriter fileToWriteTo = new FileWriter(filename, false);
          PrintWriter writeToFile = new PrintWriter(fileToWriteTo);
          for(int k=0; k<content.size(); k++)
          {
            writeToFile.println(content.get(k));
          }
          writeToFile.close();
        }
        catch(Exception f)
        {}
        System.exit(0);
      }
    //------------------------------------------------------------------
  }

  public void mouseMoved(MouseEvent e)
  {
    super.mouseMoved(e);

    // code to respond to mouse motion:
    //------------------------------------------------------------------

    //------------------------------------------------------------------
  }

  public void mouseDragged(MouseEvent e)
  {
    super.mouseDragged(e);

    // code to respond to mouse motion with a button pressed:
    //------------------------------------------------------------------

    //------------------------------------------------------------------
  }

  public void mouseClicked(MouseEvent e)
  {
    super.mouseClicked(e);

    // code to respond to mouse click:
    //------------------------------------------------------------------

    //------------------------------------------------------------------
  }

  public void mousePressed(MouseEvent e)
  {
    super.mousePressed(e);

    // code to respond to mouse button pressed:
    //------------------------------------------------------------------

    //------------------------------------------------------------------
  }

  public void mouseReleased(MouseEvent e)
  {
    super.mouseReleased(e);

    // code to respond to mouse button released:
    //------------------------------------------------------------------

    //------------------------------------------------------------------
  }

  public void mouseEntered(MouseEvent e)
  {
    super.mouseEntered(e);

    // code to respond to mouse entering window:
    //------------------------------------------------------------------

    //------------------------------------------------------------------
  }

  public void mouseExited(MouseEvent e)
  {
    super.mouseExited(e);

    // code to respond to mouse exiting window:
    //------------------------------------------------------------------

    //------------------------------------------------------------------
  }

}
