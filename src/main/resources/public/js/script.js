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

function rekisteriHaku() {
	var rekisterinumero = document.getElementById("mySearchField").value;
	var apiPromise = fetch(`/cars/` + rekisterinumero, {
		method: 'GET'
	});
	apiPromise.then(async (response) => {
		var data = await response.json();
		console.log(data);
		const json = JSON.stringify(data);
		naytaAuto(json);
		document.getElementById("autontiedot").innerHTML = json;
	});
	// VOISI LÄHETTÄÄ TUON AUTON EHKÄ SUORAAN PALVELIMELLE KÄSITELTÄVÄKSI?
}

const naytaAuto = (auto) => {

	const myobj = JSON.parse(auto);

	let showingCar = `<div class="card" style="width: 18rem;">
  <div class="card-body">
    <h5 class="card-title">${myobj.make}</h5>
    <h6 class="card-subtitle mb-2 text-muted">${myobj.model}</h6>
    <p class="card-text">${myobj.modyear}</p>
    <a href="#" class="card-link">Card link</a>
    <a href="#" class="card-link">Another link</a>
  </div>
</div>`;
	
	document.getElementById("tulos").innerHTML = showingCar;

}