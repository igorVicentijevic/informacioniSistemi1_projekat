/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SyncronizationSharedLibrary;

import java.io.Serializable;

/**
 *
 * @author igor
 */
public class SyncMessage implements Serializable {
    
    public static enum Operation{INSERT,DELETE,UPDATE};
    
    public Object objectToSyncronize;
    public Operation operation;

    public SyncMessage(Object objectToSyncronize, Operation operation) {
        this.objectToSyncronize = objectToSyncronize;
        this.operation = operation;
    }
    
    
}
