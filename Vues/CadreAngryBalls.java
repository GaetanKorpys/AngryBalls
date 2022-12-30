package exodecorateur_angryballs.maladroit.Vues;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.Vector;

import exodecorateur_angryballs.maladroit.AnimationBilles;
import exodecorateur_angryballs.maladroit.OutilsConfigurationBilleHurlante;
import exodecorateur_angryballs.maladroit.Simulation.DivisionMode;
import exodecorateur_angryballs.maladroit.Simulation.FusionMode;
import exodecorateur_angryballs.maladroit.Simulation.Mode;
import exodecorateur_angryballs.maladroit.Simulation.PresentationSujetMode;
import exodecorateur_angryballs.maladroit.Modele.Bille;
import musique.SonLong;
import outilsvues.EcouteurTerminaison;

import outilsvues.Outils;

/**
 * Vue dessinant les billes et contenant les 3 boutons de contr�le (arr�t du programme, lancer les billes, arr�ter les billes) 
 * et contenant la ligne des boutons de choix des sons pour la bille hurlante
 * 
 *  ICI : IL N'Y A RIEN A CHANGER 
 *  
 * 
 * */
public class CadreAngryBalls extends Frame implements VueBillard, KeyListener
{
    TextField pr�sentation;
    Billard billard;
    public Button lancerBilles, arr�terBilles, quitter, fusion;
    Panel haut, centre, bas, ligneBoutonsLancerArr�t;
    PanneauChoixHurlement ligneBoutonsChoixHurlement;
    EcouteurTerminaison ecouteurTerminaison;
    GraphicsDevice graphicsDevice;
    AnimationBilles animationBilles;
    SonLong[] hurlements;
    int choixHurlementInitial;

    private void configurationSon()
    {
        //---------------------- gestion des bruitages : param�trage du chemin du dossier contenant les fichiers audio --------------------------

        File file = new File(System.getProperty("user.dir").toString()); // l� o� la JVM est lanc�e : racine du projet

        File r�pertoireSon = new File(file.getAbsoluteFile(), "maladroit"+File.separatorChar+"bruits");
        System.out.println(r�pertoireSon);

        //-------------------- chargement des sons pour les hurlements --------------------------------------

        Vector<SonLong> sonsLongs = OutilsConfigurationBilleHurlante.chargeSons(r�pertoireSon, "config_audio_bille_hurlante.txt");

        hurlements = SonLong.toTableau(sonsLongs);
    }

    private void construtionCadre(String message, SonLong [] hurlements, int choixHurlementInitial)
    {
        int nombreLignes = 2, nombreColonnes = 1;

        this.haut = new Panel(); this.haut.setBackground(Color.LIGHT_GRAY);
        this.add(this.haut,BorderLayout.NORTH);

        this.centre = new Panel();
        this.add(this.centre,BorderLayout.CENTER);

        this.bas = new Panel(); this.bas.setBackground(Color.LIGHT_GRAY);
        this.add(this.bas,BorderLayout.SOUTH);

        this.pr�sentation = new TextField(message, 100); this.pr�sentation.setEditable(false);
        this.haut.add(this.pr�sentation);

        this.bas.setLayout(new GridLayout(nombreLignes, nombreColonnes));

        this.ligneBoutonsLancerArr�t = new Panel();
        this.bas.add(this.ligneBoutonsLancerArr�t);

        this.lancerBilles = new Button("Lancer les billes");
        this.ligneBoutonsLancerArr�t.add(this.lancerBilles);

        this.arr�terBilles = new Button("Arr�ter les billes");
        this.ligneBoutonsLancerArr�t.add(this.arr�terBilles);

        this.quitter = new Button("Quitter");
        this.ligneBoutonsLancerArr�t.add(this.quitter);

        this.fusion = new Button("Mode Fusion");
        this.ligneBoutonsLancerArr�t.add(this.fusion);

        this.ligneBoutonsChoixHurlement = new PanneauChoixHurlement(hurlements, choixHurlementInitial);
        this.bas.add(this.ligneBoutonsChoixHurlement);
    }


    public CadreAngryBalls(String titre, String message) throws HeadlessException
    {
        super(titre);

        configurationSon();
        choixHurlementInitial = 0;

        this.setIgnoreRepaint(true);
        this.setUndecorated(true);

        Outils.place(this, 0.33, 0.33, 0.5, 0.5);
        ecouteurTerminaison = new EcouteurTerminaison(this);

        construtionCadre(message, hurlements, choixHurlementInitial);

        billard = new Billard();
        billard.addKeyListener(this);
        this.add(this.billard);

    }

    public double largeurBillard()
    {
    return this.billard.getWidth();
    }

    public double hauteurBillard()
    {
    return this.billard.getHeight();
    }

    @Override
    public Billard getBillard() {
        return billard;
    }

    @Override
    public SonLong[] getHurlements() {return hurlements;}

    @Override
    public void miseAJour()
    {
    this.billard.repaint();
    }

    @Override
    public void montrer()
    {
        this.setVisible(true);
    }

    @Override
    public void addChoixHurlementListener(ItemListener �couteurChoixHurlant)
    {
        int i;
        for ( i = 0; i < this.ligneBoutonsChoixHurlement.boutons.length; ++i) this.ligneBoutonsChoixHurlement.boutons[i].addItemListener(�couteurChoixHurlant);
    }

    public void setGraphics(GraphicsDevice gd) {graphicsDevice = gd;}

    @Override
    public GraphicsDevice getGraphicsDevice(){return graphicsDevice;}

    @Override
    public void setAnimationBilles (AnimationBilles animationBilles){this.animationBilles = animationBilles;}

    @Override
    public void keyPressed(KeyEvent e) {
        if( e.getKeyCode() == KeyEvent.VK_ESCAPE )
        {
            if(animationBilles != null)
                animationBilles.quitter();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //Todo
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //Todo
    }
}