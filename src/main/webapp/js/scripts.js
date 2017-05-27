//UCI Informatics 124/Cs 137 - Assignment 3 Spring 2017
//Students: Mike Duong 69881873 & Bryan Linebaugh 49831189
var productprice;
var currenttax = "0.0";

function validateForm() {
	var regex = /[a-zA-Z]{2,30}$/;
	var firstName = document.forms["billingForm"]["firstName"].value;
	if (!regex.test(firstName)) {
		alert("Valid First name must be filled out");
		return false;
	}
	
	var lastName = document.forms["billingForm"]["lastName"].value;
	if (!regex.test(lastName)) {
		alert("Valid Last name must be filled out");
		return false;
	}
	
	//No regex for addresses since street format varies widely
	var address = document.forms["billingForm"]["address"].value;
	if (address == "") {
		alert("Valid Address must be filled out");
		return false;
	}
	
	regex = /^\d{5}$/;
	var zip = document.forms["billingForm"]["zip"].value;
	if (!regex.test(zip)) {
		alert("Valid Zip code must be filled out. 5 digits only.");
		return false;
	}
	
	var city = document.forms["billingForm"]["city"].value;
	if (city == "") {
		alert("Valid Address must be filled out");
		return false;
	}
	
	regex = /^\d{10}$/;
	var phone = document.forms["billingForm"]["phone"].value;
	if (!regex.test(phone)) {
		alert("Valid Phone must be filled out. 10 digits only.");
		return false;
	}
	
	regex = /\w+@\w+[.][a-z]{3}$/;
	var email = document.forms["billingForm"]["email"].value;
	if (!regex.test(email)) {
		alert("Valid Email must be filled out");
		return false;
	}
	
	regex = /^\d{16}$/;
	var card = document.forms["billingForm"]["card"].value;
	if (!regex.test(card)) {
		alert("Valid Credit card must be filled out. 16 digits only.");
		return false;
	}
	
	regex = /^[1-9][0-9]*$/;
	var quantity = document.forms["billingForm"]["quantity"].value;
	if (!regex.test(quantity)) {
		alert("Quantity must be filled out");
		return false;
	}
}

function billingOrder(name, price) {
	productprice = price;
	
	document.getElementById("gtotal").innerHTML = price;
	document.getElementById("productname").innerHTML = name;
	document.getElementById("price").innerHTML = price;
	var modal = document.getElementById('billModal');

	var span = document.getElementsByClassName("close")[0];

	modal.style.display = "block";

	span.onclick = function() {
		modal.style.display = "none";
	}

	window.onclick = function(event) {
		if (event.target == modal) {
			modal.style.display = "none";
		}
	}
}

function findCity(str) {
	if (str.length == 0) {
		document.getElementById("suggestCity").value = "";
		return;
	}
	else {
		var xmlhttp = new XMLHttpRequest();
		xmlhttp.onreadystatechange = function() {
			if (this.readyState == 4 && this.status == 200) {
				document.getElementById("suggestCity").value = this.responseText;
			}
		};
		xmlhttp.open("GET", "suggestcity.php?q=" + str, true);
		xmlhttp.send();
	}
}

function findTax(str) {
	if (str.length == 0) {
		document.getElementById("suggestTax").innerHTML = "0.0";
		return;
	}
	else {
		var xmlhttp = new XMLHttpRequest();
		xmlhttp.onreadystatechange = function() {
			if (this.readyState == 4 && this.status == 200) {
				document.getElementById("suggestTax").innerHTML = this.responseText;
				currenttax = this.responseText;
				calcSubtotal(document.forms["billingForm"]["quantity"].value);
			}
		};
		xmlhttp.open("GET", "suggesttax.php?q=" + str, true);
		xmlhttp.send();
	}
}

function calcSubtotal(quantity) {
	if (isNaN(parseFloat(currenttax))) {
		currenttax = "0.0";
	}
	
	var price = productprice;
	var total = parseInt(quantity) * parseInt(price);
	document.getElementById("price").innerHTML = total;
	
	var gtotal = total + (total * parseFloat(currenttax).toFixed(5));
	document.getElementById("gtotal").innerHTML = gtotal;
}