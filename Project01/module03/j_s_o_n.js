//自定义对象
var student = {
    age: 18,
    name: 'lim',
    weight: 110,
    /*eat: function (food) {
        console.log("吃了一点" + food);
    }*/
    //简化写法
    eat(food) {
        console.log("吃了一点" + food);
    }
};

//调用格式同java
student.eat('面条');
console.log(student.name);

//JSON(JavaScript Object Notation)，JavaScript对象标记法
//是什么：具有特定语法的文本，用来表示数据
//语法：0.花/方括号括起
//1.数据为键值对
//2.键需要使用双引号包裹
//3.每对数据由逗号分隔。
//4.字符串在双引号中（在解析时是字符串格式已被单引号包裹，故需要）
//5.大括号保存对象
//6.方括号保存数组

//JSON格式，JSON文本（未解析），JSON字符串（在赋给解析其的变量时成为字符串）
//JSON数据（解析后（成为js的json对象或其它））

//JS字符串跨行
//1.每行结尾使用反斜杠
//2.使用反引号
//3.每行结尾使用字符串拼接符+
var jsonStr = `{
    "editor.fontSize": 14.5,
    "editor.guides.indentation": false,
    "workbench.colorCustomizations": {
        "editor.selectionBackground": "#7aedf3",
        "editor.selectionHighlightBackground": "#FFF0F5",
        "tab.activeBackground": "#5f80629a",
        "editorSuggestWidget.background": "#ffffff",
        "editorSuggestWidget.selectedBackground": "#efef95"
    },
    "diffEditor.ignoreTrimWhitespace": true,
    "liveServer.settings.donotShowInfoMsg": true,
    "files.autoSave": "afterDelay",
    "filesAutoSaveDelay": 1000 
}`;
//JSON字符串转为JS对象
let jsonObj = JSON.parse(jsonStr);
//访问其中属性
console.log(jsonObj.filesAutoSaveDelay);
//键含有字符.
console.log(jsonObj["editor.fontSize"]);

//JS对象转为JSON字符串
console.log(JSON.stringify(jsonObj));
