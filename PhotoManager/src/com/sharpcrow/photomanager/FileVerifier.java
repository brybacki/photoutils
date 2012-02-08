package com.sharpcrow.photomanager;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileVerifier {

	public void verify(String path) throws IOException {
		Path startingDir = Paths.get(path /*"D:/zdjecia/albumy"*/);
		CountingFileVisitor<Path> fv = new CountingFileVisitor<Path>();
		Files.walkFileTree(startingDir, fv);

		for (Path p : fv.getFiles()) {
			System.out.println(p);
		}
		System.out.println(fv.getFiles().size() + ", " + fv.getSize());
	}

}
