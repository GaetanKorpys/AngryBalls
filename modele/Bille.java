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
 * Cas général d'une bille de billard
 *
 * Aucune des classes du package maladroit.modele ne doit faire de référence à des classes de java.awt ni à aucune autre librairie graphique JAVA car 
 * le modèle NE DOIT PAS dépendre de la vue !!! Vous devez faire les modifications en conséquence !! Exploitez les Design Patterns. 
 *
 * On rappelle que ces références à une librairie graphique sont néfastes car si on change de librairie graphique, voire on fait migrer le projet sur android, 
 * il faut modifier les classes du modèle. La maintenance devient catastrophique 
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

    public abstract Vecteur getAccélération();

    public abstract Color getCouleur();

    public abstract int getClef();

    public abstract double masse();

    /**
     * mise à jour de position et vitesse à t+deltaT à partir de position et vitesse à l'instant t
     *
     * modifie le vecteur position et le vecteur vitesse
     *
     * laisse le vecteur accélération intact
     *
     * La bille subit par défaut un mouvement uniformément accéléré
     * */
    public abstract void  déplacer( double deltaT);

    /**
     * calcul (c-à-d mise à jour) éventuel  du vecteur accélération.
     * billes est la liste de toutes les billes en mouvement
     * Cette méthode peut avoir besoin de "billes" si this subit l'attraction gravitationnelle des autres billes
     * La nature du calcul du vecteur accélération de la bille  est définie dans les classes dérivées
     * A ce niveau le vecteur accélération est mis à zéro (c'est à dire pas d'accélération)
     * */
    public abstract void gestionAccélération(Vector<Bille> billes);

    /**
     * gestion de l'éventuelle  collision de cette bille avec les autres billes
     *
     * billes est la liste de toutes les billes en mouvement
     *
     * Le comportement par défaut est le choc parfaitement élastique (c-à-d rebond sans amortissement)
     *
     * @return true si il y a collision et dans ce cas les positions et vecteurs vitesses des 2 billes impliquées dans le choc sont modifiées
     * si renvoie false, il n'y a pas de collision et les billes sont laissées intactes
     * */
    public abstract boolean gestionCollisionBilleBille(Vector<Bille> billes);

    /**
     * gestion de l'éventuelle collision de la bille (this) avec le contour rectangulaire de l'écran défini par (abscisseCoinHautGauche, ordonnéeCoinHautGauche, largeur, hauteur)
     *
     * détecte si il y a collision et le cas échéant met à jour position et vitesse
     *
     * La nature du comportement de la bille en réponse à cette collision est définie dans les classes dérivées
     * */
    public abstract boolean collisionContour(double abscisseCoinHautGauche, double ordonnéeCoinHautGauche, double largeur, double hauteur);

    /* cette méthode engendre des clignotements : idée : utiliser l'active rendering et le double buffering pour éviter ça */

    public abstract void dessine (Graphics g);   // référence awt : mauvais

    public abstract String toString();

    public abstract boolean pointIsInsideBille(Vecteur point);



//----------------- classe Bille -------------------------------------
}

