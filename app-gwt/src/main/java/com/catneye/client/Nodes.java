/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.catneye.client;

import com.google.gwt.user.client.ui.TreeItem;

/**
 *
 * @author plintus
 */
public class Nodes {
    
    private Integer id;
    private String name;
    private Integer idparent;
    private TreeItem treeItem;

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the idparent
     */
    public Integer getIdparent() {
        return idparent;
    }

    /**
     * @param idparent the idparent to set
     */
    public void setIdparent(Integer idparent) {
        this.idparent = idparent;
    }
    
    @Override
    public String toString(){
        return new StringBuilder()
                .append("id=").append(id).append("; ")
                .append("name=").append(name).append("; ")
                .append("idparent=").append(idparent).append("; ")
                .append("treeItem=").append(treeItem)
                .toString();
    }

    /**
     * @return the treeItem
     */
    public TreeItem getTreeItem() {
        return treeItem;
    }

    /**
     * @param treeItem the treeItem to set
     */
    public void setTreeItem(TreeItem treeItem) {
        this.treeItem = treeItem;
    }
}
