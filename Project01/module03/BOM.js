//BOM 浏览器对象模型，允许JavaScript与浏览器对话，将浏览器的各个组成部分封装为对象
//Navigator,Screen,History,Window,Location

//Window:浏览器窗口对象
//1.获取：直接使用window. 且可省略
window.alert("嘤嘤嘤");
alert("yyy");
//2.属性：navigator,location,history 获取对所列对象的只读引用
//3.方法
    //3.1 alert
    //3.2 confirm
    let meg= confirm("确认删除?")  //确定-true 取消-false
    console.log(meg)

    //3.3 setInterval 周期性重复执行
    setInterval(function (){
        console.log("1s过去了");
    },1000);
    setInterval(() => console.log("2s过去了"),2000);

    //3.4 set  延迟执行一次
    setTimeout(() => console.log("5s过去了"),5000);

//Location:地址栏对象
//1.获取
let locObj1 = window.location;
let locObj2 = location;
//2.属性：href:"returns a string containing the whole URL"
//2.1返回当前页面url
let urlStr = locObj1.href;
console.log(urlStr)
//2.2设置当前页面url
locObj1.href = "https://www.github.com";
