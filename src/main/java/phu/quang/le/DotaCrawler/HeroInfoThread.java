package phu.quang.le.DotaCrawler;

import java.util.ArrayList;
import java.util.List;

import phu.quang.le.Dao.Hero;
import phu.quang.le.Dao.HeroStat;
import phu.quang.le.Dao.LevelStat;
import phu.quang.le.Dao.SummonStat;

public class HeroInfoThread implements Runnable {
	public String url;
	public Hero hero = new Hero ();
	public HeroStat heroStat = new HeroStat ();
	public SummonStat summonStat = new SummonStat ();
	public List<LevelStat> levelStats = new ArrayList<> (); 
	
	public HeroInfoThread (String url) {
		this.url = url;
	}

	@Override
	public void run () {

	}
}
