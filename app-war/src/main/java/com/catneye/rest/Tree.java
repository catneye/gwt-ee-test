/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.catneye.rest;

import com.catneye.bean.TreeBeanRemote;
import com.catneye.util.Constants;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author plintus
 */
@Path("/Tree")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class Tree {

    @EJB
    TreeBeanRemote pBean;
    
    public Tree() {
        try {
            pBean = (TreeBeanRemote) InitialContext.doLookup(Constants.SERVICE_BEAN_NAME);
        } catch (NamingException ex) {
            Logger.getLogger(API.class.getName()).log(Level.SEVERE, "ProductAPI : {0}", ex);
        }
    }
    @GET
    @Path("getAllTree")
    public Response getAllProducts() {
        Logger.getLogger(API.class.getName()).log(Level.FINE, "getAllProducts : {0}", 0);
        return Response.ok(pBean.getTrees()).build();
    }

}
