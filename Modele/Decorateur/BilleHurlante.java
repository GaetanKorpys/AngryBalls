package exodecorateur_angryballs.maladroit.Modele.Decorateur;

import exodecorateur_angryballs.maladroit.Modele.Bille;
import exodecorateur_angryballs.maladroit.Modele.DecorateurBille;
import exodecorateur_angryballs.maladroit.Vues.BoutonChoixHurlement;
import mesmaths.geometrie.base.Vecteur;
import musique.SonLong;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class BilleHurlante extends DecorateurBille implements ItemListener {

    private static final int DELAI_MIN = 10;    /* d�lai minimum de rafraichissement du son. en millisecondes */
    public static final int DELAI_MAX = 150;    /* d�lai maximum de rafraichissement du son. en millisecondes */
    public SonLong sonLong;                     /* bande son � diffuser */
    int i;
    long dernierInstant;

    private static final double COEFF_VOLUME = 6;

    public BilleHurlante(Bille bille, SonLong sonLong) {
        super(bille);
        this.sonLong = sonLong;
        i = 0;
        dernierInstant = System.currentTimeMillis();
    }

    @Override
    public void d�placer(double deltaT) {
        super.d�placer(deltaT);
        Vecteur p = this.getPosition();
        Vecteur v = this.getVitesse();
        double xMax;

        xMax = getVueBillard().largeurBillard();

        double n = v.norme();

        double y = Math.exp(-COEFF_VOLUME*n);
        double volume = 1-y;
        double x1 = p.x/xMax;
        double balance = 2*x1 - 1;


        int d�lai = (int)(DELAI_MIN*volume + DELAI_MAX*y);
        long instant = System.currentTimeMillis();
        if (instant - this.dernierInstant >=d�lai)
        {
            double coeffPitch = 1;
            this.sonLong.joue(i++, volume, balance, coeffPitch);
            this.dernierInstant= instant;
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e)
    {
        if (e.getSource() instanceof BoutonChoixHurlement)
        {
            BoutonChoixHurlement boutonChoixHurlement = (BoutonChoixHurlement)(e.getSource());
            this.sonLong = boutonChoixHurlement.sonLong;
        }
    }
}
