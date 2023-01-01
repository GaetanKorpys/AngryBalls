package exodecorateur_angryballs.maladroit.Simulation;

import exodecorateur_angryballs.maladroit.Modele.Bille;
import exodecorateur_angryballs.maladroit.Modele.BilleParDefaut;
import exodecorateur_angryballs.maladroit.Modele.Decorateur.*;
import exodecorateur_angryballs.maladroit.Vues.VueBillard;
import mesmaths.geometrie.base.Vecteur;

import java.awt.*;
import java.awt.event.ItemListener;

public class ParDefautMode extends Mode{

    public ParDefautMode(VueBillard cadre){super(cadre);}

    @Override
    protected void preparerBilles() {

        int choixHurlementInitial = 0;

        Bille HurlanteNewtonArret, NewtonFrottementRebond, PesanteurFrottementRebond, MRURebond, MRUPasseMuraille, PiloteFrottementArret;


        Vecteur p0, p1, p2, p3, p4, p5, v0, v1;

        p0 = Vecteur.créationAléatoire(0, 0, xMax, yMax);
        p1 = Vecteur.créationAléatoire(0, 0, xMax, yMax);
        p2 = Vecteur.créationAléatoire(0, 0, xMax, yMax);
        p3 = Vecteur.créationAléatoire(0, 0, xMax, yMax);
        p4 = Vecteur.créationAléatoire(0, 0, xMax, yMax);
        p5 = Vecteur.créationAléatoire(0, 0, xMax, yMax);

        v0 = Vecteur.créationAléatoire(-vMax, -vMax, vMax, vMax);
        v1 = Vecteur.créationAléatoire(-vMax, -vMax, vMax, vMax);

        HurlanteNewtonArret = new BilleParDefaut(p0, rayon, new Vecteur(), Color.black, cadre);
        HurlanteNewtonArret = new BilleNewton(HurlanteNewtonArret);
        HurlanteNewtonArret = new BilleArret(HurlanteNewtonArret);
        HurlanteNewtonArret = new BilleSonCollision(HurlanteNewtonArret, hurlements);
        HurlanteNewtonArret = new BilleHurlante(HurlanteNewtonArret, hurlements[choixHurlementInitial]);

        NewtonFrottementRebond = new BilleParDefaut(p1, rayon, new Vecteur(),Color.green, cadre);
        NewtonFrottementRebond = new BilleRebond(NewtonFrottementRebond);
        NewtonFrottementRebond = new BilleNewton(NewtonFrottementRebond);
        NewtonFrottementRebond = new BilleFrottement(NewtonFrottementRebond);
        NewtonFrottementRebond = new BilleSonCollision(NewtonFrottementRebond,hurlements);

        PesanteurFrottementRebond = new BilleParDefaut(p2, rayon, new Vecteur(),Color.yellow, cadre);
        PesanteurFrottementRebond = new BilleRebond(PesanteurFrottementRebond);
        PesanteurFrottementRebond = new BillePesanteur(PesanteurFrottementRebond,new Vecteur(0,0.001) );
        PesanteurFrottementRebond = new BilleFrottement(PesanteurFrottementRebond);
        PesanteurFrottementRebond = new BilleSonCollision(PesanteurFrottementRebond, hurlements);

        MRURebond = new BilleParDefaut(p3, rayon, new Vecteur(), Color.red, cadre);
        MRURebond = new BilleMRU(MRURebond, v0);
        MRURebond = new BilleRebond(MRURebond);
        MRURebond = new BilleSonCollision(MRURebond, hurlements);

        MRUPasseMuraille = new BilleParDefaut(p4, rayon, new Vecteur(), Color.cyan, cadre);
        MRUPasseMuraille = new BilleMRU(MRUPasseMuraille, v1);
        MRUPasseMuraille = new BillePasseMuraille(MRUPasseMuraille);
        MRUPasseMuraille = new BilleSonCollision(MRUPasseMuraille, hurlements);


        PiloteFrottementArret = new BilleParDefaut(p5, rayon, new Vecteur(), Color.orange, cadre);
        PiloteFrottementArret = new BilleRebond(PiloteFrottementArret);
        PiloteFrottementArret = new BillePilote(PiloteFrottementArret);
        PiloteFrottementArret = new BilleSonCollision(PiloteFrottementArret, hurlements);


        billes.add(HurlanteNewtonArret);
        billes.add(NewtonFrottementRebond);
        billes.add(PesanteurFrottementRebond);
        billes.add(MRURebond);
        billes.add(MRUPasseMuraille);
        billes.add(PiloteFrottementArret);

        cadre.addChoixHurlementListener((ItemListener) HurlanteNewtonArret);
    }
}
