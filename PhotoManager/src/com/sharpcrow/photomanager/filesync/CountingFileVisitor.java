package com.sharpcrow.photomanager.filesync;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Collection;
import java.util.LinkedList;

import static java.nio.file.FileVisitResult.CONTINUE;

public class CountingFileVisitor<Path> extends SimpleFileVisitor<Path> {
    private Collection<Path> files = new LinkedList<>();
    private long size = 0;

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attr) {
        if (attr.isRegularFile()) {
            files.add(file);
        }
        size += attr.size();
        return CONTINUE;
    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) {
        System.err.println(exc);
        return CONTINUE;
    }

    public Collection<Path> getFiles() {
        return files;
    }

    public long getSize() {
        return size;
    }
}
