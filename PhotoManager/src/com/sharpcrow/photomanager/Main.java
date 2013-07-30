package com.sharpcrow.photomanager;


import com.sharpcrow.photomanager.filesync.FileChecksumPair;
import com.sharpcrow.photomanager.filesync.FileVerifier;

import java.util.Set;
import java.util.TreeSet;

public class Main {

    public static void main(String[] args) throws Exception {
        System.out.println("Hellow world. Not so original, huh? ");

        FileVerifier fv = new FileVerifier();
        Set<FileChecksumPair> lFiles = fv.verify("d:\\Zdjecia\\albumy\\2013\\2013-07-06 Wakacje Warszawa\\");
        Set<FileChecksumPair> rFiles = fv.verify("f:\\Zdjecia\\albumy\\2013\\2013-07-06 Wakacje Warszawa\\");

        Set<FileChecksumPair> lOnly = new TreeSet<>(FileChecksumPair.FILE_ONLY_COMPARATOR);
        lOnly.addAll(lFiles);
        lOnly.removeAll(rFiles);

        Set<FileChecksumPair> rOnly = new TreeSet<>(FileChecksumPair.FILE_ONLY_COMPARATOR);
        rOnly.addAll(rFiles);
        rOnly.removeAll(lFiles);

        System.out.println(lOnly);
        System.out.println(rOnly);
    }
}
