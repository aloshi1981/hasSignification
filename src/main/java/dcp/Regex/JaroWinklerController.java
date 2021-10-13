package dcp.Regex;

import java.io.IOException;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import Regex.Service.JaroWinkler;

/**
 * 
 * @author sihame.outhia <br>
 * La classe JaroWinklerController est un rest controlleur
 *
 */
@RestController
public class JaroWinklerController {
	
	/**
	 * cette requete sert a calculer la distance sans utilisation des threads
	 * @param data est une table de string qui contient les noms a calculer leurs similarites
	 * @return une map qui contient chaque mot avec sa distance
	 * @throws IOException 
	 */
	/*
	 * JaroWinkler j = new JaroWinkler();
	 * 
	 * @GetMapping(value = "/jaro" , consumes = MediaType.APPLICATION_JSON_VALUE,
	 * produces = MediaType.APPLICATION_JSON_VALUE) public Map<String, Double>
	 * jarowinkler(@RequestBody String[] data) throws IOException { Map<String,
	 * Double> rslt = new HashMap<String,Double>(); long startTime =
	 * System.nanoTime(); for(int i = 0; i< data.length; i++) {
	 * System.out.println("***************"); System.out.println(data[i]);
	 * rslt.put(data[i],j.JaroWinklerPlus(data[i]));
	 * 
	 * } System.out.println("Sans Threads :" + (System.nanoTime() - startTime));
	 * return rslt;
	 * 
	 * 
	 * 
	 * }
	 */
	
	
	/**
	 * cette requete sert a calculer la distance avec l'utilisation des threads
	 * @param data est une table de string qui contient les noms a calculer leurs similarites
	 * @return une map qui contient chaque mot avec sa distance
	 * @throws IOException au cas d'une erreur d'entree/sortie
	 */
	@GetMapping(value = "/JarrowinklerPlus" , consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Double> jarowinklerThread(@RequestBody String[] data) throws IOException {
		long startTime = System.nanoTime();
		for(int i = 0; i< data.length; i++) {
			JaroWinkler j = new JaroWinkler(data[i]);
			System.out.println("***************");
			System.out.println(data[i]);
			Thread t = new Thread(j);
			t.start();
			try {
				t.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		System.out.println("En utilisant les Threads :" + (System.nanoTime() - startTime));
		return JaroWinkler.map;

		

	}
	

}
