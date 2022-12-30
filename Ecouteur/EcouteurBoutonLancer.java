package exodecorateur_angryballs.maladroit.Ecouteur;

import exodecorateur_angryballs.maladroit.AnimationBilles;

import java.awt.event.ActionEvent;

public class EcouteurBoutonLancer extends Ecouteur
{

    public EcouteurBoutonLancer(AnimationBilles animationBilles)
    {
        super(animationBilles);
    }

    @Override
    public void actionPerformed(ActionEvent arg0)
    {
    this.animationBilles.lancerAnimation();
    }

}
