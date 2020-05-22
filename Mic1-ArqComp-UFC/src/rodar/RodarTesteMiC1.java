/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rodar;

import mic1.arqcomp.ufc.Mic1ArqCompUFC;

/**
 *
 * @author LaNo
 */
public class RodarTesteMiC1 {
    
    /**
     * @param args the command line arguments
     * Para rodar estes testes, descomente o metodo principal
     */
    /*public static void main(String[] args) {
        System.out.println("mic1");
        int[] tes = new int[2];
        tes[0] = 1;
        tes[1] = 0;
        Mic1ArqCompUFC mic = new Mic1ArqCompUFC();
        //mic.deslocador(1, 1, tes);//para rodar mude o metodo para public
        //System.out.println(PortaLogica.and(1, 1));
        /*int[][] ula = mic.alu(0, tes, 1, tes, 1, 0, 0, 0);//para rodar mude o metodo para public
        for (int i = 0; i < ula.length; i++){
            for (int j = 0; j < ula[i].length; j++){
                System.out.print(i);
                System.out.print(" : ");
                System.out.println(ula[i][j]);
            }
        }
        //teste de acesso ao armazenamento de controle
        String[] armaz = mic.getArmazDeControle();
        System.out.println( armaz[0] );
        int recebe = Integer.parseInt(armaz[0].split("")[0]);
        System.out.println( Integer.parseInt(armaz[1].split("")[6]) );
        System.out.println(recebe);
        System.out.println(armaz[2]);
        //teste para setar a RAM inicial
        int TAMRAM = 48;
        int contador = 0;
        mic.ram = new int[TAMRAM];
        //0000 0001; 0000 0001; 0000 0011; 0000 0001; 0000 0100; 0000 0110;
        String memram = "000000010000000100000011000000010000010000000110";
        //String[] mem = memram.split("");
        for (String i: memram.split("")){
            mic.ram[contador] = Integer.parseInt(i);
            contador++;
        }
        
        mic.cicloDeClock();
        mic.printRegistros();
        mic.cicloDeClock();
        mic.printRegistros();
        mic.cicloDeClock();
        mic.printRegistros();
        mic.cicloDeClock();
        mic.printRegistros();
        mic.cicloDeClock();
        mic.printRegistros();
        mic.cicloDeClock();
        mic.printRegistros();
    }*/
}
