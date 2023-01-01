package exodecorateur_angryballs.maladroit.Modele;

import exodecorateur_angryballs.maladroit.Vues.VueBillard;
import mesmaths.cinematique.Cinematique;
import mesmaths.geometrie.base.Geop;
import mesmaths.geometrie.base.Vecteur;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.Vector;

//Bille de billard classique avec vitesse rectiligne uniforme (donc pas d'acceleration) et des rebonds sur les bords
//C'est le composant qui va etre emballe par les decorateurs
//Les donnees sont contenus dans cette classe pour éviter la redondance dans chaque decorateur
public class BilleParDefaut extends Bille {

    public  Vecteur position;
    public  double rayon;
    public  Vecteur vitesse;
    public  Vecteur accélération;
    public int clef;
    public Color couleur;
    public static int prochaineClef = 0;
    public static double ro = 1;
    public VueBillard vueBillard;

    public BilleParDefaut(Vecteur position, double rayon, Vecteur vitesse, Color couleur, VueBillard vueBillard) {
        this.position = position;
        this.rayon = rayon;
        this.vitesse = vitesse;
        this.accélération = new Vecteur();
        this.couleur = couleur;
        this.clef = BilleParDefaut.prochaineClef ++;
        this.vueBillard = vueBillard;
    }

    public VueBillard getVueBillard(){return this.vueBillard;}

    public Vecteur getPosition()
    {
        return this.position;
    }

    public double getRayon()
    {
        return this.rayon;
    }

    public Vecteur getVitesse()
    {
        return this.vitesse;
    }

    public void setVitesse(Vecteur vitesse) {this.vitesse = vitesse;}

    public Vecteur getAccélération()
    {
        return this.accélération;
    }

    public Color getCouleur(){return this.couleur;}

    @Override
    public void setCouleur(Color couleur) {
        this.couleur = couleur;
    }

    public int getClef()
    {
        return this.clef;
    }

    public double masse() {return ro* Geop.volumeSphère(rayon);}

    public void  déplacer( double deltaT)
    {
        Cinematique.mouvementUniformémentAccéléré( this.getPosition(), this.getVitesse(), this.getAccélération(), deltaT);
    }

    @Override
    public void gestionAccélération(Vector<Bille> bille) {
        this.getAccélération().set(new Vecteur( ));
    }

    @Override
    public boolean gestionCollisionBilleBille(Vector<Bille> billes) {

        return OutilsBille.gestionCollisionBilleBille(this, billes);
    }

    @Override
    public boolean collisionContour(double abscisseCoinHautGauche, double ordonnéeCoinHautGauche, double largeur, double hauteur)
    {
        return false; //Par defaut, la bille peut quitter le billard
    }

    @Override
    public void dessine(Graphics g) {
        int width, height;
        int xMin, yMin;

        xMin = (int)Math.round(position.x-rayon);
        yMin = (int)Math.round(position.y-rayon);

        width = height = 2*(int)Math.round(rayon);

        g.setColor(couleur);
        g.fillOval( xMin, yMin, width, height);
        g.setColor(Color.CYAN);
        g.drawOval(xMin, yMin, width, height);
    }

    @Override
    public String toString()
    {
        return "centre = " + this.getPosition() + " rayon = " + this.getRayon() + " vitesse = " + this.getVitesse() + " accélération = " + this.getAccélération() + " couleur = " + this.couleur + " clef = " + this.getClef();
    }

    @Override
    public boolean pointIsInsideBille(Vecteur point) {
        double distance = Math.sqrt(Math.pow(point.x - this.getPosition().x, 2) + Math.pow(point.y - this.getPosition().y, 2));
        return distance < this.getRayon();
    }

    @Override
    public void handleMouseEvent(MouseEvent e) {
        //Ne fait rien
    }
}
