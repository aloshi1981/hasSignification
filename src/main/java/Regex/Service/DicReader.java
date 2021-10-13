package Regex.Service;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

/**
 * 
 * @author sihame.outhia <br>
 * DicReader est la classe qui charge les mots du dictionnaire
 * 
 */
public class DicReader {

	/**
	 * sortedListAlph est une variable globale qui contient les mots classes par
	 * ordre alphabétique
	 */
	public static List<Object> sortedListAlph;

	/**
	 * sortedListLength est une variable globale qui contient les mots classes du plus long au moins long
	 */
	public static List<String> sortedListLength;

	Properties prop = new Properties();
	ClassLoader loader = Thread.currentThread().getContextClassLoader();
	InputStream stream = loader.getResourceAsStream("application.properties");

	/**
	 * 
	 * @return la variable sortedListAlph bien remplie
	 * @see sortedListAlph
	 * @throws IOException si jamais le dictionnaire n'existe pas
	 */
	public List<Object> LoadingsortedListAlph() throws IOException {
		prop.load(stream);
		List<String> list = new ArrayList<String>();
		/**
		 * strMain pour lire la valeur de dic.name qui contient les noms des
		 * dictionnaires a charger dans une seule ligne depuis le fichier des proprietes
		 */
		String strMain = prop.getProperty("dic.name");
		/**
		 * filename est une table de string qui contient les noms des dictionnaires
		 * apres la separation par ","
		 */
		String[] filename = strMain.split(",");
		for (int i = 0; i < filename.length; i++) {

			InputStream in = getClass().getClassLoader().getResourceAsStream("Dictionnaires/" + filename[i]);
			try {
				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
				String ligne = null;
				while ((ligne = bufferedReader.readLine()) != null) {
					if (ligne.length() > 4) {
						list.add(ligne.replaceAll("\"", ""));
					}
				}
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}

		return sortedListAlph = list.stream().sorted().collect(Collectors.toList());

	}
	/**
	 * 
	 * @return la variable sortedListLength remplie
	 * @see sortedListLength
	 */
	public List<String> LoadingsortedListLength() {

		String[] itemsArray = new String[sortedListAlph.size()];
		itemsArray = sortedListAlph.toArray(itemsArray);
		Arrays.sort(itemsArray, (str1, str2) -> str2.length() - str1.length());
		return sortedListLength = Arrays.asList(itemsArray);

	}

}
