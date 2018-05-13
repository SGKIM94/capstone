(function () { 
  var register = document.getElementById('register');
  var file = document.getElementById('teamFile');
  var filetype = document.getElementById('teamFileType');
  var filetitle = document.getElementById('fileTitle');
  
 
  register.addEventListener('click', function() {
	  
//	  if(file.files.length == 0)
//	  {
//		  alert('파일을 등륵하세요.');	 
//	  	  location.reload();
//	  }
//	  else if(document.getElementById('teamFileType').val().equals(""))
//	  {
//		  alert('파일 분류를 선택하세요.');
//		  location.reload();
//	  }
//	  else if(document.getElementById('fileTitle').val() == null)
//	  {
//		  alert('파일 제목을 입력하세요');
//		  location.reload();
//	  }
//	  else{
		  alert('글이 등록되었습니다.');
		  location.reload();		  
//	  }	  	  
  });
})();
