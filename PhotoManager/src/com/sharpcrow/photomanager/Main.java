package com.sharpcrow.photomanager;


public class Main {

	public static void main(String[] args) throws Exception {
		System.out.println("Hellow world. Not so original, huh? ");

		FileVerifier fv = new FileVerifier();
		fv.verify("d:/Zdjecia/albumy/2008/awaria2008.09-zabawaheliosem/");
	}
}
