import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.text.*;
import java.util.*;
import java.util.List; // resolves problem with java.awt.List and java.util.List
    
    /**
     * A class that represents a picture.  This class inherits from 
     * SimplePicture and allows the student to add functionality to
     * the Picture class.  
     * 
     * @author Barbara Ericson ericson@cc.gatech.edu
     */
public class Picture extends SimplePicture 
{
      ///////////////////// constructors //////////////////////////////////
    
    /**
    * Constructor that takes no arguments 
    */
    public Picture ()
    {
    /* not needed but use it to show students the implicit call to super()
     * child constructors always call a parent constructor 
     */
    super();  
    }
    
    /**
    * Constructor that takes a file name and creates the picture 
    * @param fileName the name of the file to create the picture from
    */
    public Picture(String fileName)
    {
    //let the parent class handle this fileName
    super(fileName);
    }
    
    /**
    * Constructor that takes the width and height
    * @param height the height of the desired picture
    * @param width the width of the desired picture
    */
    public Picture(int height, int width)
    {
    //let the parent class handle this width and height
    super(width,height);
    }
    
    /**
    * Constructor that takes a picture and creates a 
    * copy of that picture
    * @param copyPicture the picture to copy
    */
    public Picture(Picture copyPicture)
    {
    //let the parent class do the copy
    super(copyPicture);
    }
    
    /**
    * Constructor that takes a buffered image
    * @param image the buffered image to use
    */
    public Picture(BufferedImage image)
    {
    super(image);
    }
    
    //////////////////// methods ///////////////////////////////////////
    
    /**
    * Method to return a string with information about this picture.
    * @return a string with information about the picture such as fileName,
    * height and width.
    */
    public String toString()
    {
    String output = "Picture, filename " + getFileName() + 
      " height " + getHeight() 
      + " width " + getWidth();
    return output;
    
    }
    
    /** Method to set the blue to 0 */
    public void zeroBlue()
    {
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
        pixelObj.setBlue(0);
      }
    }
    }
    
    public void copy(Picture sourcePicture, int startSourceRow, int endSourceRow, int startSourceCol, int endSourceCol,
         int startDestRow, int startDestCol)
    {
    Pixel[][] pixels=this.getPixels2D();
    Pixel[][] pixels1=sourcePicture.getPixels2D();
    for (int row1=0;row1<endSourceRow-startSourceRow;row1++)
    {
      for (int col1=0;col1<endSourceCol-startSourceCol;col1++)
      {
          pixels[startDestRow+row1][startDestCol+col1].setColor(pixels1[startSourceRow+row1][startSourceCol+col1].getColor());
      }
    }
    }
    public void pattern()
    {
    }
    public Picture scale()
    {
      Pixel[][] pixels=this.getPixels2D();
      Picture scaled1=new Picture((this.getHeight()/2),(this.getWidth())/2);
      Pixel[][] scaled=scaled1.getPixels2D();
      int count=2;
      for(int row=0;row<pixels.length;row++)
      {
          for(int col=0;col<pixels[0].length;col++)
          {
              if(count%2==0)
              {
                  scaled[row/2][col/2].setColor(pixels[row][col].getColor());
    pixels[row][col].setRed(255);
    pixels[row][col].setGreen(255);
    pixels[row][col].setBlue(255);
              }
              if(row>row/2||col>col/2)
              {
                  pixels[row][col].setRed(255);
                  pixels[row][col].setGreen(255);
                  pixels[row][col].setBlue(255);
              }
              count++;
          }
      }
      return scaled1;
    }
    public void negate()
    {
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
        pixelObj.setRed(255-pixelObj.getRed());
        pixelObj.setGreen(255-pixelObj.getGreen());
        pixelObj.setBlue(255-pixelObj.getBlue());
      }
    }
    }
    
//     public void mirrorHorizontal()
//     {
//     Pixel[][] pixels = this.getPixels2D();
//     Pixel topPixel = null;
//     Pixel bottomPixel = null;
//     int height = pixels.length;
//     for (int col = 0; row < pixels.length; col++)
//     {
//     for (int row = 0; col < height / 2; row++)
//     {
//         topPixel = pixels[row][col];
//         bottomPixel = pixels[row][width - 1 - col];
//         bottomPixel.setColor(topPixel.getColor());
//     }
//     }
//     }
    public void grayScale()
    {
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
        int r=pixelObj.getRed();
        int g=pixelObj.getGreen();
        int b=pixelObj.getBlue();
        int avg=(r+g+b)/3;
        pixelObj.setRed(avg);
        pixelObj.setGreen(avg);
        pixelObj.setBlue(avg);
      }
    }
    }
    
    public void fixUnderwater()
    {
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
    if(pixelObj.getRed()>28)
    {
        pixelObj.setBlue(pixelObj.getBlue()-50);
        pixelObj.setGreen(pixelObj.getGreen()-10);
    }
        if(pixelObj.getRed()<28)
        {
            pixelObj.setBlue(pixelObj.getBlue()-35);
            pixelObj.setRed(pixelObj.getRed()*2);
            pixelObj.setGreen(pixelObj.getGreen()-35);
        }
      }
    }
    }
    
    public void keepOnlyBlue()
    {
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
        pixelObj.setRed(0);
        pixelObj.setGreen(0);
      }
    }
    }
    /** Method that mirrors the picture around a 
    * vertical mirror in the center of the picture
    * from left to right */
    public void mirrorVertical()
    {
    Pixel[][] pixels = this.getPixels2D();
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    int width = pixels[0].length;
    for (int row = 0; row < pixels.length; row++)
    {
      for (int col = 0; col < width / 2; col++)
      {
        leftPixel = pixels[row][col];
        rightPixel = pixels[row][width - 1 - col];
        rightPixel.setColor(leftPixel.getColor());
      }
    } 
    }
    
    /** Mirror just part of a picture of a temple */
    public void mirrorTemple()
    {
    int mirrorPoint = 276;
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    int count = 0;
    Pixel[][] pixels = this.getPixels2D();
    
    //loop through the rows
    for (int row = 27; row < 97; row++)
    {
    //loop from 13 to just before the mirror point
      for (int col = 13; col < mirrorPoint; col++)
      {
        
        leftPixel = pixels[row][col];      
        rightPixel = pixels[row][mirrorPoint - col + mirrorPoint];
        rightPixel.setColor(leftPixel.getColor());
        count++;
      }
      System.out.println(count);
    }
    }
    public void mirrorArms()
    {
    int mirrorPoint = 206;
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    int count = 0;
    Pixel[][] pixels = this.getPixels2D();
    
    //loop through the rows
    for (int row = 154; row < 190; row++)
    {
    //loop from 13 to just before the mirror point
      for (int col = 93; col < mirrorPoint; col++)
      {
        
        leftPixel = pixels[row][col];      
        rightPixel = pixels[row]                       
                         [mirrorPoint - col + mirrorPoint];
        rightPixel.setColor(leftPixel.getColor());
        count++;
      }
      System.out.println(count);
    }
    }
    /** copy from the passed fromPic to the
    * specified startRow and startCol in the
    * current picture
    * @param fromPic the picture to copy from
    * @param startRow the start row to copy to
    * @param startCol the start col to copy to
    */
    public void copy(Picture fromPic, 
                 int startRow, int startCol)
    {
    Pixel fromPixel = null;
    Pixel toPixel = null;
    Pixel[][] toPixels = this.getPixels2D();
    Pixel[][] fromPixels = fromPic.getPixels2D();
    for (int fromRow = 0, toRow = startRow; 
         fromRow < fromPixels.length &&
         toRow < toPixels.length; 
         fromRow++, toRow++)
    {
      for (int fromCol = 0, toCol = startCol; 
           fromCol < fromPixels[0].length &&
           toCol < toPixels[0].length;  
           fromCol++, toCol++)
      {
        fromPixel = fromPixels[fromRow][fromCol];
        toPixel = toPixels[toRow][toCol];
        toPixel.setColor(fromPixel.getColor());
      }
    }   
    }
    
    /** Method to create a collage of several pictures */
    public void createCollage()
    {
    Picture schmit1 = new Picture("Mr. Schmit.jpg");
    schmit1=schmit1.scale();
    Picture schmit2=new Picture(schmit1);
    this.copy(schmit1,0,0);
    schmit2.negate();
    this.copy(schmit2,0,390);
    Picture schmitReflect = new Picture(schmit1);
    Picture schmitReflect2=new Picture(schmit1);
    schmitReflect.mirrorVertical();
    schmitReflect2.mirrorVerticalRightToLeft();
    schmitReflect2.grayScale();
    this.copy(schmitReflect,240,0);
    this.copy(schmitReflect2,240,390);
    this.grayScale();
    this.write("collage.jpg");
    }
    public void posterize()
    {
      Pixel[][] pixels=this.getPixels2D();
      for(int row=1;row<pixels.length;row++)
      {
          for(int col=0;col<pixels[0].length;col++)
          {
              for(int i=0;i<3;i++)
              {
                  int val=0;
                  if(i==0)
                  {
                      val=pixels[row][col].getRed();
                  }
                  else if(i==1)
                  {
                      val=pixels[row][col].getGreen();
                  }
                  else if(i==2)
                  {
                      val=pixels[row][col].getBlue();
                  }
                  if(val<64)
                  {
                      val=31;
                  }
                  else if(val<128)
                  {
                      val=95;
                  }
                  else if(val<192)
                  {
                      val=159;
                  }
                  else
                  {
                      val=223;
                  }
                  if(i==0)
                  {
                      pixels[row][col].setRed(val);
                  }
                  else if(i==1)
                  {
                      pixels[row][col].setGreen(val);
                  }
                  else if(i==2)
                  {
                      pixels[row][col].setBlue(val);
                  }
              }
          }
      }
    }
    public void sepia()
    {
      this.grayScale();
      Pixel[][] pixels=this.getPixels2D();
      for(int row=1;row<pixels.length;row++)
      {
          for(int col=0;col<pixels[0].length;col++)
          {
              if(pixels[row][col].getRed()<60)
              {
                  pixels[row][col].setRed((int)(pixels[row][col].getRed()*.9));
                  pixels[row][col].setGreen((int)(pixels[row][col].getGreen()*.9));
                  pixels[row][col].setBlue((int)(pixels[row][col].getBlue()*.9));
              }
              else if(pixels[row][col].getRed()<190)
              {
                  pixels[row][col].setBlue((int)(pixels[row][col].getBlue()*.8));
              }
              else
              {
                  pixels[row][col].setBlue((int)(pixels[row][col].getBlue()*.9));
              }
          }
      }
    }
    public void edgeDetection()
    {
      Pixel[][] pixels=this.getPixels2D();
      for(int row=1;row<pixels.length;row++)
      {
          for(int col=0;col<pixels[0].length;col++)
          {
              if(Math.abs(pixels[row][col].getAverage()-pixels[row+1][col].getAverage())<40)
              {
                  pixels[row][col].setColor(Color.BLACK);
              }
              else
              {
                  pixels[row][col].setColor(Color.WHITE);
              }
          }
      }
    }
    public void mirrorVerticalRightToLeft()
    {
     Pixel[][] pixels = this.getPixels2D();
     Pixel leftPixel = null;
     Pixel rightPixel = null;
     int width = pixels[0].length;
     for (int row = 0; row < pixels.length; row++)
     {
         for (int col = 0; col < width / 2; col++)
         {
             leftPixel = pixels[row][col];
             rightPixel = pixels[row][width - 1 - col];
             leftPixel.setColor(rightPixel.getColor());
            }
        }
    }
    
    
    /** Method to show large changes in color 
    * @param edgeDist the distance for finding edges
    */
    public void edgeDetection(int edgeDist)
    {
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    Pixel[][] pixels = this.getPixels2D();
    Color rightColor = null;
    for (int row = 0; row < pixels.length; row++)
    {
      for (int col = 0; 
           col < pixels[0].length-1; col++)
      {
        leftPixel = pixels[row][col];
        rightPixel = pixels[row][col+1];
        rightColor = rightPixel.getColor();
        if (leftPixel.colorDistance(rightColor) > 
            edgeDist)
          leftPixel.setColor(Color.BLACK);
        else
          leftPixel.setColor(Color.WHITE);
      }
    }
    }
    
    
    /* Main method for testing - each class in Java can have a main 
    * method 
    */
    public static void main(String[] args) 
    {
    Picture beach = new Picture("Mr. Schmit.jpg");
    beach.explore();
    beach.zeroBlue();
    beach.explore();
    }
    
} // this } is the end of class Picture, put all new methods before this
