package space.shooter.xx;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Ez a class felel a f�jlkezel�s�rt, itt tal�lhat�k a CRUD met�dusok.
 * @author Alijev D�vid
 *
 */
public class FileHandler {
	
	private String fn;
	Scanner be;
	
	/**
	 * Ez az oszt�ly konstruktora.
	 * @param fn
	 * Ezzel a param�terrel kapja meg az oszt�ly annak a f�jlnak az el�r�si �tj�t, mellyel
	 * a met�dusokat k�v�njuk v�grehajtani.
	 */
	public FileHandler(String fn) {
		this.fn = fn;
	}
	
	
	/**
	 * L�trehoz egy f�jlt, �s ha az nem l�tezik, l�trehoz egyet a megadott n�vvel.
	 * @return
	 * A l�trehozott f�jlt visszaadja
	 */
	public File Create() {
		File f = new File(this.fn);
		try {
			if (f.createNewFile()) {
				return f;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return f;
		
	}
	
	/**
	 * Beolvassa a f�jl tartalm�t.
	 * @return
	 * A beolvasott �rt�ket visszaadja.
	 */
	public int Read() {
		File file = Create();
		try {
			be = new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		int x = 0;
		
		while(be.hasNextLine()) {
			return be.nextInt();
		}
		return x;
	}
	
	/**
	 * �t�rja egy f�jl tartalm�t.
	 * @param x
	 * Erre a megakott param�eterre �rja �t a f�jl tartalm�t.
	 * Ha az I/O m�velet megakad, azt elkapja.
	 */
	public void Update(int x){
		FileWriter fw;
		try {
			fw = new FileWriter(this.fn);
			fw.write(String.valueOf(x));
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * Kit�rli a f�jlt amire megh�vtuk.
	 */
	public void Delete() {
		this.Delete();
	}

}
