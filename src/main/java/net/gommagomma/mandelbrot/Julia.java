package net.gommagomma.mandelbrot;


public class Julia
{
    static public void main(String[] args)
    {
    	Mandelbrot.PLOT_INTERMEDIATE_ITERATIONS = 100;
    	Mandelbrot.CREATE_INTERMEDIATE_FILES = false;
    	Mandelbrot.DISPLAY_ON_WINDOW = false;
    	Mandelbrot.DISPLAY_ON_CONSOLE = false;
    	Mandelbrot.COLOR_MODE = 2;

    	// *******************************************************************************************************
    	// plot the 12 Julia sets
    	// *******************************************************************************************************
    	Mandelbrot.delta = 0.0000001D;
    	Mandelbrot.MAXEMPTYIT= 100;

    	//Mandelbrot.FILE_FOLDER = "./exports/Julia_sets_0.002/"; double thik = 0.002; // low-resolution (about 5')
    	//Mandelbrot.FILE_FOLDER = "./exports/Julia_sets_0.001/"; double thik = 0.001; // low-resolution (about 23')
    	//Mandelbrot.FILE_FOLDER = "./exports/Julia_sets_0.0005/"; double thik = 0.0005; // mid-resolution (about 52')
    	Mandelbrot.FILE_FOLDER = "./exports/Julia_sets_0.00025/"; double thik = 0.00025; // hi-resolution (about ???')

    	// ** Julia set: c=-0.11031, -0.67037i ********************************
    	Mandelbrot.c = new ComplexNumber(-0.11031, -0.67037);
    	Mandelbrot.dX = 3.80;  Mandelbrot.X0=1.90; Mandelbrot.dY = 3.0; Mandelbrot.Y0 = 1.5;
    	new Mandelbrot(thik);

    	// ** Julia set: parabolico, c=-1.25 **********************************
    	Mandelbrot.c = new ComplexNumber(-1.25, 0);
    	Mandelbrot.dX = 3.80;  Mandelbrot.X0=1.90; Mandelbrot.dY = 2.0; Mandelbrot.Y0 = 1.0;
    	new Mandelbrot(thik);

    	// ** Julia set: c=-0.74543, 0.11301i *********************************
    	Mandelbrot.c = new ComplexNumber(-0.74543, 0.11301);
    	Mandelbrot.dX = 3.80;  Mandelbrot.X0=1.90; Mandelbrot.dY = 2.4; Mandelbrot.Y0 = 1.2;
    	new Mandelbrot(thik);

    	// ** Julia set: c=-0.194, 0.6557i ************************************
    	Mandelbrot.c = new ComplexNumber(-0.194, 0.6557);
    	Mandelbrot.dX = 3.80;  Mandelbrot.X0=1.90; Mandelbrot.dY = 2.8; Mandelbrot.Y0 = 1.4;
    	new Mandelbrot(thik);

    	// ** Julia set: c=-0.11, 0.6557i *************************************
    	Mandelbrot.c = new ComplexNumber(-0.11, 0.6557);
    	Mandelbrot.dX = 3.80;  Mandelbrot.X0=1.90; Mandelbrot.dY = 2.9; Mandelbrot.Y0 = 1.45;
    	new Mandelbrot(thik);

    	// ** Julia set: c=-0.12, 0.74i ***************************************
    	Mandelbrot.c = new ComplexNumber(-0.12, 0.74);
    	Mandelbrot.dX = 3.80;  Mandelbrot.X0=1.90; Mandelbrot.dY = 2.9; Mandelbrot.Y0 = 1.45;
    	new Mandelbrot(thik);

    	// ** Julia set: c=-0, i **********************************************
    	Mandelbrot.c = new ComplexNumber(0, 1);
    	Mandelbrot.dX = 3.80;  Mandelbrot.X0=1.90; Mandelbrot.dY = 3.0; Mandelbrot.Y0 = 1.5;
    	new Mandelbrot(thik);

    	// ** Julia set: c=0.31, 0.04i ****************************************
    	Mandelbrot.c = new ComplexNumber(0.31, 0.04);
    	Mandelbrot.dX = 3.80;  Mandelbrot.X0=1.90; Mandelbrot.dY = 3.1; Mandelbrot.Y0 = 1.55;
    	new Mandelbrot(thik);

    	// ** Julia set: c=0.27334, 0.00742i **********************************
    	Mandelbrot.c = new ComplexNumber(0.27334, 0.00742);
    	Mandelbrot.dX = 3.80;  Mandelbrot.X0=1.90; Mandelbrot.dY = 3.1; Mandelbrot.Y0 = 1.55;
    	new Mandelbrot(thik);

    	// ** Julia set: c=-0.481762, -0.531657i ******************************
    	Mandelbrot.c = new ComplexNumber(-0.481762, -0.531657);
    	Mandelbrot.dX = 3.80;  Mandelbrot.X0=1.90; Mandelbrot.dY = 2.9; Mandelbrot.Y0 = 1.45;
    	new Mandelbrot(thik);

    	// ** Julia set: c=-0.39054, -0.58679i ********************************
    	Mandelbrot.c = new ComplexNumber(-0.39054, -0.58679);
    	Mandelbrot.dX = 3.80;  Mandelbrot.X0=1.90; Mandelbrot.dY = 2.9; Mandelbrot.Y0 = 1.45;
    	new Mandelbrot(thik);

    	// ** Julia set: c=-0.15652, -1.03225i ********************************
    	Mandelbrot.c = new ComplexNumber(-0.15652, -1.03225);
    	Mandelbrot.dX = 3.80;  Mandelbrot.X0=1.90; Mandelbrot.dY = 3.0; Mandelbrot.Y0 = 1.5;
    	new Mandelbrot(thik);
    }
}
