/*
 * ComplexNumber.java
 *  __ _ ___ _ __  _ __  __ _ __ _ ___ _ __  _ __  __ _ 
 * / _` / _ \ '  \| '  \/ _` / _` / _ \ '  \| '  \/ _` |
 * \__, \___/_|_|_|_|_|_\__,_\__, \___/_|_|_|_|_|_\__,_|
 * |___/                     |___/                      
 *
 * gommagomma.net - Mandelbrot
 */


package net.gommagomma.mandelbrot;


public class ComplexNumber
{
    double real, imaginary;


    /**
     * Constructs a new ComplexNumber with the given real and imaginary parts.
     * @param real the real part of the complex number
     * @param imaginary the imaginary part of the complex number
     */
    public ComplexNumber(double real, double imaginary)
    {
    	this.real = real;
    	this.imaginary = imaginary;
    }


    /**
     * Returns the real part of this complex number.
     * @return the real part of this complex number
     */
    public double getRe()
    {
    	return this.real;
    }


    /**
     * Returns the imaginary part of this complex number.
     * @return the imaginary part of this complex number
     */
    public double getIm()
    {
    	return this.imaginary;
    }


    /**
     * Adds to the current complex number the given one.
     * @param z the complex number to adds to the current one
     * @return the result of the operation
     */
    public ComplexNumber add(ComplexNumber z)
    {
    	this.real += z.getRe();
    	this.imaginary += z.getIm();

    	return this;
    }


    /**
     * Subtracts from the current complex number the given one.
     * @param z the complex number to subs from the current one
     * @return the result of the operation
     */
    public ComplexNumber sub(ComplexNumber z)
    {
    	this.real -= z.getRe();
    	this.imaginary -= z.getIm();

    	return this;
    }


    /**
     * Multiplies this complex number by the given one.
     * @param z the complex number to multiply to the current one
     * @return the new complex number result of the operation
     */
    public ComplexNumber mul(ComplexNumber z)
    {
    	double real = this.real * z.getRe() - this.imaginary * z.getIm();
    	double imaginary = this.real * z. getIm() + this.getIm() * z.getRe();
    	this.real = real;
    	this.imaginary = imaginary;

    	return this;
    }


    /**
     * Conjugates the current complex number.
     * @return the conjugate
     */
    public ComplexNumber conjugate()
    {
    	this.imaginary = -this.imaginary;
        return this;
    }


    /**
     * Divides this complex number by the given one.
     * @param z the complex number to divide by the current one
     * @return the new complex number result of the operation
     */
    public ComplexNumber div(ComplexNumber z)
    {
    	double div = Math.pow(z.mod(),2);
    	this.mul(z.conjugate());
    	this.real /= div;
    	this.imaginary /= div;
        return this;
    }


    /**
     * Calculates the z^power of this complex number.
     * @param power the integer power
     * @return the new complex number result of the operation
     */
    public ComplexNumber pow(int power)
    {
    	ComplexNumber z = new ComplexNumber(this.getRe(), this.getIm());
    	for (int ii=1; ii<power; ii++)
    	{
    		z.mul(this);
    	}

    	this.real = z.getRe();
    	this.imaginary = z.getIm();

    	return this;
    }


    /**
     * Calculates the square root of this complex number
     * @return the new complex number result of the operation
     */
    public ComplexNumber square()
    {
        double re = this.real*this.real - this.imaginary*this.imaginary;
        double im = 2*this.real*this.imaginary;
        this.real = re;
        this.imaginary = im;

        return this;
    }


    /**
     * Returns the module of this complex number,
     * @return the module of this complex number
     */
    public double mod()
    {
        return Math.sqrt(Math.pow(this.real, 2) + Math.pow(this.imaginary, 2));
    }


    @Override
    public final boolean equals(Object z) 
    {
        return (z instanceof ComplexNumber && this.real == ((ComplexNumber)z).getRe()) && (this.imaginary == ((ComplexNumber)z).getIm());
    }


    @Override
    public String toString()
    {
    	StringBuilder s = new StringBuilder();

//    	s.append("(").append(this.real).append(",").append(this.imaginary).append(")");
    	s.append(this.real);
    	if (this.imaginary >= 0) { s.append("+"); }
    	s.append(this.imaginary).append("i");

    	return s.toString();
    }
}
