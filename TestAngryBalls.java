package exodecorateur_angryballs.maladroit;

import java.awt.*;
import java.awt.GraphicsDevice;

import exodecorateur_angryballs.maladroit.Observeur.Souscripteur.CadreAngryBalls;


public class TestAngryBalls
{

    public static void configurationDisplay(CadreAngryBalls cadre){

        DisplayMode oldDisplayMode, newDisplayMode;
        int w,h,r,b;

        // Get graphics configuration
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice myDevice = ge.getDefaultScreenDevice();
        GraphicsConfiguration gc = myDevice.getDefaultConfiguration();

        //Get old display
        oldDisplayMode = myDevice.getDisplayMode();

        w = oldDisplayMode.getWidth();
        h = oldDisplayMode.getHeight();
        r = oldDisplayMode.getRefreshRate();
        b = oldDisplayMode.getBitDepth();

        //Create new display
        newDisplayMode = new DisplayMode(w,h,32,DisplayMode.REFRESH_RATE_UNKNOWN);

        //Change to full screen
        if (myDevice.isFullScreenSupported())
            try {
                myDevice.setFullScreenWindow(cadre);
                /*if(myDevice.isDisplayChangeSupported())
                    myDevice.setDisplayMode(newDisplayMode);*/
            } catch (Exception e) {
                e.printStackTrace();
                myDevice.setFullScreenWindow(null);
            }

        cadre.setGraphics(myDevice);
    }

    public static void main(String[] args)
    {

        CadreAngryBalls cadre = new CadreAngryBalls("Angry balls Gaetan Korpys");
        cadre.montrer();

        AnimationBilles animationBilles = new AnimationBilles(cadre);

        //Le mode plein écran augmente les performances pour afficher les billes
        //A commenter pour passer en mode fenetré
        configurationDisplay(cadre);
    }
}
