package phu.quang.le.Util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import phu.quang.le.Dao.Hero;

public class DBUtility {

	public static Connection getConnection () {

		Connection connection = null;

		try {
			Properties prop = new Properties ();
			InputStream inputStream = DBUtility.class.getClassLoader ()
					.getResourceAsStream ("config.properties");
			prop.load (inputStream);
			String driver = prop.getProperty ("driver");
			String url = prop.getProperty ("url");
			String user = prop.getProperty ("user");
			String password = prop.getProperty ("password");
			Class.forName (driver);
			connection = DriverManager.getConnection (url, user, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace ();
		} catch (SQLException e) {
			e.printStackTrace ();
		} catch (FileNotFoundException e) {
			e.printStackTrace ();
		} catch (IOException e) {
			e.printStackTrace ();
		}

		return connection;
	}

	/**
	 * Close connection to database
	 * 
	 * @param c
	 * @throws SQLException
	 */
	public static void closeConnection (Connection c) throws SQLException {

		c.close ();
	}

	public static boolean addHero (Hero hero) {

		System.out.println ("Add " + hero.getName () + " into database!");
		Connection connection = DBUtility.getConnection ();
		String sql = "INSERT INTO heroes VALUES (default, ?, ?, ?, ?, ?, ?, ?, ?, "
				+ "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			PreparedStatement pst = connection.prepareStatement (sql);
			pst.setString (1, hero.getName ());
			pst.setString (2, hero.getImgUrl ());
			pst.setDouble (3, hero.getHeroStat ().getBeginStrength ());
			pst.setDouble (4, hero.getHeroStat ().getBeginAgility ());
			pst.setDouble (5, hero.getHeroStat ().getBeginIntel ());
			pst.setDouble (6, hero.getHeroStat ().getStrPerLevel ());
			pst.setDouble (7, hero.getHeroStat ().getAgiPerLevel ());
			pst.setDouble (8, hero.getHeroStat ().getIntPerLevel ());
			pst.setInt (9, hero.getHeroStat ().getMoveSpeed ());
			pst.setDouble (10, hero.getHeroStat ().getTurnRate ());
			pst.setInt (11, hero.getHeroStat ().getDaySightRange ());
			pst.setInt (12, hero.getHeroStat ().getNightSightRange ());
			pst.setString (13, hero.getHeroStat ().getAttackRange ());
			pst.setString (14, hero.getHeroStat ().getMissileSpeed ());
			pst.setDouble (15, hero.getHeroStat ().getCastPoint ());
			pst.setDouble (16, hero.getHeroStat ().getCastBackswing ());
			pst.setDouble (17, hero.getHeroStat ().getAttackPoint ());
			pst.setDouble (18, hero.getHeroStat ().getAttackBackswing ());
			pst.setDouble (19, hero.getHeroStat ().getBaseAttackTime ());

			pst.executeUpdate ();
		} catch (SQLException e) {
			System.err.println (e);
		}

		return true;
	}

	public static void main (String[] args) {

		Connection conn = getConnection ();
		if (conn != null) {
			System.out.println ("ok");
		} else {
			System.out.println ("not ok");
		}
	}
}
