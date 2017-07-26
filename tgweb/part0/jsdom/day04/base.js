var addBtn = document.getElementById("addBtn");
var sortBtn = document.getElementById("sortBtn");
var sortSelt = document.getElementById("sortSelt");
var resetBtn = document.getElementById("resetBtn");
var searchBtn = document.getElementById("searchBtn");
var searchTxt = document.getElementById("searchTxt");
var deleteChkBoxBtn = document.getElementById("deleteChkBoxBtn");
var chkboxAll = document.getElementById("chkboxAll");
var tableData = document.getElementById("tableData");
var tbodyData = tableData.firstChild.nextSibling;

function disableMost() {
	hiddenResetBtn();
	disableSort();
	searchTxt.disabled = "disabled";
	searchBtn.disabled = "disabled";
	deleteChkBoxBtn.disabled = "disabled";
	chkboxAll.disabled = "disabled";
}

function disableSort() {
	sortBtn.disabled = "disabled";
	sortSelt.disabled = "disabled";
}

function enableSort() {
	sortBtn.disabled = "";
	sortSelt.disabled = "";
}

function enableMost() {
	enableSort();
	searchTxt.disabled = "";
	searchBtn.disabled = "";
	deleteChkBoxBtn.disabled = "";
	chkboxAll.disabled = "";
}

function hiddenResetBtn() {
	resetBtn.style.display = "none";
}

function showRestBtn() {
	resetBtn.style.display = "block";
}

function createNewDataTr(id, name, age, salary) {
	var tr = document.createElement("tr");
	var tdId = document.createElement("td");
	var tdName = document.createElement("td");
	var tdAge = document.createElement("td");
	var tdSalary = document.createElement("td");
	var tdOperate = document.createElement("td");

	var chkbox = document.createElement("input");
	chkbox.type = "checkbox";
	chkbox.onclick = function() {
		singleChkClick();
	}

	var modifyA = document.createElement("a");
	var deleteA = document.createElement("a");
	modifyA.innerText = "修改";
	deleteA.innerText = "删除";
	modifyA.href = "#";
	deleteA.href = "#";
	modifyA.onclick = function() {
		modifyTr(tr);
	}
	deleteA.onclick = function() {
		deleteTr(tr);
	}

	tdId.appendChild(chkbox);
	tdId.appendChild(document.createTextNode(" "));
	tdId.appendChild(document.createTextNode(id));

	tdName.innerHTML = name;
	tdAge.innerHTML = age;
	tdSalary.innerHTML = salary;

	tdOperate.appendChild(modifyA);
	tdOperate.appendChild(document.createTextNode(" "));
	tdOperate.appendChild(document.createTextNode(" "));
	tdOperate.appendChild(deleteA);

	tr.appendChild(tdId);
	tr.appendChild(tdName);
	tr.appendChild(tdAge);
	tr.appendChild(tdSalary);
	tr.appendChild(tdOperate);

	return tr;
}

/**
 * status是标记修改还是增加的标志
 * 
 * @param status
 * @returns
 */
function createNewInputTr(status) {
	var tr = document.createElement("tr");
	var tdId = document.createElement("td");
	var tdName = document.createElement("td");
	var tdAge = document.createElement("td");
	var tdSalary = document.createElement("td");
	var tdOperate = document.createElement("td");

	var inputId = document.createElement("input");
	var inputName = document.createElement("input");
	var inputAge = document.createElement("input");
	var inputSalary = document.createElement("input");

	inputId.type = inputName.type = inputAge.type = inputSalary.type = "text";
	inputId.className = inputName.className = inputAge.className = inputSalary.className = "form-control input-text-size";

	var inputStatus = document.createElement("input");
	var okBtn = document.createElement("button");
	var cancelA = document.createElement("a");

	inputStatus.type = "hidden";
	inputStatus.value = status;

	okBtn.innerText = "确定";
	okBtn.onclick = function() {
		confirmInput(tr);
	}
	okBtn.className = "btn btn-default btn-sm";
	cancelA.href = "#";
	cancelA.onclick = function() {
		cancelInput(tr);
	}
	cancelA.innerText = "取消"

	tdOperate.appendChild(inputStatus);
	tdOperate.appendChild(okBtn);
	tdOperate.appendChild(document.createTextNode(" "));
	tdOperate.appendChild(cancelA);

	tdSalary.appendChild(inputSalary);
	tdAge.appendChild(inputAge);
	tdName.appendChild(inputName);
	tdId.appendChild(inputId);

	tr.appendChild(tdId);
	tr.appendChild(tdName);
	tr.appendChild(tdAge);
	tr.appendChild(tdSalary);
	tr.appendChild(tdOperate);

	return tr;
}

function addNum(n) {
	num += n;
	isEmpty();
}

function subNum(n) {
	num -= n;
	isEmpty();
}

function isEmpty() {
	if (num < 1)
		disableMost();
	else
		enableMost();
}