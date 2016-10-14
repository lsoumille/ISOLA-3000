package demos;

import api.IsolaPublicAPI;
import stubs.purchaseobjects.AlreadyExistingCard_Exception;
import stubs.purchaseobjects.PaymentException_Exception;
import stubs.purchaseobjects.UnknownUserException_Exception;

/**
 * Created by lucas on 03/05/16.
 */
public class UtilisateurStupideDemo {

    IsolaPublicAPI allWs;

    public UtilisateurStupideDemo() {
        this.allWs = new IsolaPublicAPI("localhost","8080");
    }

    public static void main(String[] args) throws Exception{
        UtilisateurStupideDemo demo = new UtilisateurStupideDemo();
        System.out.println("Utilisateur possède une carte sur liste noire");
        String idCard;
        try {
            idCard = demo.allWs.purchaser.getCard("9999");
        } catch (Exception e) {
            System.out.println("Achat refusé");
        }
        System.out.println("Trouve une carte valide");
        idCard = demo.allWs.purchaser.getCard("0000");
        demo.allWs.purchaser.addPassForCardWithCredential(idCard, "ADULT", "ONE_DAY", "BEGINNER","0000");
        boolean status = demo.allWs.validator.validatePass(idCard, 1);
        if(! status){
            System.out.println("Passage refusé ! Zone interdite!");
        }

    }
}
