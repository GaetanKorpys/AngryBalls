package exodecorateur_angryballs.maladroit.Observeur.Diffuseur;

import exodecorateur_angryballs.maladroit.Observeur.Souscripteur.Souscripteur;

import java.awt.*;

public interface Diffuseur {

    void addSouscripteur(Souscripteur s);

    void removeSouscripteur(Souscripteur s);

    void notifySouscripteurs(AWTEvent e);
}
