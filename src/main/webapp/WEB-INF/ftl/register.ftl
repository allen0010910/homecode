<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="multipart/form-data; charset=utf-8" />
<title>用户注册</title>

<script type="text/javascript" src="../sources/js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="../sources/js/jquery.form.js"></script>
<link rel="stylesheet" type="text/css" href="../sources/css/styles.css" />
<script type="text/javascript" src="../sources/js/script.js"></script>
</head>

<body>

<div id="carbonForm">
	<h1>用户注册</h1>
	
	<!-- <form method="post" id="imgUpload" enctype="multipart/form-data">
	    <input type="file" id="img" name="img" onchange="upload()"/>
    </form> -->
    <form method="post" id="signupForm" enctype="multipart/form-data">
	    <div class="fieldContainer">
	
	        <div class="formRow">
	            <div class="label">
	                <label for="name">账号:</label>
	            </div>
	            
	            <div class="field">
	                <input type="text" name="username" id="username" />
	            </div>
	        </div>
	        
	        <div class="formRow">
	            <div class="label">
	                <label for="email">密码:</label>
	            </div>
	            
	            <div class="field">
	                <input type="text" name="password" id="password" />
	            </div>
	        </div>
            
            <input type="file" id="img" name="img"/>
	        <img id="picture" style="width:400px;height:400px;display:none" />
	        <input type="hidden" name="headImg" id="headImg"/>
	    </div>
	    
	    <div class="signupButton">
	        <input type="button" name="submit" id="submit" value="Signup"/>
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
			url:"http://"+prefixUrl+"/user/doRegister",
			dataType:"json",
			data:$("#signupForm").serialize(),
			cache:false,
			success:function(data){
				alert("注册成功!")
			}
		})
	})
	
	$("#img").change(function(){
		//upload(prefixUrl)
		base64Util($("#img")[0].files[0],400,function(base64){
			$("#headImg").val(base64)
			//上传到后台服务器，路径保存到数据库
			$.ajax({
				type:'post',
				data:{'base64Str':base64},
				url:"http://"+prefixUrl+"/base64Upload",
				dataType:'json',
				cache:false,
				success:function(data){
					$("#picture").attr("src","http://"+data.webUrl)
					$("#picture").show()
					$("#headImg").val(data.path)
				}
			})
		})
	})
})

function base64Util(file,maxLen,callBack){
	var reader=new FileReader();
	var image=new Image();
	var maxSize=210000;
	var base64Str;
	if(file){
		//将文件转为DataUrl
		reader.onload = function(e){
			base64Str=reader.result;
			image.src=base64Str;
		}
		//压缩图片
		image.onload=function(){
			//获取原图比例
			var width=image.width;
			var height=image.height;
			//压缩比例
			var rate=1;
			if(width>=height){
				if(width>maxLen){
					rate=maxLen/width;
				}
			}else{
				if(height>maxLen){
					rate=maxLen/height;
				}
			}
			image.width=rate*width;
			image.height=rate*height;
			//生成canvas
			var canvas=document.createElement("canvas");
			var ctx=canvas.getContext("2d");//获取二位画布绘图环境
			//设置画布宽高
			canvas.width=image.width;
			canvas.height=image.height;
			//画到canvas上
			ctx.drawImage(image,0,0,image.width,image.height);
			//转成base64
			//计算大小，如果大于2M压缩大小
			var maxSize=2;var fileSize=file.size/(1024*1024);var qua=1;
			var base64,newFile;
			if(fileSize>maxSize){
				qua=maxSize/fileSize;
				console.log("qua:"+qua)
				base64=canvas.toDataURL('image/jpeg',qua);
				newFile=dataUrlToFile(base64,file.name);
			}else{
				console.log("rate:"+rate)
				base64=canvas.toDataURL('image/jpeg',rate);
				newFile=dataUrlToFile(base64,file.name);
			}
			callBack(base64)
		}
		reader.readAsDataURL(file);
	}
}

function dataUrlToFile(dataUrl,fileName){
	var arr=dataUrl.split(',');
	var mine=arr[0].match(/:(.*?);/)[1];
	var bstr=window.atob(arr[1]),
		n=bstr.length,
		u8arr=new Uint8Array(n);
	while(n--){
		u8arr[n]=bstr.charCodeAt(n);
	}
	return new File([u8arr],fileName,{type:mine});
}

function upload(prefixUrl){
	var options={
			type:"post",
			url:"http://"+prefixUrl+"/uploadImg",
			dataType:"json",
			cache:false,
			success:function(data){
				$("#picture").attr("src","http://"+data.webUrl)
				$("#picture").show()
				$("#headImg").val(data.path)
			}
	}
	$("#signupForm").ajaxSubmit(options)
}
</script>
</body>
</html>
