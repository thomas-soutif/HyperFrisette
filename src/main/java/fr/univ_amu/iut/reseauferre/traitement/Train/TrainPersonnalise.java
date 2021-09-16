package fr.univ_amu.iut.reseauferre.traitement.Train;

import fr.univ_amu.iut.reseauferre.traitement.Entreprise.EntrepriseAnimaux;
import fr.univ_amu.iut.reseauferre.traitement.Entreprise.EntrepriseCargo;
import fr.univ_amu.iut.reseauferre.traitement.Entreprise.EntreprisePassager;
import fr.univ_amu.iut.reseauferre.traitement.Tarif.PolitiqueTarifaire;
import fr.univ_amu.iut.reseauferre.traitement.Wagon.Wagon;

/**
 * Décorateur permettant d'ajouter des options au train.
 * <ul>
 *     <li>l'option streaming</li>
 *     <li>l'option Wifi</li>
 * </ul>
 * le but de toutes les méthodes est similaire au but des méthodes de Train
 *
 * @see Train
 */
public abstract class TrainPersonnalise extends Train{

	/**
	 * Train auquel l'option va être ajoutée.
	 */
    protected Train train;

	/**
	 * On passe en paramètre de ce constructeur le train auquel on veut ajouter une option.
	 * @param t
	 */
    public TrainPersonnalise(Train t){
        this.train = t;
        
    }

	/**
	 * Permet de déplacer le train, c'est-à-dire diminuer son temps restant de 10 minutes
	 * @param min
	 */
	@Override
	public void deplacer(long min) {
		 train.deplacer(min);
	}

	/**
	 * @see Train#getCout(PolitiqueTarifaire)
	 * @param po
	 *
	 */
	public abstract void getCout(PolitiqueTarifaire po);

	/**
	 * @see Train#correspond(EntrepriseAnimaux)
	 * @param entreprise
	 * @return
	 */
	public boolean correspond(EntrepriseAnimaux entreprise) {
		return train.correspond(entreprise);
	}

	/**
	 * @see Train#correspond(EntrepriseCargo)
	 * @param entreprise
	 * @return
	 */
	public boolean correspond(EntrepriseCargo entreprise) {
		return train.correspond(entreprise);
	}

	/**
	 * @see Train#correspond(EntreprisePassager)
	 * @param entreprise
	 * @return
	 */
	public boolean correspond(EntreprisePassager entreprise) {
		return train.correspond(entreprise);
	}

	/**
	 *Permet d'ajouter l'option.
	 */
    public abstract void personnaliser();

	/**
	 * @see Train#toString()
	 * @return
	 */
    @Override
	public String toString() {
        return train.toString();
    }

	/**
	 * Renvoie le train que l'on veut personnaliser.
	 * @return
	 */
	public Train getTrain() {
		return train;
	}

	/**
	 * @see Train#ajouterWagon(Wagon)
	 * @param wagon
	 * @return
	 */
	@Override
	public boolean ajouterWagon(Wagon wagon) {
		return train.ajouterWagon(wagon);
	}
}
