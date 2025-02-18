//事件监听
//事件绑定
//1.通过html标签中的事件系列属性进行绑定
function fun1()   {
    console.log("事件绑定一");
}
//2.通过DOM元素 设置事件属性绑定
let button1 = document.getElementById('fun2');
button1.onclick = function(){
    console.log("事件绑定二");
}

//常见事件  https://www.w3school.com.cn/jsref/dom_obj_event.asp
//1.onclick
//2.onblur 失去焦点时
//3.onfocus 获得焦点时
//4.onload  被加载时
//5.onsubmit 被提交时
//6.onkeydown 键被按下时
//7.onmouseover 鼠标悬浮之于上时
//8.onmouseout  鼠标移开时

function load() {
    console.log('页面加载完成');
}
function fun_onblur(){
    console.log('onblur');
}
function fun_onfocus() {
    console.log('onfocus');
}
function fun_onkeydown() {
    console.log('onkeydown');
}
function fun_onMouseover() {
    console.log('fun_onMouseover');
}
function fun_onMouseout() {
    console.log('fun_onMouseout');
}
function fun_onsubmit() {
    alert("fun_onsubmit");
}
