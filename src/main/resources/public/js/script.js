async function getCar() {
	let rek = document.getElementById("rekisterinumero").value;
	let res = await fetch(`/cars/${rek}`, {
		method: 'GET'
	});
	
	
}



