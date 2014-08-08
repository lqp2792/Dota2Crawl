package phu.quang.le.DotaCrawler;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class ImageWorker implements Runnable {

	public String imgURL;
	public String name;

	public ImageWorker (String name, String imgURL) {
		this.name = name;
		this.imgURL = imgURL;
	}

	@Override
	public void run () {
		String filename = "img/" + name + ".png";
		try {
			URL connection = new URL (imgURL);
			InputStream input = connection.openStream ();
			BufferedOutputStream output = new BufferedOutputStream (new FileOutputStream (
					filename));
			int b;
			while ((b = input.read ()) != -1) {
				output.write (b);
			}
			output.close ();
			input.close ();
		} catch (IOException e) {
			System.err.println (e);
		}
	}
}
