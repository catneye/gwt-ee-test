/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.catneye.util;

/**
 *
 * @author plintus
 */
public interface Constants {
    public String SERVICE_NAME = "Product service";
    public String SERVICE_BEAN_NAME = "java:global/gwt-ee-test/app-ejb/TreeBean!com.catneye.bean.TreeBeanRemote";
    public String MESSAGE_SENDER_BEAN_NAME = "java:global/gwt-ee-test/app-ejb/MessageSenderBean!com.catneye.bean.MessageSenderBeanRemote";
    public String QUEUE_NAME="/jms/queue/Queue";
}
