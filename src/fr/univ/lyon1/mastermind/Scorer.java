package fr.univ.lyon1.mastermind;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class Scorer {
	private final Code secret;
	private final static ConcurrentMap<KeyScore,Score> map = new ConcurrentHashMap<KeyScore,Score>();

	public Scorer(Code secret) {
		super();
		this.secret = secret;
	}

	public Score score(Code guess) {
		return score(guess, secret);
	}

	public static Score score(Code guess, Code secret) {
		KeyScore key = new KeyScore(guess,secret);
		if(!map.containsKey(key)){
			int blackCount = exactMatches(guess, secret);
			int whiteCount = matches(guess, secret) - blackCount;
			Score tmp = Score.valueOf(blackCount, whiteCount);
			map.put(new KeyScore(guess,secret),tmp);
			return tmp;
		}else{
			return map.get(key);
		}
	}

	public int getCodeLength() {
		return secret.length();
	}

	private static int exactMatches(Code guess, Code secret) {
		// TODO: à compléter
		int blackHits = 0;

		for (int i = 0; i < secret.asArray().length; i++) {
			if (secret.asArray()[i].compareTo(guess.asArray()[i]) == 0)
				blackHits++;
		}

		return blackHits;
	}

	private static int matches(Code guess, Code secret) {
		int matchesCount = 0;

		List<Integer> removedIndex = new ArrayList<Integer>();
		for (int i = 0; i < secret.length(); i++) {
			for (int j = 0; j < guess.length(); j++) {
				if (guess.asArray()[j] == secret.asArray()[i] && !(removedIndex.contains(i))) {
					matchesCount++;
					removedIndex.add(i);
				}
			}
		}
		return matchesCount;
	}

	private static class KeyScore{
		Code guess;
		Code secret;
		
		public KeyScore(Code guess , Code secret){
			this.guess = guess;
			this.secret = secret;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((guess == null) ? 0 : guess.hashCode());
			result = prime * result + ((secret == null) ? 0 : secret.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			KeyScore other = (KeyScore) obj;
			if (guess == null) {
				if (other.guess != null)
					return false;
			} else if (!guess.equals(other.guess))
				return false;
			if (secret == null) {
				if (other.secret != null)
					return false;
			} else if (!secret.equals(other.secret))
				return false;
			return true;
		}
	}
	
}
