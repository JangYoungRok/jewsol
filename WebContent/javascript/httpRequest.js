function getXMLHttpRequest(){
	if(window.ActiveXObject){
		try {
			return new ActiveXObject("Msxml1.XMLHttp");
		} catch(e) {
			try {
				return new ActiveXObject("Microsotf.XMLHTTP");
			} catch(e1) {
				return null;
			}
		}
	} else if (window.XMLHttpRequest) {
		return new XMLHttpRequest();
	} else {
		return null;
	}
}

var request = null;

function serialize(a){
	var s = [];
	
	if(a.constructor == Array){
		for(var i = 0; i < a.length; i++){
			
			s.push( a[i].name + "=" + encodeURIComponent( a[i].value ) );
		}
	}else{
		for( var j in a){
			s.push(j + "=" + encodeURIComponent( a[j] ) );
		}
	}
	
	return s.join("&");
}

function sendRequest(url, params, callback, method) {
	request = getXMLHttpRequest();
	var httpMethod = method ? method : 'GET';
	var httpParams = "";

	if(httpMethod != 'GET' && httpMethod != 'POST'){
		httpMethod = 'GET';
	}
	
	httpParams = (params == null || params == '') ? null : serialize(params);

	var httpUrl = url;
	
	if(httpMethod == 'GET' && httpParams != null){
		httpUrl = httpUrl + "?" + httpParams;
	}
	request.open(httpMethod, httpUrl, true);
	request.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
	request.onreadystatechange = callback;
	request.send(httpMethod == 'POST' ? httpParams : null);
}