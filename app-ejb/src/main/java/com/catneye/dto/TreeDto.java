/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.catneye.dto;

import com.catneye.entity.Leafs;
import com.catneye.entity.Nodes;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author plintus
 */
public class TreeDto extends DtoBase implements Serializable{
    private List<Nodes> nodes=new ArrayList();
    private List<Leafs> leafs=new ArrayList();

    /**
     * @return the nodes
     */
    public List<Nodes> getNodes() {
        return nodes;
    }

    /**
     * @param nodes the nodes to set
     */
    public void setNodes(List<Nodes> nodes) {
        this.nodes = nodes;
    }

    /**
     * @return the leafs
     */
    public List<Leafs> getLeafs() {
        return leafs;
    }

    /**
     * @param leafs the leafs to set
     */
    public void setLeafs(List<Leafs> leafs) {
        this.leafs = leafs;
    }
}
