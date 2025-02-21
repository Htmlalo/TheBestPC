const categoryBtn = document.querySelector('.category-btn');
const categoryList = document.querySelector('.category-list');

categoryBtn.addEventListener('click', () => {
    categoryList.classList.toggle('active');
});

// Close category menu when clicking outside
document.addEventListener('click', (e) => {
    if (!e.target.closest('.category-menu')) {
        categoryList.classList.remove('active');
    }
});
document.addEventListener('DOMContentLoaded', function () {
    // Automatically close notifications after 5 seconds
    const notifications = document.querySelectorAll('.tb-notification');
    notifications.forEach(notification => {
        setTimeout(() => {
            closeNotification(notification.id);
        }, 5000);
    });
});

function closeNotification(notificationId) {
    const notification = document.getElementById(notificationId);
    if (notification) {
        notification.classList.add('hiding');
        setTimeout(() => {
            notification.remove();
        }, 500);
    }
}