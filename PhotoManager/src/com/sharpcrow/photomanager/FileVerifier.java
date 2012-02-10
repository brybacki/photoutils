package com.sharpcrow.photomanager;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.codec.digest.DigestUtils;

public class FileVerifier {

	public void verify(String path) throws Exception {
		CountingFileVisitor<Path> fv = buildFileTree(path);

		for (Path p : fv.getFiles()) {
			System.out.println(p);
		}

		System.out.println(fv.getFiles().size() + ", " + fv.getSize());

		for (Path p : fv.getFiles()) {
			String cksum = digest(p);

			System.out.println(p + " " + cksum);
		}
	}

	private String digest(Path p) throws IOException {
		String cksum = null;

		try (InputStream newInputStream =  new FileInputStream(p.toFile()) ) {
			cksum = DigestUtils.md5Hex(newInputStream);
		} catch (FileNotFoundException ex) {
			System.err.println("Missing file " + p);

		}

		return cksum;
	}

	private CountingFileVisitor<Path> buildFileTree(String path)
			throws IOException {
		Path startingDir = Paths.get(path);
		CountingFileVisitor<Path> fv = new CountingFileVisitor<Path>();
		Files.walkFileTree(startingDir, fv);

		return fv;
	}

	//	verifyImage(){
	//		BufferedImage buffImg = ImageIO.read(p.toFile());
	//		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	//		ImageIO.write(buffImg, "png", outputStream);
	//	}

}
