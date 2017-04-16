document.write('<script src="base.js"><\/script>');

// 表格数据行数
var num = 0;

window.onload = function() {
	disableMost();
}

sortBtn.onclick = function() {
	if (isInAddOrModify())
		return;
	reset();
	var sortKind = sortSelt.value;
	switch (sortKind) {
	case "按序号":
		sortKind = 0;
		break;
	case "按姓名":
		sortKind = 1;
		break;
	case "按年龄":
		sortKind = 2;
		break;
	case "按薪水":
		sortKind = 3;
		break;
	default:
		sortKind = 0;
	}

	// 升序经典冒泡排序
	var tableChildNodes = tbodyData.getElementsByTagName("tr");
	for (var i = 1; i < tableChildNodes.length - 1; i++) {
		for (var j = i + 1; j < tableChildNodes.length; j++) {
			var vi = getValue(tableChildNodes[i], sortKind)
			var vj = getValue(tableChildNodes[j], sortKind);
			var res = myCompare(vi, vj);
			if (res > 0)
				swapNode(tableChildNodes[i], tableChildNodes[j]);
		}
	}
}

function trim(str) {
	var reg = /^\s*|\s*$/g;
	return str.replace(reg, "");
}

// myCompare结果非完全正确
function myCompare(a, b) {
	console.log("[" + a + "][" + b + "]");
	var reg = /[0-9]+/g;
	console.log(reg.test(a) && reg.test(b));
	if (reg.test(a) && reg.test(b))
		return a - b;
	else
		return a.localeCompare(b);
}

function getValue(node, kind) {
	var trNodes = node.getElementsByTagName("td");
	if (kind == 0)
		return trim(trNodes[kind].lastChild.nodeValue);
	else
		return trNodes[kind].innerText;
}

function swapNode(aNode, bNode) {
	var parenta = aNode.parentNode;
	var parentb = bNode.parentNode;

	if (parenta && parentb) {
		var tmpNode = aNode.cloneNode(true);
		parenta.replaceChild(tmpNode, bNode);
		parentb.replaceChild(bNode, aNode);
	}
}

function isInAddOrModify() {
	var tableChildNodes = tbodyData.childNodes;
	for (var i = 2; i < tableChildNodes.length; i++) {
		var t = tableChildNodes[i].firstChild.lastChild;
		if (t.type == "text")
			return true;
	}
	return false;
}

searchTxt.onchange = function() {
	if (this.value.length < 1) {
		resetTableData();
		hiddenResetBtn();
	}
}

searchBtn.onclick = function() {
	var stxt = searchTxt.value;
	resetTableData();
	if (stxt.length < 1) {
		hiddenResetBtn();
		return;
	}

	var tableChildNodes = tbodyData.childNodes;
	for (var i = 2; i < tableChildNodes.length; i++)
		if (tableChildNodes[i].style.display != "none") {
			var t = tableChildNodes[i].firstChild.firstChild;
			if (t.type == "checkbox") {
				var idTd = tableChildNodes[i].firstChild;
				var id = idTd.lastChild.nodeValue;
				var nameTd = idTd.nextSibling;
				var name = nameTd.innerText;
				var ageTd = nameTd.nextSibling;
				var age = ageTd.innerText;
				var salaryTd = ageTd.nextSibling;
				var salary = salaryTd.innerText;
				replaceToFont(idTd.lastChild, stxt);
				replaceToFont(nameTd, stxt);
				replaceToFont(ageTd, stxt);
				replaceToFont(salaryTd, stxt);
			}
		}

	showRestBtn();
}

function replaceToFont(txtNode, stxt) {
	var txt;
	if (txtNode.nodeType == 3)
		txt = txtNode.nodeValue;
	else if (txtNode.nodeType == 1)
		txt = txtNode.innerText;

	if (txt.indexOf(stxt) != -1) {
		var fontNode = document.createElement("font");
		fontNode.color = "blue";
		var bNode = document.createElement("b");
		var iNode = document.createElement("i");
		iNode.innerText = txt;
		bNode.appendChild(iNode);
		fontNode.appendChild(bNode);
		if (txtNode.nodeType == 3) {
			txtNode.nodeValue = "";
			txtNode.parentNode.appendChild(fontNode);
		} else if (txtNode.nodeType == 1) {
			txtNode.innerText = "";
			txtNode.appendChild(fontNode);
		}
	}
}

function resetTableData() {
	var tableChildNodes = tbodyData.childNodes;
	for (var i = 2; i < tableChildNodes.length; i++) {
		var t = tableChildNodes[i].firstChild.firstChild;
		if (t.type == "checkbox") {
			var idTd = tableChildNodes[i].firstChild;
			var nameTd = idTd.nextSibling;
			var ageTd = nameTd.nextSibling;
			var salaryTd = ageTd.nextSibling;
			replaceToTxt(idTd);
			replaceToTxt(nameTd);
			replaceToTxt(ageTd);
			replaceToTxt(salaryTd);
		}
	}
}

function replaceToTxt(tdNode) {
	var last = tdNode.lastChild;
	if (last == null)
		return;
	if (last.nodeName != "FONT")
		return;

	var firstName = tdNode.firstChild.nodeName;
	var txt = tdNode.innerText;
	tdNode.removeChild(last);
	if (firstName == "INPUT")
		tdNode.lastChild.nodeValue = txt;
	else
		tdNode.innerText = txt;
}

resetBtn.onclick = function() {
	reset();
}

function reset() {
	searchTxt.value = "";
	resetTableData();
	hiddenResetBtn();
}

deleteChkBoxBtn.onclick = function() {
	var tableChildNodes = tbodyData.childNodes;
	for (var i = tableChildNodes.length - 1; i > 1; i--)
		if (tableChildNodes[i].style.display != "none") {
			var t = tableChildNodes[i].firstChild.firstChild;
			if (t.type == "checkbox" && t.checked == true)
				deleteTr(tableChildNodes[i]);
		}
	singleChkClick();
}

addBtn.onclick = function() {
	var newInputTr = createNewInputTr(0);
	addNewInput(newInputTr, -1);
}

chkboxAll.onclick = function() {
	// 判断是否选中是判断true/false
	// 选中复选框操作是checked属性设置为"checked"
	if (chkboxAll.checked == true)
		chkAll(0);
	else
		chkAll(1);
}

function singleChkClick() {
	var tableChildNodes = tbodyData.childNodes;
	var i = 2;
	for (; i < tableChildNodes.length; i++)
		if (tableChildNodes[i].style.display != "none") {
			var t = tableChildNodes[i].firstChild.firstChild;
			if (t.type == "checkbox" && t.checked == false)
				break;
		}

	if (i == tableChildNodes.length && i != 2)
		chkboxAll.checked = "checked";
	else
		chkboxAll.checked = "";
}

function chkAll(flag) {
	var tableChildNodes = tbodyData.childNodes;
	for (var i = 2; i < tableChildNodes.length; i++)
		if (tableChildNodes[i].style.display != "none") {
			if (flag == 0)
				tableChildNodes[i].firstChild.firstChild.checked = "checked";
			else
				tableChildNodes[i].firstChild.firstChild.checked = "";
		}
}

function modifyTr(obj) {
	subNum(1);
	obj.style.display = "none";
	obj.firstChild.firstChild.checked = "";
	var modifyInput = createNewInputTr(1);
	addNewInput(modifyInput, obj);
	var status = getStatus(modifyInput);
	if (status == 1)
		setInputTrValue(modifyInput);
}

function setInputTrValue(obj) {
	var nextHidden = obj.nextSibling;
	var idTd = nextHidden.firstChild;
	var id = idTd.lastChild.nodeValue;
	var nameTd = idTd.nextSibling;
	var name = nameTd.innerText;
	var ageTd = nameTd.nextSibling;
	var age = ageTd.innerText;
	var salaryTd = ageTd.nextSibling;
	var salary = salaryTd.innerText;

	// console.log(id, name, age, salary);

	var idInput = obj.firstChild;
	var nameInput = idInput.nextSibling;
	var ageInput = nameInput.nextSibling;
	var salaryInput = ageInput.nextSibling;

	idInput.firstChild.value = id;
	nameInput.firstChild.value = name;
	ageInput.firstChild.value = age;
	salaryInput.firstChild.value = salary;
}

function deleteTr(obj) {
	obj.parentNode.removeChild(obj);
	subNum(1);
}

function getStatus(obj) {
	return obj.lastChild.firstChild.value;
}

function confirmInput(obj) {
	singleChkClick();

	var status = getStatus(obj);

	obj.style.display = "none";

	var idInput = obj.firstChild;
	var nameInput = idInput.nextSibling;
	var ageInput = nameInput.nextSibling;
	var salaryInput = ageInput.nextSibling;

	var id = trim(idInput.firstChild.value);
	var name = trim(nameInput.firstChild.value);
	var age = trim(ageInput.firstChild.value);
	var salary = trim(salaryInput.firstChild.value);

	if (status == 0)
		addNewData(obj, id, name, age, salary);
	else {
		var nextHidden = obj.nextSibling;
		var idTd = nextHidden.firstChild;
		idTd.lastChild.nodeValue = id;
		var nameTd = idTd.nextSibling;
		nameTd.innerText = name;
		var ageTd = nameTd.nextSibling;
		ageTd.innerText = age;
		var salaryTd = ageTd.nextSibling;
		salaryTd.innerText = salary;
		nextHidden.style.display = "";
		addNum(1);
	}

	obj.parentNode.removeChild(obj);
}

function cancelInput(obj) {
	singleChkClick();
	var status = getStatus(obj);

	if (status == 1) {
		addNum(1);
		obj.nextSibling.style.display = "";
	}
	obj.parentNode.removeChild(obj);
}

/**
 * 根据行号判断是修改还是新增
 * 
 * @param inputTr
 * @param line
 * @returns
 */
function addNewInput(inputTr, line) {
	reset();
	if (line == -1)
		tbodyData.appendChild(inputTr);
	else
		tbodyData.insertBefore(inputTr, line);
}

/**
 * 根据行号增加数据
 * 
 * @param id
 * @param name
 * @param age
 * @param salary
 * @returns
 */
function addNewData(obj, id, name, age, salary) {
	reset();
	var newdata = createNewDataTr(id, name, age, salary);
	if (obj == -1)
		tbodyData.appendChild(newdata);
	else
		tbodyData.insertBefore(newdata, obj);
	addNum(1);
}