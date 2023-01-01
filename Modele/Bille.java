package exodecorateur_angryballs.maladroit.Modele;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.Vector;

import exodecorateur_angryballs.maladroit.Vues.VueBillard;
import mesmaths.geometrie.base.Vecteur;


public abstract class Bille
{

    public abstract VueBillard getVueBillard();

    public abstract Vecteur getPosition();

    public abstract double getRayon();

    public abstract Vecteur getVitesse();

    public abstract void setVitesse(Vecteur vitesse);

    public abstract Vecteur getAcc�l�ration();

    public abstract Color getCouleur();

    public abstract void setCouleur(Color couleur);

    public abstract int getClef();

    public abstract double masse();

    public abstract void  d�placer( double deltaT);

    public abstract void gestionAcc�l�ration(Vector<Bille> billes);

    public abstract boolean gestionCollisionBilleBille(Vector<Bille> billes);

    public abstract boolean collisionContour(double abscisseCoinHautGauche, double ordonn�eCoinHautGauche, double largeur, double hauteur);

    public abstract void dessine (Graphics g);

    public abstract String toString();

    public abstract boolean pointIsInsideBille(Vecteur point);

    public abstract void sendEventToCor(MouseEvent e);

}

