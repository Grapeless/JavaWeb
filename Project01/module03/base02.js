//==与===
//1.==在比较时会发生类型转换
let c = 10;
console.log(c == '10');
//2.===则不会
console.log(c === '10');
console.log(c === 10);

//类型转换 - 其他类型转为number
console.log(parseInt("12"));
console.log(parseInt("123A45")); //只转化到3
console.log(parseInt("A12")); //返回NAN

//类型转换 - 其他类型转为boolean
//1.string中的空字符串转为false
//2.number中的NAN和0转为false
//3.null和undefined转为false
