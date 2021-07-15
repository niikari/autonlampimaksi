async function removeProduct(id) {
		
		let response = await fetch("/juhisbalsa/database?id=" + id , { method: "DELETE"});
		
		console.log(response.status);
		if (response.status === 200) {
			let element = document.getElementById("balsa-" + id);
			element.remove();
		} else {
			alert("Jotain meni pieleen...");
}
function toLogin() {
	window.location.href = "/login";
}
}