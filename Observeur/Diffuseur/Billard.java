package exodecorateur_angryballs.maladroit.Observeur.Diffuseur;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.Vector;

import exodecorateur_angryballs.maladroit.AnimationBilles;
import exodecorateur_angryballs.maladroit.Ecouteur.DoAction;
import exodecorateur_angryballs.maladroit.Ecouteur.EcouteurKey;
import exodecorateur_angryballs.maladroit.Ecouteur.EcouteurMouse;
import exodecorateur_angryballs.maladroit.Modele.Bille;
import exodecorateur_angryballs.maladroit.Observeur.Souscripteur.Souscripteur;

public class Billard extends Canvas implements Diffuseur, DoAction
{
    Vector<Bille> billes;
    Vector<Souscripteur> souscripteurs;

    public Billard(Vector<Bille> billes)
    {
        this.billes = billes;
    }

    public Billard()
    {
        souscripteurs = new Vector<Souscripteur>();
        //On ajoute les écouteurs
        EcouteurMouse ecouteurMouse = new EcouteurMouse(this);
        EcouteurKey ecouteurKey = new EcouteurKey(this);
        this.addMouseListener(ecouteurMouse);
        this.addMouseMotionListener(ecouteurMouse);
        this.addKeyListener(ecouteurKey);
    }

    public void setBilles(Vector<Bille> billes){
        this.billes = billes;
    }

    @Override
    public void paint(Graphics graphics)
    {
        int i;
        for ( i = 0; i < this.billes.size(); ++i)
            this.billes.get(i).dessine(graphics);
    }

    //A améliorer
    @Override
    public void doAction(AnimationBilles animationBilles, AWTEvent e) {
        if(e.getClass().equals(MouseEvent.class))
            if(billes !=null)
                for (Bille bille : billes)
                    bille.sendEventToCor((MouseEvent) e);

        if(e.getClass().equals(KeyEvent.class))
                if(((KeyEvent) e).getKeyCode() == KeyEvent.VK_ESCAPE)
                    if (animationBilles != null)
                        animationBilles.quitter();

    }

    @Override
    public void addSouscripteur(Souscripteur s) {
        souscripteurs.add(s);
    }

    @Override
    public void removeSouscripteur(Souscripteur s) {
        souscripteurs.remove(s);
    }

    @Override
    public void notifySouscripteurs(AWTEvent e) {
        for (Souscripteur s : souscripteurs) {
            s.update(this, e);
        }
    }
}
