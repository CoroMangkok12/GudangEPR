$(document).ready(function() {
    $('#login-form').on('submit', function(event) {
        event.preventDefault();
        const username = $('#username').val();
        const password = $('#password').val();

        if ((username === 'faqih' && password === 'faqih') || 
            (username === 'eprfaqih' && password === 'eprfaqih')) {
            $('#loginResult').html('<div class="alert alert-success">Login Successful</div>');
            // Redirect to barangmasuk page after successful login
            window.location.href = "/web/dashboard";
        } else {
            $('#loginResult').html('<div class="alert alert-danger">Invalid Username or Password</div>');
        }
    });
});


function switchForm(formType) {
    if (formType === 'register') {
        document.getElementById('login-form').style.display = 'none';
        document.getElementById('register-form').style.display = 'block';
    } else {
        document.getElementById('login-form').style.display = 'block';
        document.getElementById('register-form').style.display = 'none';
    }
}

$(document).ready(function() {
    // Load users from local storage or initialize with default users
    let users = JSON.parse(localStorage.getItem('users')) || [
        { username: 'epr', password: 'epr' },
        { username: 'faqih', password: 'faqih' }
    ];

    // Login form submission
    $('#login-form').on('submit', function(event) {
        event.preventDefault();
        const username = $('#username').val();
        const password = $('#password').val();

        const user = users.find(user => user.username === username && user.password === password);
        if (user) {
            $('#loginResult').html('<div class="alert alert-success">Login Successful</div>');
            // Redirect to barangmasuk page after successful login
            window.location.href = "/web/dashboard";
        } else {
            $('#loginResult').html('<div class="alert alert-danger">Invalid Username or Password</div>');
        }
    });

//     // Register form submission
    $('#register-form').on('submit', function(event) {
        event.preventDefault();
        const newUsername = $('#newUsername').val();
        const newPassword = $('#newPassword').val();

        const existingUser = users.find(user => user.username === newUsername);
        if (existingUser) {
            $('#registerResult').html('<div class="alert alert-danger">Username already exists</div>');
        } else {
            users.push({ username: newUsername, password: newPassword });
            localStorage.setItem('users', JSON.stringify(users));
            $('#registerResult').html('<div class="alert alert-success">Registration Successful</div>');
        }
    });
});

