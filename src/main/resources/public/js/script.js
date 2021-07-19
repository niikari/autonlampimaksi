async function getCar() {
	let rek = document.getElementById("rekisterinumero").value;
	let res = await fetch(`/cars/${rek}`, {
		method: 'GET'
	});


}


let map;

function initMap() {
	map = new google.maps.Map(document.getElementById("map"), {
		center: { lat: 60.192059, lng: 24.945831 },
		zoom: 10,
	});
}
