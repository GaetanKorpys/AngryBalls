package exodecorateur_angryballs.maladroit;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.Vector;
import java.awt.GraphicsDevice;

import exodecorateur_angryballs.maladroit.Ecouteur.EcouteurBoutonArreter;
import exodecorateur_angryballs.maladroit.Ecouteur.EcouteurBoutonLancer;
import exodecorateur_angryballs.maladroit.Ecouteur.EcouteurBoutonQuitter;
import exodecorateur_angryballs.maladroit.Modele.*;
import exodecorateur_angryballs.maladroit.Modele.Decorateur.BilleFrottement;
import exodecorateur_angryballs.maladroit.Modele.Decorateur.BilleMRU;
import exodecorateur_angryballs.maladroit.Modele.Decorateur.BilleNewton;
import exodecorateur_angryballs.maladroit.Modele.Decorateur.BillePesanteur;
import exodecorateur_angryballs.maladroit.Modele.Decorateur.BillePilote;
import exodecorateur_angryballs.maladroit.Modele.Decorateur.BilleArret;
import exodecorateur_angryballs.maladroit.Modele.Decorateur.BillePasseMuraille;
import exodecorateur_angryballs.maladroit.Modele.Decorateur.BilleRebond;
import exodecorateur_angryballs.maladroit.Modele.Decorateur.BilleHurlante;
import exodecorateur_angryballs.maladroit.Modele.Decorateur.BilleSonCollision;
import mesmaths.geometrie.base.Vecteur;
import musique.SonLong;
import exodecorateur_angryballs.maladroit.Vues.CadreAngryBalls;


/**
 * Gestion d'une liste de billes en mouvement ayant toutes un comportement diff�rent
 * 
 * Id�al pour mettre en place le DP decorator
 * */
public class TestAngryBalls
{

    public static void main(String[] args)
    {
        //---------------------- gestion des bruitages : param�trage du chemin du dossier contenant les fichiers audio --------------------------

        File file = new File(System.getProperty("user.dir").toString()); // l� o� la JVM est lanc�e : racine du projet

        File r�pertoireSon = new File(file.getAbsoluteFile(), "maladroit"+File.separatorChar+"bruits");
        System.out.println(r�pertoireSon);

        //-------------------- chargement des sons pour les hurlements --------------------------------------

        Vector<SonLong> sonsLongs = OutilsConfigurationBilleHurlante.chargeSons(r�pertoireSon, "config_audio_bille_hurlante.txt");

        SonLong hurlements[] = SonLong.toTableau(sonsLongs);                // on obtient un tableau de SonLong

        //------------------- cr�ation de la liste (pour l'instant vide) des billes -----------------------

        Vector<Bille> billes = new Vector<Bille>();

        //---------------- cr�ation de la vue responsable du dessin des billes -------------------------


        int choixHurlementInitial = 0;
        CadreAngryBalls cadre = new CadreAngryBalls("Angry balls Gaetan Korpys", "Animation de billes ayant des comportements diff�rents. Situation id�ale pour mettre en place le DP Decorator", billes,hurlements, choixHurlementInitial);


        DisplayMode oldDisplayMode, newDisplayMode;


        // Get graphics configuration...
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice myDevice = ge.getDefaultScreenDevice();
        GraphicsConfiguration gc = myDevice.getDefaultConfiguration();

        //Get old display
        oldDisplayMode = myDevice.getDisplayMode();

        int w,h,r,b;
        w = oldDisplayMode.getWidth();
        h = oldDisplayMode.getHeight();
        r = oldDisplayMode.getRefreshRate();
        b = oldDisplayMode.getBitDepth();

        //Create new display
        newDisplayMode = new DisplayMode(w,h,32,DisplayMode.REFRESH_RATE_UNKNOWN);


        System.out.println("\nnew"+newDisplayMode);
        System.out.println("\nold"+oldDisplayMode);

        //Change to full screen
        if (myDevice.isFullScreenSupported())
            try {
                myDevice.setFullScreenWindow(cadre);
                /*if(myDevice.isDisplayChangeSupported())
                    myDevice.setDisplayMode(newDisplayMode);*/
            } catch (Exception e) {
                e.printStackTrace();
                myDevice.setFullScreenWindow(null);
            }

        cadre.setGraphics(myDevice);
        cadre.montrer(); // on rend visible la vue

        //------------- remplissage de la liste avec 5 billes -------------------------------



        double xMax, yMax;
        double vMax = 0.1;
        xMax = cadre.largeurBillard();      // abscisse maximal
        yMax = cadre.hauteurBillard();      // ordonn�e maximale

        double rayon = 0.05*Math.min(xMax, yMax); // rayon des billes : ici toutes les billes ont le m�me rayon, mais ce n'est pas obligatoire

        Vecteur p0, p1, p2, p3, p4, p5, p6, p7,  v0, v1, v2, v3, v4, v5, v6, v7;    // les positions des centres des billes et les vecteurs vitesse au d�marrage.
                                                            // Elles vont �tre choisies al�atoirement

        //------------------- cr�ation des vecteurs position des billes ---------------------------------

        p0 = Vecteur.cr�ationAl�atoire(0, 0, xMax, yMax);
        p1 = Vecteur.cr�ationAl�atoire(0, 0, xMax, yMax);
        p2 = Vecteur.cr�ationAl�atoire(0, 0, xMax, yMax);
        p3 = Vecteur.cr�ationAl�atoire(0, 0, xMax, yMax);
        p4 = Vecteur.cr�ationAl�atoire(0, 0, xMax, yMax);
        p5 = Vecteur.cr�ationAl�atoire(0, 0, xMax, yMax);
        p6 = Vecteur.cr�ationAl�atoire(0, 0, xMax, yMax);
        p7 = Vecteur.cr�ationAl�atoire(0, 0, xMax, yMax);
        //------------------- cr�ation des vecteurs vitesse des billes ---------------------------------

        v0 = Vecteur.cr�ationAl�atoire(-vMax, -vMax, vMax, vMax);
        v1 = Vecteur.cr�ationAl�atoire(-vMax, -vMax, vMax, 0);
        v2 = Vecteur.cr�ationAl�atoire(-vMax, -vMax, vMax, vMax);
        v3 = Vecteur.cr�ationAl�atoire(-vMax, -vMax, vMax, vMax);
        v4 = Vecteur.cr�ationAl�atoire(-vMax, -vMax, vMax, vMax);
        v5 = Vecteur.cr�ationAl�atoire(-vMax, -vMax, vMax, vMax);
        v6 = Vecteur.cr�ationAl�atoire(-vMax, -vMax, vMax, vMax);
        v7 = Vecteur.cr�ationAl�atoire(-vMax, -vMax, vMax, vMax);
        //--------------- ici commence la partie � changer ---------------------------------
        Bille HurlanteNewtonArret, NewtonFrottementRebond, PesanteurFrottementRebond, MRURebond, MRUPasseMuraille, Attrapable, Fantome, Fusion, Division;

        HurlanteNewtonArret = new BilleParDefaut(p1, rayon, new Vecteur(),Color.black, cadre);
        HurlanteNewtonArret = new BilleNewton(HurlanteNewtonArret);
        HurlanteNewtonArret = new BilleArret(HurlanteNewtonArret);
        HurlanteNewtonArret = new BilleSonCollision(HurlanteNewtonArret, hurlements);
        HurlanteNewtonArret = new BilleHurlante(HurlanteNewtonArret, hurlements[choixHurlementInitial]);

        NewtonFrottementRebond = new BilleParDefaut(p2, rayon,new Vecteur(),Color.green, cadre);
        NewtonFrottementRebond = new BilleRebond(NewtonFrottementRebond);
        NewtonFrottementRebond = new BilleNewton(NewtonFrottementRebond);
        NewtonFrottementRebond = new BilleFrottement(NewtonFrottementRebond);
        NewtonFrottementRebond = new BilleSonCollision(NewtonFrottementRebond,hurlements);

        PesanteurFrottementRebond = new BilleParDefaut(p5, rayon,new Vecteur(),Color.yellow, cadre);
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


        Attrapable = new BilleParDefaut(p4, rayon,v4,Color.orange, cadre);
        Attrapable = new BilleRebond(Attrapable);
        Attrapable = new BilleSonCollision(Attrapable, hurlements);
        Attrapable = new BillePilote(Attrapable);

        /*
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

        billes.add(NewtonFrottementRebond);
        billes.add(PesanteurFrottementRebond);
        billes.add(MRURebond);
        billes.add(MRUPasseMuraille);
        billes.add(HurlanteNewtonArret);
        //billes.add(Fantome);
        //billes.add(Fusion);
        //billes.add(Division);
        billes.add(Attrapable);


        cadre.addChoixHurlementListener((ItemListener) HurlanteNewtonArret);
        cadre.addMouseListener((MouseListener) Attrapable);
        cadre.addMouseMotionListener((MouseMotionListener) Attrapable);
        cadre.addKeyListener(cadre);

        //---------------------- ici finit la partie � changer -------------------------------------------------------------

        System.out.println("billes = " + billes);


        //-------------------- cr�ation de l'objet responsable de l'animation (c'est un thread s�par�) -----------------------

        AnimationBilles animationBilles = new AnimationBilles(billes, cadre);

        //----------------------- mise en place des �couteurs de boutons qui permettent de contr�ler (un peu...) l'application -----------------

        EcouteurBoutonLancer ecouteurBoutonLancer = new EcouteurBoutonLancer(animationBilles);
        EcouteurBoutonArreter ecouteurBoutonArr�ter = new EcouteurBoutonArreter(animationBilles);
        EcouteurBoutonQuitter ecouteurBoutonQuitter = new EcouteurBoutonQuitter(animationBilles);

        //------------------------- activation des �couteurs des boutons et �a tourne tout seul ------------------------------


        cadre.lancerBilles.addActionListener(ecouteurBoutonLancer);             // pourrait �tre remplac� par Observable - Observer
        cadre.arr�terBilles.addActionListener(ecouteurBoutonArr�ter);           // pourrait �tre remplac� par Observable - Observer
        cadre.quitter.addActionListener(ecouteurBoutonQuitter);
    }
}
