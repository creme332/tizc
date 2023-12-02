package com.github.creme332.utils;

import java.awt.Image;

import javax.swing.ImageIcon;

public class IconLoader {

    /**
     * Returns a resized icon from the resources folder.
     * 
     * @param path     Must start with / since path is relative to resources folder.
     * @param iconSize size of icon in pixels
     * @return
     */
    public ImageIcon loadIcon(String path, int iconSize) {
        return new ImageIcon(
                new ImageIcon(this.getClass().getResource(path)).getImage().getScaledInstance(iconSize,
                        iconSize,
                        Image.SCALE_DEFAULT));
    }

    /**
     * Returns an icon from the resources folder.
     * 
     * @param path Must start with / since path is relative to resources folder.
     * @return
     */
    public ImageIcon loadIcon(String path) {
        return new ImageIcon(this.getClass().getResource(path));
    }

}
