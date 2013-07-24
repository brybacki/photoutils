package com.sharpcrow.photomanager;

import java.util.Comparator;

public class FileChecksumPair {

    public static Comparator<FileChecksumPair> FILE_ONLY_COMPARATOR = new Comparator<FileChecksumPair>() {
        @Override
        public int compare(FileChecksumPair o1, FileChecksumPair o2) {
            int name = o1.getFileName().compareToIgnoreCase(o2.getFileName());
            if (name != 0) {
                return name;
            } else {
                return o1.getChecksum().compareToIgnoreCase(o2.getChecksum());
            }
        }
    };

    private String fileName;
    private String checksum;

    public FileChecksumPair(String fileName, String checksum) {
        this.fileName = fileName;
        this.checksum = checksum;
    }

    public String getFileName() {
        return fileName;
    }

    public String getChecksum() {
        return checksum;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof FileChecksumPair)) {
            return false;
        }
        FileChecksumPair other = (FileChecksumPair) obj;
        return fileName.equals(other.fileName) && checksum.equals(other.checksum);
    }

    @Override
    public int hashCode() {
        int result = fileName.hashCode();
        result = 31 * result + checksum.hashCode();
        return result;
    }
}
