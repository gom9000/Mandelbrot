/*
 * ComplexPlane.java
 *  __ _ ___ _ __  _ __  __ _ __ _ ___ _ __  _ __  __ _ 
 * / _` / _ \ '  \| '  \/ _` / _` / _ \ '  \| '  \/ _` |
 * \__, \___/_|_|_|_|_|_\__,_\__, \___/_|_|_|_|_|_\__,_|
 * |___/                     |___/                      
 *
 * gommagomma.net - Mandelbrot
 */


package net.gommagomma.mandelbrot;


public class ComplexPlane
{
	private int sizeX, sizeY;
	private double thik;
	private ComplexNumber plane[][];
	private int x0, y0;


    public ComplexPlane(int sizeX, int sizeY, double thik)
    {
    	this(sizeX, sizeY, (int)(sizeX/2), (int)(sizeY/2), thik);
    }


    public ComplexPlane(int sizeX, int sizeY, int x0, int y0, double thik)
    {
    	this.sizeX = sizeX;
    	this.sizeY = sizeY;
    	this.thik = thik;
    	this.plane = new ComplexNumber[sizeX][sizeY];
    	setOrigin(x0, y0);
    }


    public void setOrigin(int x0, int y0)
    {
    	this.x0 = x0;
    	this.y0 = y0;
    	buildPlanePoints();
    }


    private void buildPlanePoints()
    {
    	for (int ii = 0; ii < sizeX; ii++)
    	{
    		for (int jj = 0; jj < sizeY; jj++)
    		{
    			plane[ii][jj] = new ComplexNumber((ii - x0) * thik, (y0 - jj) * thik);
    		}
    	}
    }


    public void setRelativePoint(int x, int y, ComplexNumber z)
    {
    	plane[x0 + x][y0 - y] = z;
    }


    public void setAbsolutePoint(int x, int y, ComplexNumber z)
    {
    	plane[x][y] = z;
    }


    public ComplexNumber getRelativePoint(int x, int y)
    {
    	return plane[x0 + x][y0 - y];
    }


    public ComplexNumber getAbsolutePoint(int x, int y)
    {
    	return plane[x][y];
    }


	public int getSizeX()
	{
		return sizeX;
	}


	public int getSizeY()
	{
		return sizeY;
	}


	public ComplexNumber getRelativePoint0(int x, int y)
	{
		return new ComplexNumber(x * thik, y * thik);
	}


	public ComplexNumber getAbsolutePoint0(int x, int y)
	{
		return new ComplexNumber((x - x0) * thik, (y0 - y) * thik);
	}
}
