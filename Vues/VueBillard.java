package exodecorateur_angryballs.maladroit.Vues;


import exodecorateur_angryballs.maladroit.AnimationBilles;
import java.awt.*;

public interface VueBillard
{

    public double largeurBillard();

    public double hauteurBillard();

    public void miseAJour();

    public void montrer();

    public GraphicsDevice getGraphicsDevice();

    public void setAnimationBilles (AnimationBilles animationBilles);
}
