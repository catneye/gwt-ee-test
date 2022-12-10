/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.catneye.util;

import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 *
 * @author plintus
 */
@RemoteServiceRelativePath("treesMethods")
public interface TreeGwtRemote {

    public TreeDtoClient getTrees();
}
