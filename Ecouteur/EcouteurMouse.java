package exodecorateur_angryballs.maladroit.Ecouteur;

import exodecorateur_angryballs.maladroit.Observeur.Diffuseur.Billard;

import javax.swing.event.MouseInputListener;
import java.awt.event.MouseEvent;

public class EcouteurMouse extends Ecouteur implements MouseInputListener {

    public EcouteurMouse(Billard billard) {
        super(billard);
    }


    @Override
    public void mousePressed(MouseEvent e) {
        this.getDiffuseur().notifySouscripteurs(e);
    }


    @Override
    public void mouseReleased(MouseEvent e) {
        this.getDiffuseur().notifySouscripteurs(e);
    }


    @Override
    public void mouseDragged(MouseEvent e) {
        this.getDiffuseur().notifySouscripteurs(e);
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        this.getDiffuseur().notifySouscripteurs(e);
    }


    @Override
    public void mouseEntered(MouseEvent e) {
        this.getDiffuseur().notifySouscripteurs(e);
    }


    @Override
    public void mouseExited(MouseEvent e) {
        this.getDiffuseur().notifySouscripteurs(e);
    }


    @Override
    public void mouseMoved(MouseEvent e) {
        this.getDiffuseur().notifySouscripteurs(e);
    }

}
