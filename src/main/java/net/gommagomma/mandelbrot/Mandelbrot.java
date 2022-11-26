/*
 * Mandelbrot.java
 *  __ _ ___ _ __  _ __  __ _ __ _ ___ _ __  _ __  __ _ 
 * / _` / _ \ '  \| '  \/ _` / _` / _ \ '  \| '  \/ _` |
 * \__, \___/_|_|_|_|_|_\__,_\__, \___/_|_|_|_|_|_\__,_|
 * |___/                     |___/                      
 *
 * gommagomma.net - Mandelbrot
 */


package net.gommagomma.mandelbrot;


import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;


public class Mandelbrot
{
	public static double dX = 3.05, dY = 2.7, X0 = 2.025, Y0 = 1.35;
	public static double SUPLIMIT = 2.0;
	public static int MAXITERATIONS = 10000;
	public static int MAXEMPTYIT = 10;
	public static int PLOT_INTERMEDIATE_ITERATIONS = 0;
	public static boolean DISPLAY_ON_WINDOW = false;
	public static boolean DISPLAY_ON_CONSOLE = false;
	public static boolean CREATE_INTERMEDIATE_FILES = false;
	public static String FILE_FOLDER = "./";
	public static int COLOR_MODE = 0;
	public static double delta = 0.00001D;
	public static ComplexNumber c = null;

	private int sizeX, sizeY, x0, y0;
	private double thik;
	private int iteration;
	private ComplexPlane plane;
	private int mset[][];
	private boolean hasPotentialDivergentPoints = false;
	int iterationDivergentPoints, iterationPotentialDivergentPoints;
	int emptyIterationStack = 0;


	public Mandelbrot(double x0, double y0, int size, double thik)
	{
		this(size, size, (int)(x0/thik), (int)(y0/thik), thik);
	}


	public Mandelbrot(double thik)
	{
		this((int)(dX/thik), (int)(dY/thik), (int)(X0/thik), (int)(Y0/thik), thik);
	}


    public Mandelbrot(int sizeX, int sizeY, int x0, int y0, double thik)
    {
    	this.iteration = 0;
    	this.hasPotentialDivergentPoints = true;
    	this.iterationDivergentPoints = 1;
    	this.iterationPotentialDivergentPoints = 1;
    	this.sizeX = sizeX;
    	this.sizeY = sizeY;
    	this.x0 = x0;
    	this.y0 = y0;
    	this.thik = thik;

    	System.out.println("start time: " + new java.util.Date());
    	System.out.println("sizeX: "+sizeX+", sizeY: "+sizeY+", x0: "+x0+", y0: "+y0+", thik: "+thik+", iterations: "+MAXITERATIONS+", mode: "+COLOR_MODE);

    	this.plane = new ComplexPlane(sizeX, sizeY, x0, y0, thik);
    	this.mset = new int[sizeX][sizeY];

    	initializeMset();

    	JFrame frame = null;
    	if (DISPLAY_ON_WINDOW)
    	{
    		frame = new JFrame();
    		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    		frame.setSize(plane.getSizeX()+100, plane.getSizeY()+100);
    		frame.setVisible(true);
    	}

    	System.out.println("begin iterations time: " + new java.util.Date());

    	while (this.hasPotentialDivergentPoints && this.iteration < MAXITERATIONS)
    	{
    		this.iteration++;
    		this.hasPotentialDivergentPoints = false;

    		doIteration();
    		updateMset();

    		System.out.println("iteration: " + this.iteration + ", iterationDivergentPoints: " + iterationDivergentPoints + ", iterationPotentialDivergentPoints: " + iterationPotentialDivergentPoints);

    		if (PLOT_INTERMEDIATE_ITERATIONS > 0 && iteration%PLOT_INTERMEDIATE_ITERATIONS == 0)
    		{
    			if (CREATE_INTERMEDIATE_FILES)
    			{
    				plotOnFile();
    			}
    			if (DISPLAY_ON_WINDOW) { plotOnWindow(frame); }
    			if (DISPLAY_ON_CONSOLE) { plotOnConsole(); }
    		}

    		if (iterationDivergentPoints == 0) emptyIterationStack++; else emptyIterationStack = 0;
    		if (emptyIterationStack%MAXEMPTYIT < 1 && (double)iterationDivergentPoints/iterationPotentialDivergentPoints < delta) break;
    	}

    	plotOnFile();
		if (DISPLAY_ON_WINDOW) { plotOnWindow(frame); }
		if (DISPLAY_ON_CONSOLE) { plotOnConsole(); }

		System.out.println("total iterations: " + this.iteration);
		System.out.println("stop time: " + new java.util.Date());
    }


    private void doIteration()
    {
    	iterationPotentialDivergentPoints = 0;
    	for (int ii = 0; ii < plane.getSizeX(); ii++)
    	{
    		for (int jj = 0; jj < plane.getSizeY(); jj++)
    		{
    			if (plane.getAbsolutePoint(ii, jj).mod() <= SUPLIMIT)
    			{
    				if (c != null)
    				{
    					// julia set
    				    plane.setAbsolutePoint(ii, jj, plane.getAbsolutePoint(ii, jj).pow(2).add(c));
    				} else {
    					// mandelbrot set
    	                plane.setAbsolutePoint(ii, jj, plane.getAbsolutePoint(ii, jj).pow(2).add(plane.getAbsolutePoint0(ii, jj)));    					
    				}
    	            iterationPotentialDivergentPoints++;
    	            hasPotentialDivergentPoints = true;
    			}
    		}
    	}
    }


    private void updateMset()
    {
    	iterationDivergentPoints = 0;
    	for (int ii = 0; ii < plane.getSizeX(); ii++)
    	{
    		for (int jj = 0; jj < plane.getSizeY(); jj++)
    		{
    			if (mset[ii][jj] == 0 && plane.getAbsolutePoint(ii, jj).mod() > SUPLIMIT)
    			{
    				mset[ii][jj] = iteration;
    				iterationDivergentPoints++;
    			}
    		}
    	}
    }


    private void initializeMset()
    {
    	for (int ii = 0; ii < plane.getSizeX(); ii++)
    	{
    		for (int jj = 0; jj < plane.getSizeY(); jj++)
    		{
    			mset[ii][jj] = 0;
    		}
    	}
    }


    private BufferedImage createImage(int colorMode)
    {
    	BufferedImage bufferedImage = new BufferedImage(plane.getSizeX(), plane.getSizeY(), BufferedImage.TYPE_INT_RGB);
    	int r, g, b;

    	if (colorMode== 0) // negative B&W image (edges and florescence)
    	{
    		for (int jj = 0; jj < plane.getSizeY(); jj++)
    		{
    			for (int ii = 0; ii < plane.getSizeX(); ii++)
    			{
    				r = mset[ii][jj] % 256; g=r; b=r;
    				if (mset[ii][jj] > 255) { r=200; g=r; b=r; }
    				if (mset[ii][jj] == 0) { r=0; g=0; b=0; }
    				bufferedImage.setRGB(ii, jj, new Color(r, g, b).getRGB());
    			}
    		}
    	}
    	else if (colorMode== 1) // positive B&W image (inside)
    	{    		
    		for (int jj = 0; jj < plane.getSizeY(); jj++)
    		{
    			for (int ii = 0; ii < plane.getSizeX(); ii++)
    			{
    				r = 255 - mset[ii][jj] % 256; g=r; b=r;
    				if (mset[ii][jj] > 255) { r=100; g=r; b=r; }
    				if (mset[ii][jj] == 0) { r=0; g=0; b=0; }
    				bufferedImage.setRGB(ii, jj, new Color(r, g, b).getRGB());
    			}
    		}
    	}
    	else if (colorMode== 2) // negative B&W image (veils +  bands)
    	{    		
    		for (int jj = 0; jj < plane.getSizeY(); jj++)
    		{
    			for (int ii = 0; ii < plane.getSizeX(); ii++)
    			{
    				r = mset[ii][jj] % 256; g=4*r+20; b=4*r+20;
    				if (mset[ii][jj] > 255) { r=255; g=r; b=r; }
    				if (g > 255 || b > 255) { g=255; b=255; }
    				if (mset[ii][jj] == 0) { r=0; g=0; b=0; }
    				bufferedImage.setRGB(ii, jj, new Color(r, g, b).getRGB());
    			}
    		}
    	}

    	return bufferedImage;
    }


    private void plotOnConsole()
    {
    	System.out.println("iteration: " + this.iteration);
    	for (int jj = 0; jj < plane.getSizeY(); jj++)
    	{
    		for (int ii = 0; ii < plane.getSizeX(); ii++)
    		{
    			System.out.print("["+mset[ii][jj]+"]");
    		}
    		System.out.println();
    	}
    	System.out.println();
    }


    private void plotOnWindow(JFrame frame)
    {
        frame.setTitle("iteration: " + iteration);
    	frame.getGraphics().drawImage(createImage(COLOR_MODE), 0, 0, null);
    }


    private void plotOnFile()
    {
    	String name = (c != null)? "julia" : "mandelbrot";
    	try {
        	File folder = new File(FILE_FOLDER);
            if (! folder.exists()) folder.mkdir();
			ImageIO.write(createImage(COLOR_MODE), "PNG", new File(folder.getPath()+"/"+name+"_"+sizeX+"_"+sizeY+"_"+x0+"_"+y0+"_"+thik+"_["+c+"]_"+iteration+"_"+COLOR_MODE+".png"));
		} catch (IOException ignore) {}
    }


    static public void main(String[] args)
    {
    	Mandelbrot.PLOT_INTERMEDIATE_ITERATIONS = 100;
    	Mandelbrot.CREATE_INTERMEDIATE_FILES = false;
    	Mandelbrot.DISPLAY_ON_WINDOW = false;
    	Mandelbrot.DISPLAY_ON_CONSOLE = false;
    	Mandelbrot.COLOR_MODE = 2;


    	// *******************************************************************************************************
    	// plot the Mandelbrot set: c=z0
    	// *******************************************************************************************************
    	Mandelbrot.delta = 0.0000001D;
    	Mandelbrot.MAXEMPTYIT= 100;
    	Mandelbrot.c = null;
    	Mandelbrot.dX = 3.05;  Mandelbrot.X0=2.025; Mandelbrot.dY = 2.7; Mandelbrot.Y0 = 1.35;
    	Mandelbrot.FILE_FOLDER = "./exports/";

    	new Mandelbrot(0.002); // low-resolution
    	//new Mandelbrot(0.001); // mid-resolution 11m
    	//new Mandelbrot(0.0005); // hi-resolution 47m
    	//new Mandelbrot(0.0002); // hi2-resolution - 1h15m - sizeX: 15249, sizeY: 13500, x0: 10124, y0: 6750
    	//new Mandelbrot(0.00012); // sizeX: 20333, sizeY: 18000, x0: 13500, y0: 9000, it=499  1h 25M
    	//new Mandelbrot(0.00011); // hi-resolution - sizeX: 27727, sizeY: 24545, x0: 18409, y0: 12272 , it=3285 1h41 30M
    	// *******************************************************************************************************



    	// *******************************************************************************************************
    	// plot some Mandelbrot set details
    	// *******************************************************************************************************
    	Mandelbrot.delta = 0.0000001D;
    	Mandelbrot.MAXEMPTYIT= 10;
    	Mandelbrot.c = null;
    	Mandelbrot.dX = 3.05;  Mandelbrot.X0=2.025; Mandelbrot.dY = 2.7; Mandelbrot.Y0 = 1.35;
    	Mandelbrot.FILE_FOLDER = "./exports/mdetails";
    	//new Mandelbrot(Mandelbrot.dX-1.245, Mandelbrot.Y0-1.31, 20000, 0.000004); // detail 1
    	//new Mandelbrot(Mandelbrot.dX-1.245, Mandelbrot.Y0-1.305, 20000, 0.000003); // detail 2
    	//new Mandelbrot(Mandelbrot.dX-1.246, Mandelbrot.Y0-1.30, 20000, 0.000002); // detail 3 - sizeX: 20000, sizeY: 20000, x0: 902000, y0: 25000
    	//new Mandelbrot(Mandelbrot.dX-1.27, Mandelbrot.Y0-1.323, 20000, 0.000001); // detail 4 - sizeX: 20000, sizeY: 20000, x0: 1780000, y0: 27000
    	// *******************************************************************************************************
    }
}
