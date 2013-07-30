package com.sharpcrow.photomanager.gui;

import javax.swing.*;
import java.awt.*;

public class SynchronizeDirectories extends JPanel {

    public SynchronizeDirectories() {
        super(new BorderLayout());

        JList<String> leftFiles = new JList<>(new String[]{"abc", "def", "ghi"});
        JList<String> rightFiles = new JList<>(new String[]{"aabc", "deaf", "gahi"});
        Dimension minimumSize = new Dimension(100, 50);
        leftFiles.setMinimumSize(minimumSize);
        rightFiles.setMinimumSize(minimumSize);

        JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true,
                new JScrollPane(leftFiles),
                new JScrollPane(rightFiles));
        split.setResizeWeight(0.5);

        add(split, BorderLayout.CENTER);
    }


    public static void main(String[] args) {
        JFrame f = new JFrame("SynchronizeDirs");

        f.getContentPane().add(new SynchronizeDirectories());
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.pack();
        f.setVisible(true);

    }

}
