package Letsdo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class letsStart {
	static int qq =0;
	static String resa="";
	static int max=0;
	static ArrayList<Integer> indices;
	static int len = 0;
	static boolean[] primes1;
	static boolean solved = false;
	static String expression = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	static int noOfBits;
	static ArrayList minTerms = new ArrayList();
	static ArrayList dontCares = new ArrayList();
	static ArrayList minAndDont = new ArrayList();
	static ArrayList[] groups;
	static ArrayList<ArrayList<Object>> newGroups = new ArrayList<ArrayList<Object>>();
	// static ArrayList<ArrayList<ArrayList<Object>>> all= new
	// ArrayList<ArrayList<ArrayList<Object>>>();
	static int[][] checked1;
	static ArrayList<String> step1Res = new ArrayList<String>();
	ArrayList<ArrayList<Integer>> numberGroups = new ArrayList<ArrayList<Integer>>();
	static ArrayList<String> start = new ArrayList<String>();
	static ArrayList<String> result = new ArrayList<String>();
	static ArrayList<String> checked = new ArrayList<String>();
	static ArrayList<Boolean> checkPrime = new ArrayList<Boolean>();
	static ArrayList<Boolean> checkMin = new ArrayList<Boolean>();
	static String[] unchecked2;
	static String[][] coverage;
	static ArrayList<String> terms = new ArrayList<String>();
	static ArrayList<String> finalTerms = new ArrayList<String>();

	public static float log(int x, int base) {
		return (float) (Math.log(x) / Math.log(base));
	}

	// this method merge the min terms and dontCares in one List
	// sorted(minAndDont)ArrayList..
	public static void mergeMinAndDont() {
		Collections.sort(minTerms);
		Collections.sort(dontCares);
		ArrayList res = new ArrayList();
		int countB = 0;
		for (int i = 0; i < minTerms.size(); i++) {
			if (countB < dontCares.size()) {
				if ((int) minTerms.get(i) == (int) dontCares.get(countB))
					countB++;
				else {
					while ((int) minTerms.get(i) > (int) dontCares.get(countB)) {
						minAndDont.add((int) dontCares.get(countB));
						countB++;
						if (countB == dontCares.size())
							break;
					}
				}
			}

			minAndDont.add((int) minTerms.get(i));
		}
		while (countB < dontCares.size()) {
			minAndDont.add((int) dontCares.get(countB));
			countB++;
		}
		for (int i = 0; i < minAndDont.size(); i++)
			System.out.print((int) minAndDont.get(i) + " ");
		// Initializing the size of Array of array List = to the size of maxOnes
		// in the terms...
		groups = new ArrayList[maxOnes() + 1];
		checked1 = new int[minAndDont.size()][2];
		for (int c = 0; c < maxOnes() + 1; c++) {
			groups[c] = new ArrayList();
		}

		// filling the groups....
		for (int i = 0; i < minAndDont.size(); i++) {
			groups[countOnes((int) minAndDont.get(i))].add((int) minAndDont.get(i));
			checked1[i][0] = (int) minAndDont.get(i);
			checked1[i][1] = 0;
		}
		for (int i = 0; i < groups.length; i++) {
			newGroups.add(groups[i]);
		}
		// all.add(newGroups);
	}

	public static int countOnes(int num) {
		int ones = 0;
		String num1 = "";
		num1 += num;
		int first = Integer.parseInt(num1, 10);
		String firstInBinary = Integer.toBinaryString(first);
		for (int i = 0; i < firstInBinary.length(); i++) {
			if (firstInBinary.charAt(i) == '1') {
				ones++;
			}
		}
		return ones;
	}

	public static String compare(int num1, int num2, int bits) {

		String numm1 = "";
		numm1 += num1;
		String numm2 = "";
		numm2 += num2;
		int first = Integer.parseInt(numm1, 10);
		String firstInBinary = Integer.toBinaryString(first);
		int second = Integer.parseInt(numm2, 10);
		String secondInBinary = Integer.toBinaryString(second);
		if (secondInBinary.length() < bits) {
			while (secondInBinary.length() != bits) {
				secondInBinary = "0" + secondInBinary;
			}
		}
		if (firstInBinary.length() < bits) {
			while (firstInBinary.length() != bits) {
				firstInBinary = "0" + firstInBinary;
			}
		}
		String result = "";
		for (int i = 0; i < bits; i++) {
			if (firstInBinary.charAt(i) != secondInBinary.charAt(i)) {
				result += "-";
			} else {
				result += firstInBinary.charAt(i);
			}
		}
		return result;
	}

	public static void step0() {
		System.out.println();
		for (int i = 0; i < groups.length; i++) {
			for (int j = 0; j < groups[i].size(); j++) {
				System.out.println((int) groups[i].get(j) + " " + convertBinary((int) groups[i].get(j), noOfBits));
			}
			System.out.println("_______");
		}
	}

	public static void step1() {
		for (int i = 0; i < groups.length; i++) {
			for (int j = 0; j < groups[i].size(); j++) {
				if (i + 1 == groups.length) {
					break;
				} else {
					for (int k = 0; k < groups[i + 1].size(); k++) {
						int c2 = (int) groups[i + 1].get(k);
						int c1 = (int) groups[i].get(j);
						int diff = (int) groups[i + 1].get(k) - (int) groups[i].get(j);
						if (diff > 0) {
							if (Math.abs(log(diff, 2) - (int) log(diff, 2)) < 0.00001) {
								boolean f1 = false;
								for (int z = 0; z < checked1.length; z++) {
									for (int zz = 0; zz < checked1.length; zz++) {
										if (checked1[zz][0] == c1 || checked1[zz][0] == c2) {
											if (checked1[zz][1] == 0) {
												checked1[zz][1] = 1;
											}
											f1 = true;
										}
									}
									if (f1) {
										String comp = compare(c2, c1, noOfBits);
										step1Res.add("(" + ((Integer) c2).toString() + " ," + ((Integer) c1).toString()
												+ ")  " + comp);
										break;
									}
								}
							}
						}
					}
				}
			}
		}
		System.out.println("Step 1");
		for (int i = 0; i < step1Res.size(); i++) {
			System.out.println((String) step1Res.get(i));
		}
		start = step1Res;
		for (int i = 0; i < checked1.length; i++) {
			if (checked1[i][1] == 0) {
				String un = "";
				un += checked1[i][0];
				un += " " + convertBinary(checked1[i][0], noOfBits);
				checked.add(un);
			}
		}

	}

	public static void stepn() {
		boolean done = false;
		boolean uncheck = false;
		int pow = 1;
		for (int step = 1; !done; step++) {
			if (step == 1) {
				result = (ArrayList) step1Res.clone();
				start = (ArrayList) result.clone();
			} else {
				String num1,num2;
				System.out.println("Step " + step);
				result.clear();
				unchecked2 = new String[start.size()];
				for (int d = 0; d < start.size(); d++) {
					unchecked2[d] = start.get(d);
				}
				done = true;
				if (start.size() == 1) {
					checked.add(start.get(0));
					break;
				}

				for (int i = 0; i < start.size(); i++) {

					int st = 0;
					int countBrace = 0;
					String first = start.get(i);
					if(first.charAt(0)=='('){
					for (int j = 0; countBrace < pow; j++) {
						if (first.charAt(j) == ')')
							countBrace++;
						st++;
					}
					 num1 = first.substring(0, st);
					first = first.substring(st + 2, first.length());}
					else {
						num1="";
						int jump=0;
						while(first.charAt(jump)!=' '){
							num1+=first.charAt(jump);
							jump++;
						}
						jump++;
						first=first.substring(jump,first.length());
						
					}
					for (int j = i + 1; j < start.size(); j++) {
						int stt = 0;
						int countBrace2 = 0;
						String second = start.get(j);
						if(second.charAt(0)=='('){
						for (int z = 0; countBrace2 < pow; z++) {
							if (second.charAt(z) == ')')
								countBrace2++;

							stt++;
						}
						 num2 = second.substring(0, stt);
						second = second.substring(stt + 2, second.length());}
						else {
							num2="";
							int jump=0;
							while(second.charAt(jump)!=' '){
								num2+=second.charAt(jump);
								jump++;
							}
							jump++;
							second=second.substring(jump,second.length());
						}
						int diff = 0, index = 0;
						for (int k = 0; k < first.length(); k++) {
							if (first.charAt(k) != second.charAt(k)) {
								diff++;
								index = k;
							}
						}
						if (diff == 1) {
							// if (step==2){
							// System.out.checked1ln("Ya rab");
							// }
							unchecked2[i] = "";
							unchecked2[j] = "";
							done = false;
							StringBuilder res = new StringBuilder();
							String test = "";
							res.append(num1 + num2);
							res.append("  ");
							for (int c = 0; c < first.length(); c++) {
								if (c != index) {
									res.append(first.charAt(c));
									test += first.charAt(c);
								} else {
									res.append('-');
									test += '-';
								}
							}
							boolean found = false;
							for (int l = 0; l < result.size(); l++) {
								if (result.get(l).contains(test)) {
									found = true;
								}
							}
							if (!found) {
								System.out.println(res.toString());
								result.add(res.toString());
							}
						}
					}
				}
				for (int m = 0; m < unchecked2.length; m++) {
					if (unchecked2[m] != "") {
						checked.add(unchecked2[m]);

					}
				}
			}
			start = (ArrayList) result.clone();
			if (step != 1) {
				pow *= 2;
			}
		}
	}

	public static void coverageTable() {
		boolean ok = false;
		int coverageRows = 0;
		for (int j = 0; j < checked.size(); j++) {
			ok=false;
			for (int g = 0; g < minTerms.size(); g++) {
				if (contain(checked.get(j), minTerms.get(g).toString())) {
					ok = true;
				}
			}
			if (ok) {
				if (checked.get(j).length()-(noOfBits+2)>max){
					max=checked.get(j).length()-(noOfBits+2);
				}
				coverageRows++;
			}
		}
		coverage = new String[coverageRows + 1][minTerms.size() + 1];
		for (int x = 0; x < coverage.length; x++) {
			Arrays.fill(coverage[x], "");
		}
		coverage[0][0] = "";
		boolean okay = true;
		{
			int i = 1, p = 1;
			while (p < coverageRows + 1) {
				okay = false;
				for (int g = 0; g < minTerms.size() && !okay; g++) {
					if (contain(checked.get(i - 1), minTerms.get(g).toString())) {
						okay = true;
					}
				}
				if (okay) {
					if(checked.get(i-1).charAt(0)=='('){
					coverage[p][0] = "";
					for (int h = 0; checked.get(i - 1).charAt(h) != ')'
							|| checked.get(i - 1).charAt(h + 1) != ' '; h++) {
						coverage[p][0] += checked.get(i - 1).charAt(h);
					}
					coverage[p][0] += ')';}
					else {
						coverage[p][0] = "";
						int jump=0;
						while(checked.get(i-1).charAt(jump)!=' '){
							coverage[p][0] +=checked.get(i-1).charAt(jump);
							jump++;
						}
						jump++;
					}
					p++;
				}
				i++;
			}
		}
		for (int j = 1; j < minTerms.size() + 1; j++) {
			checkMin.add(false);
			coverage[0][j] = minTerms.get(j - 1).toString();
		}
		checkMin.add(false);
		for (int i = 0; i < coverage.length; i++) {
			checkPrime.add(false);
			for (int j = 0; j < minTerms.size() + 1; j++) {
				if (i != 0 && j != 0) {
					if (contain(coverage[i][0], coverage[0][j])) {
						coverage[i][j] = "X";
					} else {
						coverage[i][j] = " ";
					}
				}
			}
		}
		// checkPrime.remove(0);
	}

	public static void tableSolver() {
		generatCombLength();
	}

	public static void minExpression() {
		for (int i = 0; i < indices.size(); i++) {
			terms.add(finalTerms.get(indices.get(i) - 1));
			// System.out.println(finalTerms.get(i-1));
		}
		String term = "";
		for (int i = qq; i < terms.size(); i++) {
			for (int j = 0; j < terms.get(i).length(); j++) {
				if (terms.get(i).charAt(j) == '1') {
					term += expression.charAt(j);
				} else if (terms.get(i).charAt(j) == '0') {
					term += expression.charAt(j);
					term += "'";
				}

			}
			qq++;
			if (i == terms.size() - 1)
				term += "";
			else
				term += " + ";
		}
		System.out.println("The minimum expression is: " + term + ".");
		 resa += term+"              ";
	}

	public static boolean isEssential() {
		boolean found = false;
		// Iterating over the rows..
		for (int i = 1; i < coverage[0].length; i++) {
			if (!checkMin.get(i)) {
				int xs = 0;
				// iterating over column
				int index = 0;
				for (int j = 1; j < coverage.length; j++) {
					if (coverage[j][i].equals("X")) {
						xs++;
						index = j;
					}
				}
				if (xs == 1) {
					terms.add(finalTerms.get(index - 1));
					checkPrime.set(index, true);
					for (int j = 0; j < coverage[index].length; j++) {
						if (coverage[index][j].equals("X")) {
							checkMin.set(j, true);
						}
					}
					found = true;
				}
			}
		}
		return found;
	}

	public static boolean isRowDominant() {

		boolean found = false;
		for (int i = 1; i < coverage.length; i++) {
			if (!checkPrime.get(i)) {
				for (int j = i + 1; j < coverage.length; j++) {
					if (!checkPrime.get(j)) {
						{
							{
								int outcome = dominatingRow(i, j);
								if (outcome == i) {
									checkPrime.set(j, true);
									found = true;
								} else if (outcome == j) {
									checkPrime.set(i, true);
									found = true;
								}
							}
						}
					}
				}
			}
		}
		return found;
	}

	public static boolean isColumnDominant() {
		boolean found = false;
		for (int i = 1; i < coverage[0].length; i++) {
			if (!checkMin.get(i)) {
				for (int j = i + 1; j < coverage[0].length; j++) {
					if (!checkMin.get(j)) {
						int outcome = dominatingColumn(i, j);
						if (outcome == i) {
							checkMin.set(j, true);
							found = true;
						} else if (outcome == j) {
							checkMin.set(i, true);
							found = true;
						}
					}
				}
			}
		}
		return found;
	}

	public static int dominatingColumn(int c1, int c2) {
		int x1 = 0, x2 = 0;
		for (int i = 0; i < coverage.length; i++) {
			if (coverage[i][c1].equals("X") && !checkPrime.get(i)) {
				x1++;
			}
		}
		for (int i = 0; i < coverage.length; i++) {
			if (coverage[i][c2].equals("X") && !checkPrime.get(i)) {
				x2++;
			}
		}
		if (x1 > x2) {
			for (int i = 0; i < coverage.length; i++) {
				if (coverage[i][c2].equals("X") && !checkPrime.get(i)) {
					if (!coverage[i][c1].equals("X")) {
						return (-1);
					}
				}
			}
			return c2;
		} else if (x2 > x1) {
			for (int i = 0; i < coverage.length; i++) {
				if (coverage[i][c1].equals("X") && !checkPrime.get(i)) {
					if (!coverage[i][c2].equals("X")) {
						return (-1);
					}
				}
			}
			return c1;
		}
		// x1=x2
		else {
			for (int i = 0; i < coverage.length; i++) {
				if (coverage[i][c1].equals("X") && !checkPrime.get(i)) {
					if (!coverage[i][c2].equals("X")) {
						return (-1);
					}
				}
			}
			return c1;
		}
	}

	public static int dominatingRow(int r1, int r2) {
		int x1 = 0, x2 = 0;
		for (int i = 0; i < coverage[r1].length; i++) {
			if (coverage[r1][i].equals("X") && !checkMin.get(i)) {
				x1++;
			}
		}
		for (int i = 0; i < coverage[r2].length; i++) {
			if (coverage[r2][i].equals("X") && !checkMin.get(i)) {
				x2++;
			}
		}
		if (x1 > x2) {
			for (int i = 0; i < coverage[r2].length; i++) {
				if (coverage[r2][i].equals("X") && !checkMin.get(i)) {
					if (!coverage[r1][i].equals("X")) {
						return (-1);
					}
				}
			}
			return r1;
		} else if (x2 > x1) {
			for (int i = 0; i < coverage[r1].length; i++) {
				if (coverage[r1][i].equals("X") && !checkMin.get(i)) {
					if (!coverage[r2][i].equals("X")) {
						return (-1);
					}
				}
			}
			return r2;
		}
		// x1=x2
		else {
			for (int i = 0; i < coverage[r1].length; i++) {
				if (coverage[r1][i].equals("X") && !checkMin.get(i)) {
					if (!coverage[r2][i].equals("X")) {
						return (-1);
					}
				}
			}
			return r2;
		}
	}

	public static boolean contain(String ch, String min) {
		for (int i = 0; i < ch.length(); i++) {
			if (Character.isDigit(ch.charAt(i))) {
				String s = "";
				while ((ch.charAt(i) != ' ' && ch.charAt(i) != ')') && i < ch.length()) {
					s += ch.charAt(i);
					i++;
					if (i == ch.length()) {
						break;
					}
				}
				if (min.equals(s)) {
					return true;
				}
			}
		}
		return false;
	}

	/*
	 * public static void completeSearch() { boolean done = false; int gps=2;
	 * while(!done) { //For main.. for(int i=coverage.length-1; i>=1; i--) {
	 * if(!checkPrime.get(i)) { //+1 because of empty cell ^_^.. boolean []vis =
	 * new boolean[minTerms.size()+1]; // Assigning all values with false..
	 * for(int j=1; j<minTerms.size()+1; j++) { vis[j] = false; } //Secondary
	 * row.. for(int j=i-1; j>=1; j--) { if(!checkPrime.get(j)) { //This loop
	 * over columns and compare if they can be get with each other.. for(int
	 * k=1; k<coverage[0].length; k++) { if(!checkMin.get(k)) {
	 * if(coverage[i][k].equals("X")) vis[k] = true;
	 * if(coverage[j][k].equals("X")) vis[k] = true; } } boolean f=false;
	 * for(int k=1; k<coverage[0].length; k++) { if(vis[k] == false) f = true; }
	 * //this means that all minTerms are covered.. if(!f) done = true; } }
	 * 
	 * } } gps++; }
	 * 
	 * }
	 */
	public static void generatCombLength() {

		primes1 = new boolean[coverage.length];
		for (len = 1; !solved; len++) {
			for (int i = 1; i < primes1.length; i++) {
				primes1[i] = false;
			}
			completeSearch(1, 0, primes1);
		}

	}

	public static void completeSearch(int curr, int size, boolean[] primes) {
		if (size == len) {
			if (validComb(primes))
			{
				minExpression();
				solved = true;
			}
			return;
		}
		if (curr == primes.length)
			return;
		// else
		primes[curr] = true;
		completeSearch(curr + 1, size + 1, primes);
		///required for backtracking..
		primes[curr] = false;
		completeSearch(curr + 1, size, primes);
		return;
	}

	// This method checks if these combination will be valid...
	public static boolean validComb(boolean[] primes) {
		indices = new ArrayList<Integer>();
		for (int i = 1; i < primes.length; i++) {
			if (primes[i] == true) {
				// its place in coverage table...
				indices.add(i);
			}
		}
		// equals size+1 because of empty cell..
		boolean[] vis = new boolean[minTerms.size() + 1];
		for (int i = 0; i < vis.length; i++)
			vis[i] = false;
		// iterating over the array list of indices of minTerms in the coverage
		// table..
		for (int i = 0; i < indices.size(); i++) {
			int row = indices.get(i);
			for (int j = 1; j < coverage[0].length; j++) {
				if (coverage[row][j].equals("X"))
					vis[j] = true;
			}
		}
		// vis.length = coverage[0].length = minTerms.size()+1......
		for (int i = 1; i < vis.length; i++) {
			if (vis[i] == false)
				return false;
		}
		// else
		return true;
	}

	public static String convertBinary(int num, int bits) {
		String num1 = "";
		num1 += num;
		int first = Integer.parseInt(num1, 10);
		String firstInBinary = Integer.toBinaryString(first);
		while (firstInBinary.length() < bits) {
			firstInBinary = "0" + firstInBinary;
		}
		return firstInBinary;
	}

	public static void call(String minTerms1 ,String dontCares1, int choose ) {
		//Scanner in = new Scanner(System.in);
		//Scanner in2 = new Scanner(System.in);

		System.out.print("Enter the min-terms of the function in the form 'a b c': ");

		System.out.print("Are ther dont cares? yes-->1 // No--2 : ");
		if (choose == 1) {
			System.out.print("Enter don't care terms in the form a b c: ");
		} else {
			dontCares1 = "";
		}
		int biggestMinTerm = 0;
		// Adding minTerms in array list of minTerms.

		for (int i = 0; i < minTerms1.length(); i++) {
			StringBuilder s = new StringBuilder();
			for (int j = i; j < minTerms1.length(); j++) {
				if (minTerms1.charAt(j) == ' ') {
					i = j;
					break;
				}
				s.append(minTerms1.charAt(j));
				i = j;
			}
			minTerms.add((int) Integer.parseInt(s.toString()));
			biggestMinTerm = Math.max(biggestMinTerm, Integer.parseInt(s.toString()));
		}
		noOfBits = (int) log(biggestMinTerm, 2) + 1;
		// Adding dontCares in array list of dont cares.
		for (int i = 0; i < dontCares1.length(); i++) {
			StringBuilder s = new StringBuilder();
			for (int j = i; j < dontCares1.length(); j++) {
				if (dontCares1.charAt(j) == ' ') {
					i = j;
					break;
				}
				s.append(dontCares1.charAt(j));
				i = j;
			}
			dontCares.add((int) Integer.parseInt(s.toString()));
			biggestMinTerm = Math.max(biggestMinTerm, Integer.parseInt(s.toString()));
		}
		// getting biggest number of bits int dont cares and comparing it with
		// the previous no. of bits from minTerms.`
		noOfBits = (int) Math.max(noOfBits, log(biggestMinTerm, 2) + 1);
		System.out.println(noOfBits);
		mergeMinAndDont();
		step0();
		step1();
		stepn();
		boolean ok = false;
		for (int i = 0; i < checked.size(); i++) {
			String search = checked.get(i);
			for (int k = 0; k < minTerms.size(); k++) {
				if (contain(search, minTerms.get(k).toString())) {
					ok = true;
				}
			}
			if (ok) {
				int j;
				if (search.charAt(0) == '(') {
					for (j = 0; j < search.length() && (search.charAt(j) != ')' || search.charAt(j + 1) != ' '); j++) {

					}
					System.out.println(search.substring(j+3,search.length()));
					finalTerms.add(search.substring(j + 3, search.length()));
				}
				else {
					int jump=0;
					while(search.charAt(jump)!=' '){
						jump++;
					}
					jump++;
					System.out.println(search.substring(jump,search.length()));
					finalTerms.add(search.substring(jump,search.length()));
				}
			}
		}
		coverageTable();

		for (int k = 0; k < coverage.length; k++) {
			for (int z = 0; z < minTerms.size() + 1; z++) {				
				System.out.print(coverage[k][z]);
				System.out.print("  ");
				if (k==0 && z==0){
					int y=0;
					while(y<max){
						System.out.print(" ");
						y++;
					}
				}
				else if (z==0){
					if (coverage[k][z].length()<max){
						int dif=(max)-coverage[k][z].length();
						int y=0;
						while(y<dif){
							System.out.print(" ");
							y++;
						}
//						System.out.print("  ");
					}
				}
				else if (k!=0 && z!=0){
					if (coverage[0][z].length()>1){
						int x=0; 
						while (x<coverage[0][z].length()-1){
							System.out.print(" ");
							x++;
						}
					}
				}
			}
			System.out.println();
		}
		tableSolver();
		if (solved) {
			for (int i = 0; i < indices.size(); i++) {
				System.out.println(finalTerms.get((indices.get(i)) - 1));
			}
		}
		System.out.println(checked);
		System.out.println(finalTerms);
		System.out.println(terms);
		//in.close();
		//in2.close();
	}

	public static int maxOnes() {
		int max = 0;
		for (int i = 0; i < minAndDont.size(); i++) {
			max = Math.max(countOnes((int) minAndDont.get(i)), max);
		}
		return max;
	}
}