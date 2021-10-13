package Regex.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.googlecode.concurrenttrees.radix.node.concrete.DefaultCharSequenceNodeFactory;
import com.googlecode.concurrenttrees.solver.LCSubstringSolver;

import info.debatty.java.stringsimilarity.NGram;

/**
 * 
 * @author sihame.outhia <br>
 * JaroWinkler est une classe qui presente des differentes methodes de calcul de la distance de JW <br>
 * presente une nouvelle methode JaroWinklerPlus
 */
public class JaroWinkler implements Runnable {

	/**
	 * s est le mot qu'on souhaite lui calculer la distance JW
	 */
	private String s;
	
	/**
	 * Constructeur de Jarowinkler
	 * @param s mot 
	 */
	public JaroWinkler(String s) {
		super();
		this.s = s;
	}

	/**
	 * Constructeur sans parametres 
	 */
	public JaroWinkler() {
		// TODO Auto-generated constructor stub
	}

	public static Map<String, Double> map = new HashMap<String,Double>(); 

//	private Map<Double, String> JWDistance1(String nom) {
//	JaroWinkler jw = new JaroWinkler();
//	Map<Double, String> vals = new HashMap<Double, String>();
//	String r = "";
//	if (DicReader.sortedListAlph.contains(nom)) {
//		r = "1 :" + nom;
//	} else {
//		for (int i = 0; i < DicReader.sortedListAlph.size(); i++) {
//			vals.put(1-jw.distance(nom, (String) DicReader.sortedListAlph.get(i)),
//					(String) DicReader.sortedListAlph.get(i));
//
//		}
//	}
//	return vals;
//
//}

	/**
	 * 
	 * @return le mot qu'on souhaite lui calculer la distance
	 */
	public String getS() {
		return s;
	}

	/**
	 * Met a jour le mot 
	 * @param s une chaine de caractere
	 */
	public void setS(String s) {
		this.s = s;
	}

	/**
	 * 
	 * @param nom le string a lui calculer la distance de Jarowinkler
	 * @return Map qui contient la distance entre chaque mot du dictionnaire et le param nom
	 */
	private Map<Double, String> JWDistance2(String nom) {
		NGram twogram = new NGram();
		Map<Double, String> vals = new HashMap<Double, String>();
		if (DicReader.sortedListAlph.contains(nom)) {
			vals.put(1.0, nom);
		} else {
			for (int i = 0; i < DicReader.sortedListAlph.size(); i++) {
				vals.put(twogram.distance(nom, (String) DicReader.sortedListAlph.get(i)),
						(String) DicReader.sortedListAlph.get(i));

			}
		}
		return vals;

	}

//private Map<Double, String> JWDistance3(String nom) {
//	JaroWinklerSimilarity similarity = new JaroWinklerSimilarity();
//	Map<Double, String> vals = new HashMap<Double, String>();
//	String r = "";
//	if (DicReader.sortedListAlph.contains(nom)) {
//		r = "1 :" + nom;
//	} else {
//		for (int i = 0; i < DicReader.sortedListAlph.size(); i++) {
//			vals.put(similarity.apply(nom, (String) DicReader.sortedListAlph.get(i)),
//					(String) DicReader.sortedListAlph.get(i));
//
//		}
//	}
//	return vals;
//
//}
//
//private Map<Double, String> JWDistance4(String nom) {
//	Map<Double, String> vals = new HashMap<Double, String>();
//	String r = "";
//	if (DicReader.sortedListAlph.contains(nom)) {
//		r = "1 :" + nom;
//	} else {
//		for (int i = 0; i < DicReader.sortedListAlph.size(); i++) {
//			vals.put(StringUtils.getJaroWinklerDistance(nom, (String) DicReader.sortedListAlph.get(i)),
//					(String) DicReader.sortedListAlph.get(i));
//
//		}
//	}
//	return vals;
//
//}
//
//private double JWinkler1Plus(String s1) {
//	double s = 0;
//
//	for (int i = 0; i < DicReader.sortedListLength.size(); i++) {
//		if (s1.contains(DicReader.sortedListLength.get(i))) {
//			s1 = s1.replace(DicReader.sortedListLength.get(i), "");
//			System.out.println("s1 = " + s1);
//			s = s + DicReader.sortedListLength.get(i).length();
//		}
//	}
//	if (!this.isAlpha(s1) || s1.isEmpty()) {
//		return 1;
//	} else {
//		Map<Double, String> vals = this.JWDistance1(s1);
//		List<Double> l = new ArrayList<Double>(vals.keySet());
//		double max = l.stream().max(Comparator.comparing(String::valueOf)).get();
//		System.out.println(max);
//		String s2 = vals.get(max);
//		System.out.println("s2 = " + s2);
//
//		String LCSubstring = this.LCSubstring(s1, s2);
//		s = (s + LCSubstring.length()) * 2;
//		double sbar =  Math.abs((s1.length() + s2.length()) - s);
//		System.out.println("sbar/s = " + sbar/s);
//		return Math.pow(max, sbar / s);
//	}
//
//}

	/**
	 * 
	 * @param s1
	 * @return un nombre qui presente la distance du jarowinkler du string
	 */
	private double JWinkler2Plus(String s1) {
		double s = 0;

		for (int i = 0; i < DicReader.sortedListLength.size(); i++) {
			if (s1.contains(DicReader.sortedListLength.get(i))) {
				s1 = s1.replace(DicReader.sortedListLength.get(i), "");
				System.out.println("s1 = " + s1);
				s = s + DicReader.sortedListLength.get(i).length();
			}
		}
		/**
		 * s'il ya plus de lettres ou bien si le string est null alors le mot est trouve
		 */
		if (!this.isAlpha(s1) || s1.isEmpty()) {
			return 1;
		} 
		/**
		 * sinon on calcule la distance JW du mot reste
		 *  @see  la methode JWDistance2
		 */
		else {
			Map<Double, String> vals = this.JWDistance2(s1);
			List<Double> l = new ArrayList<Double>(vals.keySet());
			double max = l.stream().max(Comparator.comparing(String::valueOf)).get();
			System.out.println(max);
			String s2 = vals.get(max);
			System.out.println("s2 = " + s2);

			String LCSubstring = this.LCSubstring(s1, s2);
			s = (s + LCSubstring.length()) * 2;
			double sbar = Math.abs((s1.length() + s2.length()) - s);
			System.out.println("sbar/s = " + sbar / s);
			return Math.pow(max, sbar / s);
		}

	}

//private double JWinkler3Plus(String s1) {
//	double s = 0;
//
//	for (int i = 0; i < DicReader.sortedListLength.size(); i++) {
//		if (s1.contains(DicReader.sortedListLength.get(i))) {
//			s1 = s1.replace(DicReader.sortedListLength.get(i), "");
//			System.out.println("s1 = " + s1);
//			s = s + DicReader.sortedListLength.get(i).length();
//		}
//	}
//	if (!this.isAlpha(s1) || s1.isEmpty()) {
//		return 1;
//	} else {
//		Map<Double, String> vals = this.JWDistance3(s1);
//		List<Double> l = new ArrayList<Double>(vals.keySet());
//		double max = l.stream().max(Comparator.comparing(String::valueOf)).get();
//		System.out.println(max);
//		String s2 = vals.get(max);
//		System.out.println("s2 = " + s2);
//
//		String LCSubstring = this.LCSubstring(s1, s2);
//		s = (s + LCSubstring.length()) * 2;
//		double sbar = Math.abs((s1.length() + s2.length()) - s);
//		return Math.pow(max, sbar / s);
//	}
//
//}
//
//private double JWinkler4Plus(String s1) {
//	double s = 0;
//
//	if (DicReader.sortedListLength.contains(s1)) {
//		return 1;
//	} else {
//		for (int i = 0; i < DicReader.sortedListLength.size(); i++) {
//			if (s1.contains(DicReader.sortedListLength.get(i))) {
//				s1 = s1.replace(DicReader.sortedListLength.get(i), "");
//				System.out.println("s1 = " + s1);
//				s = s + DicReader.sortedListLength.get(i).length();
//			}
//		}
//		if (!this.isAlpha(s1) || s1.isEmpty()) {
//			return 1;
//		} else {
//			Map<Double, String> vals = this.JWDistance4(s1);
//			List<Double> l = new ArrayList<Double>(vals.keySet());
//			double max = l.stream().max(Comparator.comparing(String::valueOf)).get();
//			System.out.println(max);
//			String s2 = vals.get(max);
//			System.out.println("s2 = " + s2);
//
//			String LCSubstring = this.LCSubstring(s1, s2);
//			s = (s + LCSubstring.length()) * 2;
//			double sbar = Math.abs((s1.length() + s2.length()) - s);
//			return Math.pow(max, sbar / s);
//		}
//	}
//
//}

	/**
	 * C'est une methode qui retourne le plus long string commun entre 2 string
	 * @param s1 le premier string
	 * @param s2 le deuxieme string
	 * @return le plus long string commun entre les deux
	 */
	private String LCSubstring(String s1, String s2) {
		LCSubstringSolver solver = new LCSubstringSolver(new DefaultCharSequenceNodeFactory());
		solver.add(s1);
		solver.add(s2);
		return solver.getLongestCommonSubstring().toString();
	}

//NewJarowinkler Sans double parcours
	/*
	 * public double NewJWinkler(String s1) { Map<Double, String> vals = new
	 * HashMap<Double, String>(); if(DicReader.sortedListAlph.contains(s1)) { return
	 * 1; }else { for (int i = 0; i < DicReader.sortedListAlph.size(); i++) {
	 * 
	 * double j = StringUtils.getJaroWinklerDistance(s1, (String)
	 * DicReader.sortedListAlph.get(i)); int LCSlength = LCSubstring(s1, (String)
	 * DicReader.sortedListAlph.get(i)).length(); double S = LCSlength * 2; double
	 * Sbar =( s1.length() + ((String) DicReader.sortedListAlph.get(i)).length()) -
	 * S; vals.put(Math.pow(j, Sbar / S), (String) DicReader.sortedListAlph.get(i));
	 * } } List<Double> l = new ArrayList<Double>(vals.keySet()); double max =
	 * l.stream().max(Comparator.comparing(String::valueOf)).get(); return max;
	 * 
	 * }
	 */

	public double JaroWinklerPlus(String s1) {
		return JWinkler2Plus(s1);
	}

	/**
	 * 
	 * @param name le nom a tester s'il contien des alphabets ou non
	 * @return true s'il contient au mois une lettre sinon false
	 */
	private boolean isAlpha(String name) {
		char[] chars = name.toCharArray();

		for (char c : chars) {
			if (Character.isLetter(c)) {
				return true;
			}
		}

		return false;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		double r = this.JaroWinklerPlus(s);
		map.put(s, r);

	}

}
