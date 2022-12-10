/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.catneye.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

/**
 *
 * @author plintus
 */
@Entity
@Table(name = "leafs", catalog = "test", schema = "public")
@NamedQueries({
    @NamedQuery(name = "Leafs.findAll", query = "SELECT l FROM Leafs l"),
    @NamedQuery(name = "Leafs.findById", query = "SELECT l FROM Leafs l WHERE l.id = :id"),
    @NamedQuery(name = "Leafs.findByName", query = "SELECT l FROM Leafs l WHERE l.name = :name"),
    @NamedQuery(name = "Leafs.findByAdddate", query = "SELECT l FROM Leafs l WHERE l.adddate = :adddate"),
    @NamedQuery(name = "Leafs.findByIdnodes", query = "SELECT l FROM Leafs l WHERE l.idnodes = :idnodes")})
public class Leafs implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Size(max = 256)
    @Column(name = "name", length = 256)
    private String name;
    @Column(name = "adddate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date adddate;
    @Column(name = "idnodes")
    private Integer idnodes;

    public Leafs() {
    }

    public Leafs(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getAdddate() {
        return adddate;
    }

    public void setAdddate(Date adddate) {
        this.adddate = adddate;
    }

    public Integer getIdnodes() {
        return idnodes;
    }

    public void setIdnodes(Integer idnodes) {
        this.idnodes = idnodes;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Leafs)) {
            return false;
        }
        Leafs other = (Leafs) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.catneye.bean.Leafs[ id=" + id + " ]";
    }
    
}
