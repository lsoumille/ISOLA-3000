package demos;

import api.IsolaPublicAPI;

/**
 * Created by lucas on 02/05/16.
 */
public class UtilisateurUniqueDemo {

    IsolaPublicAPI allWs;

    public UtilisateurUniqueDemo() {
        this.allWs = new IsolaPublicAPI("localhost","8080");
    }

    public static void main(String[] args) throws Exception {
        UtilisateurUniqueDemo demo = new UtilisateurUniqueDemo();
        System.out.println("Demo d'un utilisateur qui vient qu'une fois sans compte");
        String idCard = demo.allWs.purchaser.getCard("0000");
        System.out.println("Achat réussi");
        demo.allWs.purchaser.addPassForCardWithCredential(idCard, "ADULT", "ONE_DAY", "ALL", "0000");
        boolean status = demo.allWs.validator.validatePass(idCard, 1);
        if(status) {
            System.out.println("\t ==> Passage accordé");
        }
    }

}
