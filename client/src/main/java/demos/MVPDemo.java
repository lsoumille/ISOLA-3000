package demos;

import api.IsolaPublicAPI;
import stubs.purchaseobjects.AbstractPass;

/**
 * Created by lucas on 28/03/16.
 */
public class MVPDemo {

    IsolaPublicAPI allWs;

    public MVPDemo() {
        this.allWs = new IsolaPublicAPI("localhost","8080");
    }

    public static void main(String[] args) {
        String idCardLucas = "";
        MVPDemo demo = new MVPDemo();
        System.out.println("Début de la simulation");
        System.out.println("Création de l'utilisateur A : Lucas S 21 ans avec une carte de crédit valide");
        try {
            demo.allWs.userModifier.register("Lucas","S",21,"0000", "lucassoumille@yahoo.fr");
            System.out.println("\t ==> Ajout réussi");
            System.out.println("On essaye de l'ajouter une deuxième fois");
            demo.allWs.userModifier.register("Lucas","S",21,"0000", "lucassoumille@yahoo.fr");
        } catch (Exception e) {
            System.out.println("\t ==> Ajout d'un utilisateur existant détecté");
        }
        System.out.println("Création de l'utilisateur B : Nico H 18 ans avec une carte de crédit invalide");
        try {
            demo.allWs.userModifier.register("Nico","H",18,"1234","mochedu04@gmail.com");
            System.out.println("\t ==> Ajout réussi");
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("L'utilisateur A achète une carte Cime");
        try {
            idCardLucas = demo.allWs.purchaser.addCardForUser("Lucas","S");
            System.out.println("\t ==> Achat réalisé (log dans le serveur .NET)");
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("L'utilisateur B essaye d'acheter une carte Cime");
        try {
            demo.allWs.purchaser.addCardForUser("Nico","H");
        } catch (Exception e) {
            System.out.println("\t ==> Achat refusé");
        }
        System.out.println("L'utilisateur A essaye de passer un portique (sans forfait)");
        boolean status = demo.allWs.validator.validatePass(idCardLucas, 1);
        if(! status) {
            System.out.println("\t ==> Passage refusé");
        }
        System.out.println("Affichage du catalogue de forfait");
        for(AbstractPass pass : demo.allWs.purchaser.listAllPass())
            System.out.println("Pass : " + pass.getType() + " / " + pass.getZone() + " / " + pass.getAge() );
        System.out.println("Achat d'un forfait");
        try {
            demo.allWs.purchaser.addPassForCard(idCardLucas,"STUDENT" , "ONE_DAY","ALL");
            System.out.println("\t ==> Forfait acheté");
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Nombre de transactions de l'utilisateur A :");
        System.out.println("\t ==> " + demo.allWs.userModifier.getHistory("Lucas", "S").size());
        System.out.println("L'utilisateur A essaye de passer un portique avec forfait");
        status = demo.allWs.validator.validatePass(idCardLucas, 1);
        if(status) {
            System.out.println("\t ==> Passage accordé");
        }
        status = demo.allWs.validator.validatePass(idCardLucas, 1);
        if(status) {
            System.out.println("\t ==> Passage accordé");
        }
    }
}
