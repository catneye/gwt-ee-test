/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.catneye.util;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 *
 * @author plintus
 */
public class TreesAsyncWatcher {

    private TreeDtoClient trees = new TreeDtoClient();
    private TreesAsync treeGwt = GWT.create(TreeGwt.class);

    private void refreshWatchList() {
        // Initialize the service proxy.
        if (treeGwt == null) {
            treeGwt = GWT.create(TreeGwt.class);
        }

        // Set up the callback object.
        AsyncCallback<TreeDtoClient> callback = new AsyncCallback<TreeDtoClient>() {
            @Override
            public void onFailure(Throwable caught) {
                // TODO: Do something with errors.
            }

            @Override
            public void onSuccess(TreeDtoClient result) {
                //updateTable(result);
            }
        };

        // Make the call to the stock price service.
        treeGwt.getTrees(trees, callback);
    }
}
