/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.catneye.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author plintus
 */
public class Client implements EntryPoint {

    @Override
    public void onModuleLoad() {
        List<HashMap> trees = new ArrayList();
        HashMap<Integer, Leafs> leafs = new HashMap();
        List<TreeItem> roots = new ArrayList();

        String url = "http://127.0.0.1:8080/app-war/API/Tree/getAllTree";
        //Window.alert("url : " + url);
        RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, url);
        builder.setHeader("content-type", "application/json;charset=UTF-8");
        try {
            Request response = builder.sendRequest(null, new RequestCallback() {
                @Override
                public void onResponseReceived(Request request, Response response) {

                    String responseBody = response.getText();
                    //Window.alert("response : " + responseBody);
                    int statusCode = response.getStatusCode();
                    //Window.alert("statusCode() : " + statusCode);

                    if (statusCode == Response.SC_OK) {

                        JSONValue jsonValue = JSONParser.parseStrict(responseBody);
                        JSONObject jsonObject = jsonValue.isObject();
                        JSONArray jsonNodes = jsonObject.get("nodes").isArray();
                        JSONArray jsonLeafs = jsonObject.get("leafs").isArray();
                        //Window.alert("jsonNodes count : " + jsonNodes.size());
                        //Window.alert("jsonLeafs count : " + jsonLeafs.size());

                        for (int i = 0; i < jsonNodes.size(); i++) {
                            JSONValue element = jsonNodes.get(i);

                            //Window.alert("node element : " + element);
                            Nodes node = new Nodes();
                            node.setId(Double.valueOf(element.isObject().get("id").isNumber().doubleValue()).intValue());
                            if (element.isObject().get("idparent") != null) {
                                node.setIdparent(Double.valueOf(element.isObject().get("idparent").isNumber().doubleValue()).intValue());
                            }
                            node.setName(element.isObject().get("name").isString().stringValue());

                            //Window.alert("node : " + node.toString());
                            if (node.getIdparent() == null) {
                                HashMap<Integer, Nodes> nodes = new HashMap();
                                nodes.put(node.getId(), node);
                                trees.add(nodes);
                            } else {
                                for (HashMap tree : trees) {
                                    if (tree.containsKey(node.getIdparent())) {
                                        tree.put(node.getId(), node);
                                        break;
                                    }
                                }
                            }
                        }
                        for (int i = 0; i < jsonLeafs.size(); i++) {
                            JSONValue element = jsonLeafs.get(i);
                            Leafs leaf = new Leafs();
                            leaf.setId(Double.valueOf(element.isObject().get("id").isNumber().doubleValue()).intValue());
                            leaf.setIdnodes(Double.valueOf(element.isObject().get("idnodes").isNumber().doubleValue()).intValue());
                            leaf.setName(element.isObject().get("name").isString().stringValue());
                            leafs.put(leaf.getId(), leaf);
                        }

                        //Window.alert("trees : " + trees);
                        //Window.alert("leafs : " + leafs);
                        for (HashMap nodes : trees) {

                            for (Object value : nodes.values()) {
                                Nodes node = (Nodes) value;
                                if (node.getIdparent() == null) {
                                    TreeItem root = new TreeItem();
                                    root.setText(node.getName());
                                    node.setTreeItem(root);

                                    roots.add(root);
                                } else {
                                    TreeItem item = new TreeItem();
                                    item.setText(node.getName());
                                    Nodes rootNode = (Nodes) nodes.get(node.getIdparent());
                                    rootNode.getTreeItem().addItem(item);
                                    node.setTreeItem(item);
                                }
                            }
                            /*
            nodes.forEach((key, value) -> {
                Nodes node = (Nodes) value;
                //Window.alert("node : " + node);
                if (node.getIdparent() == null) {
                    TreeItem root = new TreeItem();
                    root.setText(node.getName());
                    node.setTreeItem(root);

                    roots.add(root);
                } else {
                    TreeItem item = new TreeItem();
                    item.setText(node.getName());
                    Nodes rootNode = (Nodes) nodes.get(node.getIdparent());
                    rootNode.getTreeItem().addItem(item);
                    node.setTreeItem(item);
                }
            });*/
                        }
                        if (!leafs.isEmpty()) {

                            for (Object value : leafs.values()) {
                                Leafs leaf = (Leafs) value;
                                //Window.alert("leaf : " + leaf);
                                //Window.alert("trees : " + trees.size());
                                for (HashMap nodes : trees) {
                                    //Window.alert("nodes : " + nodes);
                                    if (nodes.containsKey(leaf.getIdnodes())) {
                                        Nodes node = (Nodes) nodes.get(leaf.getIdnodes());
                                        //Window.alert("node : " + node);
                                        TreeItem item = new TreeItem();
                                        item.setText(leaf.getName());
                                        leaf.setTreeItem(item);
                                        //Exception
                                        node.getTreeItem().addItem(item);
                                        //break;
                                    } else {
                                        //Window.alert("not exist : " + leaf.getIdnodes());
                                    }
                                }
                            }
                            /*
            leafs.forEach((key, value) -> {
                Leafs leaf = value;
                Window.alert("leaf : " + leaf);
                Window.alert("trees : " + trees.size());
                for (HashMap nodes : trees) {
                    Window.alert("nodes : " + nodes);
                    if (nodes.containsKey(leaf.getIdnodes())) {
                        Nodes node = (Nodes) nodes.get(leaf.getIdnodes());
                        Window.alert("node : " + node);
                        TreeItem item = new TreeItem();
                        item.setText(leaf.getName());
                        leaf.setTreeItem(item);
                        //Exception
                        node.getTreeItem().addItem(item);
                        //break;
                    } else {
                        Window.alert("not exist : " + leaf.getIdnodes());
                    }
                }
            });*/
                        }
                        //Window.alert("roots count : " + roots.size());
                        Tree t = new Tree();
                        for (TreeItem root : roots) {
                            t.addItem(root);
                            RootPanel.get().add(t);
                        }

                        //Window.alert("trees : " + trees);
                        //Window.alert("leafs : " + leafs);
                        //Window.alert("trees count : " + trees.size());
                    } else {
                    }
                    //Window.alert("trees : " + trees);
                    //Window.alert("leafs : " + leafs);
                }

                @Override
                public void onError(Request request, Throwable exception) {
                }
            });
        } catch (RequestException e) {
            System.out.println("RequestException() : " + e);
        }

    }

}
