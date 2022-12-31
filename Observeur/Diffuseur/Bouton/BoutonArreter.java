package exodecorateur_angryballs.maladroit.Observeur.Diffuseur.Bouton;

import exodecorateur_angryballs.maladroit.AnimationBilles;
import exodecorateur_angryballs.maladroit.Ecouteur.EcouteurBouton;

import java.awt.*;
import java.awt.event.ActionEvent;

public class BoutonArreter extends Bouton
{
    public BoutonArreter(String messageBouton, AnimationBilles animationBilles) {
        super(messageBouton, animationBilles);
    }

    @Override
    public void action(AnimationBilles animationBilles, AWTEvent e) {
        animationBilles.arręterAnimation();
    }

}
