/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.catneye.util;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 *
 * @author plintus
 */
public interface TreesAsync {
    void getTrees(TreeDtoClient symbols, AsyncCallback<TreeDtoClient> callback);
}
