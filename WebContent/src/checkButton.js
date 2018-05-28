function formCheck() {
  var none = document.querySelector('input[name="val_1"]:checked');
  if(!none) {
    alert('체크해주세요');
    return false;
  }else return true;
}