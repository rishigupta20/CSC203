/*
Helper For Lab 8
*/
import processing.core.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.nio.file.FileAlreadyExistsException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class drawPoints extends PApplet{

	public static String filename;

	public void settings() {
    size(500, 500);
	}
  
	public void setup() {
    	background(180);
    	noLoop();
  	}

  	public void draw() {
		double x, y, z;
		File infile = new File(filename);
		File outfile = new File("drawMe.txt");
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(outfile);
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
		String[] lines = loadStrings(infile);
		println("there are " + lines.length);
		List<Point> l1 = new ArrayList<>();
		for (int i = 0; i < lines.length; i++) {
			if (lines[i].length() > 0) {
				String[] words = lines[i].split(",");
				x = Double.parseDouble(words[0]);
				y = Double.parseDouble(words[1]);
				z = Double.parseDouble(words[2]);
				Point p1 = new Point(x, y, z);
				l1.add(p1);
			}
		}
		List<Point> l2 = l1.stream()
					.filter(p -> p.getZ() <= 2)
					.map(p -> new Point(p.getX() * 0.5, p.getY() * 0.5, p.getZ() * 0.5))
					.map(p -> new Point(p.getX() - 150, p.getY() - 37, p.getZ()))
					.collect(Collectors.toList());

		for (Point p : l2) {
			ellipse((float) p.getX(), (float) p.getY(), 1, 1);
			pw.println(p.getX() + ", " + p.getY() + ", " + p.getZ());
		}
		pw.close();
	}

  	public static void main(String args[]) {
      PApplet.main("drawPoints");
	  filename = args[0];
   }
}
