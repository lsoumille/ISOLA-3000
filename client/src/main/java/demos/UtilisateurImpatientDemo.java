package demos;

import api.IsolaPublicAPI;

/**
 * Created by lucas on 02/05/16.
 */
public class UtilisateurImpatientDemo {

    IsolaPublicAPI allWs;

    public UtilisateurImpatientDemo() {
        this.allWs = new IsolaPublicAPI("localhost","8080");
    }

    public static void main(String[] args) throws Exception {
        UtilisateurImpatientDemo demo = new UtilisateurImpatientDemo();
        System.out.println("Demo d'un utilisateur qui utilise Fidelicime");
        System.out.println("Creation de l'utilisateur : Fast Fast 35 ans carte de credit valide");
        demo.allWs.userModifier.register("Fast", "Fast", 35, "0000", "Fast06@gmail.com");
        System.out.println("\t ==> Ajout réussi");
        System.out.println("L'utilisateur A achète une carte Cime");
        String idCard = demo.allWs.purchaser.addCardForUser("Fast", "Fast");
        System.out.println("\t ==> Achat réalisé (log dans le serveur .NET)");
        demo.allWs.purchaser.subscribeFidelicime(idCard);
        boolean status = demo.allWs.validator.validatePass(idCard, 1);
        if(status) {
            System.out.println("\t ==> Passage accordé");
        }
    }
}
