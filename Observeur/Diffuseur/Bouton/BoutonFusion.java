package exodecorateur_angryballs.maladroit.Observeur.Diffuseur.Bouton;

import exodecorateur_angryballs.maladroit.AnimationBilles;

import java.awt.*;

public class BoutonFusion extends Bouton {

    public BoutonFusion(String messageBouton, AnimationBilles animationBilles) {
        super(messageBouton, animationBilles);
    }

    @Override
    public void doAction(AnimationBilles animationBilles, AWTEvent e) {
        animationBilles.setFusionMode();
    }
}
