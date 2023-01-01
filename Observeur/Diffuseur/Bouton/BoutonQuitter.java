package exodecorateur_angryballs.maladroit.Observeur.Diffuseur.Bouton;

import exodecorateur_angryballs.maladroit.AnimationBilles;

import java.awt.*;

public class BoutonQuitter extends Bouton{

    public BoutonQuitter(String messageBouton, AnimationBilles animationBilles) {
        super(messageBouton, animationBilles);
    }

    @Override
    public void doAction(AnimationBilles animationBilles, AWTEvent e) {
        animationBilles.quitter();
    }
}
