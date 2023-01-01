package exodecorateur_angryballs.maladroit.Simulation;

import exodecorateur_angryballs.maladroit.Modele.Bille;
import exodecorateur_angryballs.maladroit.Vues.VueBillard;
import mesmaths.geometrie.base.Vecteur;
import musique.SonLong;

import java.util.Vector;

public abstract class Simulation {
    protected Vector<Bille> billes;
    protected double xMax, yMax,xMin,yMin;
    protected double vMax;
    protected double rayon;
    protected double diametre;
    protected Vecteur pesanteur;
    protected VueBillard cadre;
    protected SonLong[] hurlements;


    public Simulation(VueBillard cadre) {
        billes = new Vector<Bille>();
        vMax = 0.1;
        pesanteur = new Vecteur(0, 0.001);
        this.cadre = cadre;
        hurlements = cadre.getHurlements();

        xMax = cadre.largeurBillard();
        yMax = cadre.hauteurBillard();
        rayon = 0.05 * Math.min(xMax, yMax);
        diametre = 2 * rayon;
        xMax = cadre.largeurBillard() - diametre*2;
        yMax = cadre.hauteurBillard() - diametre*2;
        xMin = diametre*2;
        yMin = diametre*2;
    }


    public Vector<Bille> getBilles() {
        return this.billes;
    }

    public void genererBilles(VueBillard vueBillard) {

        this.billes.clear();
        this.preparerBilles();
    }

    protected abstract void preparerBilles();
}
