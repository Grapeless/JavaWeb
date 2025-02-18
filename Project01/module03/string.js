//定义字符串（对象）
//1.var 变量名 = new String("...");
//2.var 变量名 = "...";

//属性
//length

//方法
//1.charAt(index )  //获取指定位置的字符
//2.indexOf(string ) //获取所检索字符所在位置
//3.trim()  //去除首尾空白
//4.subString(startIndex , endIndex(exclusive) ) //截取字符串
//5.endWith(string )   //是否以指定字符串结尾
//6.7 toUpperCase() ,toLowerCase();  //大小写转化
var str = " a bc  ";
var strTrim = str.trim();
console.log(str.length);
console.log(str.charAt(1));
console.log(str.indexOf('a'));
console.log(strTrim);
console.log(str.substring(1,4));

str.toLocaleUpperCase()