function  checkName(url){
	var nickName=document.getElementById("nickName").value;
	if(nickName.length>0){
		var parameter="nickName="+nickName;
		sendAsynchronRequest(url,parameter,getTextCallBack);
	}else{
		alert("please input the nickname!");
	}
}

function getTextCallBack(){
	if(xmlHttp.readyState==4){//0(未初始化);1(正在装载);2 (装载完毕);3 (交互中);4 (完成) 
		if(xmlHttp.status==200){//200(OK);404(not found)		
			var xmlDoc=xmlHttp.responseXML;
			var message=xmlDoc.getElementsByTagName("message")[0];
			var error=xmlDoc.getElementsByTagName("error")[0];
			
			var textDiv =document.getElementById("textDiv");
			var userName=document.getElementById("nickName");
			if(message!=null){		
				textDiv.innerHTML="<font color='blue'>"+message.firstChild.nodeValue+"</font>";
			}
			if(error!=null){
				userName.value="";
				userName.focus();
				textDiv.innerHTML="<font color='blue'>"+error.firstChild.nodeValue+"</font>";
			}
		}else{
			alert("the inner error occur!");
		}
	}
}