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

    // Count-up animation function
    function countUp(element, endValue, duration) {
        let startValue = 0;
        let startTime = null;
        function animate(timestamp) {
            if (!startTime) startTime = timestamp;
            let progress = timestamp - startTime;
            let currentValue = Math.min(Math.floor(progress / duration * endValue), endValue);
            element.textContent = formatNumber(currentValue);
            if (currentValue < endValue) {
                requestAnimationFrame(animate);
            }
        }
        requestAnimationFrame(animate);
    }

    // Get elements and their end values
    const totalUniqueItems = document.getElementById('totalUniqueItems');
    const totalQuantity = document.getElementById('totalQuantity');

    if (totalUniqueItems) {
        const totalUniqueItemsValue = parseInt(totalUniqueItems.textContent.replace(/,/g, ''));
        countUp(totalUniqueItems, totalUniqueItemsValue, 2000);
    }

    if (totalQuantity) {
        const totalQuantityValue = parseInt(totalQuantity.textContent.replace(/,/g, ''));
        countUp(totalQuantity, totalQuantityValue, 2000);
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
