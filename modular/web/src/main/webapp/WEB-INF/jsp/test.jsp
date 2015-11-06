<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <script src="external/jquery.js"></script>
	<script src="external/sha256.js"></script>
	<script src="external/aes.js"></script>

    <script type="text/javascript">
     
    $(document).ready(function(){

        $("#testhash").click(function(){
			var hash = CryptoJS.SHA256($("#sensitive").val());
			$("#hash").val(hash);
			$("#hashform").submit();


			//var wordArray = CryptoJS.enc.Utf8.parse('hello');
			//var base64 = CryptoJS.enc.Base64.stringify(wordArray);
			//alert('base64:'+base64);
			//var parsedWordArray = CryptoJS.enc.Base64.parse(base64);
			//var utf8 = parsedWordArray.toString(CryptoJS.enc.Utf8);
			//alert('utf8:'+utf8);

			//var enc = CryptoJS.AES.encrypt('Hello','secret');
            //alert('enc:'+enc);
			//var dec = CryptoJS.AES.decrypt(enc,'secret');
            //alert('dec:'+dec.toString(CryptoJS.enc.Utf8));
			
        });
        
        $("#ajaxtest").click(function(){
			$.ajax({
			  url: "testajax.htm",
			  type: "GET",
				success: function(response){
					alert(response);
				}, 
	 			error: function(e){ 
	 				alert(e);
		         } 			  
			});
        });        

    });

	</script>

<h2>This is sample page for end to end testing of environment</h2>

<form method="post" action="testdb.htm">
<input type="submit" value="Test Database">
</form>
<form method="post" action="testad.htm">
<input type="submit" value="Test Active Directory">
</form>
<form method="post" action="testpwd.htm">
<input type="submit" value="Test J2C Auth alias">
</form>
<form method="post" action="testex.htm">
<input type="submit" value="Test Exception">
</form>
<form method="post" action="testtx.htm">
<input type="submit" value="Test Transaction">
</form>
<form method="post" action="testcache.htm">
<input type="submit" value="Test Cache">
</form>
<form method="post" action="invalidcache.htm">
<input type="submit" value="Invalidate Cache">
</form>
<form method="post" action="testprop.htm">
<input type="submit" value="Test Properties">
</form>
<form method="post" action="testget.htm">
<input type="submit" value="Test GET">
</form>
<form method="post" action="testput.htm">
<input type="submit" value="Test PUT">
</form>
<form method="post" action="testnode.htm">
<input type="submit" value="Test NODE">
</form>
<form method="post" action="testhash.htm" id="hashform">
<input type="hidden" id="sensitive" name="sensitive" value="secret"/>
<input type="hidden" id="hash" name="hash"/>
<input type="button" value="Test Hash" id="testhash">
</form>

<form method="post" action="testajax.htm">
	<input type="button" value="Test AJAX" id="ajaxtest">
</form>


<h2><c:out value="${message}"/></h2>
<br/>
<h1>Images served from IHS</h1>
<br/>
<a href="#">
<img src="banner/bubanc.png">
</a>
<br/>
<a href="#">
<img src="banner/cubinet.png">
</a>
<br/>

