package exodecorateur_angryballs.maladroit.modele;

import mesmaths.geometrie.base.Vecteur;

import java.awt.*;
import java.util.Vector;

public interface IBille {

    public Vecteur getPosition();

    public double getRayon();

    public Vecteur getVitesse();

    public Vecteur getAcc�l�ration();

    public int getClef();

    public double masse();

    public  void  d�placer(double deltaT);

    public abstract void gestionAcc�l�ration(Vector<BilleParDefaut> bille);

    public boolean gestionCollisionBilleBille(Vector<BilleParDefaut> billes);

    public abstract void collisionContour(double abscisseCoinHautGauche, double ordonn�eCoinHautGauche, double largeur, double hauteur);

    public void dessine (Graphics g);

    public String toString();
}
