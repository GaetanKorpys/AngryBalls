package exodecorateur_angryballs.maladroit.Ecouteur;

import exodecorateur_angryballs.maladroit.Observeur.Diffuseur.Diffuseur;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class EcouteurKey extends Ecouteur implements KeyListener {
    public EcouteurKey(Diffuseur diffuseur) {
        super(diffuseur);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //this.getDiffuseur().notifySouscripteurs(e);
    }

    //Quand une touche est préssée, on informe les souscripteurs du diffuseur
    @Override
    public void keyPressed(KeyEvent e) {
        this.getDiffuseur().notifySouscripteurs(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //this.getDiffuseur().notifySouscripteurs(e);
    }
}
