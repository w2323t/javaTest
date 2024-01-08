package csp2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/**@author wjr*/
public class Main {
	
	public static List<Role> p1 = new ArrayList<Role>();
	public static List<Role> p2 = new ArrayList<Role>();

	public static List<Role> firstAttack;
	public static List<Role> lastAttack;
 
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		firstAttack = p1;
		lastAttack = p2;
		Role hero1 = new Role(0, 30);
		Role hero2 = new Role(0, 30);

		p1.add(hero1);
		p2.add(hero2);
		int n = Integer.parseInt(scanner.nextLine());
		String[] cmd;
		String temp;
		for (int i = 0; i < n; i++) {
			temp = scanner.nextLine() + " ";
			cmd = temp.split(" ");
			if (cmd.length == 4) {
				action(cmd[0], Integer.parseInt(cmd[1]),
						Integer.parseInt(cmd[2]), Integer.parseInt(cmd[3]));
			} else if (cmd.length == 3) {
				action(cmd[0], Integer.parseInt(cmd[1]),
						Integer.parseInt(cmd[2]), 0);
			} else {
				action(cmd[0], 0, 0, 0);
			}
		}
		
		if (p1.get(0).health <= 0)
			System.out.println(-1);
		else {
			if (p2.get(0).health > 0) {
				System.out.println(0);}
			else {
				System.out.println(1);}
		}

		result(p1);
		result(p2);
		scanner.close();
	}
 

	public static void action(String operate, int a, int b, int c) {
		if (equals(operate)) {
			firstAttack.add(a, new Role(b, c));}
		else if (equals(operate)) {

			firstAttack.get(a).health -= lastAttack.get(b).attack;
			lastAttack.get(b).health -= firstAttack.get(a).attack;
			dieOfRetinue();
		} else {
			
			List<Role> t;
			t = firstAttack;
			firstAttack = lastAttack;
			lastAttack = t;
		}
 
	}

	public static void dieOfRetinue() {
		for (int i = 1; i < p1.size(); i++) {
			if (p1.get(i).health <= 0) {
				p1.remove(i);}
		}
		for (int j = 1; j < p2.size(); j++) {
			if (p2.get(j).health <= 0) {
				p2.remove(j);}
		}
	}
 

	public static void result(List<Role> lL) {
		System.out.println(lL.get(0).health);
		System.out.print(lL.size() - 1 + " ");
		if (lL.size() > 1) {
			for (int i = 1; i < lL.size(); i++) {
				System.out.print(lL.get(i).health + " ");}
			System.out.println();
		} else {
			System.out.println();
		}
	}
 
}
 

class Role {
	public int health;
	public int attack;
 
	public Role(int a, int h) {
		health = h;
		attack = a;
	}
}
