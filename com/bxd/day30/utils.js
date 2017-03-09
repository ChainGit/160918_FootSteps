//最大值(实数)
Array.prototype.max = function(){
	var a = this;
	var max = 0;
	for(var x = 1;x<a.length;x++)
		if(a[x]>a[max])
		max = x;
	return a[max];
}

//最小值(实数)
Array.prototype.min = function(){
	var a = this;
	var min = 0;
	for(var x = 1;x<a.length;x++)
		if(a[x]<a[min])
		min = x;
	return a[min];
}

//插入排序(整数)
Array.prototype.sort = function(){
	var a = this;
	for(var i = 0;i<a.length-1;i++){
		var t = i;
		for(var j = i+1;j<a.length;j++)
			if(a[t]>a[j])
				t = j;
		if(t!=i)
			this.swap(i,t);
	}
}

//交换元素
Array.prototype.swap = function(i,j){
	var a = this;
	if(i!=j){
		a[j]^=a[i];
		a[i]^=a[j];
		a[j]^=a[i];
	}
}

//查找(二分)/整数
Array.prototype.search = function(k){
	var a = this;
	this.sort(a);
	var max=a.length-1,min=0,mid;

	while(min<=max){
		mid=(max+min)>>>1;
		if(k>a[mid])
			min = mid+1;
		else if(k<a[mid])
			max = mid-1;
		else
			return mid;
	}
	return -1;
}

//翻转数组
Array.prototype.reverse = function(){
	var a = this;
	for(var i =0;i<(a.length>>1);i++)
		this.swap(i,a.length-1-i);
}