package space.shooter.xx;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Ez a class felel a fájlkezelésért, itt találhatók a CRUD metódusok.
 * @author Alijev Dávid
 *
 */
public class FileHandler {
	
	private String fn;
	Scanner be;
	
	/**
	 * Ez az osztály konstruktora.
	 * @param fn
	 * Ezzel a paraméterrel kapja meg az osztály annak a fájlnak az elérési útját, mellyel
	 * a metódusokat kívánjuk végrehajtani.
	 */
	public FileHandler(String fn) {
		this.fn = fn;
	}
	
	
	/**
	 * Létrehoz egy fájlt, és ha az nem létezik, létrehoz egyet a megadott névvel.
	 * @return
	 * A létrehozott fájlt visszaadja
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
	 * Beolvassa a fájl tartalmát.
	 * @return
	 * A beolvasott értéket visszaadja.
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
	 * Átírja egy fájl tartalmát.
	 * @param x
	 * Erre a megakott paraméeterre írja át a fájl tartalmát.
	 * Ha az I/O mûvelet megakad, azt elkapja.
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
	 * Kitörli a fájlt amire meghívtuk.
	 */
	public void Delete() {
		this.Delete();
	}

}
