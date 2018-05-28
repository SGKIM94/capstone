(function() {
  var score = document.getElementById('result');
  console.log(score);
  var sum = 0;
  for(var i = 0; i < 7; i++) {
    sum += parseInt(document.querySelector('input[name="val_'+ (i + 1) + '"]:checked').value);
  }
  score.innerHTML = sum;
})();

(function () {
  var sumList = [];
  sumList.length = 7;
  // 초기화
  for(var i = 0; i < sumList.length; i++) {
    sumList[i] = 0;
  }
  var radio = [];
  radio.length = 7;
  
  for(var i = 0; i < radio.length; i++) {
    radio[i] = document.querySelectorAll('input[name="val_' + (i + 1) + '"]');
  }
  
  for(var i = 0; i < radio[0].length; i++) {
    radio[0][i].addEventListener('click', function() {
      sumList[0] = document.querySelector('input[name="val_1"]:checked').value;
      sum();
    });
  }
  for(var i = 0; i < radio[0].length; i++) {
    radio[1][i].addEventListener('click', function() {
      sumList[1] = document.querySelector('input[name="val_2"]:checked').value;;
      sum();
    });
  }
  for(var i = 0; i < radio[0].length; i++) {
    radio[2][i].addEventListener('click', function() {
      sumList[2] = document.querySelector('input[name="val_3"]:checked').value;
      sum();
    });
  }
  for(var i = 0; i < radio[0].length; i++) {
    radio[3][i].addEventListener('click', function() {
      sumList[3] = document.querySelector('input[name="val_4"]:checked').value;
      sum();
    });
  }
  for(var i = 0; i < radio[0].length; i++) {
    radio[4][i].addEventListener('click', function() {
      sumList[4] = document.querySelector('input[name="val_5"]:checked').value;
      sum();
    });
  }
  for(var i = 0; i < radio[0].length; i++) {
    radio[5][i].addEventListener('click', function() {
      sumList[5] = document.querySelector('input[name="val_6"]:checked').value;
      sum();
    });
  }
  for(var i = 0; i < radio[0].length; i++) {
    radio[6][i].addEventListener('click', function() {
      sumList[6] = document.querySelector('input[name="val_7"]:checked').value;
      sum();
    });
  }
  
  function sum() {
    var score = document.getElementById('result');
    var sum = 0;
    for(var i = 0; i < sumList.length; i++) {
      sum += parseInt(sumList[i]);
    }
    score.innerHTML = sum;
  }
})();