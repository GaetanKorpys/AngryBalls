package exodecorateur_angryballs.maladroit.modele.Son;

import exodecorateur_angryballs.maladroit.modele.Bille;
import exodecorateur_angryballs.maladroit.modele.DecoratorBille;
import mesmaths.geometrie.base.Vecteur;
import musique.SonLong;
import java.util.Vector;


public class BilleSonCollision extends DecoratorBille {
    private static final double COEFF_VOLUME = 6;
    private static final int DELAI_MIN = 10;    /* délai minimum de rafraichissement du son. en millisecondes */
    public static final int DELAI_MAX = 150;    /* délai maximum de rafraichissement du son. en millisecondes */
    public SonLong[] tabSonLong;
    int i;
    long dernierInstant;

    public BilleSonCollision(Bille bille, SonLong[] tabSonLong) {
        super(bille);
        this.tabSonLong = tabSonLong;
    }

    @Override
    public boolean gestionCollisionBilleBille(Vector<Bille> billes) {
        boolean res = _decoredBille.gestionCollisionBilleBille(billes);

        if(res)
            playSon(this, tabSonLong[3]);
        return res;
    }

    @Override
    public boolean collisionContour(double abscisseCoinHautGauche, double ordonnéeCoinHautGauche, double largeur, double hauteur) {
        boolean res = _decoredBille.collisionContour(abscisseCoinHautGauche,ordonnéeCoinHautGauche,largeur,hauteur);

        if(res)
            playSon(this, tabSonLong[4]);
        return res;
    }

    /*
    public void playSonBille(Bille bille, Vector<Bille>billes)
    {
        if(bille.gestionCollisionBilleBille(billes))
        {
            playSon(bille, hurlements[0]);
            System.out.println("\nBILLE");
        }
    }

    public void playSonContour(Bille bille)
    {
        if(bille.collisionContour(0,0, bille.getVueBillard().largeurBillard(), bille.getVueBillard().hauteurBillard()))
        {
            playSon(bille, hurlements[1]);
            System.out.println("\nCONTOUR");
        }
    }
    */
    public void playSon(Bille bille, SonLong sonLong)
    {
        Vecteur p = bille.getPosition();
        Vecteur v = bille.getVitesse();
        double xMax;

        xMax = bille.getVueBillard().largeurBillard();

        double n = v.norme();

        double y = Math.exp(-COEFF_VOLUME*n);
        double volume = 1-y;
        double x1 = p.x/xMax;
        double balance = 2*x1 - 1;

        //sonLong.joue(0, volume, balance, coeffPitch);

        int délai = (int)(DELAI_MIN*volume + DELAI_MAX*y);
        long instant = System.currentTimeMillis();
        if (instant - this.dernierInstant >=délai)
        {
            double coeffPitch = 1;
            sonLong.joue(i++, volume, balance, coeffPitch);
            this.dernierInstant= instant;
        }

    }
}
