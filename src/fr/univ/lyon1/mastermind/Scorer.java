package fr.univ.lyon1.mastermind;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class Scorer {
	private final Code secret;
	
	public Scorer(Code secret) {
		super();
		this.secret = secret;
	}
	
	public Score score(Code guess) {
		return score(guess,secret);
	}

	public static Score score(Code guess, Code secret) {
			int blackCount = exactMatches(guess,secret);
			int whiteCount = matches(guess,secret) - blackCount;
			return Score.valueOf(blackCount, whiteCount);
	}

	public int getCodeLength() {
		return secret.length();
	}
	
	private static int exactMatches(Code guess, Code secret) {
		//TODO: à compléter
		
		return whiteHits;
	}

	private static int matches(Code guess, Code secret) {
		//TODO: à compléter
		
		return matchesCount;
	}

}
