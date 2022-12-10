/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.catneye.bean;

import com.catneye.dto.ProductDto;
import com.catneye.entity.Product;
import com.catneye.util.TransformUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author plintus
 */
@Stateless
public class ProductBean implements ProductBeanRemote {

    @PersistenceContext(unitName = "test-ejbPU")
    private EntityManager em;

    /**
     * Gets all Products list.
     *
     * @return this Product objects.
     */
    @Override
    public List<ProductDto> getProducts() {
        List<ProductDto> ret = new ArrayList<>();
        Query query = em.createNamedQuery("Product.findAll");
        List<Product> data = query.getResultList();
        for (Product p : data) {
            ProductDto pi = (ProductDto) TransformUtil.clone(new ProductDto(), p);
            pi.setResult(true);
            ret.add(pi);
        }
        return ret;
    }

    /**
     * Get Product.
     * @param id This Product id.  
     * @return this Product object.
     */
    @Override
    public ProductDto getProduct(Integer id) {
        ProductDto ret = null;
        try {
            Query query = em.createNamedQuery("Product.findById");
            query.setParameter("id", id);
            Product data = (Product) query.getSingleResult();
            ret = (ProductDto) TransformUtil.clone(new ProductDto(), data);
            ret.setResult(true);
        } catch (NoResultException ex) {
            Logger.getLogger(ProductBean.class.getName()).log(Level.FINE, "getProduct : {0} NoResultException", id);
        }
        return ret;
    }

    /**
     * Get Products list.
     * @param name This Product name.  
     * @return this Product objects.
     */
    @Override
    public List<ProductDto> getProducts(String name) {
        List<ProductDto> ret = new ArrayList<>();
        Query query = em.createNamedQuery("Product.findByName");
        query.setParameter("name", name);
        List<Product> data = query.getResultList();
        for (Product p : data) {
            ProductDto pi = (ProductDto) TransformUtil.clone(new ProductDto(), p);
            pi.setResult(true);
            ret.add(pi);
        }
        return ret;
    }

    /**
     * Set Product.
     * @param product Is object.
     * @return this modified Product object.
     */
    @Override
    public ProductDto setProduct(ProductDto product) {

        Product data = null;
        if (product.getId() != null) {
            Query query = em.createNamedQuery("Product.findById");
            query.setParameter("id", product.getId());
            try {
                data = (Product) query.getSingleResult();
                data.setAdddate(product.getAdddateUtil());
                data.setName(product.getName());
                data.setPrice(product.getPrice());

                em.merge(data);
                em.flush();
            } catch (NoResultException ex) {
                Logger.getLogger(ProductBean.class.getName()).log(Level.FINE, "getProduct : {0} NoResultException, Create new ", product.getId());
            }
        }
        if (data == null) {
            data = new Product();
            data.setAdddate(product.getAdddateUtil());
            data.setName(product.getName());
            data.setPrice(product.getPrice());
            em.persist(data);
            em.flush();
        }

        ProductDto pi = (ProductDto) TransformUtil.clone(new ProductDto(), data);
        pi.setResult(true);
        return pi;
    }

    /**
     * Remove Product.
     * @param id is Product id.  
     * @return is removed Product object.
     */
    @Override
    public ProductDto removeProduct(Integer id) {

        ProductDto ret = null;
        try {
            Query query = em.createNamedQuery("Product.findById");
            query.setParameter("id", id);
            Product data = (Product) query.getSingleResult();
            ret = (ProductDto) TransformUtil.clone(new ProductDto(), data);
            ret.setResult(true);
            em.remove(data);
        } catch (NoResultException ex) {
            Logger.getLogger(ProductBean.class.getName()).log(Level.FINE, "removeProduct : {0} NoResultException", id);
        }
        return ret;
    }
}
