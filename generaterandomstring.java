public static String generateRandom()
{
		String aToZ="ABCDEFGHIJKLMNOPQRSTUVWXYZ1234";
		Random rand=new Random();
		StringBuilder res=new StringBuilder();
		for (int i = 0; i < 7; i++) {
		   int randIndex=rand.nextInt(aToZ.length()); 
		   res.append(aToZ.charAt(randIndex));            
		}
		return res.toString();
}
