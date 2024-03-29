package exodecorateur_angryballs.maladroit.Ecouteur;

import exodecorateur_angryballs.maladroit.Observeur.Diffuseur.Bouton.Bouton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EcouteurBouton extends Ecouteur implements ActionListener{

   public EcouteurBouton(Bouton bouton){
       super(bouton);
   }

   //Quand on clique sur un bouton, il informe ses souscripteurs
    @Override
    public void actionPerformed(ActionEvent e)
    {
        this.getDiffuseur().notifySouscripteurs(e);
    }

}
