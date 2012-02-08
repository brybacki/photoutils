package com.sharpcrow.photomanager;

import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
		System.out.println("Hellow world. Not so original, huh? ");

		FileVerifier fv = new FileVerifier();
		fv.verify("D:/zdjecia/albumy/");
	}
}
