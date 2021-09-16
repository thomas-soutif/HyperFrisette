package fr.univ_amu.iut.reseauferre.traitement;

import fr.univ_amu.iut.reseauferre.affichage.Systeme;
import fr.univ_amu.iut.reseauferre.affichage.ihm.Observable;
import fr.univ_amu.iut.reseauferre.affichage.ihm.Observateur;
import fr.univ_amu.iut.reseauferre.traitement.Controleur.AttributionGlouton;
import fr.univ_amu.iut.reseauferre.traitement.Controleur.Controleur;
import fr.univ_amu.iut.reseauferre.traitement.Controleur.Sillon;
import fr.univ_amu.iut.reseauferre.traitement.Entreprise.Entreprise;
import fr.univ_amu.iut.reseauferre.traitement.Tarif.PolitiqueTarifaire;
import fr.univ_amu.iut.reseauferre.traitement.Tarif.TarifOrdinaire;

import java.time.LocalTime;
import java.util.*;


public class ApplicationHyperFrisette implements Observable {

	/**
	 * Collection qui à une entreprise associe sa facture
	 *
	 * @see Entreprise
	 */
	private static Map<Entreprise,Double> tarifEntreprise = new HashMap<>();
	private static PolitiqueTarifaire politiqueTarifaire;
	private static LocalTime heureActuelle = LocalTime.of(0,0,0);
	private List<Observateur> observateurs = new ArrayList<>();

	public static void main(String[] args) {

		Controleur.setAttribuerSillons(new AttributionGlouton());
		attribuerPolitiqueTarifaire(new TarifOrdinaire());



        Systeme.affichageBienvenue();
        //UNIQUEMENT POUR LE DEBUGAGE


	}

	public static void calculerCoutTotalTrajet(Set<Sillon> listeSillons){

		for(Sillon s : listeSillons)
		{
		    boolean estRef = false;
			s.getCout(politiqueTarifaire); // récupere le cout du sillon(qui lui va calculer le cout du train, de l'horaire et de la ligne
			double coutTotal = politiqueTarifaire.getCoutTotal();

			for(Map.Entry<Entreprise,Double> entry : tarifEntreprise.entrySet())
            {
                if(entry.getKey().getNumSIREN() == s.getTrain().getEntreprise().getNumSIREN()) {
                    double coutEnt = entry.getValue();
                    entry.setValue(coutEnt + coutTotal);
                    estRef = true;
                    break;
                }
            }

            //sinon si l'entreprise n'existe pas dans la liste, il faut donc la rajouter
            if(estRef == false)
                tarifEntreprise.put(s.getTrain().getEntreprise(),coutTotal);
		}



	}

	private static void attribuerPolitiqueTarifaire(PolitiqueTarifaire politiqueTarifaire) {
		ApplicationHyperFrisette.politiqueTarifaire = politiqueTarifaire;
	}

    public static Map<Entreprise, Double> getTarifEntreprise() {
        return tarifEntreprise;
    }

	public static void reinitialiserTarif(){

		Set<Entreprise> keys = new HashSet<>();
		for(Map.Entry<Entreprise,Double> entry : getTarifEntreprise().entrySet())
        {
            keys.add(entry.getKey()); // Recupere toute les clés primaires dans la map
        }
        // On les supprime toutes
        for(Entreprise e : keys)
            tarifEntreprise.remove(e);
	}
    public static LocalTime recupererHeure()
	{
		return heureActuelle;
	}
	public static void incrementerHeure(long min){

        LocalTime dixMin = LocalTime.of(0, 0).plusMinutes(min);
        heureActuelle = heureActuelle.plusMinutes(dixMin.getMinute());

	}

	@Override
	public void notifier() {
		for(Observateur obs: observateurs)
			obs.actualiser(heureActuelle);
	}

	@Override
	public void ajouterObservateur(Observateur obs) {
		observateurs.add(obs);
	}

	public LocalTime incrementerHeure(int temps){
		LocalTime nt = heureActuelle.plusMinutes(temps);
		heureActuelle = nt;
		notifier();
		return nt;
	}

	public LocalTime getTempsCourant() {
		return heureActuelle;
	}

	@Override
	public void supprimerObservateur(Observateur obs) {
		observateurs.remove(obs);
	}

}