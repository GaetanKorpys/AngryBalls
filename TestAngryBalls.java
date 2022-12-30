package exodecorateur_angryballs.maladroit;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.Vector;
import java.awt.GraphicsDevice;

import exodecorateur_angryballs.maladroit.Ecouteur.EcouteurBoutonArreter;
import exodecorateur_angryballs.maladroit.Ecouteur.EcouteurBoutonFusion;
import exodecorateur_angryballs.maladroit.Ecouteur.EcouteurBoutonLancer;
import exodecorateur_angryballs.maladroit.Ecouteur.EcouteurBoutonQuitter;
import exodecorateur_angryballs.maladroit.Modele.*;
import exodecorateur_angryballs.maladroit.Modele.Decorateur.BilleFrottement;
import exodecorateur_angryballs.maladroit.Modele.Decorateur.BilleMRU;
import exodecorateur_angryballs.maladroit.Modele.Decorateur.BilleNewton;
import exodecorateur_angryballs.maladroit.Modele.Decorateur.BillePesanteur;
import exodecorateur_angryballs.maladroit.Modele.Decorateur.BillePilote;
import exodecorateur_angryballs.maladroit.Modele.Decorateur.BilleArret;
import exodecorateur_angryballs.maladroit.Modele.Decorateur.BillePasseMuraille;
import exodecorateur_angryballs.maladroit.Modele.Decorateur.BilleRebond;
import exodecorateur_angryballs.maladroit.Modele.Decorateur.BilleHurlante;
import exodecorateur_angryballs.maladroit.Modele.Decorateur.BilleSonCollision;
import exodecorateur_angryballs.maladroit.Vues.VueBillard;
import mesmaths.geometrie.base.Vecteur;
import musique.SonLong;
import exodecorateur_angryballs.maladroit.Vues.CadreAngryBalls;


/**
 * Gestion d'une liste de billes en mouvement ayant toutes un comportement différent
 * 
 * Idéal pour mettre en place le DP decorator
 * */
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
                System.out.println("\nErreur");
                //e.printStackTrace();
                myDevice.setFullScreenWindow(null);
            }

        cadre.setGraphics(myDevice);
    }

    public static void main(String[] args)
    {

        CadreAngryBalls cadre = new CadreAngryBalls("Angry balls Gaetan Korpys", "Animation de billes ayant des comportements différents. Situation idéale pour mettre en place le DP Decorator");
        cadre.montrer();

        AnimationBilles animationBilles = new AnimationBilles(cadre);

        configurationDisplay(cadre);

        //----------------------- mise en place des écouteurs de boutons qui permettent de contrôler (un peu...) l'application -----------------

        EcouteurBoutonLancer ecouteurBoutonLancer = new EcouteurBoutonLancer(animationBilles);
        EcouteurBoutonArreter ecouteurBoutonArrêter = new EcouteurBoutonArreter(animationBilles);
        EcouteurBoutonQuitter ecouteurBoutonQuitter = new EcouteurBoutonQuitter(animationBilles);
        EcouteurBoutonFusion ecouteurBoutonFusion = new EcouteurBoutonFusion(animationBilles);


        //------------------------- activation des écouteurs des boutons et ça tourne tout seul ------------------------------

        cadre.lancerBilles.addActionListener(ecouteurBoutonLancer);
        cadre.arrêterBilles.addActionListener(ecouteurBoutonArrêter);
        cadre.quitter.addActionListener(ecouteurBoutonQuitter);
        cadre.fusion.addActionListener(ecouteurBoutonFusion);
    }
}
