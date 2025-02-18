//定义函数1
//1.无返回值类型声明,如果该函数有返回值直接return即可，无则省略
//2.形参表无类型
/*  function 函数名（形参表列）{
    //函数体
} 
*/
function add(a, b) {
    return a + b;
}
//定义函数2
/* var 函数名 = function(形参表列){  
    //函数体
}
 */
var subtract = function (a, b) {
    return a - b;
}

let A = 3;
let B = 4;
let c1 = add(A, B);
let c2 = subtract(A, B);
console.log(c1);
console.log(c2);