/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Category;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Alex
 */
@Stateless
public class CategoryFacade extends AbstractFacade<Category> {
    @PersistenceContext(unitName = "OnlineStorePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public Short findIdByName(String name){
        return (Short)em.createQuery("SELECT c.id FROM Category c WHERE c.name = :name").setParameter("name", name).getSingleResult();
    }

    public CategoryFacade() {
        super(Category.class);
    }
    
}
