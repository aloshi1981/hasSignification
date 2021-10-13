package dcp.Regex;

import java.io.IOException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import Regex.Service.DicReader;

/**
 * 
 * @author sihame.outhia <br>
 *loadingDics est un controlleur de chargement des dictionnaires
 */
@RestController
public class LoadingDics {
	
	/**
	 * @see DicReader#LoadingsortedListAlph
	 * @see DicReader#LoadingsortedListLength
	 * @throws IOException au cas ou un dictionnaire n'existe pas
	 */
	@GetMapping(value = "/loading")
	public void filereader() throws IOException {

		DicReader d = new DicReader();
		//System.out.println(DicReader.sortedListAlph);
		d.LoadingsortedListAlph();
		d.LoadingsortedListLength();
		//System.out.println(DicReader.sortedListAlph);

	}

}
