<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <title>Dashboard</title>
    <link rel="icon" type="image/x-icon" th:href="@{/img/icons/dashboard.png}" sizes="16x16">
    <link rel="stylesheet" th:href="@{/css/styles.css}">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <script th:src="@{/js/dashboard.js}"></script>
    
</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-custom">
        <div class="container">
            <a class="navbar-brand d-flex align-items-center" href="#">
                <img th:src="@{/img/LogosB.png}" alt="Logo" height="30" class="d-inline-block align-top mr-3">
                <span class="company-name">PT. Enggal Prima Raya</span>
            </a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav ml-auto">
                    <li class="nav-item active">
                        <a class="nav-link" href="http://localhost:9009/web/dashboard">Dashboard</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="http://localhost:9009/web/barangmasuk">Gudang</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="http://localhost:9009/web/kirim">SPB</a>
                    </li>
                    <li class="nav-item">
                        <button type="button" class="btn btn-danger" id="logoutButton">Logout</button>
                    </li>
                </ul>
            </div>
        </div>
    </nav>

    <div class="container mt-5">
        <h1 class="mb-4">Dashboard</h1>
        <div class="row">
            <div class="col-md-6">
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title">Total Jenis Barang</h5>
                        <p class="card-text" style="font-size: 2rem;" th:text="${totalUniqueItems}">0</p>
                    </div>
                </div>
            </div>
            <div class="col-md-6">
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title">Total Quantity Barang</h5>
                        <p class="card-text" style="font-size: 2rem;" th:text="${totalQuantity}">0</p>
                    </div>
                </div>
            </div>

            <div class="col-md-4">
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title">Last Activity</h5>
                        <p>Last Activity: <span th:text="${#temporals.format(lastActivity, 'yyyy-MM-dd HH:mm:ss')}">No activity</span></p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>

document.addEventListener('DOMContentLoaded', function() {
    // Logout button functionality
    const logoutButton = document.getElementById('logoutButton');
    if (logoutButton) {
        logoutButton.addEventListener('click', function() {
            window.location.href = '/login';
        });
    }

    // Format the last activity date
    const lastActivity = document.querySelector('.card-text:nth-of-type(3)');
    if (lastActivity && lastActivity.textContent !== 'No activity') {
        const date = new Date(lastActivity.textContent);
        lastActivity.textContent = date.toLocaleString();
    }

    // Function to format numbers with commas
    function formatNumber(num) {
        return num.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    }

    // Format the numbers in the cards
    const totalUniqueItems = document.querySelector('.card-text:nth-of-type(1)');
    const totalQuantity = document.querySelector('.card-text:nth-of-type(2)');

    if (totalUniqueItems) {
        totalUniqueItems.textContent = formatNumber(parseInt(totalUniqueItems.textContent));
    }

    if (totalQuantity) {
        totalQuantity.textContent = formatNumber(parseInt(totalQuantity.textContent));
    }

    // Add animations to cards
    const cards = document.querySelectorAll('.card');
    cards.forEach(card => {
        card.addEventListener('mouseenter', function() {
            this.style.transform = 'scale(1.05)';
            this.style.transition = 'transform 0.3s ease-in-out';
        });

        card.addEventListener('mouseleave', function() {
            this.style.transform = 'scale(1)';
        });
    });
});

body {
    background-color: #f4f7f6;
    font-family: 'Arial', sans-serif;
}
.card {
    border: none;
    border-radius: 10px;
    box-shadow: 0 4px 8px rgba(0,0,0,0.1);
    transition: transform 0.3s;
}
.card:hover {
    transform: translateY(-10px);
}
.card-header {
    background-color: #4e73df;
    color: #fff;
    border-radius: 10px 10px 0 0;
    padding: 1rem;
}
.card-body {
    padding: 1.5rem;
}
.card-title {
    font-size: 1.5rem;
    font-weight: bold;
}
.card-text {
    font-size: 1.25rem;
}
.container {
    margin-top: 50px;
}