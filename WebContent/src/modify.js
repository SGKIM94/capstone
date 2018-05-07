
(function () {
  var modify_section = document.getElementsByClassName('modify_section');
  
  var flag = false;
  
  for( var i = 0; i< modify_section.length;i++){
	  var modify_board = document.getElementById('modify_board');
	  var cancel_btn = document.getElementById('modify_cancel');
	 // var modifyfile = document.getElementById('modifyfile').val;
	  var modify_btn = modify_section.item(i);
	  var register = document.getElementById('register');
	 // document.getElementById('mod_fileno').value = modifyfile;
	  
	  cancel_btn.addEventListener('click', function(){
		  modify_board.style.display = 'none';
	    flag = !flag;
	  });
	  modify_btn.addEventListener('click', function(){
		    flag === false ? modify_board.style.display = 'block' : modify_board.style.display = 'none';
		    flag = !flag;
	  }); 
	  register.addEventListener('click', function() {
	    alert('파일이 수정 되었습니다');
	    location.reload();
	  });
  }
})();