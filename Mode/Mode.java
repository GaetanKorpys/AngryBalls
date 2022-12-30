package exodecorateur_angryballs.maladroit.Mode;

import exodecorateur_angryballs.maladroit.Modele.Bille;
import exodecorateur_angryballs.maladroit.Vues.VueBillard;
import mesmaths.geometrie.base.Vecteur;

import java.awt.*;
import java.util.Vector;

public abstract class Mode {
    protected Vector<Bille> billes;
    protected double xMax, yMax,xMin,yMin;
    protected double vMax;
    protected double rayon;
    protected double diametre;
    protected Vecteur pesanteur;
    protected Color color;
    protected VueBillard vueBillard;


    public Mode(VueBillard vueBillard) {
        this.billes = new Vector<>();
        this.vMax = 0.1;
        this.pesanteur = new Vecteur(0, 0.001);
        this.color = this.getColorTable();
        this.vueBillard = vueBillard;
    }


    public Color getColorTable() {
        return Color.WHITE;
    }

    public Vector<Bille> getBilles() {
        return this.billes;
    }


    public void genererBilles(VueBillard vueBillard) {
        this.xMax = vueBillard.largeurBillard();
        this.yMax = vueBillard.hauteurBillard();
        this.rayon = 0.05 * Math.min(xMax, yMax);
        this.diametre = 2 * rayon;
        this.xMax = vueBillard.largeurBillard() - this.diametre*2;
        this.yMax = vueBillard.hauteurBillard() - this.diametre*2;
        this.xMin = this.diametre*2;
        this.yMin = this.diametre*2;

        this.billes.clear();
        this.preparerBilles();
    }


    protected abstract void preparerBilles();
}
