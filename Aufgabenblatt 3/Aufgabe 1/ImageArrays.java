import ch.unibas.informatik.cs101.ImageWindow;
import java.util.Random;

public class ImageArrays {
	static final int WIDTH = 500;
	static final int HEIGHT = 500;

	// col = 0 for red, 1 for green, 2 for blue
	static int[] mirroredChannelH(int col, int[] data) {
		int[] res = data.clone();
		for(int y = 0; y < HEIGHT; ++y) {
			for(int x = 0; x < WIDTH; ++x) {
				int destIndex = (HEIGHT * y + x) * 3;
				int sourceIndex = (HEIGHT * (HEIGHT-y-1) + x) * 3;
				res[destIndex+col] = data[sourceIndex+col];
			}
		}
		return res;
	}

	static int[] mirroredChannelV(int col, int[] data) {
		int[] res = data.clone();
		for(int y = 0; y < HEIGHT; ++y) {
			for(int x = 0; x < WIDTH; ++x) {
				int destIndex = (HEIGHT * y + x) * 3;
				int sourceIndex = (HEIGHT * y + (WIDTH-x-1)) * 3;

				res[destIndex+col] = data[sourceIndex+col];
			}
		}
		return res;
	}

	static void draw(ImageWindow w, int[] data) {
		for(int y = 0; y < HEIGHT; ++y) {
			for(int x = 0; x < WIDTH; ++x) {
				int pixIndex = (HEIGHT * y + x) * 3;

			 	int red = data[pixIndex];
			  int green = data[pixIndex+1];
			 	int blue = data[pixIndex+2];

			 	w.setPixel(x, y, red, green, blue);
		 	}
	 	}
	}

	static void mirrorColors(ImageWindow w, int[] imageData) {
		imageData = mirroredChannelH(0, imageData);
		imageData = mirroredChannelV(1, imageData);
		imageData = mirroredChannelV(2, imageData);
		imageData = mirroredChannelH(2, imageData);
		draw(w, imageData);
	}

	public static void main(String[] args) {
		//create & open the first window (to display the source image)
		ImageWindow sourceWindow= new ImageWindow(WIDTH,HEIGHT);
		sourceWindow.openWindow("source",0,0);
		//load the image
		sourceWindow.loadImage("horn.jpg");
		//redraw to see the image
		sourceWindow.redraw();
		/*  TODO: create an array that is large enough to hold
                 *       the complete image information
                 */
		int[] imageData = new int[WIDTH*HEIGHT*3];

		/*  TODO: store the complete image information in the
                 *        array you have created.
                 *  HINT: int red=sourceWindow.getPixelRed(xPosition, yPosition);
		 *        int green=sourceWindow.getPixelGreen(xPosition, yPosition);
		 *        int blue=sourceWindow.getPixelBlue(xPosition, yPosition);
	 	 */
		 for(int y = 0; y < HEIGHT; ++y) {
			 for(int x = 0; x < WIDTH; ++x) {
				 int red = sourceWindow.getPixelRed(x,y);
				 int green = sourceWindow.getPixelGreen(x,y);
				 int blue = sourceWindow.getPixelBlue(x,y);

				 int pixIndex = (HEIGHT * y + x) * 3;
				 imageData[pixIndex] = red;
				 imageData[pixIndex+1] = green;
				 imageData[pixIndex+2] = blue;
			 }
		 }

		//create & open the second windo (to draw your copy into)
		ImageWindow destinationWindow= new ImageWindow(WIDTH,HEIGHT);
		destinationWindow.openWindow("Image rotated by 90 degree",550,0);
		/*  TODO: write back your array data into the destinationWindow so that
                 *        it appears to be rotated 90 degrees.
                 *  HINT: destinationWindow.setPixel(xPos,yPos,red,green,blue);
		 */
		 for(int x = 0; x < WIDTH; ++x) {
			 for(int y = 0; y < HEIGHT; ++y) {
				 int pixIndex = (HEIGHT * y + x) * 3;

				 int red = imageData[pixIndex];
				 int green = imageData[pixIndex+1];
				 int blue = imageData[pixIndex+2];

				 destinationWindow.setPixel(y, x, red, green, blue);
			 }
		 }




                // redraw to see the changed image
                destinationWindow.redraw();
                // Create another output window
		ImageWindow destinationWindow2= new ImageWindow(500,500);
		destinationWindow2.openWindow("Image with permuted color channels",0,550);
                /*  TODO: call here your function which permutes the color channels
                 */
		mirrorColors(destinationWindow2, imageData);
                // redraw to see the changed image
		destinationWindow2.redraw();
	}


        /*  TODO: implement here the function to rotate the color channels.
         *  HINT: think about the arguments you need for the function.
         */



}
