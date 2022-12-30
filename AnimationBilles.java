package exodecorateur_angryballs.maladroit;

import java.util.Vector;

import exodecorateur_angryballs.maladroit.Modele.Bille;
import exodecorateur_angryballs.maladroit.Simulation.DivisionMode;
import exodecorateur_angryballs.maladroit.Simulation.FusionMode;
import exodecorateur_angryballs.maladroit.Simulation.Mode;
import exodecorateur_angryballs.maladroit.Simulation.PresentationSujetMode;
import exodecorateur_angryballs.maladroit.Vues.VueBillard;

/**
 * responsable de l'animation des billes, c-�-d responsable du mouvement de la liste des billes. met perp�tuellement � jour les billes. 
 * g�re le d�lai entre 2 mises � jour (deltaT) et pr�vient la vue responsable du dessin des billes qu'il faut mettre � jour la sc�ne
 * 
 * ICI : IL N'Y A RIEN A CHANGER
 * */
public class AnimationBilles  implements Runnable
{


    Vector<Bille> billes;
    VueBillard cadre;
    private Thread thread;
    public static boolean running;
    private Mode mode;

    private void initializedBilles()
    {
        this.arr�terAnimation();
        //On indique au billard les billes pr�sentes selon le mode
        this.mode.genererBilles(cadre);
        this.billes = mode.getBilles();
        this.cadre.getBillard().setBilles(billes);
        this.lancerAnimation();
    }

    public AnimationBilles(Vector<Bille> billes, VueBillard cadre)
    {
        this.billes = billes;
        this.cadre = cadre;
        this.thread = null;
        running = true;
        this.cadre.setAnimationBilles(this);
    }

    public AnimationBilles(VueBillard cadre)
    {
        this.cadre = cadre;
        this.thread = null;
        running = true;
        this.cadre.setAnimationBilles(this);

        //On choisit le mode, par defaut il s'agit des billes demand�es dans le sujet
        this.setPresentationSujetMode();
    }

    public void setPresentationSujetMode() {
        mode = new PresentationSujetMode(cadre);
        initializedBilles();
    }

    public void setFusionMode() {
        mode = new FusionMode(cadre);
        initializedBilles();
    }

    public void setDivisionMode() {
        mode = new DivisionMode(cadre);
        initializedBilles();
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
                        billeCourante.d�placer(deltaT);
                        billeCourante.gestionAcc�l�ration(billes);
                        billeCourante.gestionCollisionBilleBille(billes);
                        billeCourante.collisionContour( 0, 0, cadre.largeurBillard(), cadre.hauteurBillard());

                    }

                cadre.miseAJour();
                Thread.sleep((int)deltaT);
            }

        }

        catch (InterruptedException e)
        {
            //System.out.println("\Arret du thread");
        }
    }

    static double maxVitessesCarr�es(Vector<Bille> billes) {
        double vitesse2Max = 0;

        int i;
        double vitesse2Courante;

        for ( i = 0; i < billes.size(); ++i)
            if ( (vitesse2Courante = billes.get(i).getVitesse().normeCarr�e()) > vitesse2Max)
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

    public void arr�terAnimation() {
        if (thread != null)
            {
            this.thread.interrupt();
            this.thread = null;
            }
    }

    public void quitter() {
        running = false;
        cadre.getGraphicsDevice().setFullScreenWindow(null);
        System.exit(0);
    }

}
