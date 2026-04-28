document.addEventListener('DOMContentLoaded', function () {
    // ── Mobile Menu ──
    const menuOpenButton = document.querySelector("#menu-open-button");
    const menuCloseButton = document.querySelector("#menu-close-button");
    const navLinks = document.querySelectorAll(".nav-menu .nav-link");

    if (menuOpenButton) {
        menuOpenButton.addEventListener("click", () => {
            document.body.classList.add("show-mobile-menu");
        });
    }

    if (menuCloseButton) {
        menuCloseButton.addEventListener("click", () => {
            document.body.classList.remove("show-mobile-menu");
        });
    }

    navLinks.forEach(link => {
        link.addEventListener("click", (e) => {
            e.preventDefault();
            document.body.classList.remove("show-mobile-menu");

            setTimeout(() => {
                const href = link.getAttribute("href");
                const target = document.querySelector(href);
                if (target) {
                    target.scrollIntoView({ behavior: "smooth" });
                }
            }, 350);
        });
    });

    // ── Contact Form ──
    const form = document.getElementById('contact-form');

    if (form) {
        form.addEventListener('submit', function (e) {
            e.preventDefault();

            const name = document.getElementById('contact-name').value.trim();
            const email = document.getElementById('contact-email').value.trim();
            const message = document.getElementById('contact-message').value.trim();

            if (!name || !email || !message) {
                alert('Please fill all fields.');
                return;
            }

            fetch('https://ksp-pavers-app.onrender.com/api/contact', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({ name, email, message })
            })
            .then(async res => {
                const text = await res.text();
                if (!res.ok) throw new Error(text || "Something went wrong");
                return text;
            })
            .then(msg => {
                alert(msg);
                form.reset();
            })
            .catch(err => {
                console.error(err);
                alert("Failed to submit: " + err.message);
            });
        });
    }
});