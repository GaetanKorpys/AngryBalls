package exodecorateur_angryballs.maladroit.Vues;


import exodecorateur_angryballs.maladroit.AnimationBilles;
import exodecorateur_angryballs.maladroit.Observeur.Diffuseur.Billard;
import musique.SonLong;

import java.awt.*;
import java.awt.event.ItemListener;

public interface VueBillard
{

    public double largeurBillard();

    public double hauteurBillard();

    public void miseAJour();

    public void montrer();

    public GraphicsDevice getGraphicsDevice();

    public void setAnimationBilles (AnimationBilles animationBilles);

    public Billard getBillard();

    public void addChoixHurlementListener(ItemListener écouteurChoixHurlant);

    public SonLong[] getHurlements();
}
