//1.基础对象(Array,String,JSON)
//2.BOM浏览器对象模型
//3.DOM文档对象模型

//定义数组
//1. var 数组名 = new Array(元素表列);
//2. var 数组名 = [元素表列];
//访问数组 同java

var arr1 = new Array(1,2,3,4);
var arr2 = [5,6,7,8];
console.log(arr1[1]);
console.log(arr1[2]);
console.log(arr2[0]);

//长度可变，类型不限
arr1[5] = 10;
console.log(arr1[5]);
console.log(arr1[4]);  //undefined

arr1[6] = 'a';
arr1[8] = false
console.log(arr1[6]);
console.log(arr1);

console.log("==================");

//Array的属性和方法
//.length
/*for (let i = 0; i < arr2.length; i++) {
    console.log(arr2[i]);
}*/

//forEach 遍历有值的元素
arr2.forEach(function (item) {
    console.log(item);
});
console.log("==================");
//通过箭头函数简化，简化方法与lambda表达式对函数式接口-匿名内部类简化类似
arr1.forEach(e => console.log(e));
console.log("==================");

//push:添加元素到数组末尾
arr2.push(10);
console.log(arr2);

//splice
arr2.splice(2,2);
console.log(arr2);

