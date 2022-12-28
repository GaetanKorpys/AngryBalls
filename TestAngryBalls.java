package exodecorateur_angryballs.maladroit;

import java.awt.*;
import java.awt.event.ItemListener;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.Vector;
import java.awt.GraphicsDevice;

import exodecorateur_angryballs.maladroit.modele.*;
import exodecorateur_angryballs.maladroit.modele.Acceleration.BilleFrottement;
import exodecorateur_angryballs.maladroit.modele.Acceleration.BilleMRU;
import exodecorateur_angryballs.maladroit.modele.Acceleration.BilleNewton;
import exodecorateur_angryballs.maladroit.modele.Acceleration.BillePesanteur;
import exodecorateur_angryballs.maladroit.modele.Catch.BillePilote;
import exodecorateur_angryballs.maladroit.modele.CollisionParoi.BilleArret;
import exodecorateur_angryballs.maladroit.modele.CollisionParoi.BillePasseMuraille;
import exodecorateur_angryballs.maladroit.modele.CollisionParoi.BilleRebond;
import exodecorateur_angryballs.maladroit.modele.Son.BilleHurlante;
import exodecorateur_angryballs.maladroit.modele.Son.BilleSonCollision;
import mesmaths.geometrie.base.Vecteur;
import musique.SonLong;
import exodecorateur_angryballs.maladroit.vues.CadreAngryBalls;


/**
 * Gestion d'une liste de billes en mouvement ayant toutes un comportement différent
 * 
 * Idéal pour mettre en place le DP decorator
 * */
public class TestAngryBalls
{

    /**
     * Finds a display mode that is different from the current display
     * mode and is likely to cause a display change event.
     */
    private static DisplayMode findDisplayMode(GraphicsDevice gd) {
        DisplayMode dms[] = gd.getDisplayModes();
        DisplayMode currentDM = gd.getDisplayMode();
        for (DisplayMode dm : dms) {
            if (!dm.equals(currentDM) && dm.getRefreshRate() == currentDM.getRefreshRate())
            {
                // different from the current dm and refresh rate is the same
                // means that something else is different => more likely to
                // cause a DM change event
                return dm;
            }
        }
        return null;
    }

    /**
     * @param args
     */
    public static void main(String[] args)
    {
        //---------------------- gestion des bruitages : paramétrage du chemin du dossier contenant les fichiers audio --------------------------

        File file = new File(System.getProperty("user.dir").toString()); // là où la JVM est lancée : racine du projet

        File répertoireSon = new File(file.getAbsoluteFile(), "maladroit"+File.separatorChar+"bruits");
        System.out.println(répertoireSon);

        //-------------------- chargement des sons pour les hurlements --------------------------------------

        Vector<SonLong> sonsLongs = OutilsConfigurationBilleHurlante.chargeSons(répertoireSon, "config_audio_bille_hurlante.txt");

        SonLong hurlements[] = SonLong.toTableau(sonsLongs);                // on obtient un tableau de SonLong

        //------------------- création de la liste (pour l'instant vide) des billes -----------------------

        Vector<Bille> billes = new Vector<Bille>();

        //---------------- création de la vue responsable du dessin des billes -------------------------


        GraphicsDevice myDevice;
        DisplayMode oldDisplayMode, newDisplayMode;

        myDevice = java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        oldDisplayMode = myDevice.getDisplayMode();

        int w,h,r,b;
        w = oldDisplayMode.getWidth();
        h = oldDisplayMode.getHeight();
        r = oldDisplayMode.getRefreshRate();
        b = oldDisplayMode.getBitDepth();

        newDisplayMode = findDisplayMode(myDevice);

        int choixHurlementInitial = 0;
        CadreAngryBalls cadre = new CadreAngryBalls("Angry balls Gaetan Korpys", "Animation de billes ayant des comportements différents. Situation idéale pour mettre en place le DP Decorator", billes,hurlements, choixHurlementInitial);

        System.out.println("\nnew"+newDisplayMode);
        System.out.println("\nold"+oldDisplayMode);

        if (myDevice.isFullScreenSupported())
            try {
                myDevice.setFullScreenWindow(cadre);
                /*if(newDisplayMode != null)
                    myDevice.setDisplayMode(newDisplayMode);
                else
                    myDevice.setDisplayMode(oldDisplayMode);*/
            } catch (Exception e) {
                e.printStackTrace();
                myDevice.setFullScreenWindow(null);
            }

        cadre.montrer(); // on rend visible la vue
        //------------- remplissage de la liste avec 5 billes -------------------------------



        double xMax, yMax;
        double vMax = 0.1;
        xMax = cadre.largeurBillard();      // abscisse maximal
        yMax = cadre.hauteurBillard();      // ordonnée maximale

        double rayon = 0.05*Math.min(xMax, yMax); // rayon des billes : ici toutes les billes ont le même rayon, mais ce n'est pas obligatoire

        Vecteur p0, p1, p2, p3, p4, p5, p6, p7,  v0, v1, v2, v3, v4, v5, v6, v7;    // les positions des centres des billes et les vecteurs vitesse au démarrage.
                                                            // Elles vont être choisies aléatoirement

        //------------------- création des vecteurs position des billes ---------------------------------

        p0 = Vecteur.créationAléatoire(0, 0, xMax, yMax);
        p1 = Vecteur.créationAléatoire(0, 0, xMax, yMax);
        p2 = Vecteur.créationAléatoire(0, 0, xMax, yMax);
        p3 = Vecteur.créationAléatoire(0, 0, xMax, yMax);
        p4 = Vecteur.créationAléatoire(0, 0, xMax, yMax);
        p5 = Vecteur.créationAléatoire(0, 0, xMax, yMax);
        p6 = Vecteur.créationAléatoire(0, 0, xMax, yMax);
        p7 = Vecteur.créationAléatoire(0, 0, xMax, yMax);
        //------------------- création des vecteurs vitesse des billes ---------------------------------

        v0 = Vecteur.créationAléatoire(-vMax, -vMax, vMax, vMax);
        v1 = Vecteur.créationAléatoire(-vMax, -vMax, vMax, 0);
        v2 = Vecteur.créationAléatoire(-vMax, -vMax, vMax, vMax);
        v3 = Vecteur.créationAléatoire(-vMax, -vMax, vMax, vMax);
        v4 = Vecteur.créationAléatoire(-vMax, -vMax, vMax, vMax);
        v5 = Vecteur.créationAléatoire(-vMax, -vMax, vMax, vMax);
        v6 = Vecteur.créationAléatoire(-vMax, -vMax, vMax, vMax);
        v7 = Vecteur.créationAléatoire(-vMax, -vMax, vMax, vMax);
        //--------------- ici commence la partie à changer ---------------------------------
        Bille HurlanteNewtonArret, NewtonFrottementRebond, PesanteurFrottementRebond, MRURebond, MRUPasseMuraille, Attrapable, Fantome, Fusion, Division;

        HurlanteNewtonArret = new BilleParDefaut(p4, rayon, new Vecteur(),Color.black, cadre);
        HurlanteNewtonArret = new BilleNewton(HurlanteNewtonArret);
        HurlanteNewtonArret = new BilleArret(HurlanteNewtonArret);
        HurlanteNewtonArret = new BilleSonCollision(HurlanteNewtonArret, hurlements);
        HurlanteNewtonArret = new BilleHurlante(HurlanteNewtonArret, hurlements[choixHurlementInitial]);

        NewtonFrottementRebond = new BilleParDefaut(p2, rayon,new Vecteur(),Color.green, cadre);
        NewtonFrottementRebond = new BilleRebond(NewtonFrottementRebond);
        NewtonFrottementRebond = new BilleNewton(NewtonFrottementRebond);
        NewtonFrottementRebond = new BilleFrottement(NewtonFrottementRebond);
        NewtonFrottementRebond = new BilleSonCollision(NewtonFrottementRebond,hurlements);

        PesanteurFrottementRebond = new BilleParDefaut(p1, rayon,new Vecteur(),Color.yellow, cadre);
        PesanteurFrottementRebond = new BilleRebond(PesanteurFrottementRebond);
        PesanteurFrottementRebond = new BillePesanteur(PesanteurFrottementRebond,new Vecteur(0,0.001) );
        PesanteurFrottementRebond = new BilleFrottement(PesanteurFrottementRebond);
        PesanteurFrottementRebond = new BilleSonCollision(PesanteurFrottementRebond, hurlements);

        MRURebond = new BilleParDefaut(p0, rayon,new Vecteur(), Color.red, cadre);
        MRURebond = new BilleMRU(MRURebond, v0);
        MRURebond = new BilleRebond(MRURebond);
        MRURebond = new BilleSonCollision(MRURebond, hurlements);

        MRUPasseMuraille = new BilleParDefaut(p3, rayon,new Vecteur(), Color.cyan, cadre);
        MRUPasseMuraille = new BilleMRU(MRUPasseMuraille, v3);
        MRUPasseMuraille = new BillePasseMuraille(MRUPasseMuraille);
        MRUPasseMuraille = new BilleSonCollision(MRUPasseMuraille, hurlements);

        /*
        Attrapable = new BilleParDefaut(p4, rayon,Color.orange, cadre);
        Attrapable = new BilleRebond(Attrapable);
        Attrapable = new BillePilote(Attrapable);


        Fantome = new BilleParDefaut(p5, rayon,Color.gray, cadre);
        Fantome = new BilleMRU(Fantome, v5);
        Fantome = new BilleRebond(Fantome);
        Fantome = new BilleFantome(Fantome);

        Fusion = new BilleParDefaut(p6, rayon,Color.orange, cadre);
        Fusion = new BilleRebond(Fusion);
        Fusion = new BilleMRU(Fusion, v6);
        Fusion = new BilleFusion(Fusion);

        Division = new BilleParDefaut(p7, rayon, Color.PINK, cadre);
        Division = new BilleDivision(Division);
        */

        billes.add(HurlanteNewtonArret);
        billes.add(NewtonFrottementRebond);
        billes.add(PesanteurFrottementRebond);
        billes.add(MRURebond);
        billes.add(MRUPasseMuraille);
        //billes.add(Fantome);
        //billes.add(Fusion);
        //billes.add(Division);
        //billes.add(Attrapable);

        cadre.addChoixHurlementListener((ItemListener) HurlanteNewtonArret);
        //cadre.addMouseListener((MouseListener) Attrapable);

        //---------------------- ici finit la partie à changer -------------------------------------------------------------

        System.out.println("billes = " + billes);


        //-------------------- création de l'objet responsable de l'animation (c'est un thread séparé) -----------------------

        AnimationBilles animationBilles = new AnimationBilles(billes, cadre);

        //----------------------- mise en place des écouteurs de boutons qui permettent de contrôler (un peu...) l'application -----------------

        EcouteurBoutonLancer écouteurBoutonLancer = new EcouteurBoutonLancer(animationBilles);
        EcouteurBoutonArreter écouteurBoutonArrêter = new EcouteurBoutonArreter(animationBilles);

        //------------------------- activation des écouteurs des boutons et ça tourne tout seul ------------------------------


        cadre.lancerBilles.addActionListener(écouteurBoutonLancer);             // pourrait être remplacé par Observable - Observer
        cadre.arrêterBilles.addActionListener(écouteurBoutonArrêter);           // pourrait être remplacé par Observable - Observer
    }
}
