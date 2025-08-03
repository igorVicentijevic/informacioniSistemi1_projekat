/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SharedLibrary;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author igor
 */
@XmlRootElement
public class MestoDTO implements Serializable {
    public String naziv;
}
