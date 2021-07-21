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

function rekisteriHaku(){
var rekisterinumero = document.getElementById("mySearchField").value;
var apiPromise = fetch(`/cars/` + rekisterinumero, {
    method: 'GET'
});
apiPromise.then(async (response)=>{
var data = await response.json();
console.log(data);
const json = JSON.stringify(data);
document.getElementById("tulos").innerHTML = json;
});
}