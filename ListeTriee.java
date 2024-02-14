/**
 * Classe ListeTriee
 * @author Étienne André
 * @since 2021-11-12
 *
 */



public class ListeTriee{

    // Attribut de liste sous-jacente
    public Liste liste;
    
    public ListeTriee(Liste listevide){
        // Affectation de la liste vide à l'attribut privé
        liste = listevide;
    }
    
    /**
     * retourne la premiere place de la liste
     * @return tete de liste
     */
    public int tete(){
    	return liste.tete();
    }
	
    /**
     * permet de connaitre la place suivante dans la liste
     * @param p place en cours
     * @return place derriere p dans la liste
     */
    public int suc(int p){
        return liste.suc(p);
    }
    
    /**
     * retourne la valeur associee a la place p
     * @param p place de la liste
     * @return la valeur associee  p
     */
    public String val(int p){
        return liste.val(p);
    }
 
    /**
     * indique si la place p est a la fin de la liste ou non
     * @param p place de la liste
     * @return vrai si p est a la fin de la liste, faux sinon
     */   
    public boolean finliste(int p){
        return liste.finliste(p);
    }
	
    
    /**
     * ajoute un element au bon endroit dans la liste triee
     * @param chaine element a inserer
     */
    public void adjlisT(String chaine){
        int pos = tete();
        int prec = pos;

        if(finliste(tete()) || (prec == tete() && val(pos).compareTo(chaine) > 0)){
            liste.adjtlis(chaine);
        }else {

            while(!finliste(pos) && val(pos).compareTo(chaine) < 0){
                prec = pos;
                pos = suc(pos);
            }

            liste.adjlis(prec, chaine);    


            
        }
    }
	
    /**
     * permet de supprimer un element d'une liste. Supprime le premier element dont la valeur est egale a "chaine" ; ne fait rien si "chaine" n'appartient pas a la liste.
     * @param chaine l'element a supprimer 
     */
    public void suplisT(String chaine){
        int pos = 0;
        boolean trouve = false;

        if(!finliste(tete())){
            while(!finliste(pos)){
                if(val(pos).equals(chaine) && !trouve){
                    liste.suplis(pos);
                    trouve = true;
                }
                pos = suc(pos);
            }
        }
        
    }
		
    public String toString(){
	    return liste.toString();
    }
}
