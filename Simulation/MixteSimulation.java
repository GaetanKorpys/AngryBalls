package exodecorateur_angryballs.maladroit.Simulation;

import exodecorateur_angryballs.maladroit.Modele.Bille;
import exodecorateur_angryballs.maladroit.Modele.BilleParDefaut;
import exodecorateur_angryballs.maladroit.Modele.Decorateur.*;
import exodecorateur_angryballs.maladroit.Vues.VueBillard;
import mesmaths.geometrie.base.Vecteur;

import java.awt.*;

public class MixteSimulation extends Simulation {

    public MixteSimulation(VueBillard cadre) {
        super(cadre);
    }

    @Override
    protected void preparerBilles() {
        Bille[] tabBilles = new Bille[6];


        tabBilles[0] = new BilleParDefaut(Vecteur.créationAléatoire(0, 0, xMax, yMax), rayon, new Vecteur(), Color.PINK, cadre);
        tabBilles[0] = new BilleMRU(tabBilles[0],Vecteur.créationAléatoire(-vMax, -vMax, vMax, vMax));
        tabBilles[0] = new BilleArret(tabBilles[0]);
        tabBilles[0] = new BillePilote(tabBilles[0]);
        tabBilles[0] = new BilleFantome(tabBilles[0]);

        tabBilles[1] = new BilleParDefaut(Vecteur.créationAléatoire(0, 0, xMax, yMax), rayon, new Vecteur(), Color.PINK, cadre);
        tabBilles[1] = new BilleMRU(tabBilles[1],Vecteur.créationAléatoire(-vMax, -vMax, vMax, vMax));
        tabBilles[1] = new BillePasseMuraille(tabBilles[1]);
        tabBilles[1] = new BillePilote(tabBilles[1]);
        tabBilles[1] = new BilleFantome(tabBilles[1]);

        tabBilles[2] = new BilleParDefaut(Vecteur.créationAléatoire(0, 0, xMax, yMax), rayon, new Vecteur(), Color.BLACK, cadre);
        tabBilles[2] = new BilleRebond(tabBilles[2]);
        tabBilles[2] = new BillePesanteur(tabBilles[2],new Vecteur(0,0.001));
        tabBilles[2] = new BillePilote(tabBilles[2]);
        tabBilles[2] = new BilleChangementCouleur(tabBilles[2]);

        tabBilles[3] = new BilleParDefaut(Vecteur.créationAléatoire(0, 0, xMax, yMax), rayon, new Vecteur(), Color.BLACK, cadre);
        tabBilles[3] = new BilleRebond(tabBilles[3]);
        tabBilles[3] = new BilleNewton(tabBilles[3]);
        tabBilles[3] = new BillePilote(tabBilles[3]);
        tabBilles[3] = new BilleChangementCouleur(tabBilles[3]);

        tabBilles[4] = new BilleParDefaut(Vecteur.créationAléatoire(0, 0, xMax, yMax), rayon, new Vecteur(), Color.RED, cadre);
        tabBilles[4] = new BilleRebond(tabBilles[4]);
        tabBilles[4] = new BillePilote(tabBilles[4]);
        tabBilles[4] = new BilleSonCollision(tabBilles[4], hurlements);

        tabBilles[5] = new BilleParDefaut(Vecteur.créationAléatoire(0, 0, xMax, yMax), rayon, new Vecteur(), Color.RED, cadre);
        tabBilles[5] = new BilleRebond(tabBilles[5]);
        tabBilles[5] = new BillePilote(tabBilles[5]);
        tabBilles[5] = new BilleFrottement(tabBilles[5]);
        tabBilles[5] = new BilleSonCollision(tabBilles[5], hurlements);

        billes.add(tabBilles[0]);
        billes.add(tabBilles[1]);
        billes.add(tabBilles[2]);
        billes.add(tabBilles[3]);
        billes.add(tabBilles[4]);
        billes.add(tabBilles[5]);

    }
}
