/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SharedLibraryPodsistem3;

import java.io.Serializable;
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author igor
 */
@XmlRootElement
public class OcenaDTO implements Serializable {
     
    public Integer idOce;
    public int ocena;
    public Date datumVremeDavanjaOcena;
    public Integer idK;
    public Integer idSni;
}
