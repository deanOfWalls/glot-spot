// script.js

document.getElementById('registerForm').addEventListener('submit', function(event) {
    event.preventDefault(); // Prevent form from reloading the page

    const email = document.getElementById('email').value;

    fetch(`http://localhost:8080/api/users/register?email=${encodeURIComponent(email)}`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        }
    })
    .then(response => {
        if (response.ok) {
            return response.text();
        } else {
            throw new Error('Registration failed with status: ' + response.status);
        }
    })
    .then(data => {
        document.getElementById('response').innerText = 'Registration successful! Check the console for the magic link.';
        console.log(data);
    })
    .catch(error => {
        document.getElementById('response').innerText = 'Error: ' + error.message;
        console.error('Error:', error);
    });
});
