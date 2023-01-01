package exodecorateur_angryballs.maladroit.Simulation;

import exodecorateur_angryballs.maladroit.Modele.Bille;
import exodecorateur_angryballs.maladroit.Modele.BilleParDefaut;
import exodecorateur_angryballs.maladroit.Modele.Decorateur.BilleFusion;
import exodecorateur_angryballs.maladroit.Modele.Decorateur.BillePilote;
import exodecorateur_angryballs.maladroit.Modele.Decorateur.BilleRebond;
import exodecorateur_angryballs.maladroit.Modele.Decorateur.BilleSonCollision;
import exodecorateur_angryballs.maladroit.Vues.VueBillard;
import mesmaths.geometrie.base.Vecteur;

import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.concurrent.ThreadLocalRandom;

public class FusionMode extends Mode{

    public FusionMode(VueBillard vueBillard){super(vueBillard);}

    @Override
    protected void preparerBilles() {
        Bille[] tabBilles = new Bille[11];

        int r,g,b;

        for (int i = 0; i < 11; i++) {
            r = ThreadLocalRandom.current().nextInt(0, 255 + 1);
            g = ThreadLocalRandom.current().nextInt(0, 255 + 1);
            b = ThreadLocalRandom.current().nextInt(0, 255 + 1);

            Color color = new Color(r,g,b);
            tabBilles[i] = new BilleParDefaut(Vecteur.créationAléatoire(0, 0, xMax, yMax), rayon, new Vecteur(), color, cadre);
            tabBilles[i] = new BilleRebond(tabBilles[i]);
            tabBilles[i] = new BillePilote(tabBilles[i]);
            tabBilles[i] = new BilleFusion(tabBilles[i]);
            tabBilles[i] = new BilleSonCollision(tabBilles[i], hurlements);

            billes.add(tabBilles[i]);
        }
    }
}
