package exodecorateur_angryballs.maladroit.modele;

import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.Vector;

import exodecorateur_angryballs.maladroit.vues.BoutonChoixHurlement;
import exodecorateur_angryballs.maladroit.vues.VueBillard;
import mesmaths.cinematique.Cinematique;
import mesmaths.cinematique.Collisions;
import mesmaths.geometrie.base.Geop;
import mesmaths.geometrie.base.Vecteur;
import musique.SonLong;


/**
 * Cas g�n�ral d'une bille de billard
 *
 * Aucune des classes du package maladroit.modele ne doit faire de r�f�rence � des classes de java.awt ni � aucune autre librairie graphique JAVA car 
 * le mod�le NE DOIT PAS d�pendre de la vue !!! Vous devez faire les modifications en cons�quence !! Exploitez les Design Patterns. 
 *
 * On rappelle que ces r�f�rences � une librairie graphique sont n�fastes car si on change de librairie graphique, voire on fait migrer le projet sur android, 
 * il faut modifier les classes du mod�le. La maintenance devient catastrophique 
 *
 *  A MODIFIER
 *
 *
 * */
public abstract class Bille
{

    public abstract VueBillard getVueBillard();

    public abstract Vecteur getPosition();

    public abstract double getRayon();

    public abstract Vecteur getVitesse();

    public abstract void setVitesse(Vecteur vitesse);

    public abstract Vecteur getAcc�l�ration();

    public abstract Color getCouleur();

    public abstract int getClef();

    public abstract double masse();

    /**
     * mise � jour de position et vitesse � t+deltaT � partir de position et vitesse � l'instant t
     *
     * modifie le vecteur position et le vecteur vitesse
     *
     * laisse le vecteur acc�l�ration intact
     *
     * La bille subit par d�faut un mouvement uniform�ment acc�l�r�
     * */
    public abstract void  d�placer( double deltaT);

    /**
     * calcul (c-�-d mise � jour) �ventuel  du vecteur acc�l�ration.
     * billes est la liste de toutes les billes en mouvement
     * Cette m�thode peut avoir besoin de "billes" si this subit l'attraction gravitationnelle des autres billes
     * La nature du calcul du vecteur acc�l�ration de la bille  est d�finie dans les classes d�riv�es
     * A ce niveau le vecteur acc�l�ration est mis � z�ro (c'est � dire pas d'acc�l�ration)
     * */
    public abstract void gestionAcc�l�ration(Vector<Bille> billes);

    /**
     * gestion de l'�ventuelle  collision de cette bille avec les autres billes
     *
     * billes est la liste de toutes les billes en mouvement
     *
     * Le comportement par d�faut est le choc parfaitement �lastique (c-�-d rebond sans amortissement)
     *
     * @return true si il y a collision et dans ce cas les positions et vecteurs vitesses des 2 billes impliqu�es dans le choc sont modifi�es
     * si renvoie false, il n'y a pas de collision et les billes sont laiss�es intactes
     * */
    public abstract boolean gestionCollisionBilleBille(Vector<Bille> billes);

    /**
     * gestion de l'�ventuelle collision de la bille (this) avec le contour rectangulaire de l'�cran d�fini par (abscisseCoinHautGauche, ordonn�eCoinHautGauche, largeur, hauteur)
     *
     * d�tecte si il y a collision et le cas �ch�ant met � jour position et vitesse
     *
     * La nature du comportement de la bille en r�ponse � cette collision est d�finie dans les classes d�riv�es
     * */
    public abstract boolean collisionContour(double abscisseCoinHautGauche, double ordonn�eCoinHautGauche, double largeur, double hauteur);

    /* cette m�thode engendre des clignotements : id�e : utiliser l'active rendering et le double buffering pour �viter �a */

    public abstract void dessine (Graphics g);   // r�f�rence awt : mauvais

    public abstract String toString();

    public abstract boolean pointIsInsideBille(Vecteur point);



//----------------- classe Bille -------------------------------------
}

