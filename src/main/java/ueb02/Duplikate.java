package ueb02;

class Duplikate {
	/**
	 * Gibt ein StringSet mit den Wörtern zurück, welche mindestens zwei mal im Text vorkommen.
	 * Alle Satzzeichen im Text sollen ignoriert werden.
	 * @param text Eingabetext, kann Satzzeichen enthalten welche ignoriert werden.
	 * @return StringSet mit den Wörtern, welche mind. zwei mal vorkommen.
	 */
	static StringSet findeDuplikate(String text) {
		StringSet stringSet = new StringSetImpl();
		StringSet stringSetDuplikate = new StringSetImpl();

		for (String str : text.split(" ")){
			StringBuilder sb = new StringBuilder();
			for(char c : str.toCharArray()){
				if (Character.isAlphabetic(c))
					sb.append(c);
			}
			String pure = sb.toString();
			if (!stringSet.add(pure))
				stringSetDuplikate.add(pure);
		}

		return stringSetDuplikate;
	}
}
