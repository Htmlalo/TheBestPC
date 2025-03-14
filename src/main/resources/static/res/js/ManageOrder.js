document.addEventListener("DOMContentLoaded", function () {
    const toggleButtons = document.querySelectorAll(".toggle-order-btn");

    toggleButtons.forEach((button) => {
        button.addEventListener("click", function () {
            const orderBody = this.closest(".order-card").querySelector(".order-body");
            orderBody.classList.toggle("collapsed");

            // Đổi ký hiệu nút
            if (orderBody.classList.contains("collapsed")) {
                this.textContent = "+";
            } else {
                this.textContent = "-";
            }
        });
    });
});
// Navigation handling
document.querySelectorAll('.nav-item').forEach(item => {
    item.addEventListener('click', (e) => {
        e.preventDefault();

        // Update active state
        document.querySelectorAll('.nav-item').forEach(nav => {
            nav.classList.remove('active');
        });
        item.classList.add('active');

        // Show corresponding section
        const sectionId = item.getAttribute('data-section');
        document.querySelectorAll('.content-section').forEach(section => {
            section.style.display = 'none';
        });
        document.getElementById(sectionId).style.display = 'block';

        // Update banner title
        const title = item.querySelector('span').textContent;
        document.querySelector('.welcome-title').textContent = title;
    });

});
document.querySelectorAll('.order-tab').forEach(tab => {
    tab.addEventListener('click', () => {
        document.querySelectorAll('.order-tab').forEach(t => {
            t.classList.remove('active');
        });
        tab.classList.add('active');
    });
});


document.querySelectorAll('.order-tab').forEach(tab => {
    tab.addEventListener('click', () => {
        document.querySelectorAll('.order-tab').forEach(t => t.classList.remove('active'));
        tab.classList.add('active');

        let statusMap = {
            "TẤT CẢ": "ALL",
            "MỚI": "PENDING",
            "ĐANG XỬ LÝ": "PROCESSING",
            "ĐANG VẬN CHUYỂN": "SHIPPED",
            "HOÀN THÀNH": "DELIVERED",
            "HỦY": "CANCELLED"
        };

        let rawStatus = tab.textContent.trim();
        let status = statusMap[rawStatus] || "ALL";

        let orderLists = document.querySelectorAll('.order-list');
        console.log(orderLists.length)
        let orderList = orderLists[0]; // Chắc chắn chọn đúng cái đầu tiên
        orderList.innerHTML = ""; // Xóa sạch trước khi thêm mới
        console.log(orderLists.length)
        let apiUrl = "http://localhost:9090/TheBestPc/api/orders";
        if (status !== "ALL") {
            apiUrl += `?status=${encodeURIComponent(status)}`;
        }
        fetch(apiUrl)
            .then(response => response.json())
            .then(orders => {
                if (!orders || orders.length === 0) {
                    orderList.innerHTML = `<div  class="no-orders">
                    <img src="/TheBestPc/api/placeholder/120/120" alt="No orders">
                    <p>Quý khách chưa có đơn hàng nào.</p>
              
                </div>`;
                    return;
                }

                const fragment = document.createDocumentFragment();

                orders.forEach(order => {
                    const orderCard = document.createElement("div");
                    orderCard.classList.add("order-card");

                    let orderHTML = `
                        <div class="order-header">
                            <span class="order-date">Ngày đặt: ${order.createDate}</span>
                            <span class="order-status">Trạng thái: ${order.status}</span>
                        </div>
                        <div class="order-body">
                    `;

                    order.orderItems.forEach(item => {
                        orderHTML += `
                            <div class="order-item">
                                <img src="/TheBestPc/uploads/${item.imageUrl}" alt="${item.productName}">
                                <div class="item-info">
                                    <span class="item-name">${item.productName}</span>
                                    <span class="item-quantity">Số lượng: ${item.quantity}</span>
                                    <span class="item-price">Giá: ${item.price} VNĐ</span>
                                </div>
                            </div>
                        `;
                    });

                    orderHTML += `
                        <div class="order-summary">
                            <span class="total-label">Tổng tiền:</span>
                            <span class="total-amount">${order.totalPrice} VNĐ</span>
                        </div>
                    </div>`;

                    orderCard.innerHTML = orderHTML;
                    fragment.appendChild(orderCard);
                });

                orderList.appendChild(fragment);
            })
            .catch(error => console.error("Lỗi khi fetch đơn hàng:", error));
    });
});



