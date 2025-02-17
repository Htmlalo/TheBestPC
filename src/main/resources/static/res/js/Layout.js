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