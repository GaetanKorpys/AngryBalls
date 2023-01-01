package exodecorateur_angryballs.maladroit.Observeur.Souscripteur;

import exodecorateur_angryballs.maladroit.Ecouteur.DoAction;

import java.awt.*;

//Uniquement CadreAngryBalls
public interface Souscripteur {

    public void update(DoAction ecoutable, AWTEvent e);
}
