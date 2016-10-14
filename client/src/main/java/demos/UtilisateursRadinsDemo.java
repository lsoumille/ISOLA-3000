package demos;

import api.IsolaPublicAPI;

/**
 * Created by lucas on 03/05/16.
 */
public class UtilisateursRadinsDemo {

    IsolaPublicAPI allWs;

    public UtilisateursRadinsDemo() {
        this.allWs = new IsolaPublicAPI("localhost","8080");
    }

    public static void main(String[] args) throws Exception {
        UtilisateursRadinsDemo demo = new UtilisateursRadinsDemo();
        System.out.println("Demo de deux utilisateurs qui achètent une carte pour deux");
        String idCard = demo.allWs.purchaser.getCard("0000");
        demo.allWs.purchaser.addPassForCardWithCredential(idCard, "ADULT", "ONE_DAY", "ALL", "0000");
        boolean status = demo.allWs.validator.validatePass(idCard, 1);
        if(status) {
            System.out.println("\t ==> Passage accordé");
        }
        status = demo.allWs.validator.validatePass(idCard, 1);
        if(! status) {
            System.out.println("\t ==> Passage Non accordé");
        }
    }
}
