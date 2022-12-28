package exodecorateur_angryballs.maladroit.modele;

import mesmaths.geometrie.base.Vecteur;

import java.awt.*;
import java.util.Vector;

public interface IBille {

    public Vecteur getPosition();

    public double getRayon();

    public Vecteur getVitesse();

    public Vecteur getAccélération();

    public int getClef();

    public double masse();

    public  void  déplacer(double deltaT);

    public abstract void gestionAccélération(Vector<BilleParDefaut> bille);

    public boolean gestionCollisionBilleBille(Vector<BilleParDefaut> billes);

    public abstract void collisionContour(double abscisseCoinHautGauche, double ordonnéeCoinHautGauche, double largeur, double hauteur);

    public void dessine (Graphics g);

    public String toString();
}
