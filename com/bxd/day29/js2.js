//弱类型的优缺点
var x = 3;
console.log(x);
x="abc";
console.log(x);
x=3.45;
console.log(x);
x=true;
console.log(x);
x='c'
console.log(x);

//算数运算符
var a = 3567;
console.log(a/1000*1000);

var a1 = 2.3;
var a2 = 4.7;
console.log(a1+a2);

console.log("12"+1);
console.log("12"-1);
console.log(true+1);
console.log(2%5);
console.log(2%2);

//逻辑运算符
var m =3,n;
n=m++;
console.log(n+m);
console.log(n==4);
console.log(n!=4);
console.log(n>1&&n<4);
console.log(n>1&n<4);
console.log(!n);
console.log(n&2);
console.log(n<<1);
console.log(n>>>1);
console.log(n>>1);
console.log(5^3^3);

//获取类型
console.log(typeof(true));
console.log(typeof("abc"));
console.log(typeof(123));
console.log(typeof(12.3));
console.log(typeof('a'));
console.log(typeof(123)=='number');


//控制体语句
if(n>1){
	
}else if(n>2){
	
}else if(n>3){
	
}else{
	
}

if(n>1){
	
}else{

}

var x = "abc";
switch(x){
	default:
		//break;
	case "a":
		break;
	case 'b':
		break;
}

var p = 0;
while(p<3){
	//document.write("x="+p+"<br>");
	p++;
}

//while(x<10);
//{
//}

var i = 0;
do{
	i++;
}while(i<10);

for(var i = 0;i<10;i++){
	if(i==5)
		continue;
	console.log(i);
	if(i==8)
		break;
}

//打印乘法口诀表
document.write("<table>");
for(var i = 1;i<10;i++){
	document.write("<tr>");
	for(var j = 1;j<= i;j++)
		document.write("<td>"+j+"*"+i+"="+(i*j)+"&nbsp;&nbsp;</td>");
	document.write("</tr>");
}
document.write("</table>");

//数组
var arr=["abc",true,12.3,'xcc'];
arr[4]=1234;
for(var i = 0;i<arr.length;i++)
	console.log(arr[i]);

var ar1 = new Array(10);
console.log(ar1.length);
var ar2 = new Array(3,8,"13s");
console.log(ar2.length);

demo();

//一般函数
function demo(){
	console.log("asasa");
}

demo();

add(5,4);

function add(a,b){
	console.log(a+b);
}

add("a",1);

//神奇的用法
function test(){
	var p = 0;
	for(var i = 0;i<arguments.length;i++)
		p+=i;
	return p;
}

console.log(test());
console.log(test(1,4,5,6,6));
console.log(test(1,"as",34));
console.log("end");

//函数的引用
function getsum(){
	return 100;
}

var sum = getsum();
var sum2 = getsum;

console.log(sum);
console.log(sum2);
console.log(getsum);

//动态函数
var fun = new Function("x,y","return x+y;");
console.log(fun(4,8));

//匿名函数
var fun2 = function(a,b){
	return a+b;
}
console.log(fun2(4,9));