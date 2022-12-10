/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.catneye.rest;

import com.catneye.bean.ProductBeanRemote;
import com.catneye.dto.ProductDto;
import com.catneye.exception.TransactionException;
import com.catneye.util.Constants;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author plintus
 */
@Path("/Product")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class Product implements Serializable {

    @EJB
    ProductBeanRemote pBean;

    /**
     * Class constructor.
     */
    public Product() {
        try {
            pBean = (ProductBeanRemote) InitialContext.doLookup(Constants.SERVICE_BEAN_NAME);
        } catch (NamingException ex) {
            Logger.getLogger(API.class.getName()).log(Level.SEVERE, "ProductAPI : {0}", ex);
        }
    }

    /**
     * Gets all Products list.
     *
     * @return this Product objects as JSON string.
     */
    @GET
    @Path("getAllProducts")
    public Response getAllProducts() {
        Logger.getLogger(API.class.getName()).log(Level.FINE, "getAllProducts : {0}", 0);
        return Response.ok(pBean.getProducts()).build();
    }

    /**
     * Get Product.
     *
     * @param id This Product id.
     * @return this Product object as JSON string.
     */
    @GET
    @Path("getProduct/{id}")
    public Response getProduct(@PathParam("id") Integer id) {
        Logger.getLogger(API.class.getName()).log(Level.FINE, "getProduct : {0}", id);
        ProductDto ret = new ProductDto();
        try {
            ret = pBean.getProduct(id);
            return Response.ok(ret).build();
        } catch (TransactionException ex) {
            Logger.getLogger(API.class.getName()).log(Level.SEVERE, "ProductAPI : {0}", ex);
            ret.setDebug(ex.getMessage());
            return Response.ok(ret).status(500).build();
        }
    }

    /**
     * Get Products list.
     *
     * @param name This Product name.
     * @return this Product object as JSON string.
     */
    @GET
    @Path("getProducts/{name}")
    public Response getProducts(@PathParam("name") String name) {
        Logger.getLogger(API.class.getName()).log(Level.FINE, "getProducts : {0}", name);
        List<ProductDto> ret = new ArrayList();
        try {
            ret = pBean.getProducts(name);
            return Response.ok(ret).build();
        } catch (TransactionException ex) {
            Logger.getLogger(API.class.getName()).log(Level.SEVERE, "ProductAPI : {0}", ex);
            return Response.ok(ret).status(500).build();
        }
    }

    //http://127.0.0.1:8080/gradle-war/rest/ProductAPI/setProduct/{"product":{"adddate": "2020-09-10T07:05:52.234Z[UTC]","id": 1,"name": "p11","price": 12.12}}
    /**
     * Set Product.
     *
     * @param obj Is JSON string. example: {"product":{"adddate":
     * "2020-09-10T07:05:52.234Z[UTC]","id": 1,"name": "p11","price": 12.12}}
     * @return this Product object as JSON string.
     */
    /*@POST
    @Path("setProduct/{obj}")
    public Response setProduct(@PathParam("obj") ProductDto obj) {
        Logger.getLogger(ProductAPI.class.getName()).log(Level.FINE, "setProduct : {0}", obj);
        ProductDto ret = new ProductDto();
        try {
            ret = pBean.setProduct(obj);
            return Response.ok(ret).build();
        } catch (TransactionException ex) {
            Logger.getLogger(ProductAPI.class.getName()).log(Level.SEVERE, "ProductAPI : {0}", ex);
            ret.setDebug(ex.getMessage());
            return Response.ok(ret).status(500).build();
        }
    }*/

    /**
     * Remove Product.
     *
     * @param id is Product id.
     * @return is removed Product object.
     */
    @DELETE
    @Path("removeProduct/{id}")
    public Response removeProduct(@PathParam("id") Integer id) {
        Logger.getLogger(API.class.getName()).log(Level.FINE, "removeProduct : {0}", id);
        ProductDto ret = new ProductDto();
        try {
            ret = pBean.removeProduct(id);
            //Send jms message
            /*msgBean.sendTextMessage(new StringBuilder()
                    .append("Product id:")
                    .append(id)
                    .append("removed ")
                    .toString());*/
            return Response.ok(ret).build();
        } catch (TransactionException ex) {
            Logger.getLogger(API.class.getName()).log(Level.SEVERE, "ProductAPI : {0}", ex);
            ret.setDebug(ex.getMessage());
            return Response.ok(ret).status(500).build();
        }
    }
}
