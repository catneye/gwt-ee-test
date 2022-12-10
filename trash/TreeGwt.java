/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.catneye.util;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 *
 * @author plintus
 */
public class TreeGwt extends RemoteServiceServlet implements TreeGwtRemote{
    
    //@EJB
    //TreeBeanRemote treeBean;

    @Override
    public TreeDtoClient getTrees() {
        TreeDtoClient ret = new TreeDtoClient();
        //TreeDto trees = treeBean.getTrees();
        
        //ret.getNodes().addAll(trees.getNodes());
        //ret.getLeafs().addAll(trees.getLeafs());
        return ret;
    }
}
