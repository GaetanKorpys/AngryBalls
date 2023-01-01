package exodecorateur_angryballs.maladroit.Observeur.Diffuseur.Bouton;

import exodecorateur_angryballs.maladroit.AnimationBilles;

import java.awt.*;

public class BoutonMixte extends Bouton{

    public BoutonMixte(String messageBouton, AnimationBilles animationBilles) {
        super(messageBouton, animationBilles);
    }

    @Override
    public void action(AnimationBilles animationBilles, AWTEvent e) {
        animationBilles.setMixteMode();
    }
}
