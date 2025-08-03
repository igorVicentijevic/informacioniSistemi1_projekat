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
public class RequestDTO implements Serializable{
    public RequestDTO(String command, Object o){
        this.command = command;
        this.o = o;
    }
    public String command;
    public Object o;
}
