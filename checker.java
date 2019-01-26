public int check(String din)
 {
	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	Date date = new Date();
	String s=formatter.format(date);
	if(s.equals(din))
		return 1;
	return 0;
}

public int checkcoach(String a)
{
	if(a.charAt(0)=='S')
	{
		char ch=a.charAt(1);
		if(ch>='1'&&ch<='9')
			return 1;
		else
			return 0;
	}
}

public int checkseat(String s)
{
	int a=Integer.parseInt(s);
	if(a>=1&&a<=72)
		return 1;
	else
		return 0;
}