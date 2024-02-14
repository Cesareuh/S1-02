/**
 * Classe principale de la SAÉ
 * @author Étienne André Sergueï Lenglet
 * @since 2021-11-04
 *
 */


public class Principale{

    private static final String[] ELEMENTS_DE_DEBUT
	= {"ABITEBOUL", "ADLEMAN", "AL-KINDI", "ALUR", "BERNERS-LEE",
	"BOOLE", "BUCHI", "BUTLER", "CLARKE", "CURRY"};
    private static final String[] ELEMENTS_DE_FIN
	= {"RABIN", "RIVEST", "SHAMIR", "SIFAKIS", "TORVALDS",
	"TURING", "ULLMAN", "VALIANT", "WIRTH", "YAO"};
    
    // NOTE: pour fichier 10 000
     	private static final String[] ELEMENTS_DE_DEBUT_SUPPR
     = {"ABBADI", "ABERGEL", "ALIAS", "ALIOUI", "AKKUS", "ALAZARD",
     "ALLA", "AIDARA", "ABRANTES", "AARAB"};
    // NOTE: pour fichier 1 000
    //private static final String[] ELEMENTS_DE_DEBUT_SUPPR
    // = {"ABADIE", "ABDALLAH", "ABRAHAM", "ADAM", "AFONSO",
    // "ALBERT", "ALEXANDRE", "ALI", "ALIX", "ALLAIN"};
    // NOTE: pour fichier 10 000
    private static final String[] ELEMENTS_DE_FIN_SUPPR
     = {"WEIS", "ZANIN", "WERQUIN", "YAGOUBI", "WERNERT",
     "WAWRZYNIAK", "ZULIANI", "ZAIRE", "WAVRANT", "VILLAR"}; //
    // NOTE: pour fichier 1 000
    //private static final String[] ELEMENTS_DE_FIN_SUPPR
    //= {"WEBER", "WEISS", "WINTERSTEIN", "WOLFF", "YANG",
    //"YILDIRIM", "YILDIZ", "YILMAZ", "ZIEGLER", "ZIMMERMANN"}; //
	
    // Type des listes, peut etre utile pour factoriser les tests
    private static final int CONTIGUE	       = 1;
    private static final int CHAINEE	       = 2;
    private static final int CHAINEE_PLIBRES   = 3;
	
    // Exemple d'utilisation de LectureFichier et remplissage d'une liste
    public static void remplir_liste(ListeTriee liste, String nom_fichier){
	LectureFichier lf = new LectureFichier(nom_fichier);
	// 		
        String[] liste_noms = lf.lireFichier();
        for (int i = 0; i < liste_noms.length; i++) {
            liste.adjlisT(liste_noms[i]);
        }
    }
		
    public static void main(String [] args){
	System.out.println("Bienvenue !");

	//Exemple d'utilisation de la classe EcritureFichier
    
	EcritureFichier fichier = new EcritureFichier("resultats.csv");
	fichier.ouvrirFichier();
	fichier.ecrireLigne("liste;operation;emplacement;duree(ns)");
	

        long duree;
        // Tester les trois types de liste
        for(int i = 1; i <= 3; i ++){

            // Temps exec adjlisT début alphabet
            duree = test_liste(i, ELEMENTS_DE_DEBUT, true);
            fichier.ecrireLigne(typeListe(i) + ";" + "ajout;" + "debut;" + duree);
                                                                                                                                                                                                    
            // Temps exec adjlisT fin alphabet
            duree = test_liste(i, ELEMENTS_DE_FIN, true);
            fichier.ecrireLigne(typeListe(i) + ";" + "ajout;" + "fin;" + duree);

            // Temps exec suplisT debut alphabet
            
            duree = test_liste(i, ELEMENTS_DE_DEBUT_SUPPR, false);
            fichier.ecrireLigne(typeListe(i) + ";" + "suppression;" + "debut;" + duree);

            // Temps exec suplisT fin alphabet

            duree = test_liste(i, ELEMENTS_DE_FIN_SUPPR, false);
            fichier.ecrireLigne(typeListe(i) + ";" + "suppression;" + "fin;" + duree);


        }
        fichier.fermerFichier();


    }

    public static long test_liste(int type, String[] noms, boolean adj){
        long date_debut;
        long date_fin;
        long duree;
        long moy = 0;
        ListeTriee lt;


        if(adj){
            for(int i = 0; i < 100; i++){
                lt = liste_triee(type, 10100);
                date_debut = System.nanoTime();
                for(int j = 0; j < 10; j++){
                    lt.adjlisT(noms[j]);
                }
                date_fin = System.nanoTime();
                duree = (date_fin - date_debut);
                moy += duree;
            }
        }else{
            for(int i = 0; i < 100; i++){
                lt = liste_triee(type, 10100);
                date_debut = System.nanoTime();
                for(int j = 0; j < 10; j++){
                    lt.suplisT(noms[j]);
                }
                date_fin = System.nanoTime();
                duree = (date_fin - date_debut);
                moy += duree;
            }
        }
        
        moy = moy/100;
        return moy;
    }

    public static String typeListe(int i){
        String s;
        if(i == 1){
            s = "contigue";
        }else if(i == 2){
            s = "chainee";
        }else{
            s = "chainee avec places libres";
        }

        return s;
    }

    /**
     * Initialise une liste en fonction des paramètres
     * @param type_l type de la liste
     * @param nb_noms nombres max de noms
     * @return liste créée
     */
    public static ListeTriee liste_triee(int type_l, int nb_noms){
        ListeTriee lt;
        if(type_l == CONTIGUE){
            lt = new ListeTriee(new ListeContigue(nb_noms));
        }else if(type_l == CHAINEE){
            lt = new ListeTriee(new ListeChainee(nb_noms));
        }else{
            lt = new ListeTriee(new ListeChaineePlacesLibres(nb_noms));
        }

        int n;
        if(nb_noms >= 1000){
            n = 1000;
        }else if(nb_noms >= 10000){
            n = 10000;
        }else if(nb_noms >= 100000){
            n = 100000;
        }else{
            n = 100;
        }
        remplir_liste(lt, "noms"+n+".txt");
        return lt;
    }
    
}
