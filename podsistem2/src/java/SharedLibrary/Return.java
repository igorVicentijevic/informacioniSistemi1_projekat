/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SharedLibrary;

import java.io.Serializable;

/**
 *
 * @author igor
 */
public class Return implements Serializable{
    private String command;
    private Object returnData;

    public Return(String command, Object returnData) {
        this.command = command;
        this.returnData = returnData;
    }

    public String getCommand() {
        return command;
    }

    public Object getReturnData() {
        return returnData;
    }
    
    
}
