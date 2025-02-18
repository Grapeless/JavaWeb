
alert('Hello JS');   //每行最后一个分号可省略

// 注释语法同java

//输出语句
//1.弹出警告框
window.alert("hello javascript");
//2.写入html输出
document.write("hello javascript");
//3.写入浏览器控制台
console.log("hello javascript");

//弱类型语言
let a = 20;
a = "字符串";

//var的特点：
//1.全局作用域
//2.重复定义
//3.提升（Hoisting）

//let的特点：
//1.局部作用域
//2.不重复定义
//3.不会提升

//const的特点：
//1.常量，变量值无法被改变

//原始数据类型，使用typeof获取数据类型
//1.number:包括整数小数，即NAN(Not A Number)
alert(typeof 3);
alert(typeof 3.14);

//2.string:字符串，使用单双引号声明皆可
alert(typeof 'a');
alert(typeof "a");
alert(typeof "abc");

//3.boolean
alert(typeof true);

//4.null:对象为空
alert(typeof null);

//5.undefined:变量已声明，但未初始化
alert(typeof b);





