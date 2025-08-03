/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import SharedLibrary.KorisnikDTO;

/**
 *
 * @author igor
 */
public interface KorisnikDeletedListener {
    public void OnKorisnikDeleted(KorisnikDTO korisnikDTO);
}
