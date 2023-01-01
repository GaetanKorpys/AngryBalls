package exodecorateur_angryballs.maladroit.Modele;

import exodecorateur_angryballs.maladroit.Vues.VueBillard;
import mesmaths.geometrie.base.Vecteur;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.Vector;

public abstract class DecorateurBille extends Bille {

    protected Bille _decoredBille;

    public DecorateurBille(Bille bille) {
        _decoredBille = bille;
    }

    @Override
    public boolean gestionCollisionBilleBille(Vector<Bille> billes) {
        return _decoredBille.gestionCollisionBilleBille(billes);
    }

    @Override
    public VueBillard getVueBillard() {return _decoredBille.getVueBillard();}

    @Override
    public Vecteur getPosition() {
        return _decoredBille.getPosition();
    }

    @Override
    public double getRayon() {
        return _decoredBille.getRayon();
    }

    @Override
    public Vecteur getVitesse() {
        return _decoredBille.getVitesse();
    }

    @Override
    public void setVitesse(Vecteur vitesse) {_decoredBille.setVitesse(vitesse);}

    @Override
    public Vecteur getAcc�l�ration() {
        return _decoredBille.getAcc�l�ration();
    }

    @Override
    public Color getCouleur() {return _decoredBille.getCouleur();}

    @Override
    public void setCouleur(Color couleur) {_decoredBille.setCouleur(couleur);}

    @Override
    public int getClef() {
        return _decoredBille.getClef();
    }

    @Override
    public double masse() {
        return _decoredBille.masse();
    }

    @Override
    public void d�placer(double deltaT) {
        _decoredBille.d�placer(deltaT);
    }

    @Override
    public void gestionAcc�l�ration(Vector<Bille> billes) {
        _decoredBille.gestionAcc�l�ration(billes);
    }

    @Override
    public boolean collisionContour(double abscisseCoinHautGauche, double ordonn�eCoinHautGauche, double largeur, double hauteur) {
        return _decoredBille.collisionContour(abscisseCoinHautGauche, ordonn�eCoinHautGauche, largeur, hauteur);
    }

    @Override
    public void dessine(Graphics g) {
        _decoredBille.dessine(g);
    }

    @Override
    public String toString() {
       return _decoredBille.toString();
    }

    @Override
    public boolean pointIsInsideBille(Vecteur point) {
        return _decoredBille.pointIsInsideBille(point);
    }

    @Override
    public void sendEventToCor(MouseEvent e) {
        _decoredBille.sendEventToCor(e);
    }
}
