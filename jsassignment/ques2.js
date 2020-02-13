var str=prompt("Enter string");
console.log(str); 
	var as="";
	var I;
	var strLength=str.length;
	str=str.toLowerCase();
	var str2=str;
	for(I=strLength;I>=0;I--)
	{
		as=as+str.charAt(I);
	}
	
	if (str==as)
	{
	 console.log("Is Palindrome");
	}
	else
	{
		console.log("Not");
	}


