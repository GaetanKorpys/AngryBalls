package exodecorateur_angryballs.maladroit.Ecouteur;

import exodecorateur_angryballs.maladroit.AnimationBilles;

import java.awt.event.ActionEvent;

public class EcouteurBoutonArreter extends Ecouteur
{
    public EcouteurBoutonArreter(AnimationBilles animationBilles)
    {
        super(animationBilles);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
    this.animationBilles.arrêterAnimation();
    }

}
