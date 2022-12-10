/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.catneye.bean;

import com.catneye.dto.TreeDto;
import com.catneye.entity.Leafs;
import com.catneye.entity.Nodes;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author plintus
 */
@Stateless
public class TreeBean implements TreeBeanRemote {

    @PersistenceContext(unitName = "test-ejbPU")
    private EntityManager em;

    @Override
    public TreeDto getTrees() {
        TreeDto trees = new TreeDto();
        Query rootsQuery = em.createNamedQuery("Nodes.findRoots");
        List<Nodes> roots = rootsQuery.getResultList();
        for (Nodes root : roots) {
            trees.getNodes().add(root);
            Query nodesQuery = em.createNamedQuery("Nodes.findByIdparent");
            nodesQuery.setParameter("idparent", root.getId());
            List<Nodes> nodes = nodesQuery.getResultList();

            for (Nodes node : nodes) {
                trees.getNodes().add(node);
                Query leafsQuery = em.createNamedQuery("Leafs.findByIdnodes");
                leafsQuery.setParameter("idnodes", node.getId());
                List<Leafs> leafs = leafsQuery.getResultList();
                for (Leafs leaf : leafs) {
                    trees.getLeafs().add(leaf);
                }
            }
        }
        return trees;
    }
}
