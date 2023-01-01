package exodecorateur_angryballs.maladroit.Observeur.Diffuseur.Bouton;

import exodecorateur_angryballs.maladroit.AnimationBilles;

import java.awt.*;

public class BoutonReset extends Bouton {

    public BoutonReset(String messageBouton, AnimationBilles animationBilles) {
        super(messageBouton, animationBilles);
    }

    @Override
    public void doAction(AnimationBilles animationBilles, AWTEvent e) {
        animationBilles.resetSimulation();
    }
}
