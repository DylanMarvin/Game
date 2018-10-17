
package javanetworking;

public class Window {
    static final int XBORDER = 0;
    static final int YBORDER = 0;
    static final int YTITLE = 25;
    static final int WINDOW_WIDTH = 960;
    static final int WINDOW_HEIGHT = 985;
    static final int WINDOW_BORDER = 8; 

    static int MIN_X;
    static int MAX_X;
    static int MIN_Y;
    static int MAX_Y;

    static final double frameRate = 60.0;
    static boolean animateFirstTime = true;
    static int xsize = -1;
    static int ysize = -1;
    
        // ///////////////////////////////////////////////////////////////////////
    public static int getX(int x)
    {
        return (x + XBORDER);
    }

    public static int getY(int y)
    {
        return (y + YBORDER + YTITLE);
    }

    public static int getYNormal(int y)
    {
        return (-y + YBORDER + YTITLE + getHeight2());
    }

    public static int getWidth2()
    {
        return (xsize - getX(0) - XBORDER);
    }

    public static int getHeight2()
    {
        return (ysize - getY(0) - YBORDER);
    }
}

