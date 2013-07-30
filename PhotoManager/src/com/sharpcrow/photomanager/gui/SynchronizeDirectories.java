package com.sharpcrow.photomanager.gui;

import com.sharpcrow.photomanager.filesync.FileChecksumPair;
import com.sharpcrow.photomanager.filesync.FileVerifier;

import javax.swing.*;
import java.awt.*;
import java.util.Set;
import java.util.TreeSet;

public class SynchronizeDirectories extends JPanel {

    private final JList<String> leftFiles;
    private final JList<String> rightFiles;

    public SynchronizeDirectories() {
        super(new BorderLayout());

        leftFiles = new JList<>(new String[]{"abc", "def", "ghi"});
        rightFiles = new JList<>(new String[]{"aabc", "deaf", "gahi"});
        Dimension minimumSize = new Dimension(100, 50);
        leftFiles.setMinimumSize(minimumSize);
        rightFiles.setMinimumSize(minimumSize);

        JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true,
                new JScrollPane(leftFiles),
                new JScrollPane(rightFiles));
        split.setResizeWeight(0.5);

        add(split, BorderLayout.CENTER);
    }

    public void run() throws Exception {
        FileVerifier fv = new FileVerifier();
        Set<FileChecksumPair> lFiles = fv.verify("d:\\Zdjecia\\albumy\\2013\\2013-07-06 Wakacje Warszawa\\");
        Set<FileChecksumPair> rFiles = fv.verify("d:\\Zdjecia\\albumy\\2013\\2013-07-01 Wakacje Karwia\\");

        Set<FileChecksumPair> lOnly = new TreeSet<>(FileChecksumPair.FILE_ONLY_COMPARATOR);
        lOnly.addAll(lFiles);
        lOnly.removeAll(rFiles);

        Set<FileChecksumPair> rOnly = new TreeSet<>(FileChecksumPair.FILE_ONLY_COMPARATOR);
        rOnly.addAll(rFiles);
        rOnly.removeAll(lFiles);

//        leftFiles.setListData( lOnly.toArray() );
//        rightFiles.setListData(rOnly.toArray() );
    }


    public static void main(String[] args) throws Exception {
        JFrame f = new JFrame("SynchronizeDirs");
        SynchronizeDirectories sync = new SynchronizeDirectories();
        sync.run();

        f.getContentPane().add(sync);

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.pack();
        f.setVisible(true);

    }

}
