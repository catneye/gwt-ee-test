/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.catneye.bean;

import com.catneye.dto.TreeDto;
import javax.ejb.Remote;


/**
 *
 * @author plintus
 */
@Remote
public interface TreeBeanRemote {
    
    public TreeDto getTrees();
}
