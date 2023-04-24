package kr.ac.kopo.ui;

import java.util.Scanner;

public abstract class BaseUI {
	
	protected abstract void execution() throws Exception;

	private Scanner sc;
	
	protected BaseUI() {
		sc = new Scanner(System.in);
	}
	
	protected int scanInt(String msg) {
		System.out.print(msg);
		int num = Integer.parseInt(sc.nextLine());
		return num;
	}
	
	protected long scanLong(String msg) {
		System.out.print(msg);
		Long num = Long.parseLong(sc.nextLine());
		return num;
	}
	
	protected String scanStr(String msg) {
		System.out.print(msg);
		return sc.nextLine();
	}
}