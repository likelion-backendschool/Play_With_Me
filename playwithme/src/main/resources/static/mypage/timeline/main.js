(function () {
  "use strict";

  // define variables
  var items = document.querySelectorAll(".timeline li");

  // check if an element is in viewport
  // http://stackoverflow.com/questions/123999/how-to-tell-if-a-dom-element-is-visible-in-the-current-viewport
  function isElementInViewport(el) {
    var rect = el.getBoundingClientRect();
    return (
      rect.top >= 0 &&
      rect.left >= 0 &&
      rect.bottom <=
        (window.innerHeight || document.documentElement.clientHeight) &&
      rect.right <= (window.innerWidth || document.documentElement.clientWidth)
    );
  }

  function callbackFunc() {
    for (var i = 0; i < items.length; i++) {
      if (isElementInViewport(items[i])) {
        items[i].classList.add("in-view");
      }
    }
  }

  // listen for events
  window.addEventListener("load", callbackFunc);
  window.addEventListener("resize", callbackFunc);
  window.addEventListener("scroll", callbackFunc);
})();

// 메모 폼 체크
function spaceCheck(form){
const isSpace = form.querySelector('.form-control').value.trim();
    if (isSpace.length == 0) {
        alert("내용을 입력해주세요.");
        return;
        }
        form.submit();
}

// 수정 버튼 클릭시 기존 메모와 수정버튼, 삭제버튼 안보이도록
function findIdToHide(id){
    const find = document.getElementsByClassName(id);
    for(var i = 0; i < find.length; i++){
        //console.log('숨김완료')
        find[i].style.display ='none';
    }
}

// 수정 버튼 클릭시 수정폼 나타도록
function findIdToShow(id){
    const find = document.getElementsByClassName(id);
    for(var i = 0; i < find.length; i++){
        find[i].style.display ='block';
    }
}

// 메모 삭제 전 confirm
function confirmMsg(name,id){
    if ( !confirm(name + " 메모를 삭제할까요?") ) {
        return false;
        }
    window.location.href = "/mypage/timeline/memo/delete/"+id;
}
