package space.shooter.xx;

/**
 * Ez az oszt�ly felel az id�z�t��rt a j�t�kban.
 * @author Alijev D�vid
 *
 */
public class Timer {
	
	private float timeSeconds;
	private int actual_time;
	private int count;
	private float delta;
	
	/**
	 * Az oszt�ly konstruktora null�z�dik a timer. az igazi id�, �s a sz�mol�s.
	 * @param delta
	 * Ez a delta float �rt�k adja meg a j�t�kban 2 render() f�ggv�ny megh�v�sa k�z�tt eltelt id�t, ezzel lehet m�rni a m�sodperceket a programban.
	 */
	public Timer(float delta) {
		this.timeSeconds = 0f;
		this.actual_time = 0;
		this.count = 0;
		this.delta = delta;
	}
	
	/**
	 * @return
	 * Visszaadja a timeSec �rt�ket, ehhez a float �rt�khez ad�dik hozz� mindegy egyes frameben a delta.
	 */
	public float getTimeSeconds() {
		return this.timeSeconds;
	}
	
	/**
	 * @return
	 * Visszaadja az igaz�b�l eltelt id�t(m�sodpercben).
	 */
	public int getActual_Time() {
		return actual_time;
	}
	
	/**
	 * @return
	 * Visszaadja a count �rt�ket. Ez az �rt�k minden egyes render() megh�v�skor n�vekszik egyel.
	 * Erre az�rt van sz�ks�g mert a render() met�dusok olyan gyorsan h�v�dnak meg, hogy ha valami v�grehajt�dik, az id� alatt nagyon sokszor megt�rt�nik
	 * pedig csak 1x kellene. �gy mindig meg kell vizsg�lni, hogy ez az �rt�k marad�k n�lk�l oszthat�-e 60-al, mert az minden frameben csak 1x t�rt�nik meg.
	 */
	public int getCount() {
		return count;
	}
	
	/**
	 * A timeSecinds v�ltoz�hoz hozz�adja a az el�z� �s a most megh�vott render() met�dusok k�z�tt eltelt id�t.
	 */
	public void timer() {
		timeSeconds += delta; 
	}
	
	/**
	 * Hozz�ad a count �rt�khez egyet.
	 */
	public void count() {
		count++;
	}
	
	/**
	 * Friss�ti a timert, vagyis ha a timeSeconds v�ltoz� t�bb egyn�l, az azt jelenti, hogy eltelt
	 * 1 m�sodperc, teh�t ezt lenull�zza, �s az igaz id�t mutat� v�ltoz�hoz hozz�ad egyet.
	 */
	public void updateTimer() {
		if (this.timeSeconds > 1) {
			actual_time += 1;
			timeSeconds = 0;
		}
	}

}
