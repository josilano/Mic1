/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mic1.arqcomp.ufc;

/**
 *
 * @author LaNo
 */
public class PortaLogica {

    public static int and(int a, int b) {
	return a*b;
    }
	
    public static int or(int a, int b) {
        return a+b-a*b;
    }

    public static int not(int a) {
        return 1-a;
    }

    public static int xOr(int a, int b) {
        return a+b-2*a*b;
    }

    public static int nand(int a, int b) {
        return 1-a*b;
    }

    public static int nor(int a,  int b) {
        return 1-a-b+a*b;
    }
}
