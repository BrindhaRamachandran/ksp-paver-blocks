const navlinks = document.querySelectorAll(".nav-menu .nav-link");
const menuOpenButton = document.querySelector("#menu-open-button");
const menuCloseButton = document.querySelector("#menu-close-button");

	menuOpenButton.addEventListener("click", () => {
// toggle mobile menu vivibility
	document.body.classList.toggle("show-mobile-menu");
	});

// close menu when the close button is clicked
	menuCloseButton.addEventListener("click", () => menuOpenButton.click());

// close menu when the nav link is clicked
	navlinks.forEach(link => {
	    link.addEventListener("click", () => menuOpenButton.click());
	});
	
	document.getElementById("contactForm").addEventListener("submit", function (e) {
	    e.preventDefault(); // stop page refresh

	    fetch("http://localhost:8080/api/contact", {
	        method: "POST",
	        headers: {
	            "Content-Type": "application/json"
	        },
	        body: JSON.stringify({
	            name: document.getElementById("name").value,
	            email: document.getElementById("email").value,
	            message: document.getElementById("message").value
	        })
	    })
	    .then(res => res.text())
	    .then(data => alert(data))
	    .catch(err => console.error(err));
	});

