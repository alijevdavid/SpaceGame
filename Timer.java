package space.shooter.xx;

/**
 * Ez az osztály felel az idõzítõért a játékban.
 * @author Alijev Dávid
 *
 */
public class Timer {
	
	private float timeSeconds;
	private int actual_time;
	private int count;
	private float delta;
	
	/**
	 * Az osztály konstruktora nullázódik a timer. az igazi idõ, és a számolás.
	 * @param delta
	 * Ez a delta float érték adja meg a játékban 2 render() függvény meghívása között eltelt idõt, ezzel lehet mérni a másodperceket a programban.
	 */
	public Timer(float delta) {
		this.timeSeconds = 0f;
		this.actual_time = 0;
		this.count = 0;
		this.delta = delta;
	}
	
	/**
	 * @return
	 * Visszaadja a timeSec értéket, ehhez a float értékhez adódik hozzá mindegy egyes frameben a delta.
	 */
	public float getTimeSeconds() {
		return this.timeSeconds;
	}
	
	/**
	 * @return
	 * Visszaadja az igazából eltelt idõt(másodpercben).
	 */
	public int getActual_Time() {
		return actual_time;
	}
	
	/**
	 * @return
	 * Visszaadja a count értéket. Ez az érték minden egyes render() meghíváskor növekszik egyel.
	 * Erre azért van szükség mert a render() metódusok olyan gyorsan hívódnak meg, hogy ha valami végrehajtódik, az idõ alatt nagyon sokszor megtörténik
	 * pedig csak 1x kellene. Így mindig meg kell vizsgálni, hogy ez az érték maradék nélkül osztható-e 60-al, mert az minden frameben csak 1x történik meg.
	 */
	public int getCount() {
		return count;
	}
	
	/**
	 * A timeSecinds változóhoz hozzáadja a az elõzõ ás a most meghívott render() metódusok között eltelt idõt.
	 */
	public void timer() {
		timeSeconds += delta; 
	}
	
	/**
	 * Hozzáad a count értékhez egyet.
	 */
	public void count() {
		count++;
	}
	
	/**
	 * Frissíti a timert, vagyis ha a timeSeconds változó több egynél, az azt jelenti, hogy eltelt
	 * 1 másodperc, tehát ezt lenullázza, és az igaz idõt mutató változóhoz hozzáad egyet.
	 */
	public void updateTimer() {
		if (this.timeSeconds > 1) {
			actual_time += 1;
			timeSeconds = 0;
		}
	}

}
