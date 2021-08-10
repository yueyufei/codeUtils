package com.golaxy.img;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class IMG {
	

	    public static void main(String[] args) throws IOException{
	        File file = null;
	        BufferedImage image = null;

	        try {
	            file = new File("Z:\\标\\5f690ff1213d6cc546adb69efbd79667.jpg");
	            image = ImageIO.read(file);

	            int width = image.getWidth();
	            int height = image.getHeight();

	            for (int j = 0; j < height; j++) {
	                int l = 0, r = width - 1;
	                while (l < r) {
	                    int pl = image.getRGB(l, j);
	                    int pr = image.getRGB(r, j);

	                    image.setRGB(l, j, pr);
	                    image.setRGB(r, j, pl);

	                    l++;
	                    r--;
	                }
	            }

	            file = new File("Z:\\标\\5f690ff1213d6cc546adb69efbd79667_mirror.jpg");
	            ImageIO.write(image, "jpg", file);

	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

}
