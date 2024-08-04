/**
 * 
 */
function validate(){
	
	let fName = document.getElementById("fName").value;
	let lName = document.getElementById("lName").value;
	let email = document.getElementById("email").value;
	let phoneNo = document.getElementById("phoneNo").value;
	let country = document.getElementById("country").value;
	let password = document.getElementById("password").value;
	
	let error_message = document.getElementById("error_message");
	
	error_message.style.padding = "10px";
	 let text;

	if (fName.length<3){
		text="Please enter a valid first name";
		error_message.innerHTML = text;
		return false;
	}
	if (lName.length<3){
		text="Please enter a valid last name";
		error_message.innerHTML = text;
		return false;
	}
	if (email.indexOf("@") == -1 || email.length <5){
		text="Please enter a valid email";
		error_message.innerHTML = text;
		return false;	
	}
	if(isNaN(phoneNo) || phoneNo < 5){
		text="Please enter a valid phone number";
		error_message.innerHTML = text;
		return false;
	}
	if(country.length < 5){
		text="Please enter a valid country name";
		error_message.innerHTML = text;
		return false;
	}
	if(password.length < 5){
		text="Please enter a valid password with min. of 5 characters";
		error_message.innerHTML = text;
		return false;
	}
	 
 
}