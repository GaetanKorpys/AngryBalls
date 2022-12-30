package exodecorateur_angryballs.maladroit.Ecouteur;

import exodecorateur_angryballs.maladroit.AnimationBilles;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class Ecouteur implements ActionListener {
    AnimationBilles animationBilles;

   public Ecouteur(AnimationBilles animationBilles){this.animationBilles = animationBilles;}

    @Override
    public abstract void actionPerformed(ActionEvent e);

}
