package exodecorateur_angryballs.maladroit;

import java.awt.*;
import java.util.Random;
import java.util.Vector;

import exodecorateur_angryballs.maladroit.Modele.Bille;
import exodecorateur_angryballs.maladroit.Simulation.*;
import exodecorateur_angryballs.maladroit.Vues.VueBillard;

public class AnimationBilles  implements Runnable
{


    Vector<Bille> billes;
    VueBillard cadre;
    private Thread thread;
    public static boolean running;
    private Simulation simulation;

    // Objects needed for rendering...
    Graphics graphics = null;
    Graphics2D g2d = null;
    Color background = Color.BLACK;
    Random rand = new Random();

    // Variables for counting frames per seconds
    int fps = 0;
    int frames = 0;
    long totalTime = 0;
    long curTime = System.currentTimeMillis();
    long lastTime = curTime;


    private void initializedBilles()
    {
        this.arrêterAnimation();
        //On indique au billard les billes présentes selon le mode
        this.simulation.genererBilles(cadre);
        this.billes = simulation.getBilles();
        this.cadre.getBillard().setBilles(billes);

        cadre.miseAJour();
    }

    public AnimationBilles(VueBillard cadre)
    {
        this.cadre = cadre;
        this.thread = null;
        running = true;
        this.cadre.setAnimationBilles(this);

        //On choisit le mode, par defaut il s'agit des billes demandées dans le sujet
        this.setPresentationSujetMode();
    }

    public void setPresentationSujetMode() {
        simulation = new ParDefautSimulation(cadre);
        initializedBilles();
        cadre.getCadre().présentation.setText("\tPrésentation des billes demandées dans le sujet avec un son stéréo lors des collisions. | Bille orange = bille pilotée.");
    }

    public void setFusionMode() {
        simulation = new FusionSimulation(cadre);
        initializedBilles();
        cadre.getCadre().présentation.setText("\tLes billes fusionnent en 1 unique bille plus large lors d'une collision. | Toutes pilotables.");
    }

    public void setDivisionMode() {
        simulation = new DivisionSimulation(cadre);
        initializedBilles();
        cadre.getCadre().présentation.setText("\tLes billes se divisent en 4 billes plus petites lors d'une collision. | Toutes pilotables.");
    }

    public void setMixteMode() {
        simulation = new MixteSimulation(cadre);
        initializedBilles();
        cadre.getCadre().présentation.setText("\tBilles roses = fantomes. | Billes noirs = couleur modifiable. | Billes rouges = son collision. | Toutes pilotables.");
    }

    public void resetSimulation() {
        this.initializedBilles();
    }

    @Override
    public void run()
    {
        try
        {
            double deltaT;
            Bille billeCourante;

            while (running)
            {
                deltaT = 10;

                int i;
                for ( i = 0; i < billes.size(); ++i)
                    {
                        billeCourante = billes.get(i);
                        billeCourante.déplacer(deltaT);
                        billeCourante.gestionAccélération(billes);
                        billeCourante.gestionCollisionBilleBille(billes);
                        billeCourante.collisionContour( 0, 0, cadre.largeurBillard(), cadre.hauteurBillard());

                    }

                cadre.miseAJour();
                Thread.sleep((int)deltaT);
            }

        }

        catch (InterruptedException e)
        {
            //System.out.println("\nArret du thread");
        }
    }

    static double maxVitessesCarrées(Vector<Bille> billes) {
        double vitesse2Max = 0;

        int i;
        double vitesse2Courante;

        for ( i = 0; i < billes.size(); ++i)
            if ( (vitesse2Courante = billes.get(i).getVitesse().normeCarrée()) > vitesse2Max)
               vitesse2Max = vitesse2Courante;

        return vitesse2Max;
    }

    static double minRayons(Vector<Bille> billes) {
        double rayonMin, rayonCourant;

        rayonMin = Double.MAX_VALUE;

        int i;
        for ( i = 0; i < billes.size(); ++i)
            if ( ( rayonCourant = billes.get(i).getRayon()) < rayonMin)
               rayonMin = rayonCourant;

        return rayonMin;
    }


    public void lancerAnimation() {
        if (this.thread == null)
            {
            this.thread = new Thread(this);
            thread.start();
            }
    }

    public void arrêterAnimation() {
        if (thread != null)
            {
            this.thread.interrupt();
            this.thread = null;
            }
    }

    public void quitter() {
        try {
            running = false;
            cadre.getGraphicsDevice().setFullScreenWindow(null);
        } finally {
            System.exit(0);
        }
    }

}
