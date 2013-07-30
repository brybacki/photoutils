package com.sharpcrow.photomanager.filesync;

import org.apache.commons.codec.digest.DigestUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.TimeUnit;

public class FileVerifier {

    public SortedSet<FileChecksumPair> verify(String path) throws Exception {

        long startTime = System.nanoTime();
        CountingFileVisitor<Path> fv = buildFileTree(path);

        long fileNamesTime = System.nanoTime() - startTime;
        for (Path p : fv.getFiles()) {
            System.out.println(p);
        }

        SortedSet<FileChecksumPair> hashedFiles = new TreeSet<>(FileChecksumPair.FILE_ONLY_COMPARATOR);
        System.out.println(fv.getFiles().size() + ", " + fv.getSize() + " in " + TimeUnit.NANOSECONDS.toMillis(fileNamesTime) + "ms");

        for (Path p : fv.getFiles()) {
            String cksum = digest(p);

            System.out.println(p + " " + cksum);
            hashedFiles.add(new FileChecksumPair(p.toString().substring(p.toString().indexOf(":")), cksum));
        }

        long cksumTime = (System.nanoTime() - startTime) - fileNamesTime;
        System.out.println("Checksums computed in " + TimeUnit.NANOSECONDS.toMillis(cksumTime) + "ms");

        return hashedFiles;
    }

    private String digest(Path p) throws IOException {
        String cksum;

        try (InputStream newInputStream = new FileInputStream(p.toFile())) {
            cksum = DigestUtils.md5Hex(newInputStream);
        } catch (FileNotFoundException ex) {
            System.err.println("Missing file " + p);
            throw ex;
        }

        return cksum;
    }

    private CountingFileVisitor<Path> buildFileTree(String path)
            throws IOException {
        Path startingDir = Paths.get(path);
        CountingFileVisitor<Path> fv = new CountingFileVisitor<>();
        Files.walkFileTree(startingDir, fv);

        return fv;
    }

    //	verifyImage(){
    //		BufferedImage buffImg = ImageIO.read(p.toFile());
    //		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    //		ImageIO.write(buffImg, "png", outputStream);
    //	}

}
