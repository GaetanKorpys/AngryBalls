package exodecorateur_angryballs.maladroit.Observeur.Diffuseur.Bouton;

import exodecorateur_angryballs.maladroit.AnimationBilles;
import exodecorateur_angryballs.maladroit.Ecouteur.DoAction;
import exodecorateur_angryballs.maladroit.Ecouteur.EcouteurBouton;
import exodecorateur_angryballs.maladroit.Observeur.Diffuseur.Diffuseur;
import exodecorateur_angryballs.maladroit.Observeur.Souscripteur.Souscripteur;

import java.awt.*;
import java.util.Vector;

public abstract class Bouton extends Button implements Diffuseur, DoAction {

    private Vector<Souscripteur> souscripteurs;

    public Bouton(String messageBouton, AnimationBilles animationBilles) {
        super(messageBouton);
        this.souscripteurs = new Vector<Souscripteur>();
        this.addActionListener(new EcouteurBouton(this));
    }

    @Override
    public void addSouscripteur(Souscripteur s) {
        souscripteurs.add(s);
    }

    @Override
    public void removeSouscripteur(Souscripteur s) {
        souscripteurs.remove(s);
    }

    @Override
    public void notifySouscripteurs(AWTEvent e) {
        for (Souscripteur s : souscripteurs) {
            s.update(this, e);
        }
    }

}
