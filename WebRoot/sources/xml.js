var xmlhttp;
		function loadXMLDoc(url, cfunc) {
			if (window.XMLHttpRequest) {// IE7+, Firefox, Chrome, Opera, Safari ����
				xmlhttp = new XMLHttpRequest();
			} else {// IE6, IE5 ����
				xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
			}
			xmlhttp.onreadystatechange = cfunc;
			xmlhttp.open("GET", url, true);
			xmlhttp.send();
		}