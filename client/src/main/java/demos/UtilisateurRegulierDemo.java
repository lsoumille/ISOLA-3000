package demos;

import api.IsolaPublicAPI;

/**
 * Created by lucas on 02/05/16.
 */
public class UtilisateurRegulierDemo {

    IsolaPublicAPI allWs;

    public UtilisateurRegulierDemo() {
        this.allWs = new IsolaPublicAPI("localhost","8080");
    }

    public static void main(String[] args) throws Exception {
        UtilisateurRegulierDemo demo = new UtilisateurRegulierDemo();
        System.out.println("Demo d'un utilisateur lambda");
        System.out.println("Creation de l'utilisateur : Jack Jack 30 ans carte de credit valide");
        demo.allWs.userModifier.register("Jack", "Jack", 30, "0000", "Jack06@gmail.com");
        System.out.println("\t ==> Ajout réussi");
        System.out.println("L'utilisateur A achète une carte Cime");
        String idCard = demo.allWs.purchaser.addCardForUser("Jack", "Jack");
        System.out.println("\t ==> Achat réalisé (log dans le serveur .NET)");
        demo.allWs.purchaser.addPassForCard(idCard, "ADULT", "ONE_DAY", "ALL");
        boolean status = demo.allWs.validator.validatePass(idCard, 1);
        if(status) {
            System.out.println("\t ==> Passage accordé");
        }
    }
}
