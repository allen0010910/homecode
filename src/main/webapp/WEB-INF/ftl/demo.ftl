<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>md5加密</title>

<script type="text/javascript" src="../sources/js/jquery-1.4.2.min.js"></script>

<link rel="stylesheet" type="text/css" href="../sources/css/styles.css" />
<script type="text/javascript" src="../sources/js/script.js"></script>
</head>

<body>

<div id="carbonForm">
	<h1>md5加密</h1>

    <form method="get" id="signupForm">

    <div class="fieldContainer">

        <div class="formRow">
            <div class="label">
                <label for="name">明文:</label>
            </div>
            
            <div class="field">
                <input type="text" name="content" id="content" />
            </div>
        </div>
        
        <div class="formRow">
            <div class="label">
                <label for="email">密文16位:</label>
            </div>
            
            <div class="field">
                <span class="span" name="sixteenBits" id="sixteenBits" ></span>
            </div>
        </div>
        
        <div class="formRow">
            <div class="label">
                <label for="email">密文32位:</label>
            </div>
            
            <div class="field">
                <span class="span" name="thirtytwoBits" id="thirtytwoBits" ></span>
            </div>
        </div>
        
    </div> <!-- Closing fieldContainer -->
    
    <div class="signupButton">
        <input type="button" name="submit" id="submit" value="Signup" />
    </div>
    
    </form>
        
</div>
<style>
.span{
	line-height: 35px
}
</style>
<script>
$(function(){
	var prefixUrl="mantianxing.imwork.net"
		//var prefixUrl="localhost:8081";
	$("#submit").click(function(){
		$.ajax({
			type:"post",
			data:$("#signupForm").serialize(),
			url:"http://"+prefixUrl+"/md5/list",
			dataType:"json",
			cache:false,
			success:function(data){
				$("#thirtytwoBits").text(data.thirtytwoBits)
				$("#sixteenBits").text(data.sixteenBits)
			}
		})
	})
})
</script>
</body>
</html>
