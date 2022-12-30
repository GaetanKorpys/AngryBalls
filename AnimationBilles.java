package exodecorateur_angryballs.maladroit;

import java.util.Vector;

import exodecorateur_angryballs.maladroit.Modele.Bille;
import exodecorateur_angryballs.maladroit.Vues.VueBillard;

/**
 * responsable de l'animation des billes, c-à-d responsable du mouvement de la liste des billes. met perpétuellement à jour les billes. 
 * gère le délai entre 2 mises à jour (deltaT) et prévient la vue responsable du dessin des billes qu'il faut mettre à jour la scène
 * 
 * ICI : IL N'Y A RIEN A CHANGER
 * */
public class AnimationBilles  implements Runnable
{


    Vector<Bille> billes;   // la liste de toutes les billes en mouvement
    VueBillard vueBillard;    // la vue responsable du dessin des billes
    private Thread thread;    // pour lancer et arrêter les billes
    public static boolean running;


    private static final double COEFF = 0.5;

    /**
     * @param billes
     * @param vueBillard
     */
    public AnimationBilles(Vector<Bille> billes, VueBillard vueBillard)
    {
        this.billes = billes;
        this.vueBillard = vueBillard;
        this.thread = null;     //est-ce utile ?
        running = true;

        this.vueBillard.setAnimationBilles(this);
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
                        billeCourante.collisionContour( 0, 0, vueBillard.largeurBillard(), vueBillard.hauteurBillard());

                    }

                vueBillard.miseAJour();
                Thread.sleep((int)deltaT);
            }

        }

        catch (InterruptedException e)
        {
            //System.out.println("\Arret du thread");
        }
    }

    /**
     * calcule le maximum de de la norme carrée (pour faire moins de calcul) des vecteurs vitesse de la liste de billes
     *
     * */
    static double maxVitessesCarrées(Vector<Bille> billes)
    {
    double vitesse2Max = 0;

    int i;
    double vitesse2Courante;

    for ( i = 0; i < billes.size(); ++i)
        if ( (vitesse2Courante = billes.get(i).getVitesse().normeCarrée()) > vitesse2Max)
           vitesse2Max = vitesse2Courante;

    return vitesse2Max;
    }

    /**
     * calcule le minimum  des rayons de a liste des billes
     *
     *
     * */
    static double minRayons(Vector<Bille> billes)
    {
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
        running = false;
        vueBillard.getGraphicsDevice().setFullScreenWindow(null);
        System.exit(0);
    }
}
