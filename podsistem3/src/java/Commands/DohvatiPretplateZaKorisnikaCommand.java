/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

import Database.DatabaseHandler;
import SharedLibrary.Return;
import SharedLibraryPodsistem3.PretplataDTO;
import entities.Pretplata;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author root
 */
public class DohvatiPretplateZaKorisnikaCommand extends Command{

    @Override
    public Return obradiKomandu(Object receivedObject) {
        PretplataDTO pretplataDTO = (PretplataDTO) receivedObject;
        List<Pretplata> pretplate = new DatabaseHandler().dohvatiPretplateZaKorisnika(pretplataDTO.korisnik.idK);
        List<PretplataDTO> pretplateDTO = new ArrayList<>();
        for(Pretplata p: pretplate)
            pretplateDTO.add(p.convertToDTO());
        Return r = new Return("dohvatiSvePretplateZaKorisnika",pretplateDTO);
        return r;
    }
    
}
