package phu.quang.le.DotaCrawler;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import phu.quang.le.Util.DBUtility;

public class ImageCrawler {

	public static void main (String[] args) {
		ExecutorService service = Executors.newFixedThreadPool (10);
		Connection connection = DBUtility.getConnection ();
		String sql = "Select name, img_url FROM heroes";
		Statement stmt;
		try {
			stmt = connection.createStatement ();
			ResultSet rs = stmt.executeQuery (sql);
			while(rs.next ()) {
				String name = rs.getString (1);
				String imgURL = rs.getString (2);
				ImageWorker worker = new ImageWorker (name, imgURL);
				service.execute (worker);
			}
		} catch (SQLException e) {
			System.err.println (e);
		}
	}
}
