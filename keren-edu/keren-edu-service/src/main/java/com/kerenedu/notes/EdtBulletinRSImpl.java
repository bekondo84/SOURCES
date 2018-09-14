
package com.kerenedu.notes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;

import com.bekosoftware.genericdaolayer.dao.tools.RestrictionsContainer;
import com.bekosoftware.genericmanagerlayer.core.ifaces.GenericManager;
import com.kerem.core.KerenExecption;
import com.kerem.core.MetaDataUtil;
import com.kerenedu.configuration.CacheMemory;
import com.kerenedu.core.ifaces.report.ViewNoteHelperManagerRemote;
import com.kerenedu.inscription.Inscription;
import com.kerenedu.inscription.InscriptionManagerRemote;
import com.kerenedu.model.report.ViewNoteHelper;
import com.megatimgroup.generic.jax.rs.layer.annot.Manager;
import com.megatimgroup.generic.jax.rs.layer.impl.AbstractGenericService;
import com.megatimgroup.generic.jax.rs.layer.impl.MetaData;

/**
 * Classe d'implementation du Web Service JAX-RS
 * 
 * @since Thu Feb 15 12:46:28 CET 2018
 * 
 */
@Path("/edtbulletin")
public class EdtBulletinRSImpl extends AbstractGenericService<EdtBulletin, Long> implements EdtBulletinRS {

	/**
	 * On injecte un Gestionnaire d'entites
	 * 
	 */
	@Manager(application = "kereneducation", name = "BulletinManagerImpl", interf = BulletinManagerRemote.class)
	protected BulletinManagerRemote managerBul;

	@Manager(application = "kereneducation", name = "BulletinHelperGenerateManagerImpl", interf = BulletinHelperGenerateManagerRemote.class)
	protected BulletinHelperGenerateManagerRemote managerHelper;

	@Manager(application = "kereneducation", name = "ModelBulletinManagerImpl", interf = ModelBulletinManagerRemote.class)
	protected ModelBulletinManagerRemote managerModel;

	@Manager(application = "kereneducation", name = "InscriptionManagerImpl", interf = InscriptionManagerRemote.class)
	protected InscriptionManagerRemote managerinscrit;

	@Manager(application = "kereneducation", name = "ViewNoteHelperManagerImpl", interf = ViewNoteHelperManagerRemote.class)
	protected ViewNoteHelperManagerRemote managerNoteHelper;
	
	@Manager(application = "kereneducation", name = "ExamenManagerImpl", interf = ExamenManagerRemote.class)
	protected ExamenManagerRemote managerxamen;
	
	@Manager(application = "kereneducation", name = "NoteDetailManagerImpl", interf = NoteDetailManagerRemote.class)
	protected NoteDetailManagerRemote managernotedlt;

	public EdtBulletinRSImpl() {
		super();
	}

	/**
	 * Methode permettant de retourner le gestionnaire d'entites
	 * 
	 */
	@Override
	public GenericManager<EdtBulletin, Long> getManager() {
		return null;
	}

	public String getModuleName() {
		return ("kereneducation");
	}

	@Override
	public MetaData getMetaData(HttpHeaders headers) {
		// TODO Auto-generated method stub
		try {

			MetaData meta = MetaDataUtil.getMetaData(new EdtBulletin(), new HashMap<String, MetaData>(),
					new ArrayList<String>());
			return meta;
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public EdtBulletin update(@Context HttpHeaders headers,Long id, EdtBulletin entity) {
		CacheMemory.setFiliere(entity.getFiliere());
		CacheMemory.setClasse(entity.getClasse());
		return entity; // To change body of generated methods, choose Tools |
						// Templates.
	}

	@Override
	public EdtBulletin save(@Context HttpHeaders headers,EdtBulletin entity) {
		// To change body of generated methods, choose Tools | Templates.
		CacheMemory.setFiliere(entity.getFiliere());
		CacheMemory.setClasse(entity.getClasse());

		// generate bulletin
		System.out.println(
				"EdtBulletinRSImpl.save() ============ Début genration des bulletins ===== de la classe de :::======"
						+ entity.getClasse().getLibelle());
		this.generateBulletin(entity);
		System.out.println(
				"EdtBulletinRSImpl.save() ============ Fin de la generation des bulletins =======de la classe de :::======"
						+ entity.getClasse().getLibelle());
		// affectation des rangs enfonction des moyennes
		return entity;
	}

	private void generateBulletin(EdtBulletin critere) {
		RestrictionsContainer container = RestrictionsContainer.newInstance();
		List<Bulletin> datas = new ArrayList<Bulletin>();
		if (critere.getClasse() != null) {
			container.addEq("classe.id", critere.getClasse().getId());
		}

		datas = managerBul.filter(container.getPredicats(), null, new HashSet<String>(), 0, -1);
		System.out.println("EdtBulletinRSImpl.generateBulletin() Buletin dejà generé trouvée " + datas);
		
		// 0- supprimer les bulletin trouvé et regenerer
		for (Bulletin b : datas) {
			managerBul.delete(b.getId());
		} // end for(Bulletin b : datas) to delete

		// 1- recherche des élève ibscrit dans la classe
		container = new RestrictionsContainer();
		if (critere.getClasse() != null) {
			container.addEq("classe.id", critere.getClasse().getId());
		}
		List<Inscription> eleves = managerinscrit.filter(container.getPredicats(), null, new HashSet<String>(), 0, -1);
		System.out.println(
				"EdtBulletinRSImpl.generateBulletin() nombre eleve de la classe ============== " + eleves.size());
		if (eleves == null || eleves.isEmpty()) {
			throw new KerenExecption("Aucun Eleve inscrit dans la classe choisis !!!");
		}
		//find examen 
		Examen examen = managerxamen.find("id", critere.getSeq().getId());
			for (Inscription inscrit : eleves) {
				container = RestrictionsContainer.newInstance();
				container.addEq("classe.id", critere.getClasse().getId());
				container.addEq("eleve.id", inscrit.getId());
				container.addEq("examen.id", examen.getId());
				List<ViewNoteHelper> noteeleves = managerNoteHelper.filter(container.getPredicats(), null,
						new HashSet<String>(), 0, -1);
				System.out.println("EdtBulletinRSImpl.generateBulletin() nobre note"+noteeleves.size());
				List<LigneBulletinClasse> lignelist = new ArrayList<LigneBulletinClasse>();
				Bulletin bulletin = new Bulletin();
				for (ViewNoteHelper h : noteeleves) {
					bulletin = new Bulletin(h,examen);
					LigneBulletinClasse ligne = new LigneBulletinClasse();
					ligne = new LigneBulletinClasse(h);
					ligne.setId(-1);
					lignelist.add(ligne);
				} // fin for (BulletinHelperGenerate h : listNote)
				bulletin.setId(-1);
				bulletin.setLignes(lignelist);
				managerBul.save(bulletin);
				System.out.println("EdtBulletinRSImpl.generateBulletin()  Fin Traitement Bulettin eleve============= "+ inscrit.getEleve().getNom());
			} // fin for(Inscription inscrit : eleves)

	}// fin

}
